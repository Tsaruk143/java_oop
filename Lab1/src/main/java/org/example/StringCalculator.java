package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException {
        try {
            if (numbers.isEmpty()) {
                return 0;
            } else {
                String delimiter = ",";

                if (numbers.startsWith("//")) {
                    Pattern pattern = Pattern.compile("//(?:\\[(.*?)\\])?\n(.*)");
                    Matcher matcher = pattern.matcher(numbers);

                    if (matcher.find()) {
                        delimiter = Pattern.quote(matcher.group(1));
                        numbers = matcher.group(2);
                    } else {
                        int delimiterEnd = numbers.indexOf("\n");
                        delimiter = numbers.substring(2, delimiterEnd);
                        numbers = numbers.substring(delimiterEnd + 1);
                    }
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