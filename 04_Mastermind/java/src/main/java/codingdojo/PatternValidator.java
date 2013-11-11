package codingdojo;

import java.util.LinkedList;
import java.util.List;

public class PatternValidator {

    private Pattern secretPattern;

    public PatternValidator(Pattern secretPattern) {
        this.secretPattern = secretPattern;
    }

    public String getGreeting() {
        return "Hello World!";
    }

    public Pattern getSecretPattern() {
        return secretPattern;
    }

    public Pattern validate(Pattern guess) {
        int postitonMatches = 0;
        int colorMatches = 0;

        // FIXME inline?
        List<Color> secretColors = secretPattern.getColors();
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
