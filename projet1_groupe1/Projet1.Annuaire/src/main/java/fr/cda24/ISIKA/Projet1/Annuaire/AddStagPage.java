package fr.cda24.ISIKA.Projet1.Annuaire;

import fr.cda24.ISIKA.Projet1.model.TableViewStagiaires;
import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.model.Noeud;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
 * Cette classe représente la page d'ajout d'un nouveau stagiaire.
 * Elle hérite de VBox et prend en entrée un arbre binaire.
 */
public class AddStagPage<T> extends VBox {

    // Adresse email de l'utilisateur connecté
	private String email;
    // Mot de passe de l'utilisateur connecté
	private String password;
    // Page d'origine
	private T originPage;
    // Arbre binaire utilisé pour stocker les données des stagiaires
	private BinaryTree arbre;
    // Noeud de l'arbre binaire
	Noeud n = new Noeud(null, 0, 0, 0);
    // Stagiaire à ajouter
	Stagiaire stg = new Stagiaire(null, null, null, null, null);
	/**
     * Constructeur de la classe AddStagPage.
     * Il prend en entrée un arbre binaire et crée les éléments de la page d'ajout.
     * 
     * @param arbre l'arbre binaire à utiliser pour stocker les données des stagiaires
     */
	public AddStagPage(BinaryTree<?> arbre) {
		this.originPage = originPage;
		this.arbre = arbre;

        // Récupération de l'utilisateur connecté à partir de la session
		Session session = Session.getInstance();
		if (session.getLoggedInUser() != null) {
			this.email = session.getLoggedInUser().getEmail();
			this.password = session.getLoggedInUser().getPassword();
		}

		
		 
		// Création de la HBox du titre
		HBox hbTitle = new HBox();
		Button lblTitle = new Button("Ajout d'un nouveau stagiaire");

        lblTitle.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-family: Roboto; -fx-padding: 10px 20px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
		hbTitle.getChildren().add(lblTitle);
		hbTitle.setAlignment(Pos.CENTER);

		// Création des labels et des textFields, RadioButton, datepicker etc
		Label lblLastName = new Label("Entrez le nom : ");
		TextField tfLastName = new TextField();
		tfLastName.setStyle("-fx-prompt-text-fill: gray;");
		tfLastName.setPromptText("ex : Snow");
		tfLastName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		Label lblFirstName = new Label("Entrez le prénom : ");
		TextField tfFirstName = new TextField();
		tfFirstName.setStyle("-fx-prompt-text-fill: gray;");
		tfFirstName.setPromptText("ex : Jon");
		tfFirstName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		// Création d'une choiceBox qui contiendra tous les département de france
		Label lblDep = new Label("Département");
		ChoiceBox<String> cbDep = new ChoiceBox();
		cbDep.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
				"65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
				"82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "971", "972", "973",
				"974", "976");
		cbDep.getSelectionModel().select(0);

		// Création d'un label et d'un choiceBox pour le choix du cyle
		Label lblCycle = new Label("Cycle : ");
		TextField tfCycle = new TextField();
		tfCycle.setStyle("-fx-prompt-text-fill: gray;");
		tfCycle.setPromptText("ex : CDA24");
		tfCycle.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		// Création d'un label et d'un textfield pour l'année d'inscription
		Label lblAnnIns = new Label("Année d'inscription : ");
		TextField tfAnnIns = new TextField();
		tfAnnIns.setStyle("-fx-prompt-text-fill: gray;");
		tfAnnIns.setPromptText("ex : 2022");
		tfAnnIns.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

		// Création de la gridpane globale
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		// Ajout des éléments à la GridPane
		gridPane.add(lblLastName, 0, 0);
		gridPane.add(tfLastName, 1, 0);
		gridPane.add(lblFirstName, 0, 1);
		gridPane.add(tfFirstName, 1, 1);
		gridPane.add(lblDep, 0, 2);
		gridPane.add(cbDep, 1, 2);
		gridPane.add(lblAnnIns, 0, 3);
		gridPane.add(tfAnnIns, 1, 3);
		gridPane.add(lblCycle, 0, 4);
		gridPane.add(tfCycle, 1, 4);

		// Création de la HBox qui contiendra le bouton ajouter ou retour
		HBox hbAddBack = new HBox();
		HBox hbSuccess = new HBox();
		Button btnAdd = new Button("Ajouter");
		Button btnBack = new Button("Retour");
		Label lblSuccessError = new Label("");
		lblSuccessError.setVisible(false);

		// Ajout d'un gestionnaire d'événements pour le bouton d'ajout
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// vérifier si tous les champs sont remplis
				if (tfLastName.getText().isEmpty() || tfFirstName.getText().isEmpty() || cbDep.getValue() == null
						|| tfCycle.getText().isEmpty() || tfAnnIns.getText().isEmpty()) {
					// afficher un message d'erreur
					lblSuccessError.setText("Veuillez remplir tous les champs !");
					lblSuccessError.setTextFill(Color.RED);
					lblSuccessError.setVisible(true);
				} else {
					// tous les champs sont remplis, ajouter le stagiaire
					stg.setNom(tfLastName.getText());
					stg.setPrenom(tfFirstName.getText());
					stg.setDepartement(cbDep.getValue());
					stg.setProm(tfCycle.getText());
					stg.setAnneeForm(tfAnnIns.getText());
					// j'ajoute le stagiaire au noeud
					n.setStagiaire(stg);
					// j'ajoute mon noeud à l'arbre
					arbre.insertNoeud(stg);
					// afficher un message de succès
					lblSuccessError.setText("Stagiaire ajouté avec succès !");
					lblSuccessError.setTextFill(Color.GREEN);
					lblSuccessError.setVisible(true);
				}
			}
		});
		// Ajout d'un gestionnaire d'événements pour le bouton de retour
		btnBack.setOnAction(e -> {
			MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
		    // Récupération de la scène de la page principale
			Scene sceneMainPage = mainPage.getMyScene();
		    // Récupération de la fenêtre et définition de la scène principale
			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.setScene(sceneMainPage);
			stage.show();
		});
		// On créer les ombres qui apparaitrons sous les boutons
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(0);
		shadow.setOffsetY(5);
		shadow.setColor(Color.color(0, 0, 0, 0.2));
		btnAdd.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnBack.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
		btnAdd.setEffect(shadow);
		btnBack.setEffect(shadow);
        lblTitle.setEffect(shadow);


		hbAddBack.getChildren().addAll(btnAdd, btnBack);
		hbAddBack.setAlignment(Pos.CENTER);
		hbAddBack.setSpacing(30);
		hbSuccess.getChildren().add(lblSuccessError);
		hbSuccess.setAlignment(Pos.CENTER);

		// Création de la HBox pour le logo
		HBox hbLilLogo = new HBox();
		try {
		    // Chargement de l'image du logo
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
		this.getChildren().addAll(hbTitle, gridPane, hbAddBack, hbSuccess, hbLilLogo);

		// Ajout des marges pour chaque élément de la VBox
		VBox.setMargin(hbTitle, new Insets(70, 0, 20, 0));
		VBox.setMargin(gridPane, new Insets(20, 0, 20, 0));
		VBox.setMargin(hbAddBack, new Insets(50, 0, 0, 0));
		VBox.setMargin(hbLilLogo, new Insets(40, 0, 0, 0));
		VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0));

		// Création du dégradé de couleur verticale de Aliceblue à White
		Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.6, Color.ALICEBLUE),
				new Stop(1, Color.LIGHTSKYBLUE) };
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

		// Application du dégradé à l'arrière-plan de la AddPage
		this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	// Création et retour de la scène
	public Scene getMyScene() {
		return new Scene(this, 425, 620);
	}
}
