package eu.iris.ms.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

        private Student student;
        private final String ID_STUDENT = "STU001";
        private final String FIRSTNAME = "Alice";
        private final String LASTNAME = "Dupont";
        private final String EMAIL = "alicedupont@email.com";
        private final int AGE = 25;
        private ArrayList<Double> grades = new ArrayList<Double>();

        @BeforeEach
        void setUp() { student = new Student(ID_STUDENT, FIRSTNAME, LASTNAME, EMAIL, AGE, grades);}

        @Test
        void shouldAcceptValidStudentId() {
            Student student2 = new Student("STU002", "Bob", "Dylan",
                    "bobdylan@email.com", 25, new ArrayList<>());
            assertEquals("STU002", student2.getIdStudent());
        }

        @Test
        void shouldRejectNullStudentId() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(null, FIRSTNAME, LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("Student ID cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectEmptyStudentId() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(" ", FIRSTNAME, LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("Student ID cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectInvalidStudentIdFormat() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student("ABC123", FIRSTNAME, LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("Student ID must match pattern STU### (e.g., STU001", exception.getMessage());
        }

        @Test
        void shouldAcceptValidFirstName() {
            Student student2 = new Student(ID_STUDENT, " Bob", LASTNAME, EMAIL, AGE, grades);
            assertEquals("Bob", student2.getFirstName());
        }

        @Test
        void shouldRejectNullFirstName() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, null, LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("First name cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectEmptyFirstName() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, " ", LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("First name cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectFirstNameWithLessThanTwoCharacters() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, "A", LASTNAME, EMAIL, AGE, grades)
            );
            assertEquals("First name must be at least 2 characters long", exception.getMessage());
        }

        @Test
        void shouldAcceptValidLastName() {
            Student student2 = new Student(ID_STUDENT, FIRSTNAME, "  Bob  ", EMAIL, AGE, grades);
            assertEquals("Bob", student2.getLastName());
        }

        @Test
        void shouldRejectNullLastName() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, null, EMAIL, AGE, grades)
            );
            assertEquals("Last name cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectEmptyLastName() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, "", EMAIL, AGE, grades)
            );
            assertEquals("Last name cannot be null or empty", exception.getMessage());
        }

        @Test
        void shouldRejectLastNameWithLessThanTwoCharacters() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, "D", EMAIL, AGE, grades)
            );
            assertEquals("Last name must be at least 2 characters long", exception.getMessage());
        }
        @Test
        void shouldAcceptValidEmail() {
            Student student3 = new Student(ID_STUDENT, FIRSTNAME, LASTNAME,
                    "charlietango@mail.com", AGE, grades);
            assertEquals("charlietango@mail.com", student3.getEmail());
        }

        @Test
        void shouldRejectNullEmail() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, LASTNAME, null, AGE, grades)
            );
            assertEquals("Invalid email format", exception.getMessage());
        }

        @Test
        void shouldRejectInvalidEmailFormat() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, LASTNAME, "charlietangomailcom", AGE, grades)
            );
            assertEquals("Invalid email format", exception.getMessage());
        }

        @Test
        void shouldAcceptValidAge() {
            Student student3 = new Student(ID_STUDENT, FIRSTNAME, LASTNAME, EMAIL, 16, grades);
            assertEquals(16, student3.getAge());
        }

        @Test
        void shouldRejectAgeBelowMinimum() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, LASTNAME, EMAIL, 15, grades)
            );
            assertEquals("Age must be between 16 and 65", exception.getMessage());
        }

        @Test
        void shouldRejectAgeAboveMaximum() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Student(ID_STUDENT, FIRSTNAME, LASTNAME, EMAIL, 66, grades)
            );
            assertEquals("Age must be between 16 and 65", exception.getMessage());
        }

        @Test
        void shouldAddGradeWithValidParameters() {
            student.addGrade(19.5);

            assertEquals(1, student.getGradeCount());
            assertEquals(19.5, student.getBestGrade());
        }

        @Test
        void shouldRejectInvalidGrades() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> student.addGrade(25.0)
            );
            IllegalArgumentException exceptionNegative = assertThrows(
                    IllegalArgumentException.class,
                    () -> student.addGrade(-1.0)
            );
            assertEquals("Grade must be between 0 and 20", exception.getMessage());
            assertEquals("Grade must be between 0 and 20", exceptionNegative.getMessage());
        }

        @Test
        void shouldCalculateAverageGrade() {
            student.addGrade(15);
            student.addGrade(16);
            student.addGrade(17);

            assertEquals(16, student.getAverageGrade());
        }

        @Test
        void shouldThrowExceptionWhenNoGradesAvailable() {
            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    () -> student.getAverageGrade()
            );
            assertEquals("Cannot calculate average: no grades available", exception.getMessage());

        }

        @Test
        void shouldReturnTrueWhenAverageIsPassingGrade() {
            student.addGrade(15.0);
            student.addGrade(16.0);
            student.addGrade(17.0);

            assertTrue(student.hasPassingGrade());
        }

        @Test
        void shouldReturnFalseWhenAverageIsBelowPassingGrade() {
            Student student2 = new Student("STU002", "Bob", "Dylan", "bobdylan@mail.com", 27, grades);

            student2.addGrade(0.0);
            student2.addGrade(2.0);
            student2.addGrade(4.0);

            assertFalse(student2.hasPassingGrade());
        }

        @Test
        void shouldReturnFalseWhenNoGradesAvailable() {
            assertFalse(student.hasPassingGrade());
        }

        @Test
        void shouldReturnFullName() {
            assertEquals("Alice Dupont", student.getFullName());
        }

        @Test
        void shouldReturnGradeCount() {
            Student student2 = new Student("STU002", "Bob", "Dylan", "bobdylan@mail.com", 27, grades);

            student2.addGrade(0.0);
            student2.addGrade(2.0);
            student2.addGrade(4.0);
            assertEquals(3, student2.getGradeCount());
        }

        @Test
        void shouldReturnZeroWhenNoGradesAvailable() {
            Student student2 = new Student("STU002", "Bob", "Dylan", "bobdylan@mail.com", 27, grades);

            assertEquals(0.0, student2.getGradeCount());
        }

        @Test
        void shouldReturnBestGrade() {
            student.addGrade(15.0);
            student.addGrade(16.0);
            student.addGrade(17.0);

            assertEquals(17.0, student.getBestGrade());
        }

        @Test
        void shouldReturnZeroAsBestGradeWhenNoGradesAvailable() {
            Student student2 = new Student("STU002", "Bob", "Dylan", "bobdylan@mail.com", 27, grades);

            assertEquals(0.0, student2.getBestGrade());
        }

        @Test
        void shouldReturnWorstGrade() {
            student.addGrade(15.0);
            student.addGrade(8.0);
            student.addGrade(17.0);

            assertEquals(8.0, student.getWorstGrade());
        }

        @Test
        void shouldReturnZeroAsWorstGradeWhenNoGradesAvailable() {
            Student student2 = new Student("STU002", "Bob", "Dylan", "bobdylan@mail.com", 27, grades);

            assertEquals(0.0, student2.getWorstGrade());
        }

        @Test
        void shouldReturnGrades() {
            student.addGrade(15.0);
            student.addGrade(16.0);
            student.addGrade(17.0);

            assertEquals(List.of(15.0, 16.0, 17.0), student.getGrades());
        }

        @Test
        void shouldReturnCopyOfGradesList() {
            student.addGrade(15.0);
            ArrayList<Double> studentGrades = student.getGrades();
            studentGrades.add(8.0);

            assertEquals(1, student.getGradeCount());
        }
    }


