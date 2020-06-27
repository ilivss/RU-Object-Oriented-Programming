package quiz;

public class OpenQuestion extends Question {
    private String answer;

    public  OpenQuestion(String  question, String  answer, int score){
        super(question, score);
        this.answer = answer;
    }

    public  OpenQuestion(String  question , String  answer){
        super(question);
        this.answer = answer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    @Override
    public String correctAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return super.getQuestion();
    }


}
