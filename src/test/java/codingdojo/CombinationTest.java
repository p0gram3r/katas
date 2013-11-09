package codingdojo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Color;

import org.junit.Test;

public class CombinationTest {

    @Test
    public void testGetColorsReturnsConstructorParameter() {
        Color[] colors = new Color[] { Color.RED, Color.YELLOW, Color.BLUE };

        Combination c = new Combination(colors);
        assertThat(c.getColors(), is(colors));
    }

}
