package service;

/** Reusable math functions used by the game (answers are always calculated, never stored). */
public class MathService {

    /** Returns 1 if number is prime, otherwise 0. Numbers less than 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) return 0;
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    /** Returns a % b. Throws IllegalArgumentException when the divisor is zero. */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor must not be zero.");
        }
        return a % b;
    }

    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    /** Throws ArithmeticException when tan is undefined (90, 270, ... degrees). */
    public static double calculateTan(double degree) {
        double normalized = ((degree % 180) + 180) % 180;
        if (Math.abs(normalized - 90) < 1e-9) {
            throw new ArithmeticException("Tangent is undefined at " + degree + " degrees.");
        }
        return Math.tan(Math.toRadians(degree));
    }

    /** Calculates sin / cos / tan by name. */
    public static double calculateTrig(String type, double degree) {
        switch (type) {
            case "sin": return calculateSin(degree);
            case "cos": return calculateCos(degree);
            case "tan": return calculateTan(degree);
            default: throw new IllegalArgumentException("Unknown function: " + type);
        }
    }

    /** Compares decimal answers using a tolerance. */
    public static boolean isClose(double expected, double actual) {
        return Math.abs(expected - actual) <= 0.01;
    }

    /** Quick console test of the functions (Tasks 03-05, 09). */
    public static void main(String[] args) {
        System.out.println("isPrime(7)  = " + isPrime(7));
        System.out.println("isPrime(8)  = " + isPrime(8));
        System.out.println("isPrime(1)  = " + isPrime(1));
        System.out.println("17 % 5      = " + calculateMod(17, 5));
        System.out.println("-17 % 5     = " + calculateMod(-17, 5));
        System.out.println("sin(30)     = " + calculateSin(30));
        System.out.println("cos(60)     = " + calculateCos(60));
        System.out.println("tan(45)     = " + calculateTan(45));
        try { calculateMod(5, 0); } catch (IllegalArgumentException e) { System.out.println("Validation error: " + e.getMessage()); }
        try { calculateTan(90); } catch (ArithmeticException e) { System.out.println("Validation error: " + e.getMessage()); }
    }
}
