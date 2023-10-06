package org.example;
import java.util.ArrayList;
import java.util.List;
public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException {
        try {
            if (numbers.isEmpty()) {
                return 0;
            } else {
                String delimiter = ",";

                if (numbers.startsWith("//")) {
                    int delimiterEnd = numbers.indexOf("\n");
                    delimiter = numbers.substring(2, delimiterEnd);
                    numbers = numbers.substring(delimiterEnd + 1);
                }

                numbers = numbers.replaceAll(delimiter, ",");
                numbers = numbers.replaceAll("\n", ",");

                if (numbers.contains(",,")) {
                    System.out.println("Помилка: дві коми підряд недопустимі.");
                    return 0;
                }

                String[] numberArray = numbers.split(",");

                int sum = 0;
                List<Integer> negativeNumbers = new ArrayList<>();

                for (String num : numberArray) {
                    int value = Integer.parseInt(num);
                    if (value < 0) {
                        negativeNumbers.add(value);
                    }
                    if (value < 1001) {
                        sum += value;
                    }
                }

                if (!negativeNumbers.isEmpty()) {
                    System.out.println("Помилка: недозволені від'ємні числа: " + negativeNumbers);
                    throw new IllegalArgumentException();
                }

                return sum;
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }

}