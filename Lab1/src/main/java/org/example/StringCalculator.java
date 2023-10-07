package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
                    Pattern pattern = Pattern.compile("//(?:\\[(.*)?\\])*\n(.*)");
                    Matcher matcher = pattern.matcher(numbers);

                    if (matcher.find()) {
                        numbers = matcher.group(2);
                        delimiter = Pattern.quote(matcher.group(1));
                        if (delimiter.contains("][")) {
                            String d = Pattern.quote("][");
                            String[] delimiters = delimiter.split(d);
                            Arrays.sort(delimiters, new Comparator<String>() {
                                @Override
                                public int compare(String s1, String s2) {
                                    String delimiter1 = s1.replace("\\Q", "").replace("\\E", "");
                                    String delimiter2 = s2.replace("\\Q", "").replace("\\E","");
                                    return Integer.compare(delimiter2.length(), delimiter1.length());
                                }
                            });
                            for (String customDelimiter : delimiters) {
                                numbers = numbers.replaceAll(Pattern.quote(customDelimiter.replace("\\Q", "").replace("\\E", "")), ",");
                            }
                        }

                        numbers = numbers.replaceAll(delimiter, ",");
                    } else {
                        int delimiterEnd = numbers.indexOf("\n");
                        delimiter = numbers.substring(2, delimiterEnd);
                        numbers = numbers.substring(delimiterEnd + 1);
                        numbers = numbers.replaceAll(delimiter, ",");
                    }
                }

                numbers = numbers.replaceAll("\n", ",");

                if (numbers.contains(",,")) {
                    System.out.println("Помилка: два деліметри підряд недопустимі.");
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