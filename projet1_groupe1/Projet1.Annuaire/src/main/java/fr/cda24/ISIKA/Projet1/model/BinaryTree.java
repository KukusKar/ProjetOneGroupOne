package fr.cda24.ISIKA.Projet1.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe représentant un arbre binaire de recherche générique avec des éléments qui implémentent Comparable
 * @param <E> type des éléments de l'arbre binaire qui implémentent Comparable
 */
public class BinaryTree<E extends Comparable<E>> {

	private Noeud racine;
	private RandomAccessFile raf;
	private List<Stagiaire> stagiaires; // variable de classe pour stocker les données lues depuis le fichier binaire


	/**
     * Initialise un nouvel arbre binaire avec une racine nulle.
     * Charge les données des stagiaires à partir du fichier binaire "src/main/java/fichier/stagiaires.bin".
     * Si le fichier est vide, il est initialisé avec les données du fichier "src/main/java/annexe/STAGIAIRES.DON".
     */
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

	/**
     * Effectue un parcours infixe de l'arbre binaire et retourne une liste des stagiaires triée.
     *
     * @return une liste des stagiaires triée par ordre croissant
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier binaire
     */
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

	/**
     * Renvoie la liste des stagiaires stockés dans l'arbre binaire.
     *
     * @return la liste des stagiaires stockés dans l'arbre binaire
     */
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}
	/**
     * Définit la liste des stagiaires lus depuis le fichier binaire.
     *
     * @param stagiaires la liste des stagiaires lus depuis le fichier binaire.
     */
	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	/**
     * Insère un nouveau noeud dans l'arbre binaire contenant le stagiaire donné en paramètre.
     *
     * @param stagiaire le stagiaire à insérer dans l'arbre binaire.
     */
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
	/**
     * Retourne le flux d'accès aléatoire associé au fichier binaire contenant les données des stagiaires.
     *
     * @return le flux d'accès aléatoire associé au fichier binaire contenant les données des stagiaires.
     */
	public RandomAccessFile getRaf() {
		return raf;
	}

	/**
     * Définit le flux d'accès aléatoire associé au fichier binaire contenant les données des stagiaires.
     *
     * @param raf le flux d'accès aléatoire associé au fichier binaire contenant les données des stagiaires.
     */
	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	/**
     * Renvoie la racine de l'arbre binaire.
     *
     * @return la racine de l'arbre binaire
     */
	public Noeud getRacine() {
		return racine;
	}
	/**
	 * 
	 *	Renvoie la racine de l'arbre binaire.	
	 *
	 *  @param racine de l'arbre binaire
	 */
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

}


