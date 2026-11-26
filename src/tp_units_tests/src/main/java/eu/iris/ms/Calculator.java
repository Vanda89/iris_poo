package eu.iris.ms;

/**
 * Classe Calculator pour les premiers tests avec JUnit.
 *
 * Cette classe contient des méthodes mathématiques simples pour apprendre
 * les concepts de base des tests unitaires :
 * - Comportement prévisible
 * - Cas normaux et cas d'erreur
 * - Gestion des exceptions
 */
public class Calculator {

    Calculator(){

    }

    /**
     * Addition de deux entiers
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Soustraction de deux entiers
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplication de deux entiers
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Division de deux nombres décimaux
     * @throws ArithmeticException si le diviseur est zéro
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException();
        }

        return a / b;
    }

    /**
     * Calcul de la factorielle d'un nombre
     * @throws IllegalArgumentException si n est négatif
     */
    public int factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    /**
     * Vérifie si un nombre est pair
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Calcule une puissance (exposant positif uniquement)
     * @throws IllegalArgumentException si l'exposant est négatif
     */
    public double power(double base, int exponent) throws IllegalArgumentException {
        double result = 1;

        if (exponent < 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i <= exponent ; i++) {
            result = result * base;
        }
        return result;
    }

    public double average(int [] notes) throws ArithmeticException {

        double result = 0;

        for (int note : notes) {
            result += note;
        }

        return result / notes.length;
    }



}