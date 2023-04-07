package fr.cda24.ISIKA.Projet1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private boolean isAdmin;
	
	private static List<Employee> employees = new ArrayList<>();
	
	public Employee(String firstName, String lastName, LocalDate birthDate, String email, String password, boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		employees.add(this);
	}
	static {
		LocalDate birthDate = LocalDate.now();
	Employee employee = new Employee("John", "Doe", birthDate, "john.doe@example.com", "Password123", true);
	Employee employee1 = new Employee("Jane", "Doe", birthDate, "jane.doe@example.com", "Password123", false);
	
//	employees.add(employee);
//    employees.add(employee1);

	}

	public static boolean isUserAdmin(String email, String password) {
		Employee employee = getEmployeeByEmailAndPassword(email, password);
		if (employee != null ) {
			return employee.isAdmin();
		} else {
			return false;
		}
	}
	
	public static Employee getEmployeeByEmailAndPassword(String email, String password) {
		for (Employee employee : employees) {
			if (employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
				return employee;
			}
		}
		return null;
	}
	
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
	public static void addEmployee(Employee employee) {
        employees.add(employee);
    }
	public static Employee findEmployee(String email) {
	    for (Employee employee : employees) {
	        if (employee.getEmail().equals(email)) {
	            return employee;
	        }
	    }
	    return null;
	}
	
	public static List<Employee> getEmployees() {
	    return employees;
	}

	// Getters et setters
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
