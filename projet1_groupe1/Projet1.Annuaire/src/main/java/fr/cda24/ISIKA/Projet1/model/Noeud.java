package fr.cda24.ISIKA.Projet1.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import fr.cda24.ISIKA.Projet1.model.BinaryTree;

public class Noeud {
	// instance class noeud

	// taille max atributs du noeud
	// nom : 15 char = 30 OCT par ligne
	public final static int TAILLE_MAX_LIGNE = 30;
	public final static int TAILLE_MAX_NOEUD_OCTET = 30 * 5 * 2 + 3 * 4;

	private Stagiaire stagiaire;
	private int gauche;
	private int droit;
	private int doublon;
	private Stagiaire stg;

	// je crée mon constructeur et je m'assure qu'ils n'y a rien dans les noeuds
	// gauche et doite d'entrée
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.gauche = -1;
		this.droit = -1;
		this.doublon = -1;
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

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public String getNomLong() {
		String nomLong = this.stagiaire.getNom();
		for (int i = this.stagiaire.nom.length(); i < TAILLE_MAX_LIGNE; i++) {
			nomLong += " ";
		}
		return nomLong;
	}

	public String getPrenomLong() {
		String prenomLong = this.stagiaire.getPrenom();
		for (int i = this.stagiaire.prenom.length(); i < TAILLE_MAX_LIGNE; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}

	public String getDeptLong() {
		String deptLong = this.stagiaire.getDepartement();
		for (int i = this.stagiaire.departement.length(); i < TAILLE_MAX_LIGNE; i++) {
			deptLong += " ";
		}
		return deptLong;
	}

	public String getPromLong() {
		String promLong = this.stagiaire.getProm();
		for (int i = this.stagiaire.prom.length(); i < TAILLE_MAX_LIGNE; i++) {
			promLong += " ";
		}
		return promLong;
	}

	public String getAnneeLong() {
		String anneeLong = this.stagiaire.getAnneeForm();
		for (int i = this.stagiaire.anneeForm.length(); i < TAILLE_MAX_LIGNE; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}

//			

//METHODE RECURSIVE
	public Noeud(Stagiaire stg, int gauche, int droit, int doublon) {
		// this.stg = stg;
		this.stagiaire = stg;
		this.gauche = gauche;
		this.droit = droit;
		this.doublon = doublon;
	}

	public void insert(Stagiaire stagiaire, RandomAccessFile raf) {
		// System.out.println("this -> " + this.getStagiaire());
		// System.out.println("stagiaire -> " + stagiaire);

		int compare = this.getStagiaire().getNom().compareTo(stagiaire.getNom());
		try {

			if (compare > 0) {

				if (this.gauche() == -1) {
					// tu as trouvé l'emplacement
					this.gauche = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
					raf.seek(raf.getFilePointer() - 12);
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
					raf.seek(raf.getFilePointer() - 8);
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
			} else if (compare == 0) {
				if (this.doublon == -1) {
					this.doublon = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt(this.doublon);
					raf.seek(raf.length());
					// ecrire le noeud stagiaire
					Noeud stag = new Noeud(stagiaire);
					ecrireNoeud(stag, raf);

				} else {
					raf.seek(this.doublon * TAILLE_MAX_NOEUD_OCTET);
					// System.out.println("FD -> " + droit);
					Noeud filsDoublon = lireNoeud(raf);
					filsDoublon.insert(stagiaire, raf);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Noeud ecrireNoeud(Noeud stag, RandomAccessFile raf) {
		try {
			raf.writeChars(stag.getNomLong());
			raf.writeChars(stag.getPrenomLong());
			raf.writeChars(stag.getDeptLong());
			raf.writeChars(stag.getPromLong());
			raf.writeChars(stag.getAnneeLong());
			// entier fils gauche
			raf.writeInt(-1);
			// entier fils droit
			raf.writeInt(-1);
			raf.writeInt(-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stag;
	}

	// methode lire neoud
	public Noeud lireNoeud(RandomAccessFile raf) {
		String nom = "";
		String prenom = "";
		String dpt = "";
		String prom = "";
		String annee = "";
		int fg = -1;
		int fd = -1;
		int dblon = -1;
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
				prom += raf.readChar();
			}
			for (int i = 0; i < Noeud.TAILLE_MAX_LIGNE; i++) {
				annee += raf.readChar();
			}
			fg = raf.readInt();
			fd = raf.readInt();
			dblon = raf.readInt();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Noeud noeudLu = new Noeud(new Stagiaire(nom.trim(), prenom.trim(), dpt.trim(), prom.trim(), annee.trim()), fg,
				fd, dblon);
		return noeudLu;

	}

	// On néttois les "*" à la suite de chaque chaine de caractère pour l'affichage
	public String cleanChain(String chaine) {
		return chaine.replace("*", "");
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

	public Stagiaire getStg() {
		return stg;
	}

	public void setStg(Stagiaire stg) {
		this.stg = stg;
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", gauche=" + gauche + ", droit=" + droit + "]";
	}

	public void parcoursInfixeList(RandomAccessFile raf, ArrayList<Stagiaire> stagiairesList) {
		// Noeud n = lireNoeud(raf);
		if (this.getGauche() != -1) {
			try {
				raf.seek(this.getGauche() * (TAILLE_MAX_NOEUD_OCTET));
				Noeud gauche = lireNoeud(raf);
				gauche.parcoursInfixeList(raf, stagiairesList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// n.parcoursInfixeList( raf, stagiairesList);
		}
		// System.out.println(this.getStagiaire());
		stagiairesList.add(this.getStagiaire()); // Ajouter le stagiaire à la liste
		if (this.doublon != -1) {
			try {
				raf.seek(this.doublon * TAILLE_MAX_NOEUD_OCTET);
				Noeud doublon = lireNoeud(raf);
				doublon.parcoursInfixeList(raf, stagiairesList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (this.getDroit() != -1) {
			try {
				raf.seek(this.getDroit() * (TAILLE_MAX_NOEUD_OCTET));
				Noeud droit = lireNoeud(raf);
				droit.parcoursInfixeList(raf, stagiairesList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// *************METHODE RECHERCHE**************************

	// Méthode pour rechercher un stagiaire dans l'arbre
	public Stagiaire rechercher(Stagiaire element, RandomAccessFile raf) {
		// On appel la méthode privée de recherche avec le nœud courant
		return rechercher(this, element, raf);
	}

	// Méthode pour rechercher un stagiaire dans l'arbre à partir d'un nœud
	// spécifique
	private Stagiaire rechercher(Noeud noeud, Stagiaire element, RandomAccessFile raf) {
		// Si le nœud est null, le stagiaire n'est pas dans l'arbre
		if (noeud == null) {
			return null;
		}
		// Compare les noms des stagiaires pour déterminer l'ordre dans l'arbre
		int comparaison = element.getNom().compareTo(noeud.getStagiaire().getNom());

		// Si le nom est inférieur à celui du nœud, on va dans le sous-arbre gauche
		if (comparaison < 0) {
			if (noeud.gauche != -1) {
				try {
					// On accède au nœud gauche dans le fichier
					raf.seek(noeud.gauche * TAILLE_MAX_NOEUD_OCTET);
					Noeud gauche = lireNoeud(raf);
					// Recherche récursive dans le sous-arbre gauche
					return rechercher(gauche, element, raf);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Si le nom est supérieur à celui du nœud, on va dans le sous-arbre droit
		} else if (comparaison > 0) {
			if (noeud.droit != -1) {
				try {
					// On accède au nœud droit dans le fichier
					raf.seek(noeud.droit * TAILLE_MAX_NOEUD_OCTET);
					Noeud droit = lireNoeud(raf);
					// Recherche récursive dans le sous-arbre droit
					return rechercher(droit, element, raf);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// Si les noms sont égaux, le stagiaire a été trouvé
		else {
			return noeud.getStagiaire();
		}

		// Si aucune condition précédente n'est remplie, le stagiaire est pas la, mais
		// ou est il ?
		return null;
	}

	// *************METHODE SUPPRIMER**************************


//
//	public Noeud supprimer(Stagiaire stagiaire, BinaryTree arbre) {
//		int comparaisonNom = this.stagiaire.getNom().compareTo(stagiaire.getNom());
//		int comparaison;
//
//		// AJOUTER METHODE RECHERCHE !!
//		
//		// si le noeud à supprimer c'est une feuille, on le supprime en donnant une
//		// valeur null
//
//		if (comparaisonNom == 0 && this.gauche == -1 && this.droit == -1 && this.doublon == -1) {
//			stagiaire = null;
//		}
//	}
//
//	// si le noeud à supprimer a un fils, on l'écrasse avec le suivant
//
//	public BinaryTree supprimerFils(Stagiaire stagiaire, BinaryTree arbre) {
//		int comparaisonNom = this.stagiaire.getNom().compareTo(stagiaire.getNom());
//		if (comparaisonNom == 0 && this.gauche != -1 || this.droit != -1 && this.doublon == -1) {
//			stagiaire = this.stagiaire;
//		}
//		return arbre;
//	}
//
//	// si le noeud à supprimer a un doublon
//
//	public Noeud supprimerDoublons(Stagiaire stagiaire, BinaryTree arbre) {
//	 if(comparaisonNom == 0 && this.gauche==-1&&this.droit==-1&&this.doublon!=-1){
//		 stagiaire = this.doublon();
//		 
//		 
		 // METHODE SUPPRIMER FRED

		 public Noeud supprimer(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
		        int comparaisonNom = this.stagiaire.getNom().compareTo(stagiaire.getNom());
		        int comparaison = 0;

		        if (comparaisonNom == 0) {
		            stagiaire = null;
		        } else if (comparaisonNom < 0) {
		            comparaison = -1;
		        } else {
		            comparaison = 1;
		        }

		        if (comparaison > 0) {
		            supprimerGauche(stagiaire, raf);
		        } else if (comparaison < 0) {
		            supprimerDroit(stagiaire, raf);
		        } else {
		            supprimerNoeud(raf);
		        }
		        return this;
		    }


	private void supprimerGauche(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
		if (gauche != -1) {
			raf.seek(gauche * TAILLE_MAX_NOEUD_OCTET);
			Noeud filsGauche = lireNoeud(raf);
			filsGauche = filsGauche.supprimer(stagiaire, raf);
			modifGauche(filsGauche.getGauche());
		}
	}

	private void supprimerDroit(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
		if (droit != -1) {
			raf.seek(droit * TAILLE_MAX_NOEUD_OCTET);
			Noeud filsDroit = lireNoeud(raf);
			filsDroit = filsDroit.supprimer(stagiaire, raf);
			modifDroit(filsDroit.getDroit());
		}
	}

	private void supprimerNoeud(RandomAccessFile raf) throws IOException {
		if (doublon != -1) {
			raf.seek(doublon * TAILLE_MAX_NOEUD_OCTET);
			Noeud doublonNoeud = lireNoeud(raf);
			this.stagiaire = doublonNoeud.getStagiaire();
			this.doublon = doublonNoeud.doublon;
		} else {
			if (gauche == -1) {
				this.stagiaire = droit != -1 ? lireNoeud(raf).getStagiaire() : null;
			} else if (droit == -1) {
				this.stagiaire = gauche != -1 ? lireNoeud(raf).getStagiaire() : null;
			} else {
				Noeud minFilsDroit = lireNoeud(raf);
				this.stagiaire = minFilsDroit.getStagiaire();
				raf.seek(droit * TAILLE_MAX_NOEUD_OCTET);
				Noeud filsDroit = lireNoeud(raf);
				filsDroit = filsDroit.supprimer(minFilsDroit.getStagiaire(), raf);
				modifDroit(filsDroit.getDroit());
			}
		}
	}
}
