package pairmatching.controller;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

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
        if (select.equals("1")) {
            pairMatch();
        }
    }

    private void pairMatch() {
        output.printSchedule();
        List<String> schedule;
        while (true) {
            try {
                schedule = input.inputSchedule();
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        createPair(schedule);
    }

    private void createPair(List<String> schedule) {
    }
}
