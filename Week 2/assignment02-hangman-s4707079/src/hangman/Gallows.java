package hangman;
/**
 *
 * @author s4707079 & s1037202
 */
public class Gallows {

    private String word;
    private StringBuilder secret;
    private StringBuilder guessedletters;
    private int mistakes;

    public Gallows(String word) {                                                                                       // Constructor with own word.
        this.word = word;
        this.secret = new StringBuilder(initiateSecret(word.length()));
        this.guessedletters = new StringBuilder();
        this.mistakes = 10;
    }

    public Gallows () {                                                                                                 // Constructor with randomly chosen word.
        WordReader file = new WordReader("words.txt");

        this.word = file.getWord();
        this.secret = new StringBuilder(initiateSecret(word.length()));
        this.guessedletters = new StringBuilder();
        this.mistakes = 10;
    }

    private String initiateSecret (int size) {                                                                          // Returns a string consisting of only dots with same length as word.
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append('.');
        }

        return output.toString();
    }

    private boolean isLetterinWord (char c) {
        return word.indexOf(c) != -1;
    }

    private boolean isLetteringuessedletters (char c) {
        return guessedletters.toString().indexOf(c) != -1;
    }

    private void updateSecret (char c) {
        int i = word.indexOf(c);

        while (i != -1) {
            secret.setCharAt(i, c);
            i = word.indexOf(c, ++i);
        }
    }

    public boolean checkMistakes () {
        return mistakes < 1;
    }

    public void guessLetter (char c) {

        if (checkMistakes()) {
            throw new UnsupportedOperationException("\nHanged! x(");
        }
        else {
            if (isLetteringuessedletters(c)) {
                throw new UnsupportedOperationException("\nLetter already tried. Look at the Guessed letters...");
            }
            else {
                guessedletters.append(c);

                if (isLetterinWord(c)) {
                    updateSecret(c);
                }
                else {
                    mistakes--;
                }
            }
        }
    }

    public boolean isWordguessed () {
        return secret.toString().compareTo(word) == 0;
    }

    public String getSecret() {
        return secret.toString();
    }

    public String getGuessedletters() {
        return guessedletters.toString();
    }

    public int getMistakes() {
        return mistakes;
    }
}
