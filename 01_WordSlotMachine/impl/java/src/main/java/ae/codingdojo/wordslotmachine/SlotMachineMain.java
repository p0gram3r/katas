package ae.codingdojo.wordslotmachine;

public class SlotMachineMain {

    public static void main(String[] args) {

        WordListProvider wordListProvider = new WordListFile(SlotMachineMain.class.getClassLoader().getResourceAsStream("Worte-Top10k-De.txt"));
        int numberOfSlots = 5;
        char[] allowedCharacters = "abcdefghijklmnopqrstuvwxyzßäüö".toCharArray();
        SlotMachine machine = new SlotMachine(wordListProvider, numberOfSlots, allowedCharacters);

        for (int i = 0; i < 1000; i++) {
            SlotMachineResult result = machine.play();

            System.out.println("Try #" + i);
            System.out.println(result.isWin() ? "WIN!" : "LOSE...");
            System.out.println("Tried: " + result.getWord());
            if (result.isWin()) {
                System.out.println("Matches: " + result.getMatch());
                break;
            }
            System.out.println();
        }
    }

}
