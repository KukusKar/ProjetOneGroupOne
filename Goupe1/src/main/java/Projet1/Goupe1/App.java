package Projet1.Goupe1;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class App extends Application {
	
	public ArrayList<Stagiaire> lesStagiaires = new ArrayList<>();
	// je crée mon BorderPane
	BorderPane root = new BorderPane();
	
	public void init () {
		
	}

	@Override
	public void start(Stage stage) throws Exception {

		// je donne lui donne un style de malade
		Pane topPannel = new Pane();
		topPannel.setPrefSize(800, 100);
		topPannel.setSnapToPixel(true);
		topPannel.setStyle("-fx-background-color: darkslateblue");
		Pane leftPannel = new Pane();
		leftPannel.setPrefSize(200, 600);
		leftPannel.setSnapToPixel(true);
		leftPannel.setPadding(new Insets(10, 20, 10, 20));
		leftPannel.setStyle("-fx-background-color: lightseagreen");
		Pane bottomPannel = new Pane();
		bottomPannel.setPrefSize(800, 50);
		bottomPannel.setStyle("-fx-background-color: GREY");

		// je positionne mes composants
		root.setTop(topPannel);
		root.setLeft(leftPannel);
		root.setBottom(bottomPannel);
		root.setStyle("-fx-background-color: ALICEBLUE");

		// j'ajoute mon VBox et HBox
		HBox hbox = addHBoxHaut();
		root.setTop(addHBoxHaut());
		VBox vbox = addVBox();
		root.setLeft(addVBox());
		HBox hboxBas = addHBoxBas();
		root.setBottom(addHBoxBas());

		Scene scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.show();

	}

	private HBox addHBoxBas() {
		HBox hboxBas = new HBox();
		hboxBas.setPadding(new Insets(15, 12, 15, 12));
		hboxBas.setSpacing(20);
		hboxBas.setAlignment(Pos.BOTTOM_RIGHT);

		// je crée mes boutons
//	    Button btnCherche = new Button("Recherche avancé");
//	    btnCherche.setFont(Font.font("Ink free", 12));
//	    btnCherche.setStyle("-fx-background-color:lemonchiffon");
//    	Button btnImpr = new Button("Imprimer");
//    	btnImpr.setStyle("-fx-background-color:lemonchiffon");
//    	btnImpr.setFont(Font.font("Ink free", 12));
//    	
//    	hboxBas.getChildren().addAll(btnCherche, btnImpr);

		return hboxBas;
	}

	private HBox addHBoxHaut() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		;
		Label lblTitre = new Label("AFCEPF  Annuaire des Stagiaires");
		lblTitre.setTextFill(Color.BLACK);
		lblTitre.setFont(Font.font("Ink free", FontWeight.BOLD, FontPosture.REGULAR, 20));

		// On ajoute mon Label Titre
		hbox.getChildren().addAll(lblTitre);

		return hbox;
	}


	private VBox addVBox() throws Exception {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(80, 20, 40, 20));
		vbox.setSpacing(30);

		// je crée mes boutons
		Button btnImport = new Button("Importer liste");
		btnImport.setTextFill(Color.WHITE);
		btnImport.setFont(Font.font("Ink free", 12));
		btnImport.setStyle("-fx-background-color:darkslategray");

		Button btnAfficher = new Button("Afficher stagiaires");
		btnAfficher.setStyle("-fx-background-color:lemonchiffon");
		btnAfficher.setFont(Font.font("Ink free", 12));

		btnAfficher.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent event) {
				// j'instancie un nouveau Panneau
				Pane centerPannel = new Pane();
				centerPannel.setPrefSize(400, 400);
				centerPannel.setSnapToPixel(true);
				TableViewFStagiaires tvStag = new TableViewFStagiaires(lesStagiaires);
				centerPannel.getChildren().add((tvStag.getTable()));
				root.setCenter(centerPannel);

			}
		});

		Button btnAjouter = new Button("Ajouter un stagiaire");
		btnAjouter.setStyle("-fx-background-color:mintcream");
		btnAjouter.setFont(Font.font("Ink free", 12));
		
		btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				// j'instancie un nouveau Panneau
				Pane centerPannel = new Pane();
				centerPannel.setPrefSize(400, 400);
				centerPannel.setSnapToPixel(true);
				GPFormulaire gPane = new GPFormulaire(lesStagiaires);
				centerPannel.getChildren().add((gPane.getForm()));
				root.setCenter(centerPannel);
			}
		});
		
		Button btnSupprimer = new Button("Supprimer un stagiaire");
		btnSupprimer.setStyle("-fx-background-color:mintcream");
		btnSupprimer.setFont(Font.font("Ink free", 12));
		Button btnMiseajour = new Button("Mettre à jour la liste");
		btnMiseajour.setStyle("-fx-background-color:mintcream");
		btnMiseajour.setFont(Font.font("Ink free", 12));

		vbox.getChildren().addAll(btnImport, btnAfficher, btnAjouter, btnSupprimer, btnMiseajour);

		return vbox;
	}



	public static void main(String[] args) {
		launch();
	}

}