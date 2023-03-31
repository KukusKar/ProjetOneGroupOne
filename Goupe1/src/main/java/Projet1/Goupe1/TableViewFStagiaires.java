package Projet1.Goupe1;

import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class TableViewFStagiaires extends GridPane{
	
	private TableView<Stagiaire> table = new TableView<Stagiaire>();

	
	public TableViewFStagiaires(ArrayList <Stagiaire> lesStagiaires) {//observablelist
		
		table.setEditable(true);
		
		//Je rajoute mes colonnes
		TableColumn<Stagiaire,String> colNom= new TableColumn<Stagiaire,String>("Nom");
		colNom.setMinWidth(200); 
		
		TableColumn<Stagiaire,String> colPrenom= new TableColumn<Stagiaire,String>("Prenom");
		colPrenom.setMinWidth(200); 
		
		TableColumn<Stagiaire, Integer > colDept= new TableColumn<>("Département");
		colDept.setMinWidth(200); 
		
		TableColumn<Stagiaire,String> colCours= new TableColumn<Stagiaire,String>("Cours");
		colCours.setMinWidth(200); 
		
		TableColumn<Stagiaire,Integer> colAnnee= new TableColumn<Stagiaire,Integer>("Année de formation");
		colAnnee.setMinWidth(200); 
		
		table.getColumns().addAll(colNom, colPrenom, colDept, colCours, colAnnee);

		
		
	}


	public TableView<Stagiaire> getTable() {
		return table;
	}


	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}
	
	
	

}
