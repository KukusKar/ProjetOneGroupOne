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
/**
 * Cette classe représente l'application principale.
 */
public class App extends Application {
	// Indique si le fichier de dons a été chargé ou non
	private static boolean donFileLoaded = false;
	// Arbre binaire utilisé pour stocker les données des utilisateurs
	private BinaryTree binaryTree = new BinaryTree<>();

	/**
     * Cette méthode est appelée au démarrage de l'application.
     * Elle crée l'instance de la page de connexion, désactive le redimensionnement
     * de la fenêtre et définit la page de connexion comme scène principale.
     * 
     * @param stage la fenêtre principale de l'application
     * @throws Exception si une exception est levée pendant l'exécution de la méthode
     */
	@Override
	public void start(Stage stage) throws Exception {

        // Création de l'instance de la page de connexion en lui passant l'arbre binaire
		LoginPage loginPage = new LoginPage(binaryTree); 

		// Désactiver le redimensionnement de la fenêtre
		stage.setResizable(false);

        // Obtenir la scène de la page de connexion
		Scene scene = loginPage.getMyScene();
        // Définition de la scène comme scène principale de la fenêtre
		stage.setScene(scene); 
        // Afficher la fenêtre
		stage.show();
	}
	/**
     * Cette méthode est appelée au lancement de l'application.
     * Elle appelle la méthode launch() de la classe Application pour démarrer l'application.
     * 
     * @param args les arguments passés en ligne de commande
     */
	public static void main(String[] args) {
		launch();
	}
}