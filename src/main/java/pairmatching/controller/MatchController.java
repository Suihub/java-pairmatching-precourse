package pairmatching.controller;

import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
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
            scheduleAnnounce();
            pairMatch();
        }
    }

    private void scheduleAnnounce() {
        output.printSchedule();
    }

    private void pairMatch() {
        List<String> schedule = createSchedule();
        createCrews(schedule);
    }

    private List<String> createSchedule() {
        List<String> schedule;
        while (true) {
            try {
                schedule = input.inputSchedule();
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return schedule;
    }

    private void createCrews(List<String> schedule) {
        try {
            Course course = Course.CourseSetting(schedule.get(0));
            Level level = Level.levelSetting(schedule.get(1));
            Mission.missionSetting(level.getName(), schedule.get(2));
            Crews crews = new Crews(Course.getCrewNames(course), level);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            pairMatch();
        }
    }
}
