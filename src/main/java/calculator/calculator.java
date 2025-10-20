package techCourse_calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calculator {

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|:";

        Matcher matcher = Pattern.compile("//(.*)\n(.*)").matcher(input);

        if (matcher.matches()) {
            delimiter = Pattern.quote(matcher.group(1));
            input = matcher.group(2);
        }

        String allowedChars = "[0-9" + delimiter.replace("|", "") + "]+";
        String inputWithoutChars = input.replaceAll("\\s+", "");

        if (!Pattern.matches("(" + allowedChars + ")+", inputWithoutChars.replaceAll(delimiter, ""))) {
            throw new IllegalArgumentException("허용되지 않은 구분자/문자 포함 : " + input);
        }

        String[] tokens = input.split(delimiter);

        int sum = 0;

        for (String token : tokens) {
            Matcher numMatcher = Pattern.compile("-?\\d+").matcher(token);
            while (numMatcher.find()) {
                int num = Integer.parseInt(numMatcher.group());
                if (num < 0) {
                    throw new IllegalArgumentException("양수와 구분자만 허용 : " + num);
                }
                sum += num;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = sc.nextLine();

        int result = add(input);
        System.out.println("결과 : " + result);

        sc.close();
    }

}

