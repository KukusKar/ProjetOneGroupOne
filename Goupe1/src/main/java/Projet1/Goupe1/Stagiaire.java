package Projet1.Goupe1;

public class Stagiaire {
	
	public String nom;
	public String prenom;
	public int departement;
	public String cours;
	public int anneeForm;
	
	public Stagiaire(String nom, String prenom, int departement, String cours, int anneeForm) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.cours = cours;
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
	public int getDepartement() {
		return departement;
	}
	public void setDepartement(int departement) {
		this.departement = departement;
	}
	public String getCours() {
		return cours;
	}
	public void setCours(String cours) {
		this.cours = cours;
	}
	public int getAnneeForm() {
		return anneeForm;
	}
	public void setAnneeForm(int anneeNaiss) {
		this.anneeForm = anneeNaiss;
	}
	
	@Override
	public String toString() {
		return "TableViewFStagiaires [Nom= " + nom + "\tPrenom= " + prenom + "\tDepartement= " + departement + "\tCours= "
				+ cours + "\tAnnée de formation= " + anneeForm + "]";
	}
	
}
