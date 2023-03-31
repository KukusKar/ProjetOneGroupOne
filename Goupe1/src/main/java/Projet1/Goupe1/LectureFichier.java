package Projet1.Goupe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectureFichier {
	
	

	public static void main(String[] args) {
		
		 ArrayList<Stagiaire> lesStagiaires = new ArrayList<>(); // observable list
		
		//FileReader = objet pour lire
		
		try {
			FileReader fr = new FileReader("D:/Programming/ISIKA/Projet_1/Git/Goupe1/src/main/java/Projet1/Goupe1/stagiaires.don");
			BufferedReader br = new BufferedReader(fr);
			

			
				String contenu = "";
			
			while(br.ready()) {
				//contenu += br.readLine();
				
				//if (contenu.contains("*") ) {
					Stagiaire stagiaire = new Stagiaire(br.readLine(), br.readLine(), br.readLine(), br.readLine(), Integer.parseInt(br.readLine()));
					;
					br.readLine();
					lesStagiaires.add(stagiaire);
			//	System.out.println(stagiaire + "\n");
			//	}

			
			}
			
			for(Stagiaire stagiaire : lesStagiaires) {
				System.out.println(stagiaire);
			}
			
			System.out.println(contenu);
	
			fr.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
