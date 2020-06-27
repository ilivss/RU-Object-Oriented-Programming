package geometric;

import java.util.InputMismatchException;

/**
 *
 * @author s4707079 & s1037202
 */

public class Controller {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View();
        this.model = new Model(10);
    }

    public void start (){
        // welcome message
        // Please enter a command.

        String command = view.getCommand();

        while (!command.equals("quit")){
            switch(command){
                case "show"     :   view.print(model.show()); break;
                case "circle"   :   model.addCircle(view.getDouble(), view.getDouble(), view.getDouble()); break;
                case "rectangle":   model.addRectangle(view.getDouble(), view.getDouble(), view.getDouble(), view.getDouble()); break;
                case "move"     :   model.move(view.getInt(), view.getDouble(), view.getDouble()); break;
                case "remove"   :   try {
                                        model.remove(view.getInt());

                                    } catch (InputMismatchException e) {
                                        view.print(e.getMessage());
                                    }; break;
                case "sort"     :   String s = view.getNextLine();
                                    if (s.isEmpty()){
                                        model.sort();
                                    } else {
                                        model.sort(s.charAt(1));
                                    }; break;
                default         : view.wrongCommand(); break;
            }

            command = view.getCommand();
        }
    }

}
