package fr.cda24.ISIKA.Projet1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Cette classe serve à instancier des nouveaux employés
 */
public class Employee {
	
	// Attributs
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private boolean isAdmin;
	
	// Liste des employés
	private static List<Employee> employees = new ArrayList<>();
	
	/**
	 * Constructeur de la classe Employee.
	 * 
	 * @param firstName le prénom de l'employé.
	 * @param lastName le nom de l'employé.
	 * @param birthDate la date de naissance de l'employé.
	 * @param email l'adresse e-mail de l'employé.
	 * @param password le mot de passe de l'employé.
	 * @param isAdmin un booléen indiquant si l'employé est administrateur ou non.
	 */
	public Employee(String firstName, String lastName, LocalDate birthDate, String email, String password, boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
//		employees.add(this);
	}
	// Initialisation des employés
	static {
		LocalDate birthDate = LocalDate.now();
	Employee employee = new Employee("John", "Doe", birthDate, "john.doe@example.com", "Password123", true);
	Employee employee1 = new Employee("Jane", "Doe", birthDate, "jane.doe@example.com", "Password123", false);
	
	employees.add(employee);
    employees.add(employee1);

	}

	/**
	 * Vérifie si un utilisateur est administrateur ou non.
	 * 
	 * @param email l'adresse e-mail de l'utilisateur.
	 * @param password le mot de passe de l'utilisateur.
	 * @return un booléen indiquant si l'utilisateur est administrateur ou non.
	 */
	public static boolean isUserAdmin(String email, String password) {
		Employee employee = getEmployeeByEmailAndPassword(email, password);
		if (employee != null ) {
			return employee.isAdmin();
		} else {
			return false;
		}
	}
	/**
	 * Récupère un employé par son adresse e-mail et son mot de passe.
	 * 
	 * @param email l'adresse e-mail de l'employé.
	 * @param password le mot de passe de l'employé.
	 * @return l'employé correspondant à l'adresse e-mail et au mot de passe donnés, ou null si aucun employé ne correspond.
	 */
	public static Employee getEmployeeByEmailAndPassword(String email, String password) {
		for (Employee employee : employees) {
			if (employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
				return employee;
			}
		}
		return null;
	}
	/**
	 * Vérifie si une adresse e-mail et un mot de passe sont valides.
	 * 
	 * @param email l'adresse e-mail à vérifier.
	 * @param password le mot de passe à vérifier.
	 * @return un booléen indiquant si l'adresse e-mail et le mot de passe sont valides ou non.
	 */
	public static boolean validateEmailAndPassword(String email, String password) {
	    // Vérifier si l'adresse e-mail est dans un format valide
	    Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	    Matcher emailMatcher = emailPattern.matcher(email);
	    if (!emailMatcher.matches()) {
	        return false;
	    }

	    // Vérifier si le mot de passe respecte les critères de sécurité
	    // Par exemple, vérifier qu'il contient au moins 8 caractères, une lettre majuscule, une lettre minuscule et un chiffre
	    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
	    Matcher passwordMatcher = passwordPattern.matcher(password);
	    if (!passwordMatcher.matches()) {
	        return false;
	    }

	    return true;
	}
	/**
	 * Ajoute un nouvel employé à la liste des employés
	 * @param employee l'employé à ajouter
	 */
	public static void addEmployee(Employee employee) {
	    employees.add(employee);
	}

	/**
	 * Trouve l'employé correspondant à l'adresse email donnée
	 * @param email l'adresse email de l'employé recherché
	 * @return l'employé correspondant à l'adresse email donnée, null si aucun employé ne correspond
	 */
	public static Employee findEmployee(String email) {
	    for (Employee employee : employees) {
	        if (employee.getEmail().equals(email)) {
	            return employee;
	        }
	    }
	    return null;
	}
	/**
	 * Renvoie la liste de tous les employés
	 * @return la liste de tous les employés
	 */
	public static List<Employee> getEmployees() {
	    return employees;
	}

	/**
	 * Renvoie le prénom de l'employé
	 * @return le prénom de l'employé
	 */	
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Modifie le prénom de l'employé
	 * @param firstName le nouveau prénom de l'employé
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Renvoie le nom de famille de l'employé
	 * @return le nom de famille de l'employé
	 */
	public String getLastName() {
	    return lastName;
	}

	/**
	 * Modifie le nom de famille de l'employé
	 * @param lastName le nouveau nom de famille de l'employé
	 */
	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}

	/**
	 * Renvoie la date de naissance de l'employé
	 * @return la date de naissance de l'employé
	 */
	public LocalDate getBirthDate() {
	    return birthDate;
	}

	/**
	 * Modifie la date de naissance de l'employé
	 * @param birthDate la nouvelle date de naissance de l'employé
	 */
	public void setBirthDate(LocalDate birthDate) {
	    this.birthDate = birthDate;
	}

	/**
	 * Renvoie l'adresse email de l'employé
	 * @return l'adresse email de l'employé
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * Modifie l'adresse email de l'employé
	 * @param email la nouvelle adresse email de l'employé
	 */
	public void setEmail(String email) {
	    this.email = email;
	}

	/**
	 * Renvoie le mot de passe de l'employé
	 * @return le mot de passe de l'employé
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * Modifie le mot de passe de l'employé
	 * @param password le nouveau mot de passe de l'employé
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * Indique si l'employé est un administrateur
	 * @return true si l'employé est un administrateur, false sinon
	 */
	public boolean isAdmin() {
	    return isAdmin;
	}

	/**
	 * Modifie le statut administrateur de l'employé
	 * @param isAdmin true si l'employé est un administrateur, false sinon
	 */
	public void setAdmin(boolean isAdmin) {
	    this.isAdmin = isAdmin;
	}
	/**
	 * Supprime l'employé correspondant à l'adresse email donnée de la liste des employés
	 *
	 *	 
	 * @param email l'adresse email de l'employé à supprimer
	 */
	public static boolean removeEmployee(String email) {
	    Employee employee = findEmployee(email);
	    if (employee != null) {
	        employees.remove(employee);
	        return true;
	    }
	    return false;
	}
}
