package quiz;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameUI {
    private Scanner in = new Scanner(System.in);

    public void printQuestion(String s){
        System.out.print(String.format("\n%s\n", s));
    }

    public String getAnswer() throws Exception {
        String out = in.nextLine().toLowerCase();

        if (out.length() > 0){
            return out;
        }
        else {
            throw new Exception("Enter an answer!");
        }
    }

    public void printWarning(String e){
        System.out.println(String.format("Warning: %s", e));
    }

    public void printScore(int scoreRoundone, int scoreRoundtwo, int totalScore) {
        System.out.println("Score overview:");
        System.out.println(String.format("You scored %d out of %d points in the first round.", scoreRoundone, totalScore));
        System.out.println(String.format("You scored %d out of %d (remaining points after round 1) in the second round.", scoreRoundtwo, totalScore-scoreRoundone));
        System.out.println(String.format("%d points were left.", totalScore-scoreRoundone-scoreRoundtwo));
    }

    public void printPlayAgain() {
        System.out.println("Play again? y/n");
    }
}
