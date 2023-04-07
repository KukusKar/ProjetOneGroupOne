package fr.cda24.ISIKA.Projet1.Annuaire;

import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;


public class Session {
    private static Session instance;
    private Employee loggedInUser;
    private BinaryTree sortedBinaryTree;


    private Session() {}

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Employee employee) {
        this.loggedInUser = employee;
    }
    
    public BinaryTree getSortedBinaryTree() {
        return sortedBinaryTree;
    }

    public void setSortedBinaryTree(BinaryTree binaryTree) {
        this.sortedBinaryTree = binaryTree;
    }
}
