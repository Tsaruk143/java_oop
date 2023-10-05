package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            StringCalculator calculator = new StringCalculator();
            System.out.println(calculator.add("1,-1,2,-2,3"));
        }
        catch (Exception e)
        {System.out.println(e);}
    }
}