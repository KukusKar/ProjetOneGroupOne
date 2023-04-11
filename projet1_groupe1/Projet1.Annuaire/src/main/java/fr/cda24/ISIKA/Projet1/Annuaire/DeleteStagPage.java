package fr.cda24.ISIKA.Projet1.Annuaire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Noeud;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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


	public class DeleteStagPage<T> extends VBox {

		private String email;
		private String password;
		private T originPage;
		private BinaryTree arbre;
		Noeud n = new Noeud(null, 0, 0, 0);
		Stagiaire stg = new Stagiaire(null, null, null, null, null);

		/**
		 * Page de suppression d'un stagiaire. Cette page permet à l'utilisateur de
		 * supprimer un stagiaire en entrant son nom, prénom, département, cycle et année d'inscription.
		 *
		 * @param arbre L'arbre binaire de recherche qui contient les stagiaires
		 */
		public DeleteStagPage(BinaryTree<?> arbre) {
			// Initialisation des attributs
			this.originPage = originPage;
			this.arbre = arbre;

			// Récupération des informations de l'utilisateur connecté
			Session session = Session.getInstance();
			if (session.getLoggedInUser() != null) {
				this.email = session.getLoggedInUser().getEmail();
				this.password = session.getLoggedInUser().getPassword();
			}

			// Création de la HBox du titre
			HBox hbTitle = new HBox();
			Label lblTitle = new Label("Suppresion d'un stagiaire");
			lblTitle.setStyle(
					"-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-family: Roboto; -fx-padding: 10px 20px; -fx-background-radius: 15px; -fx-border-radius: 15px;");
			hbTitle.getChildren().add(lblTitle);
			hbTitle.setAlignment(Pos.CENTER);

			// Création des labels et des textFields, RadioButton, datepicker etc
			Label lblLastName = new Label("Entrez le nom : ");
			TextField tfLastName = new TextField();
			tfLastName.setStyle("-fx-prompt-text-fill: gray;");
			tfLastName.setPromptText("ex : Des neiges");
			tfLastName.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

			Label lblFirstName = new Label("Entrez le prénom : ");
			TextField tfFirstName = new TextField();
			tfFirstName.setStyle("-fx-prompt-text-fill: gray;");
			tfFirstName.setPromptText("ex : La reine");
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
					// Créez un stagiaire avec le nom et le prénom saisis
					Stagiaire stagiaireASupprimer = new Stagiaire(tfLastName.getText(), tfFirstName.getText(),
							cbDep.getValue(), tfCycle.getText(), tfAnnIns.getText());

					System.out.println("Suppression du stagiaire : " + stagiaireASupprimer.getNom());
					// Supprimez le stagiaire de l'arbre
					try {
						Noeud racine = arbre.getRacine(); // Obtenez la racine de l'arbre
						if (racine != null) {
							// Recherchez le stagiaire dans l'arbre
							Stagiaire stagiaireTrouve = racine.rechercher(stagiaireASupprimer, arbre.getRaf());
							if (stagiaireTrouve != null) {
								// Le stagiaire a été trouvé dans l'arbre
								racine.supprimer(stagiaireASupprimer, arbre.getRaf());
								lblSuccessError.setText("Stagiaire supprimé(e) avec succès.");
								lblSuccessError.setTextFill(Color.GREEN);
								lblSuccessError.setVisible(true);
							} else {
								// Le stagiaire n'a pas été trouvé dans l'arbre
								lblSuccessError.setText("La suppression a échoué.");
								lblSuccessError.setTextFill(Color.RED);
								lblSuccessError.setVisible(true);
								System.out.println("La suppréssion du stagiaire : " + stagiaireASupprimer.getNom() + " "
										+ stagiaireASupprimer.getPrenom() + " a échoué");
							}
						}
					} catch (IOException e) {
						// Gérez l'exception si nécessaire
						e.printStackTrace();
					}
				}
			});
			//Notez que vous devez également modifier la condition dans le bloc else pour afficher le message d'erreur lorsque la suppression échoue.





			/**
			 * Cette méthode est appelée lorsqu'on appuie sur le bouton "Retour". Elle renvoie l'utilisateur à la page
			 * principale en créant une nouvelle instance de MainPage.
			 *
			 * @param e L'événement qui a déclenché la méthode
			 */
			btnBack.setOnAction(e -> {
				MainPage<T> mainPage = new MainPage<T>(email, password, originPage, arbre);
				Scene sceneMainPage = mainPage.getMyScene();
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

			/**
			 * Cette méthode crée une HBox contenant le logo de l'application.
			 * @return la HBox contenant le logo de l'application
			 * 
			 */
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
			VBox.setMargin(hbLilLogo, new Insets(40, 0, 0, 0));
			VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0));

			// Création du dégradé de couleur verticale de Aliceblue à White
			Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.6, Color.ALICEBLUE), new Stop(1, Color.LIGHTSKYBLUE) };
			LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

			// Application du dégradé à l'arrière-plan de la AddPage
			this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

		}
	/**
	 * 
	 * Retourne la scene de la page.
	 * 
	 * @return la scene de la page
	 */
		public Scene getMyScene() {
			return new Scene(this, 425, 620);
		}
		/**
		 * 
		 * Retourne l'origine de la page
		 * 
		 * @return l'origine de la page
		 */
		public T getOriginPage() {
	        return originPage;
	    }

	}

