package quiz;

import java.util.List;

public class Controller {
    private Game model;
    private GameUI view;

    public Controller (){
        model = new Game();;
        view = new GameUI();
    }

    public void start(){
        // welcome message
        model.loadExampleQuestions();                       // Load up example questions

        // First round
        for (Question q : model.getQuestions()){
            view.printQuestion(q.toString());               // Print question

            boolean answered = false;
            while (!answered){
                try {
                    if (model.checkAnswer(q, view.getAnswer())) {
                        model.addScoreRound(1, q.getScore());
                    } else {
                        model.getWrongQuestions().add(q);           // if wrong: add question to wrong list.
                    }
                    answered = true;
                }
                catch (ArrayIndexOutOfBoundsException e1) {
                    view.printWarning(e1.getMessage());
                }
                catch (Exception e2) {
                    view.printWarning(e2.getMessage());
                }
            }
        }

        // Second round
        for (Question q : model.getWrongQuestions()) {
            view.printQuestion(q.toString());

            boolean answered = false;
            while (!answered) {
                try {
                    if (model.checkAnswer(q, view.getAnswer())) {
                        model.addScoreRound(2, q.getScore());
                    }
                    answered = true;
                }
                catch (ArrayIndexOutOfBoundsException e1) {
                    view.printWarning(e1.getMessage());
                }
                catch (Exception e2) {
                    view.printWarning(e2.getMessage());
                }
            }
        }

        // Display score
        view.printScore(model.getScoreRound(1), model.getScoreRound(2), model.totalScore());

        // Ask to play again
        view.printPlayAgain();
        try {
            if (view.getAnswer().equals("y")) {
                Controller humanController = new Controller();
                humanController.start();
            }
        }
        catch (Exception e) {
            view.printWarning(e.getMessage());
        }
    }
}
