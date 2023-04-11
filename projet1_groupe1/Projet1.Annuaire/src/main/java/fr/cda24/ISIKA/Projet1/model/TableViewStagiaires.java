package fr.cda24.ISIKA.Projet1.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


/**
 * Cette classe représente une TableView personnalisée pour afficher une liste de stagiaires.
 * Elle hérite de la classe BorderPane.
 */
public class TableViewStagiaires extends BorderPane {

	// Création des attributs
	private TableView<Stagiaire> table = new TableView<Stagiaire>();
	private ObservableList<Stagiaire> observableList;
	private BinaryTree arbre;

	/**
	 * Constructeur de la classe TableViewStagiaires.
	 * 
	 * @param lesStagiaires la liste des stagiaires à afficher dans la table.
	 */	public TableViewStagiaires(ArrayList<Stagiaire> lesStagiaires) {// observablelist

		observableList = FXCollections.observableArrayList(lesStagiaires); // Utilisez la liste triée pour remplir
																			// l'ObservableList

		table.setEditable(true);

		// Création des colonnes pour la table et définition de leurs propriétés
		TableColumn<Stagiaire, String> colNom = new TableColumn<Stagiaire, String>("Nom");
		colNom.setStyle("-fx-alignment: CENTER;");
		colNom.setMinWidth(200);
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> colPrenom = new TableColumn<Stagiaire, String>("Prénom");
		colPrenom.setStyle("-fx-alignment: CENTER;");
		colPrenom.setMinWidth(200);
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> colDept = new TableColumn<Stagiaire, String>("Département");
		colDept.setStyle("-fx-alignment: CENTER;");
		colDept.setMinWidth(200);
		colDept.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		TableColumn<Stagiaire, String> colProm = new TableColumn<Stagiaire, String>("Promotion");
		colProm.setStyle("-fx-alignment: CENTER;");
		colProm.setMinWidth(200);
		colProm.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prom"));

		TableColumn<Stagiaire, String> colAnnee = new TableColumn<Stagiaire, String>("Année de formation");
		colAnnee.setStyle("-fx-alignment: CENTER;");
		colAnnee.setMinWidth(200);
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneeForm"));

		
		table.getColumns().addAll(colNom, colPrenom, colDept, colProm, colAnnee);
		// définir une couleur semi-transparente pour l'arrière-plan du TableView

		

		// Association des données de l'observableList à la table
		table.setItems(observableList);

	}

	 /**
	 * Méthode pour définir l'observableList et mettre à jour les données de la table.
	 * 
	 * @param observableList la nouvelle liste d'observable.
	 */
	public void setObservableList(ObservableList<Stagiaire> observableList) {
		this.observableList = observableList;
		table.setItems(observableList);
	}

	/**
	 * Méthode pour obtenir la table.
	 * 
	 * @return la table des stagiaires.
	 */
	public TableView<Stagiaire> getTable() {
		return table;
	}

	
	/**
	 * Méthode pour obtenir l'observableList.
	 * 
	 * @return l'observableList des stagiaires.
	 */
	public ObservableList<Stagiaire> getObservableList() {
		return observableList;
	}

	/**
	 * Méthode pour actualiser les données de la table avec une nouvelle liste d'ObservableList
	 * 
	 * @param observableList la nouvelle liste d'ObservableList à afficher dans la table
	 */
	public void refreshTable() {
		ObservableList<Stagiaire> data = FXCollections.observableArrayList(observableList);
		table.setItems(data);
	}
	
	
}
