package codingdojo;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class CombinationValidator {

    private Combination secretCombination;

    public CombinationValidator(Combination secretCombination) {
        this.secretCombination = secretCombination;
    }

    public String getGreeting() {
        return "Hello World!";
    }

    public Combination getSecretCombination() {
        return secretCombination;
    }

    public Combination validate(Combination guess) {
        int postitonMatches = 0;
        int colorMatches = 0;

        // FIXME inline?
        List<Color> secretColors = secretCombination.getColors();
        List<Color> guessColors = guess.getColors();

        for (int i = 0; i < guessColors.size(); i++) {
            if (secretColors.get(i).equals(guessColors.get(i))) {
                postitonMatches++;
            } else if (secretColors.contains(guessColors.get(i))) {
                colorMatches++;
            }
        }

        return createResult(postitonMatches, colorMatches);
    }

    private Combination createResult(int postitonMatches, int colorMatches) {
        List<Color> colors = new LinkedList<Color>();
        for (int i = 0; i < postitonMatches; i++) {
            colors.add(Color.BLACK);
        }
        for (int i = 0; i < colorMatches; i++) {
            colors.add(Color.WHITE);
        }

        return new Combination(colors);
    }

}
