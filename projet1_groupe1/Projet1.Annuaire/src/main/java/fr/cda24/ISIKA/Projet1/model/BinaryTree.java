package fr.cda24.ISIKA.Projet1.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class BinaryTree<E extends Comparable<E>> {

	private Noeud racine;
	private RandomAccessFile raf;
	private List<Stagiaire> stagiaires; // variable de classe pour stocker les données lues depuis le fichier binaire

	public Noeud getRacine() {
		return racine;
	}

	public BinaryTree() {
		this.racine = new Noeud(null);
		try {
			this.raf = new RandomAccessFile("src/main/java/fichier/stagiaires.bin", "rw");

			if (raf.length() == 0) {
				ArrayList<Stagiaire> lesStagiaires = new ArrayList<>();
				// Lecture du fichier .DON et création des objets Stagiaire
				String donFilePath = "src/main/java/annexe/STAGIAIRES.DON";
				// Création d'un FileReader et d'un BufferedReader pour lire le fichier
				FileReader fr = new FileReader(donFilePath);
				BufferedReader br = new BufferedReader(fr);

				// Lecture des lignes du fichier et création des objets Stagiaire
				while (br.ready()) {

					Stagiaire stagiaire = new Stagiaire(br.readLine(), br.readLine(), br.readLine(), br.readLine(),
							br.readLine());

					br.readLine();
					lesStagiaires.add(stagiaire);

				}
				// Insertion des objets Stagiaire dans l'arbre binaire
        			for(Stagiaire stagiaire : lesStagiaires) {
        				this.insertNoeud(stagiaire);
        				//System.out.println(stagiaire);
        			}


				// Fermeture des flux de lecture
				fr.close();
				br.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Stagiaire> parcoursInfixe() throws IOException {
		ArrayList<Stagiaire> liste = new ArrayList<>();
		if (raf.length() == 0) {
			System.out.println("arbre vide");
		} else {
			raf.seek(0);
			racine = racine.lireNoeud(raf);
			racine.parcoursInfixeList(raf, liste);
		}
		return liste;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	// méthod insert pour ajouter des noeuds à l'arbre
	public void insertNoeud(Stagiaire stagiaire) {

		try {
			if (raf.length() == 0) {
				System.out.println("fichier vide");
				// on insert le stagiaire
				racine = new Noeud(stagiaire);
				racine.ecrireNoeud(racine, raf);
				// tu te mets dans le fichier binaire
				// tu écris le stagiaire comme premier noeud -> devient la racine


			} else {
				//System.out.println("ajout noeud ");
				// lire le premier noeud du fichier binaire et la stocker dans la racine
				raf.seek(0);

				racine = racine.lireNoeud(raf);
				racine.insert(stagiaire, raf);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// On initialise la méthode en prennant la racine et appelant la méthode
	// recursive
	// Méthode d'initialisation pour le parcours infixe

}

