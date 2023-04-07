package fr.cda24.ISIKA.Projet1.Annuaire;

import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;
import fr.cda24.ISIKA.Projet1.model.TableViewStagiaires;

import java.util.ArrayList;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */

public class App extends Application {

//	private Class<?> originPage;
	 private static boolean donFileLoaded = false;
	    private BinaryTree binaryTree = new BinaryTree<>();

	@Override
	public void start(Stage stage) throws Exception {
		//binaryTree = new BinaryTree<>();
		
		// Chargement du fichier binaire
//        if (binaryTree == null) {
//            binaryTree = new BinaryTree();
//        }
		// Création d'un nouvel objet TableViewStagiaires
		
//		ListEmployee listEmployee = new ListEmployee();
		LoginPage loginPage = new LoginPage(binaryTree); // Création de l'instance de LoginPage
		//AddStagPage addStagPage = new AddStagPage();
//		AddEmployee addEmploye = new AddEmployee(null);
		//MainPage mainPage = new MainPage(null, null, null, binaryTree);
		// Désactiver le redimensionnement de la fenêtre
        stage.setResizable(false);
        
        

        Scene scene = loginPage.getMyScene();
		stage.setScene(scene); // Définition de la scène comme scène principale de la fenêtre
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}