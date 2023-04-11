package fr.cda24.ISIKA.Projet1.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
* Classe Noeud représentant un noeud de l'arbre binaire
* 
* Cette classe permet de représenter un noeud de l'arbre binaire utilisé pour stocker les données des stagiaires.
* 
* @Attributs :
* - stagiaire : objet Stagiaire contenant les données du stagiaire stockées dans le noeud
* - gauche : indice du fils gauche dans le fichier binaire
* - droit : indice du fils droit dans le fichier binaire
* - doublon : indice du noeud suivant avec le même nom (pour les doublons) dans le fichier binaire
* - stg : objet Stagiaire (utilisé dans la méthode toString pour l'affichage)
* - index : index du noeud dans le fichier binaire
* 
* Constantes :
* - TAILLE_MAX_LIGNE : taille maximale d'une ligne (30 caractères)
* - TAILLE_MAX_STAGIAIRE : taille maximale d'un objet Stagiaire (30*5*2 = 300 caractères)
* - TAILLE_MAX_NOEUD_OCTET : taille maximale d'un noeud en octets (30*5*2 + 3*4 = 348 octets)
*/
public class Noeud {
	
	
	public final static int TAILLE_MAX_LIGNE = 30;
	public final static int TAILLE_MAX_STAGIAIRE = 30*5 * 2;
	public final static int TAILLE_MAX_NOEUD_OCTET = 30 * 5 * 2 + 3 * 4;

	private Stagiaire stagiaire;
	private int gauche;
	private int droit;
	private int doublon;
	private Stagiaire stg;
    private int index;


    /**
     * Constructeur de la classe Noeud permettant d'initialiser un objet Noeud avec un Stagiaire
     * @param stagiaire le Stagiaire associé au Noeud
     */
	public Noeud(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		this.gauche = -1;
		this.droit = -1;
		this.doublon = -1;
        this.index = -1;
	}
	/**
	 * Accesseur de l'attribut droit du Noeud
	 * @return l'entier correspondant à l'index du Noeud fils droit, -1 si il n'a pas de fils droit
	 */
	int droit() {
		return droit;
	}
	/**
	 * Accesseur de l'attribut gauche du Noeud
	 * @return l'entier correspondant à l'index du Noeud fils gauche, -1 si il n'a pas de fils gauche
	 */
	int gauche() {
		return gauche;
	}
	/**
	 * Modifie l'indice du fils gauche de ce noeud.
	 *
	 * @param noeud L'indice du nouveau fils gauche.
	 */
	public void modifGauche(int noeud) {
		gauche = noeud;
	}
	/**
	 * Modifie l'indice du fils droit de ce noeud.
	 *
	 * @param noeud L'indice du nouveau fils droit.
	 */
	public void modifDroit(int noeud) {
		droit = noeud;
	}
	/**
	 * Modifie le stagiaire stocké dans ce noeud.
	 *
	 * @param stagiaire Le nouveau stagiaire.
	 */
	void modifStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	/**
	 * Retourne le stagiaire stocké dans ce noeud.
	 *
	 * @return Le stagiaire stocké dans ce noeud.
	 */
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	/**
	 * Modifie le stagiaire stocké dans ce noeud.
	 *
	 * @param stagiaire Le nouveau stagiaire.
	 */
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	
	/**
	 * Retourne l'indice de ce noeud.
	 *
	 * @return L'indice de ce noeud.
	 */
    public int getIndex() {
        return index;
    }
    /**
     * Modifie l'indice de ce noeud.
     *
     * @param index Le nouvel indice.
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * Retourne le nom du stagiaire stocké dans ce noeud, avec des espaces pour remplir jusqu'à la taille maximale.
     *
     * @return Le nom du stagiaire, avec des espaces pour remplir jusqu'à la taille maximale.
     */
	public String getNomLong() {
		String nomLong = this.stagiaire.getNom();
		for (int i = this.stagiaire.nom.length(); i < TAILLE_MAX_LIGNE; i++) {
			nomLong += " ";
		}
		return nomLong;
	}
	/**
	 * Retourne le prénom du stagiaire stocké dans ce noeud, avec des espaces pour remplir jusqu'à la taille maximale.
	 *
	 * @return Le prénom du stagiaire, avec des espaces pour remplir jusqu'à la taille maximale.
	 */
	public String getPrenomLong() {
		String prenomLong = this.stagiaire.getPrenom();
		for (int i = this.stagiaire.prenom.length(); i < TAILLE_MAX_LIGNE; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}
	/**
	 * Retourne la chaîne de caractères du département de stagiaire formatée pour occuper TAILLE_MAX_LIGNE octets.
	 * Si la chaîne est plus petite que TAILLE_MAX_LIGNE, elle est remplie avec des espaces pour atteindre la taille requise.
	 *
	 * @return La chaîne de caractères formatée.
	 */
	public String getDeptLong() {
		String deptLong = this.stagiaire.getDepartement();
		for (int i = this.stagiaire.departement.length(); i < TAILLE_MAX_LIGNE; i++) {
			deptLong += " ";
		}
		return deptLong;
	}
	/**
	 * Retourne la chaîne de caractères de la promotion de stagiaire formatée pour occuper TAILLE_MAX_LIGNE octets.
	 * Si la chaîne est plus petite que TAILLE_MAX_LIGNE, elle est remplie avec des espaces pour atteindre la taille requise.
	 *
	 * @return La chaîne de caractères formatée.
	 */
	public String getPromLong() {
		String promLong = this.stagiaire.getProm();
		for (int i = this.stagiaire.prom.length(); i < TAILLE_MAX_LIGNE; i++) {
			promLong += " ";
		}
		return promLong;
	}
	/**
	 * Retourne la chaîne de caractères de l'année de stagiaire formatée pour occuper TAILLE_MAX_LIGNE octets.
	 * Si la chaîne est plus petite que TAILLE_MAX_LIGNE, elle est remplie avec des espaces pour atteindre la taille requise.
	 *
	 * @return La chaîne de caractères formatée.
	 */
	public String getAnneeLong() {
		String anneeLong = this.stagiaire.getAnneeForm();
		for (int i = this.stagiaire.anneeForm.length(); i < TAILLE_MAX_LIGNE; i++) {
			anneeLong += " ";
		}
		return anneeLong;
	}
	/**
	 * Écrit l'index du nœud du fils gauche dans le fichier binaire du nœud parent.
	 *
	 * @param indexParent L'index du nœud parent dans le fichier binaire.
	 * @param indexCourant L'index du nœud courant dans le fichier binaire.
	 * @param raf Le fichier binaire de l'arbre.
	 */
	public void ecrireGauche(int indexParent ,int indexCourant,  RandomAccessFile raf) {
	    try {
	        raf.seek(indexParent * TAILLE_MAX_NOEUD_OCTET + Noeud.TAILLE_MAX_STAGIAIRE);
	        raf.writeInt(indexCourant);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Écrit l'index du nœud du fils droit dans le fichier binaire du nœud parent.
	 *
	 * @param indexParent L'index du nœud parent dans le fichier binaire.
	 * @param indexCourant L'index du nœud courant dans le fichier binaire.
	 * @param raf Le fichier binaire de l'arbre.
	 */
	public void ecrireDroit(int indexParent ,int indexCourant, RandomAccessFile raf) {
	    try {
	        raf.seek(indexParent * TAILLE_MAX_NOEUD_OCTET + TAILLE_MAX_STAGIAIRE + 4);
	        raf.writeInt(indexCourant);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

//			

//METHODE RECURSIVE
	/**
	 * 
	 * Constructeur de la classe Noeud.
	 * 
	 * @param stg
	 * @param gauche
	 * @param droit
	 * @param doublon
	 */
	public Noeud(Stagiaire stg, int gauche, int droit, int doublon) {
		//this.stg = stg;
		this.stagiaire = stg;
		this.gauche = gauche;
		this.droit = droit;
		this.doublon = doublon;
	}
	/**
	 * Insère un nouveau stagiaire dans l'arbre en respectant l'ordre alphabétique des noms de famille.
	 * Si un stagiaire avec le même nom de famille existe déjà, le nouveau stagiaire est inséré dans le sous-arbre de doublons.
	 * 
	 * @param stagiaire le stagiaire à insérer
	 * @param raf le fichier binaire d'accès aléatoire pour l'arbre
	 */
	public void insert(Stagiaire stagiaire, RandomAccessFile raf) {
			//System.out.println("this -> " + this.getStagiaire());
			//System.out.println("stagiaire -> " + stagiaire);
												
			int compare = this.getStagiaire().getNom().compareTo(stagiaire.getNom());
			try {
				
				if (compare > 0) {
					
					if (this.gauche() == -1) {
						//tu as trouvé l'emplacement
						this.gauche = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
						raf.seek(raf.getFilePointer() - 12);
						raf.writeInt(this.gauche);//on donne la valeur au fils gauche
						raf.seek(raf.length());
						//ecrire le noeud stagiaire 
						Noeud stag = new Noeud(stagiaire);
						ecrireNoeud(stag, raf); //methode à créer
												
						} else {
							raf.seek(this.gauche * TAILLE_MAX_NOEUD_OCTET );
							//System.out.println("FG -> " + this.gauche);
							Noeud filsGauche = lireNoeud(raf);
							//System.out.println("Fils gauche " + filsGauche);
							filsGauche.insert(stagiaire, raf);
						}
				}
				
				else if (compare < 0) {
					if (this.droit == -1) {
						this.droit = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
						raf.seek(raf.getFilePointer() - 8);
						raf.writeInt(this.droit);
						raf.seek(raf.length());
						//ecrire le noeud stagiaire 
						Noeud stag = new Noeud(stagiaire);
						ecrireNoeud(stag, raf); 
												
						} else {
							raf.seek(this.droit * TAILLE_MAX_NOEUD_OCTET );
							//System.out.println("FD -> " + droit);
							Noeud filsDroit = lireNoeud(raf);
							filsDroit.insert(stagiaire, raf);
						}
				}
				else if (compare == 0) {
					if (this.doublon == -1) {
						this.doublon = (int) (raf.length() / TAILLE_MAX_NOEUD_OCTET);// index du noeud qu'on ajoute
						raf.seek(raf.getFilePointer() - 4);
						raf.writeInt(this.doublon);
						raf.seek(raf.length());
						//ecrire le noeud stagiaire 
						Noeud stag = new Noeud(stagiaire);
						ecrireNoeud(stag, raf); 
												
						} else {
							raf.seek(this.doublon * TAILLE_MAX_NOEUD_OCTET );
							//System.out.println("FD -> " + droit);
							Noeud filsDoublon = lireNoeud(raf);
							filsDoublon.insert(stagiaire, raf);
						}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
				
	
	/**
	 * Écrit un nœud (sans les informations des fils et du doublon) dans le fichier binaire
	 * @param stag le nœud à écrire
	 * @param raf le fichier binaire
	 * @return le nœud écrit
	 */
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
	/**
	 * Écrit un nœud complet (avec les informations des fils et du doublon) dans le fichier binaire
	 * @param stag le nœud à écrire
	 * @param raf le fichier binaire
	 */
	public void ecrireNoeudComplet(Noeud stag, RandomAccessFile raf) {
		try {
			raf.writeChars(stag.getNomLong());
			raf.writeChars(stag.getPrenomLong());
			raf.writeChars(stag.getDeptLong());
			raf.writeChars(stag.getPromLong());
			raf.writeChars(stag.getAnneeLong());
			// entier fils gauche
			raf.writeInt(stag.gauche);
			// entier fils droit
			raf.writeInt(stag.droit);
			raf.writeInt(stag.doublon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode pour lire un noeud à partir d'un fichier binaire.
	 * @param raf le fichier binaire à partir duquel lire le noeud.
	 * @return le noeud lu depuis le fichier binaire.
	 */
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
	    //Créer un nouveau noeud à partir des chaînes de caractères et entiers lus, et le renvoyer
		Noeud noeudLu =  new Noeud(new Stagiaire(nom.trim(), prenom.trim(), dpt.trim(), prom.trim(),annee.trim()), fg, fd, dblon);
		return noeudLu;

	}
	/**
     * Nettoie les "*" à la suite de chaque chaîne de caractères pour l'affichage
     * @param chaine la chaîne de caractères à nettoyer
     * @return la chaîne de caractères nettoyée
     */
	public String cleanChain(String chaine) {
	    return chaine.replace("*", "");
	}
	/**
     * Retourne l'index du noeud fils gauche
     * @return l'index du noeud fils gauche
     */
	public int getGauche() {
		return gauche;
	}
	/**
     * Modifie l'index du noeud fils gauche
     * @param gauche le nouvel index du noeud fils gauche
     */
	public void setGauche(int gauche) {
		this.gauche = gauche;
	}
	/**
     * Retourne l'index du noeud fils droit
     * @return l'index du noeud fils droit
     */
	public int getDroit() {
		return droit;
	}
	/**
     * Modifie l'index du noeud fils droit
     * @param droit le nouvel index du noeud fils droit
     */
	public void setDroit(int droit) {
		this.droit = droit;
	}
	/**
     * Retourne le stagiaire contenu dans le noeud
     * @return le stagiaire contenu dans le noeud
     */
	public Stagiaire getStg() {
		return stg;
	}
	/**
     * Modifie le stagiaire contenu dans le noeud
     * @param stg le nouveau stagiaire contenu dans le noeud
     */
	public void setStg(Stagiaire stg) {
		this.stg = stg;
	}
	/**
     * Retourne une chaîne de caractères représentant le noeud
     * @return une chaîne de caractères représentant le noeud
     */
	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", gauche=" + gauche + ", droit=" + droit + "]";
	}

	/**
	 * Parcours l'arbre dans l'ordre infixe et ajoute chaque stagiaire dans une liste
	 *
	 * @param raf : le fichier binaire à parcourir
	 * @param stagiairesList : la liste dans laquelle les stagiaires seront ajoutés
	 */
	public void parcoursInfixeList( RandomAccessFile raf, ArrayList<Stagiaire> stagiairesList) {
	   // Noeud n = lireNoeud(raf);
	    if (this.getGauche() != -1) {
	        try {
	            raf.seek(this.getGauche() * (TAILLE_MAX_NOEUD_OCTET));
	            Noeud gauche = lireNoeud(raf);
	            gauche.parcoursInfixeList(raf, stagiairesList);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //n.parcoursInfixeList( raf, stagiairesList);
	    }
	    //System.out.println(this.getStagiaire());
	    stagiairesList.add(this.getStagiaire()); // Ajouter le stagiaire à la liste
	    if(this.doublon != -1) {
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
	            raf.seek(this.getDroit()* (TAILLE_MAX_NOEUD_OCTET));
	            Noeud droit = lireNoeud(raf);
	            droit.parcoursInfixeList(raf, stagiairesList);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	/**
	 * Recherche un stagiaire dans l'arbre à partir de la racine.
	 *
	 * @param element le stagiaire à rechercher
	 * @param raf le fichier binaire contenant l'arbre
	 * @return le stagiaire trouvé, ou null s'il n'est pas présent dans l'arbre
	 */
	public Stagiaire rechercher(Stagiaire element, RandomAccessFile raf) {
	    //On appel la méthode privée de recherche avec le nœud courant
        return rechercher(this, element, raf);
    }
	/**
	 * Recherche un stagiaire dans l'arbre à partir d'un nœud spécifique.
	 *
	 * @param noeud le nœud à partir duquel effectuer la recherche
	 * @param element le stagiaire à rechercher
	 * @param raf le fichier binaire contenant l'arbre
	 * @return le stagiaire trouvé, ou null s'il n'est pas présent dans l'arbre
	 */
	private Stagiaire rechercher(Noeud noeud, Stagiaire element, RandomAccessFile raf) {
        //Si le nœud est null, le stagiaire n'est pas dans l'arbre
        if (noeud == null) {
            return null;
        }
        //Compare les noms des stagiaires pour déterminer l'ordre dans l'arbre
        int comparaison = element.getNom().compareTo(noeud.getStagiaire().getNom());

        //Si le nom est inférieur à celui du nœud, on va dans le sous-arbre gauche
        if (comparaison < 0) {
            if (noeud.gauche != -1) {
                try {
                    //On accède au nœud gauche dans le fichier
                    raf.seek(noeud.gauche * TAILLE_MAX_NOEUD_OCTET);
                    Noeud gauche = lireNoeud(raf);
                    //Recherche récursive dans le sous-arbre gauche
                    return rechercher(gauche, element, raf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         //Si le nom est supérieur à celui du nœud, on va dans le sous-arbre droit
        } else if (comparaison > 0) {
            if (noeud.droit != -1) {
                try {
                    //On accède au nœud droit dans le fichier
                    raf.seek(noeud.droit * TAILLE_MAX_NOEUD_OCTET);
                    Noeud droit = lireNoeud(raf);
                    //Recherche récursive dans le sous-arbre droit
                    return rechercher(droit, element, raf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Si les noms sont égaux, le stagiaire a été trouvé
        else {
            return noeud.getStagiaire();
        }

        //Si aucune condition précédente n'est remplie, le stagiaire est pas la, mais ou est il ?
        return null;
    }
    

	/**
     * Supprime un stagiaire de l'arbre à partir d'un objet Stagiaire et retourne l'arbre mis à jour.
     * 
     * @param stagiaire l'objet Stagiaire à supprimer
     * @param raf le fichier binaire dans lequel sont stockés les noeuds
     * @return l'arbre binaire mis à jour après suppression du stagiaire
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture ou de l'écriture dans le fichier binaire
     */
    public Noeud supprimer(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
        if (stagiaire == null) {
            throw new NullPointerException();
        }
        if (exists(stagiaire, raf)) {
            return supprimerPrv(this, stagiaire, raf, doublon, false);
        } else {
            System.out.println("Le stagiaire n'existe pas.");
            return this;
        }
    }

    /**
     * Méthode privée pour supprimer un stagiaire à partir d'un noeud courant.
     * 
     * @param courant le noeud courant
     * @param stagiaire l'objet Stagiaire à supprimer
     * @param raf le fichier binaire dans lequel sont stockés les noeuds
     * @param parentIndex l'index du noeud parent
     * @param leftChild true si le noeud à supprimer est un fils gauche, false sinon
     * @return le noeud courant mis à jour après suppression du stagiaire
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture ou de l'écriture dans le fichier binaire
     */
    private Noeud supprimerPrv(Noeud courant, Stagiaire stagiaire, RandomAccessFile raf, int parentIndex, boolean leftChild) throws IOException {
        // Si le noeud courant est null, retourne null
        if (courant == null) {
            return null;
        }

        // Compare les noms des stagiaires
        int comparaison = courant.stagiaire.getNom().compareTo(stagiaire.getNom());
        courant.index =(int) (raf.getFilePointer() / Noeud.TAILLE_MAX_NOEUD_OCTET) -1;
        // Si le nom du stagiaire courant est plus grand, parcourt le sous-arbre gauche
        if (comparaison > 0) {
            if (courant.gauche != -1) {
                raf.seek(courant.gauche * TAILLE_MAX_NOEUD_OCTET);
                Noeud filsGauche = lireNoeud(raf);
                filsGauche = supprimerPrv(filsGauche, stagiaire, raf, courant.index, true);
               // courant.gauche = (filsGauche == null) ? -1 : filsGauche.index;
               // ecrireNoeud(courant, raf);
            }
        // Si le nom du stagiaire courant est plus petit, parcourt le sous-arbre droit
        } else if (comparaison < 0) {
            if (courant.droit != -1) {
                raf.seek(courant.droit * TAILLE_MAX_NOEUD_OCTET);
                Noeud filsDroit = lireNoeud(raf);
                filsDroit = supprimerPrv(filsDroit, stagiaire, raf, courant.index, false);
               // courant.droit = (filsDroit == null) ? -1 : filsDroit.index;
               // ecrireNoeud(courant, raf);
            }
        // Si les noms sont identiques, supprime le noeud
        } else {
            // S'il y a un doublon, remplace le stagiaire courant par le doublon
            if (courant.doublon != -1) {
                raf.seek(courant.doublon * TAILLE_MAX_NOEUD_OCTET);
                Noeud doublonNoeud = lireNoeud(raf);
                courant.stagiaire = doublonNoeud.getStagiaire();
                courant.doublon = doublonNoeud.doublon;
                ecrireNoeudComplet(courant, raf);
            } else if (courant.gauche == -1) {
                if (courant.droit != -1) {
                    //raf.seek(courant.droit * TAILLE_MAX_NOEUD_OCTET);
                    //Noeud filsDroit = lireNoeud(raf);
                    //courant = filsDroit;
                   // courant.index = parentIndex;
                    if (leftChild) {
                        ecrireGauche(parentIndex, courant.droit, raf);
                    } else {
                        ecrireDroit(parentIndex, courant.droit, raf);
                    }
                } else {
                    courant = null;
                    if (leftChild) {
                        ecrireGauche(parentIndex, -1, raf);
                    } else {
                        ecrireDroit(parentIndex, -1, raf);
                    }
                }
            } else if (courant.droit == -1) {
                if (courant.gauche != -1) {
                    //raf.seek(courant.gauche * TAILLE_MAX_NOEUD_OCTET);
//                    Noeud filsGauche = lireNoeud(raf);
//                    courant = filsGauche;
//                    courant.index = parentIndex;
                    if (leftChild) {
                        ecrireGauche(parentIndex, courant.gauche, raf);
                    } else {
                        ecrireDroit(parentIndex, courant.gauche, raf);
                    }
                } else {
                    courant = null;
                    if (leftChild) {
                        ecrireGauche(parentIndex, -1, raf);
                    } else {
                        ecrireDroit(parentIndex, -1, raf);
                    }
                }
            } else {
                raf.seek(courant.droit * TAILLE_MAX_NOEUD_OCTET);
                Noeud filsDroit = lireNoeud(raf);
                Noeud successeur = minValue(filsDroit, raf);
                courant.stagiaire =  successeur.stagiaire;
                courant.doublon = successeur.doublon;
                raf.seek(courant.index * TAILLE_MAX_NOEUD_OCTET);
                ecrireNoeudComplet(courant, raf);
                filsDroit = supprimerPrv(filsDroit, courant.stagiaire, raf, courant.index, false);
                //courant.droit = (filsDroit == null) ? -1 : filsDroit.index;
                //ecrireNoeud(courant, raf);
            }
            return courant;
            	
            }
		return courant;
    }
    /**
     * Retourne le noeud ayant la valeur minimale dans le sous-arbre enraciné à {@code courant}.
     * 
     * @param courant le noeud à partir duquel la recherche commence
     * @param raf le fichier binaire contenant les données des noeuds de l'arbre binaire
     * @return le noeud ayant la valeur minimale dans le sous-arbre enraciné à {@code courant}
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier binaire
     */
    private Noeud minValue(Noeud courant, RandomAccessFile raf) throws IOException {
        // Parcourt les noeuds de gauche jusqu'à ce qu'il n'y en ait plus
        while (courant.gauche != -1) {
            raf.seek(courant.gauche * TAILLE_MAX_NOEUD_OCTET);
            courant = lireNoeud(raf);
        }
        // Retourne le stagiaire avec la valeur minimale
        return courant;
    }
    /**
     * Vérifie si un {@link Stagiaire} est présent dans l'arbre binaire.
     * 
     * @param stagiaire le stagiaire à rechercher dans l'arbre binaire
     * @param raf le fichier binaire contenant les données des noeuds de l'arbre binaire
     * @return {@code true} si le {@link Stagiaire} est présent dans l'arbre binaire, {@code false} sinon
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier binaire
     */
    public boolean exists(Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
        return exists(this, stagiaire, raf);
    }
    /**
     * Vérifie si un {@link Stagiaire} est présent dans l'arbre binaire enraciné à {@code courant}.
     * 
     * @param courant le noeud à partir duquel la recherche commence
     * @param stagiaire le stagiaire à rechercher dans l'arbre binaire
     * @param raf le fichier binaire contenant les données des noeuds de l'arbre binaire
     * @return {@code true} si le {@link Stagiaire} est présent dans l'arbre binaire, {@code false} sinon
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier binaire
     */
    private boolean exists(Noeud courant, Stagiaire stagiaire, RandomAccessFile raf) throws IOException {
        if (courant == null) {
            return false;
        }

        int comparaison = courant.stagiaire.getNom().compareTo(stagiaire.getNom());

        if (comparaison > 0) {
            if (courant.gauche != -1) {
                raf.seek(courant.gauche * TAILLE_MAX_NOEUD_OCTET);
                Noeud filsGauche = lireNoeud(raf);
                return exists(filsGauche, stagiaire, raf);
            }
        } else if (comparaison < 0) {
            if (courant.droit != -1) {
                raf.seek(courant.droit * TAILLE_MAX_NOEUD_OCTET);
                Noeud filsDroit = lireNoeud(raf);
                return exists(filsDroit, stagiaire, raf);
            }
        } else {
            return true;
        }

        return false;
    }
}
	
	
	
	
	

	
	
	
	
	
