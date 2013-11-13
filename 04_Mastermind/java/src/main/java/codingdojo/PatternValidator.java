package codingdojo;

import java.util.LinkedList;
import java.util.List;

public class PatternValidator {

    private Pattern secretPattern;

    public PatternValidator(Pattern secretPattern) {
        this.secretPattern = secretPattern;
    }

    public Pattern getSecretPattern() {
        return secretPattern;
    }

    public Pattern validate(Pattern guess) {
        int postitonMatches = 0;
        int colorMatches = 0;

        List<Color> secretColors = new LinkedList<Color>(secretPattern.getColors());
        List<Color> guessColors = new LinkedList<Color>(guess.getColors());

        // remove color and position matches
        for (int i = guessColors.size() - 1; i >= 0; i--) {
            if (secretColors.get(i).equals(guessColors.get(i))) {
                postitonMatches++;
                secretColors.remove(i);
                guessColors.remove(i);
            }
        }

        // remove color matches
        for (int i = guessColors.size() - 1; i >= 0; i--) {
            if (secretColors.remove(guessColors.get(i))) {
                colorMatches++;
            }
        }

        return createResult(postitonMatches, colorMatches);
    }

    private Pattern createResult(int postitonMatches, int colorMatches) {
        List<Color> colors = new LinkedList<Color>();
        for (int i = 0; i < postitonMatches; i++) {
            colors.add(Color.BLACK);
        }
        for (int i = 0; i < colorMatches; i++) {
            colors.add(Color.WHITE);
        }

        return new Pattern(colors);
    }

}
