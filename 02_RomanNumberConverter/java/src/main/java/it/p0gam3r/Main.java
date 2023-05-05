package it.p0gam3r;

public class Main {
    public static void main(String[] args) {
        int input = 1000;
        String output = new RomanNumeralConverter().numberToNumeral(input);
        System.out.println(output);
    }
}