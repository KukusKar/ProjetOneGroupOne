package fr.cda24.ISIKA.Projet1.Annuaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;


	/**
	 * La classe ListEmployee affiche une TableView contenant tous les employés et permet de les ajouter ou de les supprimer.
	 * Elle hérite de VBox.
	 *
	 * @param <T> Le type de l'origine de la page.
	 */
	public class ListEmployee<T> extends VBox {

		/**
	     * L'email de l'utilisateur connecté.
	     */
		private String email;
		/**
	     * Le mot de passe de l'utilisateur connecté.
	     */
	    private String password;
	    
	    /**
	     * La TableView contenant tous les employés.
	     */
	    private TableView<Employee> tableView;
	    
	    /**
	     * La liste observable des employés.
	     */
	    private ObservableList<Employee> employeeList;
	    
	    /**
	     * L'employé connecté.
	     */
	    private Employee connectedEmployee;
	    
	    /**
	     * L'origine de la page.
	     */
	    private T originPage;
	    
	    /**
	     * L'arbre binaire des employés.
	     */
	    private BinaryTree arbre;

	    /**
	     * Constructeur de la classe ListEmployee.
	     *
	     * @param email L'email de l'utilisateur connecté.
	     * @param password Le mot de passe de l'utilisateur connecté.
	     * @param originPage L'origine de la page.
	     * @param arbre L'arbre binaire des employés.
	     */
		public ListEmployee(String email, String password, T originPage, BinaryTree<?> arbre) {
			
			

//	        this.originPage = originPage;
			this.email = email;
			this.password = password;
			this.connectedEmployee = connectedEmployee;
			this.originPage = originPage;
	        this.arbre = arbre;


			if (originPage != null && originPage instanceof MainPage) {
				MainPage<?> mainPage = (MainPage<?>) originPage;
				mainPage.setEmployeeList(Employee.getEmployees());
			}

			Session session = Session.getInstance();
			if (session.getLoggedInUser() != null) {
				this.email = session.getLoggedInUser().getEmail();
				this.password = session.getLoggedInUser().getPassword();
			}

			// Créer la TableView
			tableView = new TableView<>();

			// Créer les colonnes de la TableView
			TableColumn<Employee, String> firstNameCol = new TableColumn<>("Prénom");
			firstNameCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			firstNameCol.setMinWidth(200);
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<Employee, String> lastNameCol = new TableColumn<>("Nom");
			lastNameCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			lastNameCol.setMinWidth(200);
			lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

			TableColumn<Employee, LocalDate> birthDateCol = new TableColumn<>("Date de naissance");
			birthDateCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			birthDateCol.setMinWidth(200);
			birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

			TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
			emailCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			emailCol.setMinWidth(200);
			emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

			TableColumn<Employee, String> passwordCol = new TableColumn<>("Password");
			passwordCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			passwordCol.setMinWidth(200);
			passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

			TableColumn<Employee, String> adminCol = new TableColumn<>("Admin");
			adminCol.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;");
			adminCol.setMinWidth(200);
			adminCol.setCellValueFactory(
					cellData -> new SimpleStringProperty(cellData.getValue().isAdmin() ? "Oui" : "Non"));

			// Ajouter les colonnes à la TableView
			tableView.getColumns().addAll(firstNameCol, lastNameCol, birthDateCol, emailCol, passwordCol, adminCol);

			// Récupérer la liste des employés
			employeeList = FXCollections.observableArrayList(Employee.getEmployees().toArray(Employee[]::new));

			// Ajouter les employés à la TableView
			tableView.setItems(employeeList);
			tableView.setStyle("-fx-border-color: white; -fx-border-width: 0px;");
			refreshTable();

	        Button btnAdd = new Button("Ajouter un(e) employé(e)");
	        Button btnDeleteEmployee = new Button("Supprimer un(e) employé(e)");
			Button btnBack = new Button("Retour");

			DropShadow shadow = new DropShadow();
			shadow.setRadius(5);
			shadow.setOffsetX(0);
			shadow.setOffsetY(5);
			shadow.setColor(Color.color(0, 0, 0, 0.2));
	        btnAdd.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
			btnBack.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
	        btnDeleteEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
	        btnAdd.setEffect(shadow);
			btnBack.setEffect(shadow);
	        btnDeleteEmployee.setEffect(shadow);

			HBox hbBp = new HBox();
			hbBp.getChildren().addAll(btnAdd, btnDeleteEmployee, btnBack);
			hbBp.setAlignment(Pos.CENTER);
			hbBp.setSpacing(25);

			/**
			 * Action à effectuer lors du clic sur le bouton "Ajouter un(e) employé(e)".
			 * Affiche la page d'ajout d'un employé.
			 */
	        btnAdd.setOnAction(e -> {
	        AddEmployee addEmployee = new AddEmployee(ListEmployee.this.email, ListEmployee.this.password, originPage, arbre);
	        Scene addEmployeeScene = addEmployee.getMyScene();
	        Stage stage = (Stage) btnAdd.getScene().getWindow();
	        stage.setScene(addEmployeeScene);
	        });
	        /**
	         * Action à effectuer lors du clic sur le bouton "Supprimer un(e) employé(e)".
	         * Affiche la page de suppression d'un employé.
	         */
	        btnDeleteEmployee.setOnAction(e -> {
	            DeleteEmpPage deleteEmpPage = new DeleteEmpPage(ListEmployee.this.email, ListEmployee.this.password, originPage, arbre);
	            Scene deleteEmpPageScene = deleteEmpPage.getMyScene();
	            Stage stage = (Stage) btnDeleteEmployee.getScene().getWindow();
	            stage.setScene(deleteEmpPageScene);
	            });
	        /**
	         * Action à effectuer lors du clic sur le bouton "Retour".
	         * Retourne à la page précédente (la page principale).
	         * Affiche une erreur en cas de problème.
	         */
			btnBack.setOnAction(e -> {
			    try {
			        MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
			        Scene sceneMainPage = mainPage.getMyScene();
			        Stage stage = (Stage) btnBack.getScene().getWindow();
			        stage.setScene(sceneMainPage);
//			        stage.show();
			        System.out.println("Retour effectué avec succès.");
			    } catch (Exception ex) {
			        System.out.println("Erreur lors du retour: " + ex.getMessage());
			        ex.printStackTrace();
			    }
			});

			HBox hbLilLogo = new HBox();
			try {
				Image image = new Image(new FileInputStream("src/main/java/annexe/logo_a-removebg.png"));
				// Redimensionner l'image
				double newWidth = 70;
				double newHeight = 70;
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(newWidth);
				imageView.setFitHeight(newHeight);
				image = imageView.snapshot(null, null);

				// Ajouter l'ImageView à la HBox du logo
				hbLilLogo.getChildren().add(imageView);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// On centre le contenue de HboxLilLogo
			hbLilLogo.setAlignment(Pos.CENTER);

			VBox.setMargin(hbBp, new Insets(50, 0, 30, 0));
			VBox.setMargin(hbLilLogo, new Insets(10, 0, 20, 0));

			Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.75, Color.ALICEBLUE),
					new Stop(1, Color.LIGHTSKYBLUE) };
			LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

			// Application du dégradé à l'arrière-plan de la AddPage
			this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

			// Ajouter la TableView au VBox
			this.getChildren().addAll(tableView, hbBp, hbLilLogo);
		}

		/**
	     * Rafraîchit la TableView des employés.
	     */
		public void refreshTable() {
			// Récupérer la liste des employés
			employeeList = FXCollections.observableArrayList(Employee.getEmployees().toArray(Employee[]::new));

			// Mettre à jour les employés dans la TableView
			tableView.setItems(employeeList);
		}
		/**
	     * Retourne la scène correspondant à cette page.
	     *
	     * @return La scène correspondant à cette page.
	     */
		public Scene getMyScene() {
			return new Scene(this, 1200, 600);
		}
		/**
		 * Renvoie la page d'origine de la liste d'employés.
		 * 
		 * @return La page d'origine de la liste d'employés.
		 */
		public T getOriginPage() {
	        return originPage;
	    }
	}
