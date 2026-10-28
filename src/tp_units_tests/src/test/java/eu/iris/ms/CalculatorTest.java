package eu.iris.ms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Calculator.
 *
 * Ce fichier sert de template pour apprendre :
 * - Structure AAA (Arrange-Act-Assert)
 * - Nommage descriptif des tests
 * - Tests de cas normaux et d'exceptions
 * - Utilisation de @BeforeEach pour la préparation commune
 */
class CalculatorTest {

    private Calculator calculator;

    /**
     * Méthode exécutée AVANT chaque test
     * Évite la duplication de code de préparation
     */
    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }

    // === TESTS D'ADDITION ===

    @Test
    public void shouldAddPositiveNumbers() {
        // ARRANGE - Préparer les données
        int a = 5;
        int b = 3;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(8, result);
    }

    @Test
    public void shouldAddNegativeNumbers() {
        // TODO: Compléter ce test
        // Tester l'addition avec des nombres négatifs
        // Exemple : (-5) + (-3) = -8

        // ARRANGE - Préparer les données
        int a = -5;
        int b = -3;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(-8, result);
    }

    @Test
    public void shouldAddZero() {
        // TODO: Compléter ce test
        // Tester l'addition avec zéro
        // Exemple : 5 + 0 = 5

        // ARRANGE - Préparer les données
        int a = 5;
        int b = 0;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(5, result);
    }

    // === TESTS DE SOUSTRACTION ===

    @Test
    public void shouldSubtractNumbers() {
        // TODO: Compléter ce test
        // Exemple : 10 - 3 = 7

        // ARRANGE - Préparer les données
        int a = 10;
        int b = 3;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.subtract(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(7, result);
    }

    @Test
    public void shouldSubtractResultingInNegative() {
        // TODO: Compléter ce test
        // Exemple : 3 - 10 = -7

        // ARRANGE - Préparer les données
        int a = 3;
        int b = 10;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.subtract(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(-7, result);
    }

    // === TESTS DE MULTIPLICATION ===

    @Test
    public void shouldMultiplyNumbers() {
        // TODO: Compléter ce test
        // Exemple : 4 * 3 = 12

        // ARRANGE - Préparer les données
        int a = 4;
        int b = 3;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(12, result);
    }

    @Test
    public void shouldReturnZeroWhenMultiplyingByZero() {
        // TODO: Compléter ce test
        // Exemple : 5 * 0 = 0

        // ARRANGE - Préparer les données
        int a = 5;
        int b = 0;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(0, result);
    }

    @Test
    public void shouldMultiplyNegativeNumbers() {
        // TODO: Compléter ce test
        // Exemple : (-2) * (-3) = 6

        // ARRANGE - Préparer les données
        int a = -2;
        int b = -3;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(6, result);
    }

    // === TESTS DE DIVISION ===

    @Test
    public void shouldDivideNumbers() {
        // TODO: Compléter ce test
        // Exemple : 15.0 / 3.0 = 5.0
        // Attention : utiliser des doubles !

        // ARRANGE - Préparer les données
        double a = 15.0;
        double b = 3.0;
        // ACT - Exécuter l'action à tester
        double result = this.calculator.divide(a, b) ;

        // ASSERT - Vérifier le résultat
        assertEquals(5.0, result);
    }

    @Test
    public void shouldThrowExceptionWhenDividingByZero() {
        // TODO: Compléter ce test avec assertThrows
        // La division par zéro doit lever une ArithmeticException.
        // Pour pouvoir tester des exceptions, il faut les catch !

        // ARRANGE - Préparer les données
        double a = 12.0;
        double b = 0f;
        // ACT - Exécuter l'action à tester
        Exception exception = null;
        try {
            double result = this.calculator.divide(a, b);
        } catch(Exception e) {
            exception = e;
        }
        // ASSERT - Vérifier le résultat
        assertNotNull(exception);
        assertInstanceOf(ArithmeticException.class, exception);
    }

    // === TESTS FACTORIELLE ===

    @Test
    public void shouldCalculateFactorialOfPositiveNumber() {
        // TODO: Compléter ce test
        // Exemple : 5! = 120

        // ARRANGE - Préparer les données
        int a = 5;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(a);
        // ASSERT - Vérifier le résultat
        assertEquals(120, result);
    }

    @Test
    public void shouldReturnOneForFactorialOfZero() {
        // TODO: Compléter ce test
        // Cas spécial : 0! = 1

        // ARRANGE - Préparer les données
        int a = 0;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(a);
        // ASSERT - Vérifier le résultat
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOneForFactorialOfOne() {
        // TODO: Compléter ce test
        // Cas spécial : 1! = 1

        // ARRANGE - Préparer les données
        int a = 1;
        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(a);
        // ASSERT - Vérifier le résultat
        assertEquals(1, result);
    }

    @Test
    public void shouldThrowExceptionForNegativeFactorial() {
        // TODO: Compléter ce test avec assertThrows
        // La factorielle d'un nombre négatif doit lever IllegalArgumentException
        // ARRANGE - Préparer les données
        int a = -5;
        // ACT - Exécuter l'action à tester & ASSERT
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.calculator.factorial(a)
        );
    }

    // === TESTS NOMBRE PAIR ===

    @Test
    public void shouldReturnTrueForEvenNumbers() {
        // TODO: Compléter ce test
        // Tester plusieurs nombres pairs : 0, 2, 4, 100
        // ARRANGE - Préparer les données
        int a = 4;
        int b = 8;
        int c = 6;
        // ACT - Exécuter l'action à tester
        boolean result1 = this.calculator.isEven(a);
        boolean result2 = this.calculator.isEven(b);
        boolean result3 = this.calculator.isEven(c);
        // ASSERT - Vérifier le résultat
        assertAll("Verify if the numbers are even numbers",
                () -> assertTrue(result1),
                () -> assertTrue(result2),
                () -> assertTrue(result3)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 15, Integer.MAX_VALUE})
    public void shouldReturnFalseForOddNumbers(int number) {

        boolean result = this.calculator.isEven(number);

        assertFalse(result);
    }

    // === TESTS PUISSANCE ===

    @Test
    public void shouldCalculatePowerCorrectly() {
        // TODO: Compléter ce test
        // Exemple : 2^3 = 8.0
        // Attention à la précision (par exemple 0.001) des doubles !
        // assertEquals(8.0, calculator.power(2, 3), 0.001);
        // ARRANGE - Préparer les données
        double base = 2.0;
        int exponent = 3;
        // ACT - Exécuter l'action à tester
        double result = this.calculator.power(base, exponent);
        // ASSERT - Vérifier le résultat
        assertEquals(8.0, result, 0.001);
    }

    @Test
    public void shouldReturnOneForPowerZero() {
        // TODO: Compléter ce test
        // Tout nombre puissance 0 = 1
        // ARRANGE - Préparer les données
        double base = 2.0;
        int exponent = 0;
        // ACT - Exécuter l'action à tester
        double result = this.calculator.power(base,exponent);
        // ASSERT - Vérifier le résultat
        assertEquals(1.0, result, 0.001);

    }

    @Test
    public void shouldThrowExceptionForNegativeExponent() {
        // TODO: Compléter ce test avec assertThrows
        // Exposant négatif doit lever IllegalArgumentException
        // ARRANGE - Préparer les données
        double base = 2.0;
        int exponent = -3;
        // ACT & ASSERT
        assertThrows(
                IllegalArgumentException.class,
                () -> this.calculator.power(base, exponent)
        );
    }

    // === TESTS SUPPLÉMENTAIRES ===
    // TODO: Ajouter au moins 2 tests de votre choix
    // Idées : cas limites, combinaisons de méthodes, valeurs extrêmes
    @Test
    public void shouldCalculateAverageCorrectly() {
        // ARRANGE - Préparer les données
        int[] notes = {12, 13, 14};
        // ACT - Exécuter l'action à tester
        double result = this.calculator.average(notes);
        // ASSERT - Vérifier le résultat
        assertEquals(13, result, 0.001);
    }

    @Test
    public void shouldCalculateAverageCorrectlyWithExtremeValues() {
        // ARRANGE - Préparer les données
        int[] notes = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        // ACT - Exécuter l'action à tester
        double result = this.calculator.average(notes);
        // ASSERT - Vérifier le résultat
        assertEquals(Integer.MAX_VALUE, result, 0.001);
    }

}