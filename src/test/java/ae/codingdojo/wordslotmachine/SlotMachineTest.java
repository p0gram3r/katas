package ae.codingdojo.wordslotmachine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SlotMachineTest {

    private WordListProvider wordListProvider;

    @Before
    public void setUp() {
        wordListProvider = Mockito.mock(WordListProvider.class);
    }

    @Test
    public void testPlayReturnsMatch() {
        List<String> list = Arrays.asList("aaa");
        Mockito.when(wordListProvider.getWordList()).thenReturn(list);

        SlotMachine machine = new SlotMachine(wordListProvider, 3, "a".toCharArray());
        SlotMachineResult result = machine.play();

        assertThat(result, is(notNullValue()));
        assertThat(result.isWin(), is(true));
        assertThat(result.getMatch(), is("aaa"));
        assertThat(result.getWord(), is("aaa"));
    }

    @Test
    public void testPlayReturnsMismatch() {
        List<String> list = Arrays.asList("bbb");
        Mockito.when(wordListProvider.getWordList()).thenReturn(list);

        SlotMachine machine = new SlotMachine(wordListProvider, 3, "a".toCharArray());
        SlotMachineResult result = machine.play();

        assertThat(result, is(notNullValue()));
        assertThat(result.isWin(), is(false));
        assertThat(result.getMatch(), is(nullValue()));
        assertThat(result.getWord(), is("aaa"));
    }

    @Test
    public void testMatchesPermutationsForDifferentStringLength() {
        String a = "abc";
        String b = "a";

        assertThat(SlotMachine.matchesPermutations(a, b), is(false));
    }

    @Test
    public void testMatchesPermutationsForDifferentStrings() {
        String a = "abc";
        String b = "aaa";

        assertThat(SlotMachine.matchesPermutations(a, b), is(false));
    }

    @Test
    public void testMatchesPermutationsForSimilarStrings() {
        String a = "abc";
        String b = "acb";

        assertThat(SlotMachine.matchesPermutations(a, b), is(true));
    }
}
