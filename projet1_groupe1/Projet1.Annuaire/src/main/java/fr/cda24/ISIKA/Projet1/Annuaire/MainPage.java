package fr.cda24.ISIKA.Projet1.Annuaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.cda24.ISIKA.Projet1.model.TableViewStagiaires;
import fr.cda24.ISIKA.Projet1.model.BinaryTree;
import fr.cda24.ISIKA.Projet1.model.Employee;
import fr.cda24.ISIKA.Projet1.model.Stagiaire;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainPage<T> extends BorderPane {
	
	//Création d'un boolean isAdmin
	private boolean isAdmin;
    private List<Employee> employeeList;
    private TableViewStagiaires tableViewStagiaires;
    private T originPage;
    private BinaryTree<?> arbre;

    
    // Ajouter une variable de session pour stocker les informations de connexion de l'utilisateur
    private static Employee loggedInEmployee;

    // Stocker la liste des employés dans la classe MainPage
    private List<Employee> originalEmployeeList;
	
//    	private Class<?> originPage;


	//On le passe en argument à l'instance MainPage
    public MainPage(String email, String password,T originPage, BinaryTree<?> arbre) {
    	
    	this.originalEmployeeList = Employee.getEmployees(); // récupérer la liste d'employés
        this.employeeList = new ArrayList<>(originalEmployeeList); // faire une copie pour la page
        this.arbre = arbre;


        
    	

        
        Employee connectedEmployee = Employee.getEmployeeByEmailAndPassword(email, password);
        if (connectedEmployee != null && Employee.validateEmailAndPassword(email, password)) {
            // Stocker les informations de connexion de l'utilisateur dans la variable de session
            loggedInEmployee = connectedEmployee;
            this.isAdmin = connectedEmployee.isAdmin();
        }

        
        
    	

        //Création VBox du borderPane gauche
        VBox vbLeftPane = new VBox();
        
        //Création VBox du borderPane droite
        VBox vbRightPane = new VBox();
        //Création du dégradé de lightskyblue à aliceblue puis à white pour un dégradé très doux et rafraichissant
        Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.ALICEBLUE), new Stop(1, Color.LIGHTSKYBLUE) };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        //Creation du backgroundfill qui contiendra le dégradé gradient
        BackgroundFill fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);

        //Creattion du backgroudn avec le fill
        Background background = new Background(fill);

        //On place le background dans le vbLeftPane
        vbLeftPane.setBackground(background);
        vbRightPane.setBackground(background);

        

        //Création du Label qui prendra le nom d'utilisateur de la personne connecté en précisant si c'est un admin
        
        String adminText = "";
        if (isAdmin) {
            adminText = " (Admin)";
        }
        String usernameText = "Bonjour ";
        if (loggedInEmployee != null) {
            usernameText += loggedInEmployee.getFirstName() + " " + loggedInEmployee.getLastName();
        }
        
        
        Label lblCurrentUser = new Label(usernameText + adminText);
        lblCurrentUser.setStyle("-fx-font-size:15");
        lblCurrentUser.setStyle(("-fx-font-family:Roboto"));
        
        
        //On créer les ombres qui apparaitrons sous les boutons
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.color(0, 0, 0, 0.2));
        
        
        //Création des boutons addEmployee, addStage et delete,
        Button btnAddEmployee = new Button("Ajouter un(e) employé(e)");
        Button btnListEmployee = new Button("Liste des employé(e)s");
        Button btnAddStage = new Button("Ajouter un stagiaire");
        Button btnDelete = new Button("Supprimer un stagiaire");
        Button btnRefreshTable = new Button("Actualiser");
        Button btnLogout = new Button("Déconnexion");
        Button btnPrint = new Button("Imprimer");
        
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher...");

//        searchField.setMargin(new Insets(20));

        
     // Ajout d un écouteur de texte au champ de recherche (searchField)
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appel de la méthode filterData avec la liste des stagiaires et la nouvelle valeur de recherche
            FilteredList<Stagiaire> filteredData = filterData(tableViewStagiaires.getObservableList(), newValue);
            //Mise à jour le TableView avec les données filtrées
            tableViewStagiaires.getTable().setItems(filteredData);
        });
        
        btnListEmployee.setOnAction(e -> {
            ListEmployee listEmployee = new ListEmployee(email, password, originPage, arbre);
            Scene listEmployeeScene = listEmployee.getMyScene();
            Stage stage = (Stage) btnListEmployee.getScene().getWindow();
            stage.setScene(listEmployeeScene);
        });
        
        
        btnAddEmployee.setOnAction(e -> {
            AddEmployee<T> addEmployee = new AddEmployee<>(email, password, originPage, arbre);
            Scene addEmployeeScene = addEmployee.getMyScene();
            Stage stage = (Stage) btnAddEmployee.getScene().getWindow();
            stage.setScene(addEmployeeScene);
        });



        
        btnAddStage.setOnAction(e -> {
            AddStagPage addStagPage = new AddStagPage(arbre);
            Scene addStageScene = addStagPage.getMyScene();
            Stage stage = (Stage) btnAddStage.getScene().getWindow();
            stage.setScene(addStageScene);
        });
        
        btnDelete.setOnAction(e -> {
        	DeleteStagPage deleteStagPage = new DeleteStagPage(arbre);
            Scene deleteStagPageScene = deleteStagPage.getMyScene();
            Stage stage = (Stage) btnDelete.getScene().getWindow();
            stage.setScene(deleteStagPageScene);
        });
        
        btnLogout.setOnAction(event -> {
            // Supprimer les informations de connexion de la variable de session
            loggedInEmployee = null;
            
            LoginPage loginPage = new LoginPage(arbre);
            Scene loginScene = loginPage.getMyScene();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(loginScene);
        });
        
        btnPrint.setOnAction(event -> {
            try {
            	// Créer un fileChooser pour sélectionner le dossier de destination
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Enregistrer le fichier PDF");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
                File selectedFile = fileChooser.showSaveDialog(getScene().getWindow());
                if (selectedFile != null) {
                // Créer un fichier PDF
                	// Créer un fichier PDF
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                    document.open();                
                // Créer une table pour le contenu du TableView
                PdfPTable table = new PdfPTable(tableViewStagiaires.getTable().getColumns().size());
                
                // Ajouter les en-têtes de colonne à la table
                for (TableColumn<Stagiaire, ?> column : tableViewStagiaires.getTable().getColumns()) {
                    String header = column.getText();
                    PdfPCell cell = new PdfPCell(new Phrase(header));
                    table.addCell(cell);
                }
                
                // Ajouter les lignes de données à la table
                for (Stagiaire stagiaire : tableViewStagiaires.getObservableList()) {
                    for (TableColumn<Stagiaire, ?> column : tableViewStagiaires.getTable().getColumns()) {
                        String cellValue = column.getCellData(stagiaire).toString();
                        PdfPCell cell = new PdfPCell(new Phrase(cellValue));
                        table.addCell(cell);
                    }
                }
                
                // Ajouter la table au document PDF
                document.add(table);
                
                document.close();
                System.out.println("Le fichier PDF a été généré avec succès !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
     
        
        //On stylise les boutons avec un fond et un border radius
        btnAddEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnListEmployee.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnAddStage.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnDelete.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnLogout.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnPrint.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnAddEmployee.setEffect(shadow);
        btnListEmployee.setEffect(shadow);
        btnAddStage.setEffect(shadow);
        btnDelete.setEffect(shadow);
        btnLogout.setEffect(shadow);
        btnPrint.setEffect(shadow);
        searchField.setStyle("-fx-background-radius: 15; -fx-border-radius: 15;");

        
        
        //Création du leftPane et insertion du vbLeftPane
        BorderPane leftPane = new BorderPane(vbLeftPane);

        
        Button btnRefresh = new Button("Actualiser");
        btnRefresh.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");
        btnRefresh.setEffect(shadow);
        
        btnRefresh.setOnAction(e -> {
            tableViewStagiaires.refreshTable();
            searchField.clear();
            });

        vbLeftPane.getChildren().addAll(lblCurrentUser, btnLogout, searchField, btnAddStage, btnRefresh, btnPrint);
        vbRightPane.getChildren().addAll(btnListEmployee, btnAddEmployee, btnDelete);
        leftPane.setPrefWidth(200);
        vbLeftPane.setAlignment(Pos.CENTER);
        vbRightPane.setAlignment(Pos.CENTER);

                
        VBox.setMargin(lblCurrentUser, new Insets(0, 0, 10, 0));
		VBox.setMargin(btnLogout, new Insets(0, 0, 100, 0));
		VBox.setMargin(btnAddEmployee, new Insets(50, 0, 50, 0));
		VBox.setMargin(btnAddStage, new Insets(20, 0, 20, 0));
		VBox.setMargin(btnPrint, new Insets(50, 0, 0, 0));
		VBox.setMargin(btnDelete, new Insets(0, 0, 20, 0));
		VBox.setMargin(btnRefresh, new Insets(0, 0, 0, 0));
		VBox.setMargin(searchField, new Insets(0, 10, 20, 10));




        //On prépare le topPane et le titre 
        Label lblTitle = new Label("Liste des stagiaires");
        lblTitle.setStyle(("-fx-font-family:Roboto; -fx-font-size:40; -fx-padding: 10px ;-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius:10px"));
        BorderPane topPane = new BorderPane(lblTitle);
        topPane.setPrefHeight(150);
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        topPane.setStyle("-fx-background-color:white");

//        VBox vbCenterPane = new VBox();
        BorderPane centerPane = new BorderPane();
                
        BorderPane rightPane = new BorderPane(vbRightPane);
        rightPane.setPrefWidth(200);


      //Structure conditionnelle qui donne l'accès au bouttons AddEmployee et delete qu'aux Admin
        if (!isAdmin) {
            btnAddEmployee.setVisible(false);
            btnDelete.setVisible(false);
            btnListEmployee.setVisible(false);
            rightPane.setPrefSize(0, 0);
        }
        //Création de l'instance TableViewStagiaires
        try {
//			tableViewStagiaires = new TableViewStagiaires(new ArrayList<>());
			tableViewStagiaires = new TableViewStagiaires(arbre.parcoursInfixe());
            centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        if(tableViewStagiaires != null) {
        	System.out.println("L'arbre est déja chargé");
        } else if (tableViewStagiaires == null){
        	try {
//    			tableViewStagiaires = new TableViewStagiaires(new ArrayList<>());
    			tableViewStagiaires = new TableViewStagiaires(arbre.parcoursInfixe());
                centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
        	else {
            centerPane.setCenter(tableViewStagiaires.getTable()); // Ajoutez toujours la tableView au centerPane
        }



        
        this.setTop(topPane);
        this.setCenter(centerPane);
        this.setLeft(leftPane);
        this.setRight(rightPane);
        this.setStyle("-fx-background-color:transparent");
        
        try {
    		Image image = new Image(new FileInputStream(
    				"src/main/java/annexe/logo_a-removebg.png"));
    		// Redimensionner l'image
    		double newWidth = 70;
    		double newHeight = 70;
    		ImageView imageView = new ImageView(image);
    		imageView.setPreserveRatio(true);
    		imageView.setFitWidth(newWidth);
    		imageView.setFitHeight(newHeight);
    		image = imageView.snapshot(null, null);
    		
    		//Placer l'image
    		imageView.setTranslateX(40);
    		imageView.setTranslateY(30);

    		// Ajouter l'ImageView à la HBox du logo
    		topPane.getChildren().add(imageView);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}

    }
    
    //Méthode pour filtrer la liste des stagiaires en fonction du terme de recherche
    private FilteredList<Stagiaire> filterData(ObservableList<Stagiaire> stagiaireList, String searchTerm) {
        return stagiaireList.filtered(stagiaire -> {
            // Si le terme de recherche est vide, afficher tous les stagiaires
            if (searchTerm == null || searchTerm.isEmpty()) {
                return true;
            }
         // Convertir le terme de recherche en minuscules pour une recherche insensible à la casse
            String lowerCaseFilter = searchTerm.toLowerCase();

            // Filtrer les stagiaires en fonction de leurs noms
            if (stagiaire.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            // Filtrer les stagiaires en fonction de leurs prénoms
            else if (stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            // Filtrer les stagiaires en fonction de leur département
            else if (stagiaire.getDepartement().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            // Filtrer les stagiaires en fonction de leur promotion
            else if (stagiaire.getProm().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            // Filtrer les stagiaires en fonction de leur année de formation
            else if (String.valueOf(stagiaire.getAnneeForm()).contains(lowerCaseFilter)) {
                return true;
            }
            // Si aucune condition n'est remplie, ne rien afficher
            return false;
        });
    }
    
 // Méthode pour mettre à jour la liste des employés
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
 // Méthode pour récupérer la liste des employés
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Scene getMyScene() {
//        return new Scene(this, 1200, 800);
//        Scene scene = new Scene(this, 1400, 800);
    	if (!isAdmin) {
            return new Scene(this, 1200, 800);
        } else {
            return new Scene(this, 1400, 800);
        }
}
}

