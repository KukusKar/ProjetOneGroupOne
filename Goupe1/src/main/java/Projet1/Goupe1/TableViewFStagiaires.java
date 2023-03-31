package Projet1.Goupe1;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class TableViewFStagiaires extends GridPane{
	
	private TableView<Stagiaire> table = new TableView<Stagiaire>();

	
	public TableViewFStagiaires(ArrayList <Stagiaire> lesStagiaires) {//observablelist
		
		table.setEditable(true);
		
		//Je rajoute mes colonnes
		TableColumn<Stagiaire,String> colNom= new TableColumn<Stagiaire,String>("Nom");
		colNom.setMinWidth(50); 
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		
		TableColumn<Stagiaire,String> colPrenom= new TableColumn<Stagiaire,String>("Prenom");
		colPrenom.setMinWidth(50); 
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		
		TableColumn<Stagiaire, String> colDept= new TableColumn<Stagiaire, String>("Département");
		colDept.setMinWidth(50); 
		colDept.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		
		TableColumn<Stagiaire,String> colCours= new TableColumn<Stagiaire,String>("Cours");
		colCours.setMinWidth(50); 
		colCours.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("cours"));
		
		TableColumn<Stagiaire,Integer> colAnnee= new TableColumn<Stagiaire,Integer>("Année de formation");
		colAnnee.setMinWidth(50); 
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("anneeForm"));
		
		table.getColumns().addAll(colNom, colPrenom, colDept, colCours, colAnnee);
		table.setItems(FXCollections.observableArrayList(lesStagiaires));
	
		
	}


	public TableView<Stagiaire> getTable() {
		return table;
	}


	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}
	
	
	

}
