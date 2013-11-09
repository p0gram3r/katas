package codingdojo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CombinationTest {

    private Color[] colors;
    private Combination combination;

    @Before
    public void before() {
        colors = new Color[] { Color.RED, Color.YELLOW, Color.BLUE };
        combination = new Combination(colors);
    }

    @Test
    public void testGetColorsReturnsConstructorParameter() {

        assertThat(combination.getColors(), is(colors));
    }

    @Test
    public void testEqualsReturnsTrueForSelf() {
        assertThat(combination.equals(combination), is(true));
    }

    @Test
    public void testEqualsReturnsTrueForCombinationWithEqualColorArray() {
        Combination other = new Combination(colors);

        assertThat(combination.equals(other), is(true));
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertThat(combination.equals(null), is(false));
    }

    @Test
    public void testEqualsReturnsFalseForUnequalColorArray() {
        Combination other = new Combination(new Color[0]);

        assertThat(combination.equals(other), is(false));
    }

    @Test
    public void testEqualsReturnsFalseForIncompatibleObject() {
        assertThat(combination.equals("incompatible"), is(false));
    }

}
