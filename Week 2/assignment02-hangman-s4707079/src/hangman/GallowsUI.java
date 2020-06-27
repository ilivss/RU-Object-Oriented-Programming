package hangman;

import java.util.Scanner;
/**
 *
 * @author s4707079
 */
public class GallowsUI {

    private Gallows hangman;
    private Scanner scan;

    public GallowsUI() {
        scan = new Scanner(System.in);
    }

    public void nanach () {
        System.out.println("Welcome to Hangman!");
        System.out.println("Please enter a word or press Enter to randomly pick one");

        scan.useDelimiter("[^a-zA-Z]+");
        String input = scan.nextLine();

        if (input.isEmpty()) {
            hangman = new Gallows();
            System.out.println("Randomly picking a word.");
        }
        else {
            hangman = new Gallows(input);
        }
        printStats();

        while (!hangman.isWordguessed() && !hangman.checkMistakes()) {
            System.out.println("Enter a letter: ");
            char c = scan.next().toLowerCase().charAt(0);
            try {
                hangman.guessLetter(c);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            printStats();
        }

        if (hangman.isWordguessed() && !hangman.checkMistakes()) {
            System.out.println("You saved hangman!");
        }
        else {
            System.out.println("Unfortunately hangman was hanged.");
        }

        System.out.println("\nEnter y to try again or any other key to quit");
        char c = scan.next().toLowerCase().charAt(0);
        if (c == 'y') {
            GallowsUI newgame = new GallowsUI();

            newgame.nanach();
        }
    }

    private void printStats() {
        System.out.println("\nRemaining mistakes: " + hangman.getMistakes());
        System.out.println("Guessed letters: " + hangman.getGuessedletters());
        System.out.println("Word: " + hangman.getSecret() + "\n");
    }
}
