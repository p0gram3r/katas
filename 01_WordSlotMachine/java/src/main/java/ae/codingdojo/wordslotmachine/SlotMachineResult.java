package ae.codingdojo.wordslotmachine;

public class SlotMachineResult {

    private boolean win;
    private String word;
    private String match;

    public boolean isWin() {
        return win;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
