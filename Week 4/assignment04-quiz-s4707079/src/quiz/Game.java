package quiz;

import java.util.LinkedList;
import java.util.List;

public class Game {


    public static enum Round {First, Second};

    private List<Question> questions;
    private List<Question> wrongQuestions;
    private int scoreRoundone;
    private int scoreRoundtwo;

    public Game (){
        questions = new LinkedList<>();
        wrongQuestions = new LinkedList<>();
        scoreRoundone = 0;
        scoreRoundtwo = 0;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> getWrongQuestions() {
        return wrongQuestions;
    }

    public int getScoreRound(int round) {
        switch (round){
            case 1: return scoreRoundone;
            case 2: return scoreRoundtwo;
            default: return -1;
        }
    }

    public void addScoreRound(int round, int i) {
        switch (round){
            case 1: scoreRoundone += i; break;
            case 2: scoreRoundtwo += i;  break;
        }
    }

    public void loadExampleQuestions() {
        questions.add(new OpenQuestion("What is the big O complexity of binary search?", "O(log N)"));
        questions.add(new OpenQuestion("How would you read an integer i from scanner s in Java?", "i = s.nextInt();", 2));
        questions.add(new OpenQuestion("What is the minimum amount of constructors you have to define for a class in Java?", "0", 2));

        questions.add(new MultipleChoiceQuestion("What is the best achievable complexity of in situ sorting?", new String[] { "O(N^2)", "O(N log N)", "O(N)", "O(log N)" }, 1, 4));
        questions.add(new MultipleChoiceQuestion("How do you print \"Hello world\" on a line in Java?", new String[] { "System.out.print(\"Hello world\");", "System.out.println(\"Hello world\");", "cout << \"Hello world\";" }, 1));
        questions.add(new MultipleChoiceQuestion("How do you read a non-empty word in Java using scanner s?", new String[] { "s.nextline()", "s.next(\"\\S+\")", "s.next(\"\\a*\")", "s.next(\"\\S*\")", "s.next(\"\\\\s+\")", "s.next(\"\\s+\")", "s.nextString(\"\\s*\")", "s.next(\"\\\\S+\")", "s.nextString()" }, 7, 1));

        questions.add(new ThisThatQuestion("Every class must have a constructor", "Right", "Wrong", 1));
        questions.add(new ThisThatQuestion("Is there a difference between an interface and an abstract class?", "Yes", "No", 0, 5));
        questions.add(new ThisThatQuestion("Is there a maximum to the amount of constructors a class can have in Java?", "Yes", "No", 1));
    }

    public boolean checkAnswer(Question q, String a) {
        return q.isCorrect(a);
    }

    public int totalScore(){
        int total = 0;

        for (Question q: questions) {
            total += q.getScore();
        }

        return total;
    }
}
