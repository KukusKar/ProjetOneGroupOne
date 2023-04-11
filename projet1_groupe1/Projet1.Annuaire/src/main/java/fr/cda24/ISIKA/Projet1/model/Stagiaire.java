package fr.cda24.ISIKA.Projet1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.util.Callback;


/**
 * Classe qui sert à instancier des stagiaires
 */
public class Stagiaire {
	
	// Attributs de la classe
	public String nom;
	public String prenom;
	public String departement;
	public String prom;
	public String anneeForm;
	

	/**
	 * Constructeur de la classe Stagiaire.
	 * 
	 * @param nom Le nom du stagiaire.
	 * @param prenom Le prénom du stagiaire.
	 * @param departement Le département du stagiaire.
	 * @param prom La promotion du stagiaire.
	 * @param anneeForm L'année de formation du stagiaire.
	 */
	public Stagiaire(String nom, String prenom, String departement, String prom, String anneeForm) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.prom = prom;
		this.anneeForm = anneeForm;
	}
	
	/**
	 * Getter pour l'attribut nom.
	 * 
	 * @return Le nom du stagiaire.
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Getter pour l'attribut prenom.
	 * 
	 * @return Le prénom du stagiaire.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getter pour l'attribut prenom.
	 * 
	 * @return Le prénom du stagiaire.
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Setter pour l'attribut prenom.
	 * 
	 * @param prenom Le prénom du stagiaire.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Getter pour l'attribut departement.
	 * 
	 * @return Le département du stagiaire.
	 */
	public String getDepartement() {
		return departement;
	}
	/**
	 * Setter pour l'attribut departement.
	 * 
	 * @param departement Le département du stagiaire.
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	/**
	 * Getter pour l'attribut prom.
	 * 
	 * @return La promotion du stagiaire.
	 */
	public String getProm() {
		return prom;
	}
	/**
	 * Setter pour l'attribut prom.
	 * 
	 * @param prom La promotion du stagiaire.
	 */
	public void setProm(String prom) {
		this.prom = prom;
	}
	/**
	 * Getter pour l'attribut anneeForm.
	 * 
	 * @return L'année de formation du stagiaire.
	 */
	public String getAnneeForm() {
		return anneeForm;
	}
	/**
	 * Setter pour l'attribut anneeForm.
	 * 
	 * @param anneeNaiss L'année de formation du stagiaire.
	 */
	public void setAnneeForm(String anneeNaiss) {
		this.anneeForm = anneeNaiss;
	}
	/**
	 * Redéfinition de la méthode toString pour afficher les informations du stagiaire.
	 * 
	 * @return Une chaîne de caractères contenant les informations du stagiaire.
	 */
	@Override
	public String toString() {
		return "TableViewStagiaires [Nom= " + nom + "\tPrenom= " + prenom + "\tDepartement= " + departement + "\tProm= "
				+ prom + "\tAnnée de formation= " + anneeForm + "]";
	}


	/**
	 * 
	 * Cette méthode renvoie un objet Callback pour récupérer une liste de stagiaires à partir d'une base de données
	 * 
	 * @return un objet Callback pour récupérer une liste de stagiaires
	 */
	public static Callback getStagiaires() {
		// TODO: implémenter cette méthode pour récupérer une liste de stagiaires à partir d'une base de données
		return null;
	}


	
}

