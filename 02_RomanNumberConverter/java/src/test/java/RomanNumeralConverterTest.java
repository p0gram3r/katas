import static org.assertj.core.api.Assertions.assertThat;

import it.p0gam3r.RomanNumeralConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralConverterTest {

    private RomanNumeralConverter converter;

    @BeforeEach
    public void beforeEach() {
        converter = new RomanNumeralConverter();
    }

    @ParameterizedTest
    @CsvSource({
            "1000, M",
            "500, D",
            "100, C",
            "50, L",
            "10, X",
            "5, V",
            "1, I",
    })
    void convertSimpleNumbers(int number, String expectedResult) {
        assertThat(converter.numberToNumeral(number)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "3000, MMM",
            "3, III",
            "4, IV",
            "6, VI",
            "10, X",
            "14, XIV",
            "15, XV",
            "16, XVI",
            "1337, MCCCXXXVII",
    })
    void convertNumbers(int number, String expectedResult) {
        assertThat(converter.numberToNumeral(number)).isEqualTo(expectedResult);
    }

}
