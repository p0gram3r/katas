package codingdojo;

import static codingdojo.Color.BLACK;
import static codingdojo.Color.BLUE;
import static codingdojo.Color.BROWN;
import static codingdojo.Color.GREEN;
import static codingdojo.Color.ORANGE;
import static codingdojo.Color.RED;
import static codingdojo.Color.WHITE;
import static codingdojo.Color.YELLOW;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PatternValidatorTest {

    @Parameters
    public static Collection<Object[]> data() {
        // @formatter:off
        return Arrays.asList(new Object[][] { 
                // 2 unique colors in both pattern
                {pattern(RED, YELLOW), pattern(BLUE, ORANGE), pattern()},
                {pattern(RED, YELLOW), pattern(YELLOW, ORANGE), pattern(WHITE)},
                {pattern(RED, YELLOW), pattern(BLUE, RED), pattern(WHITE)},
                {pattern(RED, YELLOW), pattern(YELLOW, RED), pattern(WHITE, WHITE)},
                {pattern(RED, YELLOW), pattern(RED, ORANGE), pattern(BLACK)},
                {pattern(RED, YELLOW), pattern(BLUE, YELLOW), pattern(BLACK)},
                {pattern(RED, YELLOW), pattern(RED, YELLOW), pattern(BLACK, BLACK)},
                
                // 3 unique colors in both pattern
                {pattern(RED, YELLOW, GREEN), pattern(BLUE, ORANGE, BROWN), pattern()},
                {pattern(RED, YELLOW, GREEN), pattern(YELLOW, ORANGE, BROWN), pattern(WHITE)},
                {pattern(RED, YELLOW, GREEN), pattern(YELLOW, GREEN, BROWN), pattern(WHITE, WHITE)},
                {pattern(RED, YELLOW, GREEN), pattern(YELLOW, GREEN, RED), pattern(WHITE, WHITE, WHITE)},
                {pattern(RED, YELLOW, GREEN), pattern(BLUE, YELLOW, GREEN), pattern(BLACK, BLACK)},
                {pattern(RED, YELLOW, GREEN), pattern(RED, YELLOW, BROWN), pattern(BLACK, BLACK)},
                {pattern(RED, YELLOW, GREEN), pattern(RED, ORANGE, GREEN), pattern(BLACK, BLACK)},
                {pattern(RED, YELLOW, GREEN), pattern(RED, YELLOW, GREEN), pattern(BLACK, BLACK, BLACK)},
                
                // 2 colors with duplicates in guess
                {pattern(RED, YELLOW), pattern(BLUE, BLUE), pattern()},
                {pattern(RED, YELLOW), pattern(YELLOW, YELLOW), pattern(BLACK)},
                {pattern(RED, YELLOW), pattern(RED, RED), pattern(BLACK)},
                
                // 3 colors with duplicates in guess
                {pattern(RED, YELLOW, GREEN), pattern(BLUE, BLUE, BROWN), pattern()},
                {pattern(RED, YELLOW, GREEN), pattern(RED, RED, BROWN), pattern(BLACK)},
                {pattern(RED, YELLOW, GREEN), pattern(BLUE, RED, RED), pattern(WHITE)},
                {pattern(RED, YELLOW, GREEN), pattern(RED, ORANGE, RED), pattern(BLACK)},
                {pattern(RED, YELLOW, GREEN), pattern(RED, RED, RED), pattern(BLACK)},
                
                // 2 colors with duplicates in secret
                {pattern(RED, RED), pattern(BLUE, BLUE), pattern()},
                {pattern(RED, RED), pattern(YELLOW, RED), pattern(BLACK)},
                {pattern(RED, RED), pattern(RED, YELLOW), pattern(BLACK)},
                {pattern(RED, RED), pattern(RED, RED), pattern(BLACK, BLACK)},
                
                // 3 colors with duplicates in secret
                {pattern(RED, RED, YELLOW), pattern(BLUE, ORANGE, BROWN), pattern()},
                {pattern(RED, RED, YELLOW), pattern(RED, ORANGE, BROWN), pattern(BLACK)},
                {pattern(RED, RED, YELLOW), pattern(BLUE, RED, BROWN), pattern(BLACK)},
                {pattern(RED, RED, YELLOW), pattern(BLUE, ORANGE, RED), pattern(WHITE)},
                {pattern(RED, RED, YELLOW), pattern(RED, RED, BROWN), pattern(BLACK, BLACK)},
                {pattern(RED, RED, YELLOW), pattern(BLUE, RED, RED), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW), pattern(RED, ORANGE, RED), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW), pattern(RED, RED, YELLOW), pattern(BLACK, BLACK, BLACK)},
                
                // 4 colors with duplicates in secret and guess
                {pattern(RED, RED, YELLOW, YELLOW), pattern(BLUE, BLUE, BROWN, GREEN), pattern()},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, RED, BROWN, GREEN), pattern(BLACK, BLACK)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, BLUE, RED, GREEN), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, BLUE, BROWN, RED), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(BLUE, RED, RED, GREEN), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(BLUE, RED, BROWN, RED), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(BLUE, BLUE, RED, RED), pattern(WHITE, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, RED, RED, GREEN), pattern(BLACK, BLACK)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(BLUE, RED, RED, RED), pattern(BLACK, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, RED, RED, RED), pattern(BLACK, BLACK)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, RED, YELLOW, YELLOW), pattern(BLACK, BLACK, BLACK, BLACK)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(YELLOW, RED, RED, YELLOW), pattern(BLACK, BLACK, WHITE, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(YELLOW, YELLOW, RED, RED), pattern(WHITE, WHITE, WHITE, WHITE)},
                {pattern(RED, RED, YELLOW, YELLOW), pattern(RED, YELLOW, YELLOW, RED), pattern(BLACK, BLACK, WHITE, WHITE)},
        });
        // @formatter:on
    }

    private static Pattern pattern(Color... colors) {
        return new Pattern(colors);
    }

    private PatternValidator validator;
    private Pattern guess;
    private Pattern answer;

    public PatternValidatorTest(Pattern secret, Pattern guess, Pattern answer) {
        this.validator = new PatternValidator(secret);
        this.guess = guess;
        this.answer = answer;

        assertThat(validator.getSecretPattern(), is(secret));
    }

    @Test
    public void testValidateReturnsExpectedAnswerForGuess() {
        assertThat(validator.validate(guess), is(answer));
    }

}
