package fr.cda24.ISIKA.Projet1.Annuaire;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;


/**
 * Cette classe représente une session pour une application.
 */
public class Session {
    // Instance unique de la classe Session
    private static Session instance;
    // Utilisateur connecté
    private Employee loggedInUser;
    // Arbre binaire trié
    private BinaryTree sortedBinaryTree;

    // Constructeur privé pour empêcher la création d'instances directement
    private Session() {}
    /**
     * Cette méthode retourne l'instance unique de la classe Session. Elle est
     * synchronisée pour éviter des problèmes avec le multithreading.
     * 
     * @return l'instance unique de la classe Session
     */
    public static synchronized Session getInstance() {
        // Si l'instance n'a pas encore été créée, on la crée maintenant
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    /**
     * Cette méthode retourne l'utilisateur connecté.
     * 
     * @return l'utilisateur connecté
     */
    public Employee getLoggedInUser() {
        return loggedInUser;
    }
    /**
     * Cette méthode permet de définir l'utilisateur connecté.
     * 
     * @param employee l'utilisateur connecté
     */
    public void setLoggedInUser(Employee employee) {
        this.loggedInUser = employee;
    }
    /**
     * Cette méthode retourne l'arbre binaire trié.
     * 
     * @return l'arbre binaire trié
     */
    public BinaryTree getSortedBinaryTree() {
        return sortedBinaryTree;
    }
    /**
     * Cette méthode permet de définir l'arbre binaire trié.
     * 
     * @param binaryTree l'arbre binaire trié
     */
    public void setSortedBinaryTree(BinaryTree binaryTree) {
        this.sortedBinaryTree = binaryTree;
    }
}
