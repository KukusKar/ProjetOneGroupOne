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


/**
 * Cette classe permet la création d'un nouvel employé dans l'application. Elle étend la classe VBox et contient plusieurs éléments graphiques pour saisir les informations de l'employé.
 * 
 * @param <T> le type de la page d'origine
 */
public class AddEmployee<T> extends VBox {


    
	// Variable pour enregistrer la page d'origine
    private T originPage;

    // Variables pour stocker l'adresse email et le mot de passe de l'utilisateur connecté
	private String email;
	private String password;

	// Variables pour valider le format de l'adresse email
	private Pattern pattern;
	private Matcher matcher;
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// Instance de BinaryTree pour stocker les données des employés
	private BinaryTree arbre;
	
	/**
	 * Constructeur de la classe AddEmployee.
	 * 
	 * @param email l'adresse email de l'utilisateur connecté
	 * @param password le mot de passe de l'utilisateur connecté
	 * @param originPage la page d'origine à partir de laquelle l'utilisateur a accédé à cette page
	 * @param arbre l'instance de l'objet BinaryTree pour stocker les données des employés
	 */
	public AddEmployee(String email, String password, T originPage, BinaryTree <?> arbre) {
		
		// Initialisation des variables d'instance
		this.email = email;
	    this.password = password;
        this.originPage = originPage;
        this.arbre = arbre;


		
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
				Button lblTitle = new Button("Création d'un nouvel employé");
		        lblTitle.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-family: Roboto; -fx-padding: 10px 20px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
				hbTitle.getChildren().add(lblTitle);
				hbTitle.setAlignment(Pos.CENTER);
				
				
				// Labels et champs de texte pour saisir les informations de l'employé
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
//				dpEmployeeBirthDate.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-control-inner-border-radius: 15;");

				
				//Création d'un label et d'un choiceBox pour le choix du cycle
				Label lblAdmin = new Label("Est-ce un administrateur ? ");
				RadioButton rbAdminO = new RadioButton("Oui");
				RadioButton rbAdminN = new RadioButton("Non");
				
				ToggleGroup grpAdmin = new ToggleGroup();
				grpAdmin.getToggles().addAll(rbAdminO, rbAdminN);
				
				//Création d'un label et d'un texfield pour l'adresse mail de l'employé
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
				Label lblSuccessError = new Label("");
				lblSuccessError.setVisible(false);
				
				//Ajout d'un gestionnaire d'événements lorsque le bouton btnAdd est pressé
				btnAdd.setOnAction(e -> {
				    // Récupération des données saisies par l'utilisateur
				    String firstName = tfFirstName.getText();
				    String lastName = tfLastName.getText();
				    LocalDate birthDate = dpEmployeeBirthDate.getValue();
				    String newEmail = tfMail.getText();
				    String newPassword = pwEmployee.getText();
				    boolean isAdmin = grpAdmin.getSelectedToggle() == rbAdminO;

				    // Vérifier que tous les champs sont remplis
				    if (firstName.isEmpty() || lastName.isEmpty() || birthDate == null || newEmail.isEmpty() || newPassword.isEmpty()) {
				        //Affichage d'un message d'erreur si des champs sont vides
				    	lblSuccessError.setText("Veuillez remplir tous les champs.");
				    	lblSuccessError.setTextFill(Color.RED);
				        lblSuccessError.setVisible(true);
				        return;
				    }

				    //Vérification de l'existance de l'employee
				    Employee existingEmployee = Employee.findEmployee(newEmail);
				    if (existingEmployee != null) {
				        //S'il existe, on met à jour ses infos
				        existingEmployee.setFirstName(firstName);
				        existingEmployee.setLastName(lastName);
				        existingEmployee.setBirthDate(birthDate);
				        existingEmployee.setPassword(newPassword);
				        existingEmployee.setAdmin(isAdmin);
				        System.out.println("Les informations de l'employé(e) ont été mises à jour !");
				    } else {
				        //S'il n'existe pas,on crée un nouvel employé
				        Employee newEmployee = new Employee(firstName, lastName, birthDate, newEmail, newPassword, isAdmin);
				        Employee.addEmployee(newEmployee);
				        System.out.println("Nouvel(le) employé(e) créé(e)");
				    }

				    //Affichage d'un message de réussite si l'ajout est réussi
				    lblSuccessError.setText("Employé(e) ajouté(e) avec succès !");
				    lblSuccessError.setTextFill(Color.GREEN);
				    lblSuccessError.setVisible(true);
				});
				//Ajout d'un gestionnaire d'événements lorsque le bouton btnBack est pressé
				btnBack.setOnAction(e -> {

//			            MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
//			            Scene sceneMainPage = mainPage.getMyScene();
//			            Stage stage = (Stage) btnBack.getScene().getWindow();
//			            stage.setScene(sceneMainPage);
				    //Retour à la page précédente
					ListEmployee listEmployee = new ListEmployee(email, password, originPage, arbre);
					Scene listEmployeeScene = listEmployee.getMyScene();
					Stage stage = (Stage) btnBack.getScene().getWindow();
					stage.setScene(listEmployeeScene);
					
			    });
				//On créer les ombres qui apparaitrons sous les boutons
		        DropShadow shadow = new DropShadow();
		        shadow.setRadius(5);
		        shadow.setOffsetX(0);
		        shadow.setOffsetY(5);
		        shadow.setColor(Color.color(0, 0, 0, 0.2));
		      //Application de styles pour les boutons et ajout des ombres
		        btnAdd.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		        btnBack.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		        btnAdd.setEffect(shadow);
		        btnBack.setEffect(shadow);
		        lblTitle.setEffect(shadow);


		      //Ajout des boutons dans une HBox
				hbAddBack.getChildren().addAll(btnAdd, btnBack);
				hbAddBack.setAlignment(Pos.CENTER);
				hbAddBack.setSpacing(30);
				
				hbSuccess.getChildren().add(lblSuccessError);
				hbSuccess.setAlignment(Pos.CENTER);

				// Création d'une nouvelle HBox pour le logo
				HBox hbLilLogo = new HBox();
				try {
				    Image image = new Image(new FileInputStream("src/main/java/annexe/logo_a-removebg.png")); // Chargement de l'image depuis le fichier
				    double newWidth = 70; // Définition de la nouvelle largeur
				    double newHeight = 70; // Définition de la nouvelle hauteur
				    ImageView imageView = new ImageView(image); // Création d'un objet ImageView pour l'image
				    imageView.setPreserveRatio(true); // Paramétrage pour préserver le ratio de l'image
				    imageView.setFitWidth(newWidth); // Redimensionnement de la largeur de l'image
				    imageView.setFitHeight(newHeight); // Redimensionnement de la hauteur de l'image
				    image = imageView.snapshot(null, null); // Création d'une snapshot pour l'image redimensionnée

				    hbLilLogo.getChildren().add(imageView); // Ajout de l'objet ImageView à la HBox pour le logo
				} catch (FileNotFoundException e) {
				    e.printStackTrace(); // Gestion de l'exception FileNotFoundException en affichant la stack trace
				}

				hbLilLogo.setAlignment(Pos.CENTER); // Centrage du contenu de la HBox du logo

				this.getChildren().addAll(hbTitle, gridPane, hbAddBack, hbSuccess, hbLilLogo); // Ajout des éléments à la VBox de la AddPage				
		        

				
				VBox.setMargin(hbTitle, new Insets(70, 0, 20, 0)); // Définition de la marge pour le titre
				VBox.setMargin(gridPane, new Insets(20, 0, 20, 0)); // Définition de la marge pour le formulaire
				VBox.setMargin(hbAddBack, new Insets(50, 0, 0, 0)); // Définition de la marge pour les boutons de navigation
				VBox.setMargin(hbLilLogo, new Insets(20, 0, 0, 0)); // Définition de la marge pour le logo
				VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0)); // Définition de la marge pour le message de succès

				
				// Création du dégradé de couleur verticale de Aliceblue à White
			    Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.6, Color.ALICEBLUE), new Stop(1, Color.LIGHTSKYBLUE) };
			    LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
			    
			    // Application du dégradé à l'arrière-plan de la AddPage
			    this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
			    
			    
			    tfMail.textProperty().addListener((observable, oldValue, newValue) -> { // Ajout d'un listener sur le champ de l'adresse email pour valider le format de l'adresse
			        if (newValue != null && !newValue.isEmpty()) {
			            if (!validateEmailFormat(newValue)) {
			                tfMail.setStyle("-fx-border-color: red;"); // Changement du style en cas d'adresse invalide
			            } else {
			                tfMail.setStyle(null); // Retour au style par défaut en cas d'adresse valide
			            }
			        }
			    });
			    
			    
				
				
			}
	
	
	/**
	 * Création de la méthode private validateEmailFormat
	 *
	 * @param email
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
	
	public T getOriginPage() {
        return originPage;
    }
	
	
}