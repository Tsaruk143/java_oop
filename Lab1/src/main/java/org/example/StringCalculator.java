package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        else {
            numbers = numbers.replaceAll("\n", ",");

            if (numbers.contains(",,")) {
                System.out.println("Ви не можете вводити деліметри один за одним.");
                return 0;
            }

            String[] numberArray = numbers.split(",");
            int sum = 0;
            for (String num : numberArray) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }
}