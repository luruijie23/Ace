package boggle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * The BoggleDictionaryReader will analyze the words in the dictionary text file
 */
public class BoggleDictionaryReader {

    /**
     * set of legal words for Boggle
     */
    private final TreeSet<String> legalWords;

    /**
     * Class constructor
     *
     * @param filename the file containing a list of legal words.
     */
    public BoggleDictionaryReader(String filename) {
        legalWords = new TreeSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int wordCount = 0;

            while ((line = br.readLine()) != null) {
                // Trim leading and trailing whitespace from the line.
                line = line.strip();

                if (!line.isEmpty()) {
                    legalWords.add(line);
                    wordCount++;
                }
            }

            System.out.println("Initialized " + wordCount + " words in the Dictionary.");
        } catch (IOException e) {
            // Handle any IO exception by printing the stack trace.
            e.printStackTrace();
            throw new RuntimeException("Error reading the dictionary file.");
        }
    }

    /**
     * Get the set of legal words from the dictionary.
     *
     * @return A TreeSet containing legal words.
     */

    /*
     * Checks to see if a provided word is in the dictionary.
     *
     * @param word  The word to check
     * @return  A boolean indicating if the word has been found
     */
    public boolean hasWord(String word){
        for (String legalWord : legalWords) {
            word = word.toLowerCase();
            if (word.equals(legalWord)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Checks to see if a provided string is a prefix of any word in the dictionary.
     *
     * @param str  The string to check
     * @return  A boolean indicating if the string has been found as a prefix
     */
    public boolean startswith(String str){
        boolean prefix = true;
        for (String legalWord : legalWords) {
            str = str.toLowerCase();
            int i = 0;
            if(str.length() <= legalWord.length()) {
                while (i <= str.length() - 1 && i <= legalWord.length() - 1) {
                    if (str.charAt(i) == legalWord.charAt(i)) {
                        prefix = true;
                    } else if (str.charAt(i) != legalWord.charAt(i)) {
                        prefix = false;
                        break;
                    }
                    i++;
                }
                if (prefix) {
                    return true;
                }
            }
        }
        return false;
    }

}

