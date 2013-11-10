package ae.codingdojo.wordslotmachine;

import java.util.Arrays;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

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
        result.setMatch(null);
        result.setWord(word);
        result.setWin(false);

        for (String wordInWordList : wordListProvider.getWordList()) {
            if (matchesPermutations(word, wordInWordList)) {
                result.setMatch(wordInWordList);
                result.setWin(true);
                break;
            }
        }

        return result;
    }

    public static boolean matchesPermutations(String a, String b) {

        if (null == a || null == b) {
            return false;
        }

        if (StringUtils.length(a) != StringUtils.length(b)) {
            return false;
        }

        char[] charsA = a.toLowerCase().toCharArray();
        char[] charsB = b.toLowerCase().toCharArray();

        Arrays.sort(charsA);
        Arrays.sort(charsB);

        for (int i = 0; i < charsA.length; i++) {
            if (charsA[i] != charsB[i]) {
                return false;
            }
        }

        return true;
    }
}
