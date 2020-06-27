package hangman;

public class HangmanTester {

	private Gallows testgame;

	public void newGallows(String word) {
		testgame = new Gallows(word);
	}

	public String getWordSoFar() {

		return testgame.getSecret();
	}

	public void guessLetter(char c) {
		testgame.guessLetter(c);
	}

	public String getGuessedLetters() {

		return testgame.getGuessedletters();
	}
	
	public boolean isWordGuessed() {

		return testgame.isWordguessed();
	}
}
