package eu.iris.ms.school;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String idStudent = "STU001";
    private String firstName = "Alice";
    private String lastName = "Dupont";
    private String email = "alicedupont@email.com";
    private int age = 25;
    private ArrayList<Double> grades = new ArrayList<Double>();

    public Student(String idStudent, String firstName, String lastName, String email, int age, ArrayList<Double> grades) {
        if (idStudent == null || idStudent.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (!idStudent.matches("^STU\\d{3}$")) {
            throw new IllegalArgumentException("Student ID must match pattern STU### (e.g., STU001");
        }

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (firstName.trim().length() < 2) {
            throw new IllegalArgumentException("First name must be at least 2 characters long");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (lastName.trim().length() < 2) {
            throw new IllegalArgumentException("Last name must be at least 2 characters long");
        }

        if (email == null || !email.matches("^[^@]+@[^@]+\\..+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (age < 16 || age > 65) {
            throw new IllegalArgumentException("Age must be between 16 and 65");
        }

        this.idStudent = idStudent;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email;
        this.age = age;
        this.grades = grades;
    }

    public void addGrade(double grade) {
        if (grade < 0.0 || grade > 20.0) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        this.grades.add(grade);
    }

    public double getAverageGrade() {
        double average = 0;
        if (this.grades.isEmpty()) {
            throw new IllegalStateException("Cannot calculate average: no grades available");
        }

        for (double grade : this.grades) {
            average += grade;
        }

        return average / this.grades.size();
    }

    public boolean hasPassingGrade() {
        if (this.grades.isEmpty()) {
            return false;
        }

        return this.getAverageGrade() >= 10.0;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getGradeCount() {
        return this.grades.size();
    }

    public double getBestGrade() {
        if (this.grades.isEmpty()) {
            return 0.0;
        }
        double bestGrade = this.grades.getFirst();
        for (double grade : this.grades) {
            if (bestGrade < grade) {
                bestGrade = grade;
            }
        }
        return bestGrade;
    }

    public double getWorstGrade() {
        if (this.grades.isEmpty()) {
            return 0.0;
        }
        double worstGrade = this.grades.getFirst();
        for (double grade : this.grades) {
            if (worstGrade > grade) {
                worstGrade = grade;
            }
        }
        return worstGrade;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Double> getGrades() {
        return new ArrayList<>(this.grades);    }

}
