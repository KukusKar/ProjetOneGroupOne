package fr.cda24.ISIKA.Projet1.Annuaire;

import fr.cda24.ISIKA.Projet1.Annuaire.*;
import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.Annuaire.Session;

import java.io.FileInputStream;

//import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class LoginPage<T> extends VBox {

	/*
	 * On crée les attributs qui vont permettre la vérification du format de
	 * l'adresse mail pour le nom utilisateur
	 */
	
	private Session session;
	private TextField tfUserName;
    private T originPage;
	private Pattern pattern;
	private Matcher matcher;
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public LoginPage(BinaryTree arbre) {

		// Créer une variable de session
		this.session = Session.getInstance();

		
		
		// Création des HBox pour le logo, le titre et le boutton de connexion
		HBox hbLogo = new HBox();
		HBox hbTitle = new HBox();
		HBox hbLogin = new HBox();
		hbLogo.setAlignment(Pos.CENTER);

		try {
			Image image = new Image(new FileInputStream(
					"src/main/java/annexe/Logo-Isika.png"));
			// Redimensionner l'image
			double newWidth = 500;
			double newHeight = 300;
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(newWidth);
			imageView.setFitHeight(newHeight);
			image = imageView.snapshot(null, null);

			// Créer un dégradé descendant
			Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.3, Color.WHITE), new Stop(1, Color.ALICEBLUE) };
			LinearGradient gradient = new LinearGradient(0, 0, 0, newHeight, false, CycleMethod.NO_CYCLE, stops);

			// Créer un Blend qui mélange l'image originale avec le dégradé
			Blend blend = new Blend(BlendMode.MULTIPLY, null, new ColorInput(0, 0, newWidth, newHeight, gradient));

			// Créer une ImageView pour afficher l'image avec le dégradé
			imageView = new ImageView(image);
			imageView.setEffect(blend);

			// Ajouter l'ImageView à la HBox du logo
			hbLogo.getChildren().add(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Création du GridPane pour le formulaire de connexion
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		// On crée et on centre le bouttons dans le HBox
		Label lblTitle = new Label("Connectez-vous !");
		lblTitle.setStyle(("-fx-font-family:Roboto"));
		hbTitle.getChildren().add(lblTitle);
		hbTitle.setAlignment(Pos.CENTER);
		

		Button btnLogin = new Button("Connexion");
		btnLogin.setStyle(("-fx-font-family:Roboto"));

		//On créer les ombres qui apparaitrons sous les boutons
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.color(0, 0, 0, 0.2));
        btnLogin.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnLogin.setEffect(shadow);
        
		hbLogin.getChildren().add(btnLogin);
		hbLogin.setAlignment(Pos.CENTER);

		Label lblUserName = new Label("Nom d'utilisateur : ");
		lblUserName.setStyle(("-fx-font-family:Roboto"));

		tfUserName = new TextField("");
		tfUserName.setStyle("-fx-prompt-text-fill: gray;");
		tfUserName.setPromptText("ex : daenerys@gmail.com");
        tfUserName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");


		Label lblPassword = new Label("Mot de passe : ");
		lblPassword.setStyle(("-fx-font-family:Roboto"));
		PasswordField pwUser = new PasswordField();
        pwUser.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");


		gridPane.add(lblUserName, 0, 1);
		gridPane.add(tfUserName, 1, 1);
		gridPane.add(lblPassword, 0, 2);
		gridPane.add(pwUser, 1, 2);

		this.getChildren().addAll(hbLogo, hbTitle, gridPane, hbLogin);
		this.setStyle("-fx-background-color:aliceblue");
		VBox.setMargin(hbLogo, new Insets(0, 0, 20, 0));

		VBox.setMargin(gridPane, new Insets(20, 0, 0, 0));
		VBox.setMargin(hbLogin, new Insets(50, 0, 0, 0));

		/**
		 * Ici on vérifie via un Listener et l'appel de la méthode validateEmailFormat
		 * Que la chaine de caractère correspond à un format d'adresse mail valide Si la
		 * vérification échoue, le field se colore en rouge
		 */

		tfUserName.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue != null && !newValue.isEmpty()) {
		        validateEmailFormat(newValue, tfUserName);
		    } else {
		        tfUserName.setTooltip(null);
		    }
		});
		

		// appel de la méthode de connexion lors du clic sur le bouton Se connecter
		btnLogin.setOnAction(e -> {
		    String email = tfUserName.getText().trim();
		    String password = pwUser.getText().trim();
		    if (validateCredentials(email, password)) {
		        // Si les informations d'identification sont valides, on stocke l'utilisateur dans la session
		        Employee employee = Employee.getEmployeeByEmailAndPassword(email, password);
		        session.setLoggedInUser(employee);

		        MainPage mainPage = new MainPage(email, password, originPage, arbre);
		        Scene mainScene = mainPage.getMyScene();
		        Stage mainStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		        mainStage.setScene(mainScene);
		        mainStage.show();
		    } else {
		        // Afficher un message d'erreur si les informations d'identification sont invalides
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Erreur de connexion");
		        alert.setHeaderText("Les informations d'identification sont invalides.");
		        alert.setContentText("Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
		        alert.showAndWait();
		    }
		});
		//AJout d'un gestionnaire d'event pour la touche ENTRÉE
		//On appele la méthode fire() sur le btnLogin lorsque l'utilisateur appuis sur entrée pour simuler un clic.
		this.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		        btnLogin.fire();
		    }
		});




		

	}
	
	
	
	/**
     * Vérifier si les informations de connexion sont valides
     *
     * @param email l'adresse e-mail de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @return true si les informations de connexion sont valides, false sinon
     */
	private boolean validateCredentials(String email, String password) {
	    // Vérifier si l'adresse e-mail est dans un format valide
	    if (!validateEmailFormat(email, tfUserName)) {
	        return false;
	    }
	    Employee employee = Employee.getEmployeeByEmailAndPassword(email, password);
	    return employee != null;
	}

	/**
	 * Création de la méthode private validateEmailFormat
	 *
	 * @param email
	 * 
	 */
	private boolean validateEmailFormat(String email, TextField tfUsername) {
	    try {
	        InternetAddress emailAddress = new InternetAddress(email);
	        emailAddress.validate();
	        return true;
	    } catch (AddressException ex) {
	        Tooltip tooltip = new Tooltip("Adresse e-mail invalide");
	        tfUsername.setTooltip(tooltip);
	        return false;
	    }
	}
	

	public Scene getMyScene() {
		return new Scene(this, 425, 580);
	}
}

