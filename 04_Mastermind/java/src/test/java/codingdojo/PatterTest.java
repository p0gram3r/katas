package codingdojo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PatterTest {

    private Color[] colors;
    private Pattern pattern;

    @Before
    public void before() {
        colors = new Color[] { Color.RED, Color.YELLOW, Color.BLUE };
        pattern = new Pattern(colors);
    }

    @Test
    public void testGetColorsReturnsConstructorParameter() {
        assertThat(pattern.getColors(), contains(colors));
    }

    @Test
    public void testEqualsReturnsTrueForSelf() {
        assertThat(pattern.equals(pattern), is(true));
    }

    @Test
    public void testEqualsReturnsTrueForPatternWithEqualColorArray() {
        Pattern other = new Pattern(colors);

        assertThat(pattern.equals(other), is(true));
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertThat(pattern.equals(null), is(false));
    }

    @Test
    public void testEqualsReturnsFalseForUnequalColorArray() {
        Pattern other = new Pattern(new Color[0]);

        assertThat(pattern.equals(other), is(false));
    }

    @Test
    public void testEqualsReturnsFalseForIncompatibleObject() {
        assertThat(pattern.equals("incompatible"), is(false));
    }

}
