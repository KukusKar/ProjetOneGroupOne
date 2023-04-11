package fr.cda24.ISIKA.Projet1.Annuaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.model.Noeud;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
 * Cette classe représente la page de suppression d'un employé dans l'application.
 * Elle hérite de VBox et utilise une instance de BinaryTree pour stocker et supprimer les employés.
 *
 * @param <T> le type de la page d'origine de laquelle cette page a été appelée.
 */
public class DeleteEmpPage<T> extends VBox {

	private String email;
	private String password;
	private T originPage;
	private BinaryTree arbre;

	/**
     * Constructeur de la classe DeleteEmpPage. Crée une instance de la page de suppression d'un employé.
     *
     * @param email l'adresse e-mail de l'utilisateur connecté.
     * @param password le mot de passe de l'utilisateur connecté.
     * @param originPage la page d'origine de laquelle cette page a été appelée.
     * @param arbre l'arbre binaire utilisé pour stocker et supprimer les employés.
     */
	public DeleteEmpPage(String email, String password, T originPage, BinaryTree <?> arbre) {

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

		// Création de la HBox du titre
		HBox hbTitle = new HBox();
		Label lblTitle = new Label("Suppresion d'un(e) employé(e)");
		lblTitle.setStyle(
				"-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-family: Roboto; -fx-padding: 10px 20px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
		hbTitle.getChildren().add(lblTitle);
		hbTitle.setAlignment(Pos.CENTER);

		// Création des labels et des textFields, RadioButton, datepicker etc
		Label lblLastName = new Label("Entrez le nom : ");
		TextField tfLastName = new TextField();
		tfLastName.setStyle("-fx-prompt-text-fill: gray;");
		tfLastName.setPromptText("ex : Kenpachis");
		tfLastName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		Label lblFirstName = new Label("Entrez le prénom : ");
		TextField tfFirstName = new TextField();
		tfFirstName.setStyle("-fx-prompt-text-fill: gray;");
		tfFirstName.setPromptText("ex : Zaraki");
		tfFirstName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		//Création d'un datePicker
		Label lblBirthDate = new Label("Date de naissance : ");
		DatePicker dpEmployeeBirthDate = new DatePicker();

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
		HBox hbDeleteBack = new HBox();
		HBox hbSuccess = new HBox();
		Button btnDelete = new Button("Supprimer");
		Button btnBack = new Button("Retour");
		Label lblSuccessError = new Label("");
		lblSuccessError.setVisible(false);

		/**
		 * Définit le comportement du bouton de suppression d'employé lorsqu'il est cliqué.
		 * 
		 * @param event L'événement déclencheur de la fonction
		 */
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        String email = tfMail.getText();
		        boolean success = Employee.removeEmployee(email);
		        if (success) {
		            lblSuccessError.setText("Employé(e) supprimé(e) avec succès.");
		            lblSuccessError.setTextFill(Color.GREEN);
		            lblSuccessError.setVisible(true);
		        } else {
		            lblSuccessError.setText("La suppréssion à échouée. Veuillez renseigner une adresse mail valide.");
		            lblSuccessError.setTextFill(Color.RED);
		            lblSuccessError.setVisible(true);
		        }
		    }
		});

		/**
		 * Gestionnaire d'événement appelé lors du clic sur le bouton "Retour".
		 * Crée une instance de ListEmployee pour retourner à la page de la liste des employés.
		 * 
		 * @param e l'événement déclencheur
		 */
		btnBack.setOnAction(e -> {
//			MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
//			Scene sceneMainPage = mainPage.getMyScene();
//			Stage stage = (Stage) btnBack.getScene().getWindow();
//			stage.setScene(sceneMainPage);
//			stage.show();
			ListEmployee listEmployee = new ListEmployee(email, password, originPage, arbre);
			Scene listEmployeeScene = listEmployee.getMyScene();
			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(listEmployeeScene);
		});
		// On créer les ombres qui apparaitrons sous les boutons
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(0);
		shadow.setOffsetY(5);
		shadow.setColor(Color.color(0, 0, 0, 0.2));
		btnDelete.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnBack.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnDelete.setEffect(shadow);
		btnBack.setEffect(shadow);
		lblTitle.setEffect(shadow);

		hbDeleteBack.getChildren().addAll(btnDelete, btnBack);
		hbDeleteBack.setAlignment(Pos.CENTER);
		hbDeleteBack.setSpacing(30);
		hbSuccess.getChildren().addAll(lblSuccessError);
		hbSuccess.setAlignment(Pos.CENTER);

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

		// On intégre tout dans le this et on le stylise
		this.getChildren().addAll(hbTitle, gridPane, hbDeleteBack, hbSuccess, hbLilLogo);

		VBox.setMargin(hbTitle, new Insets(70, 0, 20, 0));
		VBox.setMargin(gridPane, new Insets(20, 0, 20, 0));
		VBox.setMargin(hbDeleteBack, new Insets(50, 0, 0, 0));
		VBox.setMargin(hbLilLogo, new Insets(20, 0, 0, 0));
		VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0));

		// Création du dégradé de couleur verticale de Aliceblue à White
		Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.6, Color.ALICEBLUE), new Stop(1, Color.LIGHTSKYBLUE) };
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

		// Application du dégradé à l'arrière-plan de la AddPage
		this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	/**
     * Getter pour obtenir la scene de la page DeleteEmpPage.
     *
     * @return la scene de la page DeleteEmpPage.
     */
	public Scene getMyScene() {
		return new Scene(this, 425, 620);
	}
	/**
     * Getter pour obtenir la page d'origine de laquelle cette page a été appelée.
     *
     * @return la page d'origine de laquelle cette page a été appelée.
     */
	public T getOriginPage() {
        return originPage;
    }

}

