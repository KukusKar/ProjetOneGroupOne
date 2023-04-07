package fr.cda24.ISIKA.Projet1.Annuaire;


import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class AddEmployee<T> extends VBox {

	// Variable pour enregistrer la page d'origine
//    private Class<?> originPage;
    
	private String email;
	private String password;
    private T originPage;
	private Pattern pattern;
	private Matcher matcher;
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private BinaryTree arbre;
	
	
	public AddEmployee(String email, String password, T originPage, BinaryTree <?> arbre) {
		
		this.email = email;
	    this.password = password;
        this.originPage = originPage;
        this.arbre = arbre;


		
//		this.originPage = originPage;
		Session session = Session.getInstance();
		if (session.getLoggedInUser() != null) {
		    this.email = session.getLoggedInUser().getEmail();
		    this.password = session.getLoggedInUser().getPassword();
		}
		
		// Mettre à jour la liste des employés dans MainPage
        if (originPage != null && originPage instanceof MainPage) {
            MainPage mainPage = (MainPage) originPage;
            mainPage.setEmployeeList(Employee.getEmployees());
        }
		
		//Création de la HBox du titre
				HBox hbTitle = new HBox();
				Label lblTitle = new Label("Création d'un nouvel employé");
				lblTitle.setStyle("-fx-font-family:Roboto; -fx-padding: 10px ;-fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius:10px");
				hbTitle.getChildren().add(lblTitle);
				hbTitle.setAlignment(Pos.CENTER);
				
				
				//Création des labels et des textFields, RadioButton, datepicker etc
				Label lblLastName = new Label("Entrez le nom : ");
				TextField tfLastName = new TextField();
				tfLastName.setStyle("-fx-prompt-text-fill: gray;");
				tfLastName.setPromptText("ex : Lanister");
		        tfLastName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");
				
				Label lblFirstName = new Label("Entrez le prénom : ");
				TextField tfFirstName = new TextField();
				tfFirstName.setStyle("-fx-prompt-text-fill: gray;");
				tfFirstName.setPromptText("ex : Tyrion");
		        tfFirstName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");
				
				//Création d'un datePicker
				Label lblBirthDate = new Label("Date de naissance : ");
				DatePicker dpEmployeeBirthDate = new DatePicker();
				dpEmployeeBirthDate.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-control-inner-border-radius: 15;");
//				dpEmployeeBirthDate.setStyle(
//		                "-fx-background-radius: 15;" +
//		                "-fx-background-color: -fx-box-border, -fx-inner-border, -fx-background;" +
//		                "-fx-background-insets: 0, 1, 2;" +
//		                "-fx-text-fill: -fx-text-inner-color;" +
//		                "-fx-padding: 0.333333em 0.583333em 0.333333em 0.583333em;"
//		        );
				
				//Création d'un label et d'un choiceBox pour le choix du cycle
				Label lblAdmin = new Label("Est-ce un administrateur ? ");
				RadioButton rbAdminO = new RadioButton("Oui");
				RadioButton rbAdminN = new RadioButton("Non");
				
				ToggleGroup grpAdmin = new ToggleGroup();
				grpAdmin.getToggles().addAll(rbAdminO, rbAdminN);
				
				
				Label lblMail = new Label("Entrez l'adresse mail de connexion : ");
				TextField tfMail = new TextField();
				tfMail.setStyle("-fx-prompt-text-fill: gray;");
				tfMail.setPromptText("ex : mainduroi@gmail.com");
		        tfMail.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");
				
				Label lblPwEmployee = new Label("Entrez votre mot de passe : ");
				PasswordField pwEmployee = new PasswordField();
		        pwEmployee.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");
				
				
				//Création de la gridpane globale
				GridPane gridPane = new GridPane();
				gridPane.setAlignment(Pos.CENTER);
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				
				// Ajout des éléments à la GridPane
		        gridPane.add(lblLastName, 0, 0);
		        gridPane.add(tfLastName, 1, 0);
		        gridPane.add(lblFirstName, 0, 1);
		        gridPane.add(tfFirstName, 1, 1);
		        gridPane.add(lblBirthDate, 0, 2);
		        gridPane.add(dpEmployeeBirthDate, 1, 2);
		        gridPane.add(lblMail, 0, 3);
		        gridPane.add(tfMail, 1, 3);
		        gridPane.add(lblPwEmployee, 0, 4);
		        gridPane.add(pwEmployee, 1, 4);
		        gridPane.add(lblAdmin, 0, 5);
		        gridPane.add(rbAdminO, 1, 5);
		        gridPane.add(rbAdminN, 1, 6);
		        
				//Création de la HBox qui contiendra le bouton ajouter ou retour
				HBox hbAddBack = new HBox();
				HBox hbSuccess = new HBox();
				Button btnAdd = new Button("Créer");
				Button btnBack = new Button("Retour");
				Label lblSuccess = new Label("Employee ajouté avec succès !");
				lblSuccess.setTextFill(Color.GREEN);
				lblSuccess.setVisible(false);
				
				btnAdd.setOnAction(e -> {
				    String firstName = tfFirstName.getText();
				    String lastName = tfLastName.getText();
				    LocalDate birthDate = dpEmployeeBirthDate.getValue();
				    String newEmail = tfMail.getText();
				    String newPassword = pwEmployee.getText();
				    boolean isAdmin = grpAdmin.getSelectedToggle() == rbAdminO;

				    // Création d'un nouvel employé
//				    Employee employee = new Employee(firstName, lastName, birthDate, email, password, isAdmin);
//				    Employee.addEmployee(employee);
				    
				    Employee employee = Employee.findEmployee(email);

				    if (employee == null) {
				        // L'employé n'existe pas encore, nous en créons un nouveau
				        employee = new Employee(firstName, lastName, birthDate, newEmail, newPassword, isAdmin);
				        Employee.addEmployee(employee);
				    } else {
				        // L'employé existe déjà, nous mettons simplement à jour les informations
				        employee.setFirstName(firstName);
				        employee.setLastName(lastName);
				        employee.setBirthDate(birthDate);
				        employee.setPassword(newPassword);
				        employee.setAdmin(isAdmin);
				    }
				    
				    
				    String newNewEmail = "nouvel.email@entreprise.com";
				    boolean emailExists = false;
				    
				        if (employee.getEmail().equals(newNewEmail)) {
				            emailExists = true;
				            
				        }
				    
				    if (emailExists) {
				        // Le nouvel email existe déjà dans la liste des employés
				    	System.out.println("Le nouvel email existe déja dans la liste");
				    } else {
				        // Le nouvel email n'existe pas encore dans la liste des employés
				    	System.out.println("Le nouvel email n'existe pas encore dans la liste");

				    }

				    lblSuccess.setVisible(true);

				});
				
				btnBack.setOnAction(e -> {

			            MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
			            Scene sceneMainPage = mainPage.getMyScene();
			            Stage stage = (Stage) btnBack.getScene().getWindow();
			            stage.setScene(sceneMainPage);
					
			    });
				//On créer les ombres qui apparaitrons sous les boutons
		        DropShadow shadow = new DropShadow();
		        shadow.setRadius(5);
		        shadow.setOffsetX(0);
		        shadow.setOffsetY(5);
		        shadow.setColor(Color.color(0, 0, 0, 0.2));
		        btnAdd.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		        btnBack.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		        btnAdd.setEffect(shadow);
		        btnBack.setEffect(shadow);

				
				hbAddBack.getChildren().addAll(btnAdd, btnBack);
				hbAddBack.setAlignment(Pos.CENTER);
				hbAddBack.setSpacing(30);
				
				hbSuccess.getChildren().add(lblSuccess);
				hbSuccess.setAlignment(Pos.CENTER);

				HBox hbLilLogo = new HBox();
				try {
				Image image = new Image(new FileInputStream(
						"src/main/java/annexe/logo_a-removebg.png"));
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
				
				//On centre le contenue de HboxLilLogo 
				hbLilLogo.setAlignment(Pos.CENTER);
				
				//On intégre tout dans le this et on le stylise
				this.getChildren().addAll(hbTitle, gridPane, hbAddBack, hbSuccess,  hbLilLogo);
				
		        

				
				VBox.setMargin(hbTitle, new Insets(70, 0, 20, 0));
				VBox.setMargin(gridPane, new Insets(20, 0, 20, 0));
				VBox.setMargin(hbAddBack, new Insets(50, 0, 0, 0));
				VBox.setMargin(hbLilLogo, new Insets(20, 0, 0, 0));
				VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0));
				
				// Création du dégradé de couleur verticale de Aliceblue à White
			    Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.WHITE), new Stop(1, Color.ALICEBLUE) };
			    LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
			    
			    // Application du dégradé à l'arrière-plan de la AddPage
			    this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
			    
			    
			    tfMail.textProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null && !newValue.isEmpty()) {
						if (!validateEmailFormat(newValue)) {
							tfMail.setStyle("-fx-border-color: red;");
						} else {
							tfMail.setStyle(null);
						}
					}
				});
			    
			    
				
				
			}
	
	
	/**
	 * Création de la méthode private validateEmailFormat
	 *
	 * @param email
	 * 
	 */
	private boolean validateEmailFormat(String email) {
		// On crée un Pattern en utilisant l'expression régulière EMAIL_PATTERN
		pattern = Pattern.compile(EMAIL_PATTERN);
		// On crée un Matcher de l'objet Pattern pour vérifier que le format de
		// l'adresse mail est correct
		matcher = pattern.matcher(email);
		// On retourne vrai si l'adresse mail est valide selon l'expression régulière en
		// utilisant la méthode matches
		// de Matche.
		return matcher.matches();
	}
	
	
			
	public Scene getMyScene() {
		return new Scene(this, 425, 620);
	}
	
	
}