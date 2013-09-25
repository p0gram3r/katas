package ae.codingdojo.wordslotmachine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class WordListProvider {

    private List<String> wordList = new LinkedList<String>();

    public WordListProvider(InputStream inStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            String line;
            do {
                line = reader.readLine();
                if (StringUtils.isNotEmpty(line)) {
                    wordList.add(line);
                }
            } while (line != null);

        } catch (Exception e) {
            // TODO lazy developer :-)
            e.printStackTrace();
        }
    }

    public List<String> getWordList() {
        return wordList;
    }
}
