package ae.codingdojo.wordslotmachine;

import org.apache.commons.lang.RandomStringUtils;

public class SlotMachine {

    private WordListProvider wordListProvider;
    private int numberOfSlots;
    private char[] allowedCharacters;

    public SlotMachine(WordListProvider wordListProvider, int numberOfSlots, char[] allowedCharacters) {
        this.wordListProvider = wordListProvider;
        this.numberOfSlots = numberOfSlots;
        this.allowedCharacters = allowedCharacters;
    }

    public SlotMachineResult play() {

        String word = RandomStringUtils.random(numberOfSlots, allowedCharacters);

        SlotMachineResult result = new SlotMachineResult();
        result.setMatch("");
        result.setWord(word);
        result.setWin(true);

        return result;
    }
}
