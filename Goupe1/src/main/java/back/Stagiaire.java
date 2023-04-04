package back;

public class Stagiaire {
	
	public String nom;
	public String prenom;
	public String departement;
	public String cours;
	public String anneeForm;
	
	public Stagiaire(String nom, String prenom, String departement, String cours, String anneeForm) {
		
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
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getCours() {
		return cours;
	}
	public void setCours(String cours) {
		this.cours = cours;
	}
	public String getAnneeForm() {
		return anneeForm;
	}
	public void setAnneeForm(String anneeNaiss) {
		this.anneeForm = anneeNaiss;
	}
	
	@Override
	public String toString() {
		return "TableViewFStagiaires [Nom= " + nom + "\tPrenom= " + prenom + "\tDepartement= " + departement + "\tCours= "
				+ cours + "\tAnnée de formation= " + anneeForm + "]";
	}
	
}