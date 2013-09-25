package ae.codingdojo.wordslotmachine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SlotMachineTest {

    private SlotMachine fixture;
    private WordListProvider wordListProvider;

    @Before
    public void setUp() {
        wordListProvider = Mockito.mock(WordListProvider.class);

        fixture = new SlotMachine(wordListProvider);
    }

    @Test
    public void testPlayReturnsMatch() {
        SlotMachineResult result = fixture.play();

        assertThat(result, is(notNullValue()));
        assertThat(result.isWin(), is(true));
    }
}
