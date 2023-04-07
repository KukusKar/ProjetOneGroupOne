package fr.cda24.ISIKA.Projet1.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TableViewStagiaires extends BorderPane {

	// Création des attributs
	private TableView<Stagiaire> table = new TableView<Stagiaire>();
	private ObservableList<Stagiaire> observableList;

	// Constructeur de la class
	public TableViewStagiaires(ArrayList<Stagiaire> lesStagiaires) {// observablelist

		observableList = FXCollections.observableArrayList(lesStagiaires); // Utilisez la liste triée pour remplir
																			// l'ObservableList

		table.setEditable(true);

		// Création des colonnes pour la table et définition de leurs propriétés
		TableColumn<Stagiaire, String> colNom = new TableColumn<Stagiaire, String>("Nom");
		colNom.setMinWidth(200);
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> colPrenom = new TableColumn<Stagiaire, String>("Prénom");
		colPrenom.setMinWidth(200);
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> colDept = new TableColumn<Stagiaire, String>("Département");
		colDept.setMinWidth(200);
		colDept.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		TableColumn<Stagiaire, String> colProm = new TableColumn<Stagiaire, String>("Promotion");
		colProm.setMinWidth(200);
		colProm.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prom"));

		TableColumn<Stagiaire, String> colAnnee = new TableColumn<Stagiaire, String>("Année de formation");
		colAnnee.setMinWidth(200);
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneeForm"));

		// Ajout des colonnes à la tables
		table.getColumns().addAll(colNom, colPrenom, colDept, colProm, colAnnee);

		// Association des données de l'observableList à la table
		table.setItems(observableList);

		// Définition du style pour le this
		this.setStyle("-fx-border-color: white; -fx-border-width: 0px;");

	}

	// Méthode pour définir l'observableList et mettre à jour les données de la
	// table
	public void setObservableList(ObservableList<Stagiaire> observableList) {
		this.observableList = observableList;
		table.setItems(observableList);
	}

	// Méthode pour obtenir la table
	public TableView<Stagiaire> getTable() {
		return table;
	}

	public ObservableList<Stagiaire> getObservableList() {
		return observableList;
	}

	// Méthode pour actualiser la table
	public void refreshTable() {
		ObservableList<Stagiaire> data = FXCollections.observableArrayList(observableList);
		table.setItems(data);
	}
}
