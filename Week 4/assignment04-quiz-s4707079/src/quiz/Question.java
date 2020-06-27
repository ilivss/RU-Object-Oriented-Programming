package quiz;

public abstract class Question {
    private String question;
    private int score;

    public Question (String question, int score){
        this.question = question;

        if(0 < score && score < 6){
            this.score = score;
        } else {
            this.score = 3;
        }
    }

    public Question (String question){
        this.question = question;
        this.score = 3;
    }

    public String getQuestion() {
        return question;
    }

    public int getScore() {
        return score;
    }

    public abstract boolean isCorrect(String answer);

    public abstract String correctAnswer ();

    public abstract String toString ();
}
