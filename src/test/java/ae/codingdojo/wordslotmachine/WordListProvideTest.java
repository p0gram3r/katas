package ae.codingdojo.wordslotmachine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class WordListProvideTest {

    private WordListProvider fixture;

    @Before
    public void setUp() throws Exception {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("testWordList.txt");

        fixture = new WordListProvider(inStream);
    }

    @Test
    public void testGetWordList() {
        assertThat(fixture.getWordList(), is(notNullValue()));
        assertThat(fixture.getWordList(), hasSize(2));
        assertThat(fixture.getWordList(), contains("hello", "world"));
    }
}
