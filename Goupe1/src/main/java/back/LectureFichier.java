package back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LectureFichier {

	public static void main(String[] args) {

		ArrayList<Stagiaire> lesStagiaires = new ArrayList<>();

		ArbreBinaireR arbre = new ArbreBinaireR();

		try {
			FileReader fr = new FileReader("src/main/java/back/TEST.DON");
			BufferedReader br = new BufferedReader(fr);

			// String contenu = "";

			while (br.ready()) {

				Stagiaire stagiaire = new Stagiaire(br.readLine(), br.readLine(), br.readLine(), br.readLine(),
						br.readLine());

				br.readLine();
				lesStagiaires.add(stagiaire);

			}

			for (Stagiaire stagiaire : lesStagiaires) {
				arbre.insertNoeud(stagiaire);
				//System.out.println(stagiaire);
			}

			int nbStagiaireFichiers = (int) (arbre.getRaf().length() / Noeud.TAILLE_MAX_NOEUD_OCTET);
			 arbre.getRaf().seek(0);
			for (int j = 0; j < nbStagiaireFichiers; j++) {
				arbre.getRaf().seek(j * Noeud.TAILLE_MAX_NOEUD_OCTET);
				Noeud courant = arbre.getRacine().lireNoeud(arbre.getRaf());
				//System.out.println(courant);
				System.out.println(arbre.parcoursInfixe(nbStagiaireFichiers, arbre.getRaf());
			}

			fr.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
