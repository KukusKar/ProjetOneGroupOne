package Projet1.Goupe1;

public class Noeud {
	
    // L'élément du noeud
    private Noeud valeur;

    // Référence vers le fils gauche  
    private Noeud gauche;

    // Référence vers le fils droit
    private Noeud droit;

    public Noeud() {
        this.valeur = valeur;
        this.gauche = gauche;
        this.droit = droit;
    }

    Noeud valeur() {
        return valeur;
    }
    Noeud droit() {
        return droit;
    }
    Noeud gauche() {
        return gauche;
    }

    void modifGauche(Noeud noeud) {
        gauche = noeud;
    }
    void modifDroit(Noeud noeud) {
        droit = noeud;
    }

    void modifValeur(Noeud valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Noeud [");
        builder.append(valeur);
        builder.append(", gauche=");
        builder.append( (gauche != null ? gauche.valeur : "") );
        builder.append(", droit=");
        builder.append( (droit != null ? droit.valeur : "") );
        builder.append("]");
        return builder.toString();
    }}