package pairmatching;

import pairmatching.controller.MatchController;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MatchController controller = new MatchController(new InputView(), new OutputView());
        controller.run();
    }
}
