package ae.codingdojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NumberToTextConverterTest {

    @Parameters
    public static Collection<Object[]> data() {
        /* @formatter:off */
        return Arrays.asList(new Object[][] {
                { 1, "one" },
                { 12, "twelve" },
                { 42, "forty two" },
                { 123, "one hundred twenty three" },
                { 1234, "one thousand two hundred thirty four" },
                { 12345, "twelve thousand three hundred forty five" },
                { 123456, "one hundred twenty three thousand four hundred fifty six" },
                { 1234567, "one million two hundred thirty four thousand five hundred sixty seven" },
        });
        /* @formatter:on */
    }

    private NumberToTextConverter converter;
    private int numberToConvert;
    private String expectedOutput;

    public NumberToTextConverterTest(int numberToConvert, String expectedOutput) {
        this.converter = new NumberToTextConverter();
        this.numberToConvert = numberToConvert;
        this.expectedOutput = expectedOutput;
    }

    @Test
    public void test() {
        assertThat(converter.convert(numberToConvert), is(expectedOutput));
    }

}
