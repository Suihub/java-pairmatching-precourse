package pairmatching.controller;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchController {
    private final InputView input;
    private final OutputView output;

    public MatchController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void start() {
        String select;
        while (true) {
            try {
                output.printSelect();
                select = input.inputSelect();
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        proceed(select);
    }

    private void proceed(String select) {

    }
}
