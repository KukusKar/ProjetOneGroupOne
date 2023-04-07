module fr.cda24.ISIKA.Projet1.Annuaire {
    requires javafx.controls;
	requires javafx.graphics;
	requires java.mail;
	requires javafx.base;
	requires itextpdf;
	
    opens fr.cda24.ISIKA.Projet1.model to javafx.base, javafx.graphics;

    exports fr.cda24.ISIKA.Projet1.Annuaire;
}
