package fr.cda24.ISIKA.Projet1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.util.Callback;

public class Stagiaire {
	
	public String nom;
	public String prenom;
	public String departement;
	public String prom;
	public String anneeForm;
	
	
	public Stagiaire(String nom, String prenom, String departement, String prom, String anneeForm) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.prom = prom;
		this.anneeForm = anneeForm;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getProm() {
		return prom;
	}
	public void setProm(String prom) {
		this.prom = prom;
	}
	public String getAnneeForm() {
		return anneeForm;
	}
	public void setAnneeForm(String anneeNaiss) {
		this.anneeForm = anneeNaiss;
	}
	
	@Override
	public String toString() {
		return "TableViewStagiaires [Nom= " + nom + "\tPrenom= " + prenom + "\tDepartement= " + departement + "\tProm= "
				+ prom + "\tAnnée de formation= " + anneeForm + "]";
	}


	public static Callback getStagiaires() {
		// TODO Auto-generated method stub
		return null;
	}


	
}

