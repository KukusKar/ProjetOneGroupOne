package front;

import java.util.ArrayList;

import back.Stagiaire;
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
	

	public GPFormulaire getForm() {
		return this;
	}


	// je fait un constructeur et relie mon formulaire à la liste
	public GPFormulaire(ArrayList<Stagiaire> lesStagiaires) {

		this.lesStagiaires = lesStagiaires;
		
		// Je donne un style
		this.setStyle("-fx-background-color:LEMONCHIFFON");
		this.setAlignment(Pos.CENTER_RIGHT);
		this.setPadding(new Insets(20, 20, 20, 20));

		// Titre du formulaire
		Label lblTitre = new Label("Entrez les informations du Stagiaire à ajouter");
		this.add(lblTitre, 0, 0);

		// Ligne de saisie du nom
		Label lblNom = new Label("Nom : ");
		TextField tfNom = new TextField();
		this.add(lblNom, 0, 3);
		this.add(tfNom, 2, 3);

		// Ligne de saisie du prénom
		Label lblPrenom = new Label("Prénom : ");
		TextField tfPrenom = new TextField();
		this.add(lblPrenom, 0, 4);
		this.add(tfPrenom, 2, 4);

		// Ligne de saisie du département
		Label lblDept = new Label("Département : ");
		TextField tfDept = new TextField();
		this.add(lblDept, 0, 5);
		this.add(tfDept, 2, 5);

		// Ligne de saisie du cours
		Label lblCours = new Label("Formation : ");
		TextField tfCours = new TextField();
		this.add(lblCours, 0, 6);
		this.add(tfCours, 2, 6);

		// Ligne de saisie année
		Label lblAnneeFor = new Label("Année : ");
		TextField tfAnneeFor = new TextField();
		this.add(lblAnneeFor, 0, 7);
		this.add(tfAnneeFor, 2, 7);

		// Bouton enregistrer -> ajout de stagiaire dans la liste listeStagiaires
		Button btnSave = new Button("Enregistrer");
		this.add(btnSave, 0, 8);

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stagiaire stagiaire = new Stagiaire
						(tfNom.getText(), 
						tfPrenom.getText(),
						tfDept.getText(),
						tfCours.getText(), 
						tfAnneeFor.getText());
				
				System.out.println(stagiaire +  "taille de la liste " + lesStagiaires.size());
				lesStagiaires.add(stagiaire);


			}
		});

	}
}
