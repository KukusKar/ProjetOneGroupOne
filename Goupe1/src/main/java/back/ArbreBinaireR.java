package back;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreBinaireR {
	
	
	private Noeud racine;
	private RandomAccessFile raf;

	public Noeud getRacine() {
		return racine;
	}
		
	public ArbreBinaireR() {
		this.racine = new Noeud(null);
		try {
			this.raf = new RandomAccessFile("src/main/java/fichier/stagiaires.bin", "rw");
			

				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	// méthod insert pour ajouter des noeuds à l'arbre
	public void insertNoeud(Stagiaire stagiaire) {

		try {
		if(raf.length() == 0) {
			System.out.println("fichier vide");
			// on insert le stagiaire
			racine = new Noeud(stagiaire);
			//tu te mets dans le fichier binaire
			//tu écris le stagiaire comme premier noeud -> devient la racine
			
				raf.writeChars(racine.getNomLong());
				raf.writeChars(racine.getPrenomLong());
				raf.writeChars(racine.getDeptLong());
				raf.writeChars(racine.getCoursLong());
				raf.writeChars(racine.getAnneeLong());
				//entier fils gauche
				raf.writeInt(-1);
				//entier fils droit
				raf.writeInt(-1);
		}

		else {
			System.out.println("ajout noeud ");
			 // lire le premier noeud du fichier binaire et la stocker dans la racine
			raf.seek(0);

			racine = racine.lireNoeud(raf); 
			//System.out.println(racine);// new Noeud(new Stagiaire(nom, prenom, departement, cours, anneeForm), raf.readInt(), raf.readInt());
			racine.insert(stagiaire, raf);

		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// METHODE AFFICHAGE INFIXE
		
		public Noeud parcoursInfixe(int i, RandomAccessFile raf) {
			Noeud n = lireNoeud(raf);
			if (n.getGauche() != -1) {
				System.out.println(n);
				parcoursInfixe(n.getGauche(), raf);
				n.ecrireNoeud(n, raf);
				
			}
			if (n.getDroit() != -1) {
				System.out.println(n);
				parcoursInfixe(n.getDroit(), raf);
				n.ecrireNoeud(n, raf);
			}
			return n;
			
		}
		
		
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	
}
