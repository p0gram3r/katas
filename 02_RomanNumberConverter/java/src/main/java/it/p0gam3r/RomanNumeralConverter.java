package it.p0gam3r;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RomanNumeralConverter {

    private static final Map<Integer, String> translationValues = new LinkedHashMap<>();

    static {
        translationValues.put(1000, "M");
        translationValues.put(900, "CM");
        translationValues.put(500, "D");
        translationValues.put(400, "CD");
        translationValues.put(100, "C");
        translationValues.put(90, "XC");
        translationValues.put(50, "L");
        translationValues.put(40, "XL");
        translationValues.put(10, "X");
        translationValues.put(9, "IX");
        translationValues.put(5, "V");
        translationValues.put(4, "IV");
        translationValues.put(1, "I");
    }


    public String numberToNumeral(int value) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, String> entry : translationValues.entrySet()) {
            int arabicNumber = entry.getKey();
            String romanNumber = entry.getValue();

            int count = value / arabicNumber;
            value = value % arabicNumber;
            addToResult(romanNumber, count, sb);
        }

        return sb.toString();
    }

    private void addToResult(String str, int times, StringBuilder sb) {
        IntStream.range(0, times).forEach(i -> sb.append(str));
    }

}
