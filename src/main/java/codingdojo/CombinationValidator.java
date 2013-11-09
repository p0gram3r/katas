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
        List<Color> result = new LinkedList<Color>();
        return new Combination(result.toArray(new Color[0]));
    }

}
