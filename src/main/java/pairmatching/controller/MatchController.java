package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class MatchController {
    private final InputView input;
    private final OutputView output;
    private MatchingResult match;

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
        if (select.equals("2")) {
            scheduleAnnounce();
            pairCheck();
        }
        if (select.equals("3")) {
            matchDelete();
        }
    }

    private void matchDelete() {
        match = null;
        start();
    }

    private void scheduleAnnounce() {
        output.printSchedule();
    }

    private void pairCheck() {
        List<String> schedule = createSchedule();

        try {
            Course course = Course.CourseSetting(schedule.get(0));
            Level level = Level.levelSetting(schedule.get(1));
            Mission mission = Mission.missionSetting(level.getName(), schedule.get(2));
            pairCheckPrevious(course, level, mission);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            pairCheck();
        }
    }

    private void pairCheckPrevious(Course course, Level level, Mission mission) {
        if (match == null || match.judgeCondition(course, level, mission)) {
            throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
        }
        resultMathing();
    }

    private void pairMatch() {
        List<String> schedule = createSchedule();

        try {
            Course course = Course.CourseSetting(schedule.get(0));
            Level level = Level.levelSetting(schedule.get(1));
            Mission mission = Mission.missionSetting(level.getName(), schedule.get(2));
            checkPrevious(course, level, mission);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            pairMatch();
        }
    }

    private void checkPrevious(Course course, Level level, Mission mission) {
        if (match != null && match.judgeCondition(course, level, mission)) {
            checkReMatch(course, level, mission);
            return;
        }
        createCrews(course, level, mission);
    }

    private void checkReMatch(Course course, Level level, Mission mission) {
        String answer;
        while (true) {
            try {
                 answer = input.inputRematch();
                 break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        reMatchResult(answer, course, level, mission);
    }

    private void reMatchResult(String answer, Course course, Level level, Mission mission) {
        if (answer.equals("아니오")) {
            pairMatch();
            return;
        }
        createCrews(course, level, mission);
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

    private void createCrews(Course course, Level level, Mission mission) {
        Crews crews = new Crews(Course.getCrewNames(course), level);

        if (match != null) {
            proceedMatching(crews);
            return;
        }
        match = new MatchingResult(course, level, mission);
        match.plusResult(crews.pairMatching());
        resultMathing();
    }

    private void proceedMatching(Crews crews) {
        int count = 0;
        while (count < 3) {
            if (match.plusResult(crews.pairMatching())) {
                break;
            }
            count += 1;
        }

        if (count == 3) {
            reMatchFail();
            return;
        }
        resultMathing();
    }

    private void reMatchFail() {
        try {
            throw new IllegalArgumentException("[ERROR] 페어 매칭에 실패했습니다.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            start();
        }
    }

    private void resultMathing() {
        output.printResult(match.writeMatchingResult());
        start();
    }
}
