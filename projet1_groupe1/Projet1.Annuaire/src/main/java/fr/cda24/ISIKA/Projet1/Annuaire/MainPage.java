package fr.cda24.ISIKA.Projet1.Annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.cda24.ISIKA.Projet1.model.TableViewStagiaires;
import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Classe principale représentant la page principale de l'application.
 *
 * @param <T> le type de la page originale
 */
public class MainPage<T> extends BorderPane {

	// Création d'un boolean isAdmin pour savoir si l'utilisateur connecté est un
	// admin
	private boolean isAdmin;
	// Employé connecté
	private Employee connectedEmployee;
	// Liste des employés
	private List<Employee> employeeList;
	// Tableau de stagiaires
	private TableViewStagiaires tableViewStagiaires;
	// Page originale
	private T originPage;
	// Arbre binaire pour la structure de données
	private BinaryTree<?> arbre;

	// l'utilisateur
	private static Employee loggedInEmployee;

	// Stocker la liste des employés dans la classe MainPage
	private List<Employee> originalEmployeeList;

	/**
     * Constructeur de la classe MainPage.
     *
     * @param email      l'email de l'utilisateur connecté
     * @param password   le mot de passe de l'utilisateur connecté
     * @param originPage la page originale
     * @param arbre      l'arbre binaire pour la structure de données
     */
	public MainPage(String email, String password, T originPage, BinaryTree<?> arbre) {

		// Récupération de la liste des employés
        this.originalEmployeeList = Employee.getEmployees();
        // Copie de la liste pour la page
        this.employeeList = new ArrayList<>(originalEmployeeList);
        // Assignation de l'arbre binaire
        this.arbre = arbre;

     // Récupération de l'employé connecté à partir de l'email et du mot de passe
        Employee connectedEmployee = Employee.getEmployeeByEmailAndPassword(email, password);
        if (connectedEmployee != null && Employee.validateEmailAndPassword(email, password)) {
            // Stockage des informations de connexion de l'utilisateur dans la variable de session
            loggedInEmployee = connectedEmployee;
            this.connectedEmployee = connectedEmployee;
            // Vérification si l'utilisateur connecté est un admin
            this.isAdmin = connectedEmployee.isAdmin();
        }


		// Création VBox du borderPane gauche
		VBox vbLeftPane = new VBox();

		// Création VBox du borderPane droite
		VBox vbRightPane = new VBox();
		// Création du dégradé de lightskyblue à aliceblue puis à white pour un dégradé
		// très doux et rafraichissant

		Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.ALICEBLUE),
				new Stop(1, Color.LIGHTSKYBLUE) };
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

		// Creation du backgroundfill qui contiendra le dégradé gradient
		BackgroundFill fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);

		// Creattion du backgroudn avec le fill
		Background background = new Background(fill);

		// Création du dégradé de lightskyblue à aliceblue puis à white pour un dégradé
		// très doux et rafraichissant
		Stop[] stops1 = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.ALICEBLUE),
				new Stop(1, Color.LIGHTSKYBLUE) };
		LinearGradient gradient1 = new LinearGradient(1, 0, 0, 0, true, CycleMethod.NO_CYCLE, stops1);

		// Creation du backgroundfill qui contiendra le dégradé gradient
		BackgroundFill fill1 = new BackgroundFill(gradient1, CornerRadii.EMPTY, Insets.EMPTY);

		// Creattion du backgroudn avec le fill
		Background background1 = new Background(fill1);

		// Création du dégradé de lightskyblue à aliceblue puis à white pour un dégradé
		// très doux et rafraichissant
		Stop[] stops2 = new Stop[] { new Stop(0, Color.LIGHTSKYBLUE), new Stop(0.12, Color.ALICEBLUE),
				new Stop(0.14, Color.WHITE), new Stop(0.86, Color.WHITE), new Stop(0.88, Color.ALICEBLUE),
				new Stop(1, Color.LIGHTSKYBLUE) };
		LinearGradient gradient2 = new LinearGradient(1, 0, 0, 0, true, CycleMethod.NO_CYCLE, stops2);

		// Creation du backgroundfill qui contiendra le dégradé gradient
		BackgroundFill fill2 = new BackgroundFill(gradient2, CornerRadii.EMPTY, Insets.EMPTY);

		// Creattion du backgroudn avec le fill
		Background background2 = new Background(fill2);

		// On place le background dans le vbLeftPane
		vbLeftPane.setBackground(background);
		vbRightPane.setBackground(background);

		// Création du Label qui prendra le nom d'utilisateur de la personne connecté en
		// précisant si c'est un admin

		String adminText = "";
		if (isAdmin) {
			adminText = " (Admin)";
		}
		String usernameText = "Bonjour ";
		if (loggedInEmployee != null) {
			usernameText += loggedInEmployee.getFirstName() + " " + loggedInEmployee.getLastName();
		}

		Label lblCurrentUser = new Label(usernameText + adminText);
		lblCurrentUser.setStyle("-fx-font-size:15");
		lblCurrentUser.setStyle(("-fx-font-family:Roboto"));

		// On créer les ombres qui apparaitrons sous les boutons
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(0);
		shadow.setOffsetY(5);
		shadow.setColor(Color.color(0, 0, 0, 0.2));

		// Création des boutons addEmployee, addStage et delete,
		Button btnAddEmployee = new Button("Ajouter un(e) employé(e)");
		Button btnListEmployee = new Button("Liste des employé(e)s");
		Button btnAddStage = new Button("Ajouter un(e) stagiaire");
		Button btnDeleteStag = new Button("Supprimer un(e) stagiaire");
		Button btnDeleteEmployee = new Button("Supprimer un(e) employé(e)");
		Button btnRefreshTable = new Button("Actualiser");
		Button btnLogout = new Button("Déconnexion");
		Button btnPrint = new Button("Imprimer");

		TextField searchField = new TextField();
		searchField.setPromptText("Rechercher...");

		// Ajout d un écouteur de texte au champ de recherche (searchField)
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			// Appel de la méthode filterData avec la liste des stagiaires et la nouvelle
			// valeur de recherche
			FilteredList<Stagiaire> filteredData = filterData(tableViewStagiaires.getObservableList(), newValue);
			// Mise à jour le TableView avec les données filtrées
			tableViewStagiaires.getTable().setItems(filteredData);
		});
		/**
		 * La méthode permettant de passer à la page Liste des employés lorsqu'on clique sur le bouton "Liste des employé(e)s".
		 *
		 * @param email       l'adresse email de l'utilisateur connecté
		 * @param password    le mot de passe de l'utilisateur connecté
		 * @param originPage  la page d'origine avant d'arriver sur la MainPage
		 * @param arbre       l'arbre des pages
		 */
		btnListEmployee.setOnAction(e -> {
			ListEmployee listEmployee = new ListEmployee(email, password, originPage, arbre);
			Scene listEmployeeScene = listEmployee.getMyScene();
			Stage stage = (Stage) btnListEmployee.getScene().getWindow();
			stage.setScene(listEmployeeScene);
		});
		/**
		 * La méthode permettant de passer à la page Ajout d'un(e) employé(e) lorsqu'on clique sur le bouton "Ajouter un(e) employé(e)".
		 *
		 * @param email       l'adresse email de l'utilisateur connecté
		 * @param password    le mot de passe de l'utilisateur connecté
		 * @param originPage  la page d'origine avant d'arriver sur la MainPage
		 * @param arbre       l'arbre des pages
		 */
		btnAddEmployee.setOnAction(e -> {
			AddEmployee<T> addEmployee = new AddEmployee<>(email, password, originPage, arbre);
			Scene addEmployeeScene = addEmployee.getMyScene();
			Stage stage = (Stage) btnAddEmployee.getScene().getWindow();
			stage.setScene(addEmployeeScene);
		});
		/**
		 * La méthode permettant de passer à la page Ajout d'un(e) stagiaire lorsqu'on clique sur le bouton "Ajouter un(e) stagiaire".
		 *
		 * @param arbre l'arbre des pages
		 */
		btnAddStage.setOnAction(e -> {
			AddStagPage addStagPage = new AddStagPage(arbre);
			Scene addStageScene = addStagPage.getMyScene();
			Stage stage = (Stage) btnAddStage.getScene().getWindow();
			stage.setScene(addStageScene);
		});
		/**
		 * La méthode permettant de passer à la page Suppression d'un(e) stagiaire lorsqu'on clique sur le bouton "Supprimer un(e) stagiaire".
		 *
		 * @param arbre l'arbre des pages
		 */
		btnDeleteStag.setOnAction(e -> {
			DeleteStagPage deleteStagPage = new DeleteStagPage(arbre);
			Scene deleteStagPageScene = deleteStagPage.getMyScene();
			Stage stage = (Stage) btnDeleteStag.getScene().getWindow();
			stage.setScene(deleteStagPageScene);
		});
		/**
		 * La méthode permettant de passer à la page suppression d'un(e) employé(e) lorsqu'on clique sur le bouton "supprimer".
		 *
		 * @param email       l'adresse email de l'utilisateur connecté
		 * @param password    le mot de passe de l'utilisateur connecté
		 * @param originPage  la page d'origine avant d'arriver sur la MainPage
		 * @param arbre       l'arbre des pages
		 */
		btnDeleteEmployee.setOnAction(e -> {
			DeleteEmpPage<T> deleteEmpPage = new DeleteEmpPage<>(email, password, originPage, arbre);
			Scene deleteEmpPageScene = deleteEmpPage.getMyScene();
			Stage stage = (Stage) btnDeleteEmployee.getScene().getWindow();
			stage.setScene(deleteEmpPageScene);
		});
		/**
		 * La méthode permettant de passer à la page LoginPage pour se déconnecter de la session active.
		 *
		 * @param arbre l'arbre des pages
		 */
		btnLogout.setOnAction(event -> {
			// Supprimer les informations de connexion de la variable de session
			loggedInEmployee = null;

			LoginPage loginPage = new LoginPage(arbre);
			Scene loginScene = loginPage.getMyScene();
			Stage stage = (Stage) btnLogout.getScene().getWindow();
			stage.setScene(loginScene);
		});

		/**
		 * Action associée au bouton "Imprimer". 
		 * Cette méthode crée un fichier PDF à partir des données affichées dans le TableView
		 * et le sauvegarde dans un dossier sélectionné par l'utilisateur.
		 * @param event l'événement déclencheur

		 */
		btnPrint.setOnAction(event -> {
			try {
				// Créer un fileChooser pour sélectionner le dossier de destination
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Enregistrer le fichier PDF");
				fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
				File selectedFile = fileChooser.showSaveDialog(getScene().getWindow());
				if (selectedFile != null) {
					// Créer un fichier PDF
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
					document.open();
					// Créer une table pour le contenu du TableView
					PdfPTable table = new PdfPTable(tableViewStagiaires.getTable().getColumns().size());

					// Ajouter les en-têtes de colonne à la table
					for (TableColumn<Stagiaire, ?> column : tableViewStagiaires.getTable().getColumns()) {
						String header = column.getText();
						PdfPCell cell = new PdfPCell(new Phrase(header));
						table.addCell(cell);
					}

					// Ajouter les lignes de données à la table
					for (Stagiaire stagiaire : tableViewStagiaires.getObservableList()) {
						for (TableColumn<Stagiaire, ?> column : tableViewStagiaires.getTable().getColumns()) {
							String cellValue = column.getCellData(stagiaire).toString();
							PdfPCell cell = new PdfPCell(new Phrase(cellValue));
							table.addCell(cell);
						}
					}

					// Ajouter la table au document PDF
					document.add(table);

					document.close();
					System.out.println("Le fichier PDF a été généré avec succès !");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// On stylise les boutons avec un fond et un border radius
		btnAddEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnListEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnAddStage.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnDeleteStag.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnDeleteEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnLogout.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnPrint.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnAddEmployee.setEffect(shadow);
		btnListEmployee.setEffect(shadow);
		btnAddStage.setEffect(shadow);
		btnDeleteStag.setEffect(shadow);
		btnDeleteEmployee.setEffect(shadow);
		btnLogout.setEffect(shadow);
		btnPrint.setEffect(shadow);
		searchField.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		// Création du leftPane et insertion du vbLeftPane
		BorderPane leftPane = new BorderPane(vbLeftPane);

		Button btnRefresh = new Button("Actualiser");
		btnRefresh.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnRefresh.setEffect(shadow);

		/**
		 * Action exécutée lors du clic sur le bouton Actualiser. Cette méthode
		 * rafraîchit le contenu du TableView et efface le texte du champ de recherche.
		 * @param e L'événement associé au clic sur le bouton Actualiser.
		 */
		btnRefresh.setOnAction(e -> {
			tableViewStagiaires.refreshTable();
			searchField.clear();
		});

		vbLeftPane.getChildren().addAll(lblCurrentUser, btnLogout, searchField, btnAddStage, btnRefresh, btnPrint);
		vbRightPane.getChildren().addAll(btnListEmployee, btnAddEmployee, btnDeleteEmployee, btnDeleteStag);
		leftPane.setPrefWidth(200);
		vbLeftPane.setAlignment(Pos.CENTER);
		vbRightPane.setAlignment(Pos.CENTER);

		VBox.setMargin(lblCurrentUser, new Insets(0, 0, 10, 0));
		VBox.setMargin(btnLogout, new Insets(0, 0, 20, 0));
		VBox.setMargin(btnAddEmployee, new Insets(50, 0, 20, 0));
		VBox.setMargin(btnAddStage, new Insets(20, 0, 20, 0));
		VBox.setMargin(btnPrint, new Insets(50, 0, 0, 0));
		VBox.setMargin(btnDeleteStag, new Insets(30, 0, 20, 0));
		VBox.setMargin(btnDeleteEmployee, new Insets(0, 0, 20, 0));
		VBox.setMargin(btnRefresh, new Insets(0, 0, 0, 0));
		VBox.setMargin(searchField, new Insets(20, 10, 20, 10));

		// On prépare le topPane et le titre
		Button lblTitle = new Button("Liste des stagiaires");
		lblTitle.setStyle(
				"-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 35px; -fx-font-family: Garamond; -fx-padding: 10px 20px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
		lblTitle.setPrefHeight(50);
		lblTitle.setEffect(shadow);
		BorderPane topPane = new BorderPane(lblTitle);
		topPane.setPrefHeight(150);
		BorderPane.setAlignment(lblTitle, Pos.CENTER);
		topPane.setStyle("-fx-background-color:white");

//        topPane.setBackground(background2);

		BorderPane centerPane = new BorderPane();

		BorderPane rightPane = new BorderPane(vbRightPane);
		rightPane.setPrefWidth(200);

		// Structure conditionnelle qui donne l'accès au bouttons AddEmployee et delete
		// qu'aux Admin
		if (!isAdmin) {
			btnAddEmployee.setVisible(false);
			btnDeleteStag.setVisible(false);
			btnListEmployee.setVisible(false);
			btnDeleteEmployee.setVisible(false);
			rightPane.setPrefSize(0, 0);
		}
		// Création de l'instance TableViewStagiaires
		try {
			tableViewStagiaires = new TableViewStagiaires(arbre.parcoursInfixe());
			centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (tableViewStagiaires != null) {
			System.out.println("L'arbre est déja chargé");
		} else if (tableViewStagiaires == null) {
			try {
				tableViewStagiaires = new TableViewStagiaires(arbre.parcoursInfixe());
				centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
		}

		this.setTop(topPane);
		this.setCenter(centerPane);
		this.setLeft(leftPane);
		this.setRight(rightPane);

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

			// Placer l'image
			imageView.setTranslateX(40);
			imageView.setTranslateY(30);

			// Ajouter l'ImageView à la HBox du logo
			topPane.getChildren().add(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Filtre les données de la liste de stagiaires en fonction d'un terme de recherche donné.
	 * 
	 * 
	 * @param stagiaireList
	 * @param searchTerm
	 * @return une liste filtrée de stagiaires
	 */
	private FilteredList<Stagiaire> filterData(ObservableList<Stagiaire> stagiaireList, String searchTerm) {
		return stagiaireList.filtered(stagiaire -> {
			// Si le terme de recherche est vide, afficher tous les stagiaires
			if (searchTerm == null || searchTerm.isEmpty()) {
				return true;
			}
			// Convertir le terme de recherche en minuscules pour une recherche insensible à
			// la casse
			String lowerCaseFilter = searchTerm.toLowerCase();

			// Filtrer les stagiaires en fonction de leurs noms
			if (stagiaire.getNom().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			// Filtrer les stagiaires en fonction de leurs prénoms
			else if (stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			// Filtrer les stagiaires en fonction de leur département
			else if (stagiaire.getDepartement().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			// Filtrer les stagiaires en fonction de leur promotion
			else if (stagiaire.getProm().toLowerCase().contains(lowerCaseFilter)) {
				return true;
			}
			// Filtrer les stagiaires en fonction de leur année de formation
			else if (String.valueOf(stagiaire.getAnneeForm()).contains(lowerCaseFilter)) {
				return true;
			}
			// Si aucune condition n'est remplie, ne rien afficher
			return false;
		});
	}

	/**
	 * Met à jour la liste des employés.
	 * 
	 * @param employeeList
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * Récupère la liste des employés.
	 * 
	 * @return employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * Récupère la scène associée à cette page.
	 * 
	 * 
	 * @return la scène associée à cette page
	 */
	public Scene getMyScene() {

		if (!isAdmin) {
			return new Scene(this, 1200, 800);
		} else {
			return new Scene(this, 1400, 800);
		}
	}

	/**
	 * Récupère la page d'origine associée à cette page.
	 * 
	 * @return la page d'origine associée à cette page

	 */
	public T getOriginPage() {
		return originPage;
	}
}
