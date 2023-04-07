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

	public DeleteStagPage() {

		this.originPage = originPage;
		this.arbre = arbre;

//        Noeud n = new Noeud(null, 0, 0, 0);
//    	Stagiaire stg = new Stagiaire(null, null, null, null, null);

		Session session = Session.getInstance();
		if (session.getLoggedInUser() != null) {
			this.email = session.getLoggedInUser().getEmail();
			this.password = session.getLoggedInUser().getPassword();
		}

		// Création de la HBox du titre
		HBox hbTitle = new HBox();
		Label lblTitle = new Label("Suppresion d'un stagiaire");
		lblTitle.setStyle(
				"-fx-font-family:Roboto; -fx-padding: 10px ;-fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius:10px");
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


		// Création de la HBox qui contiendra le bouton ajouter ou retour
		HBox hbDeleteBack = new HBox();
		HBox hbSuccess = new HBox();
		Button btnDelete = new Button("Supprimer");
		Button btnBack = new Button("Retour");
		Label lblSuccess = new Label("Stagiaire supprimé.");
		lblSuccess.setTextFill(Color.GREEN);
		lblSuccess.setVisible(false);

		// bouton pour ajouter un nouveau stagiaire à la liste
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        // Créez un stagiaire avec le nom et le prénom saisis
		        Stagiaire stagiaireASupprimer = new Stagiaire(
		            tfLastName.getText(),
		            null, null, null, null
		        );
		        
		        System.out.println("Suppression du stagiaire : " + stagiaireASupprimer.getNom());
		        
//		      Supprimez le stagiaire de l'arbre
		       
		            Noeud racine = arbre.getRacine(); // Obtenez la racine de l'arbre
		            if (racine != null) {
		                racine.supprimer(stagiaireASupprimer, arbre); // Supprimez le stagiaire en utilisant la racine
		                lblSuccess.setVisible(true); // Rendez le label visible si la suppression a réussi
		            } else {
		            	System.out.println("La suppréssion du stagiaire : " + stagiaireASupprimer.getNom() + " " + stagiaireASupprimer.getPrenom() + " à échoué");
		            }
		
	    }
		});

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

		hbDeleteBack.getChildren().addAll(btnDelete, btnBack);
		hbDeleteBack.setAlignment(Pos.CENTER);
		hbDeleteBack.setSpacing(30);
		hbSuccess.getChildren().add(lblSuccess);
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
		VBox.setMargin(hbLilLogo, new Insets(40, 0, 0, 0));
		VBox.setMargin(hbSuccess, new Insets(40, 0, 0, 0));


		// Création du dégradé de couleur verticale de Aliceblue à White
		Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.WHITE),
				new Stop(1, Color.ALICEBLUE) };
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

		// Application du dégradé à l'arrière-plan de la AddPage
		this.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

	}

	public Scene getMyScene() {
		return new Scene(this, 425, 560);
	}

}
