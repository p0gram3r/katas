package codingdojo;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CombinationValidatorTest {

    @Parameters
    public static Collection<Object[]> data() {
        // @formatter:off
        return Arrays.asList(new Object[][] { 
                {combine(RED, YELLOW), combine(BLUE, BLUE), combine()},
                {combine(RED, YELLOW), combine(YELLOW, BLUE), combine(WHITE)},
                {combine(RED, YELLOW), combine(BLUE, RED), combine(WHITE)},
                {combine(RED, YELLOW), combine(YELLOW, RED), combine(WHITE, WHITE)},
                {combine(RED, YELLOW), combine(RED, BLUE), combine(BLACK)},
                {combine(RED, YELLOW), combine(BLUE, YELLOW), combine(BLACK)},
                {combine(RED, YELLOW), combine(RED, YELLOW), combine(BLACK, BLACK)},
                
                {combine(RED, YELLOW, GREEN), combine(BLUE, BLUE, BLUE), combine(WHITE)},
                {combine(RED, YELLOW, GREEN), combine(YELLOW, BLUE, BLUE), combine(WHITE)},
                {combine(RED, YELLOW, GREEN), combine(YELLOW, GREEN, BLUE), combine(WHITE, WHITE)},
                {combine(RED, YELLOW, GREEN), combine(YELLOW, GREEN, RED), combine(WHITE, WHITE, WHITE)},
                {combine(RED, YELLOW, GREEN), combine(RED, YELLOW, BLUE), combine(BLACK, BLACK, WHITE)},
                {combine(RED, YELLOW, GREEN), combine(RED, YELLOW, GREEN), combine(BLACK, BLACK, BLACK)},
        });
        // @formatter:on
    }

    private static Combination combine(Color... colors) {
        return new Combination(colors);
    }

    private CombinationValidator validator;
    private Combination guess;
    private Combination answer;

    public CombinationValidatorTest(Combination secret, Combination guess, Combination answer) {
        this.validator = new CombinationValidator(secret);
        this.guess = guess;
        this.answer = answer;

        assertThat(validator.getSecretCombination(), is(secret));
    }

    @Test
    public void testValidateReturnsExpectedAnswerForGuess() {
        assertThat(validator.validate(guess), is(answer));
    }

}
