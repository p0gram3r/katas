package ae.codingdojo;

import java.util.HashMap;
import java.util.Map;

public class NumberToTextConverter {

    public static final Map<Integer, String> translations = new HashMap<Integer, String>();
    static {
        translations.put(1, "one");
        translations.put(2, "two");
        translations.put(3, "three");
        translations.put(4, "four");
        translations.put(5, "five");
        translations.put(6, "six");
        translations.put(7, "seven");
        translations.put(8, "eight");
        translations.put(9, "nine");
        translations.put(10, "ten");
        translations.put(11, "eleven");
        translations.put(12, "twelve");
        translations.put(13, "thirteen");
        translations.put(14, "fourteen");
        translations.put(15, "fifteen");
        translations.put(16, "sixteen");
        translations.put(17, "seventeen");
        translations.put(18, "eighteen");
        translations.put(19, "nineteen");
        translations.put(20, "twenty");
        translations.put(30, "thirty");
        translations.put(40, "forty");
        translations.put(50, "fifty");
        translations.put(60, "sixty");
        translations.put(70, "seventy");
        translations.put(80, "eighty");
        translations.put(90, "ninety");
        translations.put(100, "hundred");
        translations.put(1000, "thousand");
        translations.put(1000000, "million");
    }

    public String convert(int numberToConvert) {
        String result = "";

        int multiplier = 0;
        do {
            // extract a block
            int block = numberToConvert % 1000;
            int a = block / 100;
            int b = (block / 10) % 10;
            int c = block % 10;

            // prepend the block translation to the previous results
            String blockTranslation = translateBlock(a, b, c);
            String multiplierTranslation = translateMultiplier(multiplier);
            result = blockTranslation + multiplierTranslation + result;

            multiplier++;
        } while ((numberToConvert = numberToConvert / 1000) != 0);

        // trim removes leading space
        return result.trim();
    }

    private String translateBlock(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();

        // in a number of format "abc", "a" contains the value of the hundreds
        if (a != 0) {
            sb.append(translateIfNotZero(a));
            sb.append(translateIfNotZero(100));
        }

        // in a number of format "abc", "b" contains the value of the tens
        if (b == 1) {
            // special case for 10-19: translate sum of b and c
            sb.append(translateIfNotZero(b * 10 + c));
        } else {
            // translate parts separately
            sb.append(translateIfNotZero(b * 10));
            sb.append(translateIfNotZero(c));
        }

        return sb.toString();
    }

    private String translateMultiplier(int multiplier) {
        // there is no multiplier for the first block
        if (multiplier > 0) {
            return translateIfNotZero((int) Math.pow(1000, multiplier));
        }

        return "";
    }

    private String translateIfNotZero(int number) {
        StringBuilder result = new StringBuilder();

        if (number != 0) {
            result.append(" ");
            result.append(translations.get(number));
        }

        return result.toString();
    }

}
