package Projet1.Goupe1;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

public class GPFormulaire extends GridPane {

	public ArrayList<Stagiaire> lesStagiaires; // observable list

	// je relie mon formulaire à la liste
	public void GPFormulaire(ArrayList<Stagiaire> lesStagiaires) {

		// Je crée mon GridPane
		GridPane root = new GridPane();

		// Je donne un style
		root.setStyle("-fx-background-color:LEMONCHIFFON");
		root.setAlignment(Pos.CENTER_RIGHT);
		root.setPadding(new Insets(20, 20, 20, 20));

		// Titre du formulaire
		Label lblTitre = new Label("Entrez les informations du Stagiaire à ajouter");
		root.add(lblTitre, 0, 0);

		// Ligne de saisie du nom
		Label lblNom = new Label("Nom : ");
		TextField tfNom = new TextField();
		tfNom.setText("Nom");
		root.add(lblNom, 0, 3);
		root.add(tfNom, 2, 3);

		// Ligne de saisie du prénom
		Label lblPrenom = new Label("Prénom : ");
		TextField tfPrenom = new TextField();
		tfPrenom.setText("Prénom");
		root.add(lblPrenom, 0, 4);
		root.add(tfPrenom, 2, 4);

		// Ligne de saisie du département
		Label lblDept = new Label("Département : ");
		TextField tfDept = new TextField();
		lblDept.setText("34");
		root.add(lblDept, 0, 5);
		root.add(tfDept, 2, 5);

		// Ligne de saisie du cours
		Label lblCours = new Label("Formation : ");
		TextField tfCours = new TextField();
		lblCours.setText("Cours");
		root.add(lblCours, 0, 6);
		root.add(tfCours, 2, 6);

		// Ligne de saisie du cours
		Label lblAnneeFor = new Label("Année : ");
		TextField tfAnneeFor = new TextField();
		lblAnneeFor.setText("Cours");
		root.add(lblAnneeFor, 0, 7);
		root.add(tfAnneeFor, 2, 7);

		// Bouton enregistrer -> ajout de stagiaire dans la liste listeStagiaires
		Button btnSave = new Button("Enregistrer");
		root.add(btnSave, 0, REMAINING);

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stagiaire stagiaire = new Stagiaire(tfNom.getText(), tfPrenom.getText(),
						Integer.parseInt(tfDept.getText()), tfCours.getText(), Integer.parseInt(tfAnneeFor.getText()));

				lesStagiaires.add(stagiaire);

			}
		});

	}
}
