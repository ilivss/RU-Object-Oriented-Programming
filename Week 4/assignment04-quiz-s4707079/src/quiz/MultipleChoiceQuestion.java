package quiz;

public class MultipleChoiceQuestion extends Question {

    private String[] answers;
    private int correctAnswer;

    public  MultipleChoiceQuestion(String  question , String[] answers , int  correctAnswer , int  score){
        super(question, score);
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public  MultipleChoiceQuestion(String  question , String[] answers , int  correctAnswer){
        super(question);
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        if (answer.charAt(0) - 97 < answers.length) {
            return answers[correctAnswer].equalsIgnoreCase(answers[answer.charAt(0) - 97]);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Please choose one of the answers!\n");
        }
    }

    @Override
    public String correctAnswer() {
        return String.format("%c", 97 + correctAnswer);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(String.format("%s\n", super.getQuestion()));

        int i = 97;

        for (String a : answers) {
            out.append(String.format("%c) %s\n", i++, a));
        }

        return out.toString();
    }
}
