package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        else {
            String delimetr = ",";

            if (numbers.startsWith("//")) {
                int delimetrEnd = numbers.indexOf("\n");
                delimetr = numbers.substring(2, delimetrEnd);
                numbers = numbers.substring(delimetrEnd + 1);
            }

            numbers = numbers.replaceAll(delimetr, ",");
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