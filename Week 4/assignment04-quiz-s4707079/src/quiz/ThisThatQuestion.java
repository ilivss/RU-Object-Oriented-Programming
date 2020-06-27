package quiz;

public class ThisThatQuestion extends MultipleChoiceQuestion {
    public ThisThatQuestion(String question, String answer1, String answer2, int correctAnswer, int score){
        super(question, new String[]{answer1, answer2}, correctAnswer, score);
    }

    public ThisThatQuestion(String question, String answer1, String answer2, int correctAnswer){
        super(question, new String[]{answer1, answer2}, correctAnswer);
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer.equalsIgnoreCase(getAnswers()[super.getCorrectAnswer()]);
    }

    @Override
    public String correctAnswer() {
        return super.getAnswers()[super.getCorrectAnswer()];
    }

    @Override
    public String toString() {
       return String.format("%s or %s: %s", super.getAnswers()[0], super.getAnswers()[1], super.getQuestion());
    }
}
