package back;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import javafx.scene.control.TableView;

public class Noeud {


	// instance class noeud

	// taille max atributs du noeud
	// nom : 15 char = 30 OCT
	public final static int TAILLE_MAX_LIGNE = 30;
	public final static int TAILLE_MAX_NOEUD_OCTET = 30 * 5 * 2 + 2 * 4;

	private Stagiaire stagiaire;
	private int gauche;
	private int droit;
	private Stagiaire stg;

	// je crée mon constructeur et je m'assure qu'ils n'y a rien dans les noeuds
	// gauche et doite d'entrée
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.gauche = -1;
		this.droit = -1;
	}

	int droit() {
		return droit;
	}

	int gauche() {
		return gauche;
	}

	public void modifGauche(int noeud) {
		gauche = noeud;
	}

	public void modifDroit(int noeud) {
		droit = noeud;
	}

	void modifStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	

	public int getGauche() {
		return gauche;
	}

	public void setGauche(int gauche) {
		this.gauche = gauche;
	}

	public int getDroit() {
		return droit;
	}

	public void setDroit(int droit) {
		this.droit = droit;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public String getNomLong() {
		String nomLong = this.stagiaire.getNom();
		for (int i = this.stagiaire.nom.length(); i < TAILLE_MAX_LIGNE; i++) {
			nomLong += "*";
		}
		return nomLong;
	}

	public String getPrenomLong() {
		String prenomLong = this.stagiaire.getPrenom();
		for (int i = this.stagiaire.prenom.length(); i < TAILLE_MAX_LIGNE; i++) {
			prenomLong += "*";
		}
		return prenomLong;
	}

	public String getDeptLong() {
		String deptLong = this.stagiaire.getDepartement();
		for (int i = this.stagiaire.departement.length(); i < TAILLE_MAX_LIGNE; i++) {
			deptLong += "*";
		}
		return deptLong;
	}

	public String getCoursLong() {
		String coursLong = this.stagiaire.getCours();
		for (int i = this.stagiaire.cours.length(); i < TAILLE_MAX_LIGNE; i++) {
			coursLong += "*";
		}
		return coursLong;
	}

	public String getAnneeLong() {
		String anneeLong = this.stagiaire.getAnneeForm();
		for (int i = this.stagiaire.anneeForm.length(); i < TAILLE_MAX_LIGNE; i++) {
			anneeLong += "*";
		}
		return anneeLong;
	}

//			

//METHODE RECURSIVE
	public Noeud(Stagiaire stg, int gauche, int droit) {
		this.stagiaire = stg;
		this.gauche = gauche;
		this.droit = droit;
	}

	public void insert(Stagiaire stagiaire, RandomAccessFile raf) {

		int compare = this.getStagiaire().getNom().compareTo(stagiaire.getNom());
		try {

			if (compare > 0) {

				if (this.gauche() == -1) {
					// tu as trouvé l'emplacement
					this.gauche = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
					raf.seek(raf.getFilePointer() - 8);
					raf.writeInt(this.gauche);// on donne la valeur au fils gauche
					raf.seek(raf.length());
					// ecrire le noeud stagiaire
					Noeud stag = new Noeud(stagiaire);
					ecrireNoeud(stag, raf); // methode à créer

				} else {
					raf.seek(this.gauche * TAILLE_MAX_NOEUD_OCTET);
					// System.out.println("FG -> " + this.gauche);
					Noeud filsGauche = lireNoeud(raf);
					// System.out.println("Fils gauche " + filsGauche);
					filsGauche.insert(stagiaire, raf);
				}
			}

			else if (compare < 0) {
				if (this.droit == -1) {
					this.droit = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt(this.droit);
					raf.seek(raf.length());
					// ecrire le noeud stagiaire
					Noeud stag = new Noeud(stagiaire);
					ecrireNoeud(stag, raf);

				} else {
					raf.seek(this.droit * TAILLE_MAX_NOEUD_OCTET);
					// System.out.println("FD -> " + droit);
					Noeud filsDroit = lireNoeud(raf);
					filsDroit.insert(stagiaire, raf);
				}
			}
//				else if (compare == 0) {
//					this.getStagiaire() = (raf.length() / TAILLE_MAX_NOEUD_OCTET);
//					LinkedList<Noeud> stagiaire = new LinkedList<>();
//				    stagiaire.add(this.);
//				}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// METHODE ECRIRE NOEUD
	private Noeud ecrireNoeud(Noeud stag, RandomAccessFile raf) {
		try {
			raf.writeChars(stag.getNomLong());
			raf.writeChars(stag.getPrenomLong());
			raf.writeChars(stag.getDeptLong());
			raf.writeChars(stag.getCoursLong());
			raf.writeChars(stag.getAnneeLong());
			// entier fils gauche
			raf.writeInt(-1);
			// entier fils droit
			raf.writeInt(-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stag;
	}

	// METHODE LIRE NOEUD
	public Noeud lireNoeud(RandomAccessFile raf) {
		String nom = "";
		String prenom = "";
		String dpt = "";
		String cours = "";
		String annee = "";
		int fg = -1;
		int fd = -1;
		try {
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				nom += raf.readChar();
			}
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				prenom += raf.readChar();
			}
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				dpt += raf.readChar();
			}
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				cours += raf.readChar();
			}
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				annee += raf.readChar();
			}
			fg = raf.readInt();
			fd = raf.readInt();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Noeud noeudLu = new Noeud(new Stagiaire(nom, prenom, dpt, cours, annee), fg, fd);
		return noeudLu;

	}
//
//	// METHODE AFFICHAGE INFIXE
//	
//	public Noeud parcoursInfixe(int i, RandomAccessFile raf) {
//		Noeud n = lireNoeud(raf);
//		if (n.getGauche() != -1) {
//			System.out.println(n);
//			parcoursInfixe(n.getGauche(), raf);
//			n.ecrireNoeud(n, raf);
//			
//		}
//		if (n.getDroit() != -1) {
//			System.out.println(n);
//			parcoursInfixe(n.getDroit(), raf);
//			n.ecrireNoeud(n, raf);
//		}
//		return n;
//		
//	}
//	


	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", gauche=" + gauche + ", droit=" + droit + "]";
	}

}
