package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        else {
            String[] numberArray = numbers.split(",");
            int sum = 0;
            for (String num : numberArray) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }
}