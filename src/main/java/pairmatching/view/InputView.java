package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private final List<String> validate;

    public InputView() {
        this.validate = new ArrayList<>();
        validate.add("1");
        validate.add("2");
        validate.add("3");
        validate.add("Q");
    }

    public String inputSelect() {
        String input = Console.readLine();
        selectValidate(input);
        return input;
    }

    public List<String> inputSchedule() {
        System.out.println("과정, 레벨, 미션을 선택하세요");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");

        List<String> schedule = Arrays.asList(Console.readLine().split(","));

        scheduleValidate(schedule);

        return schedule.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String inputRematch() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하겠습니까?");
        System.out.println("네 | 아니오");

        String input = Console.readLine();

        System.out.println();

        rematchValidate(input);
        return input;
    }

    private void selectValidate(String input) {
        if (!validate.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 선택지에 적힌 숫자 혹은 문자를 입력해주세요.");
        }
    }

    private void scheduleValidate(List<String> schedule) {
        if (schedule.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 예시와 같이 입력해주세요.");
        }
    }

    private void rematchValidate(String input) {
        if (!input.equals("네") && input.equals("아니오")) {
            throw new IllegalArgumentException("[ERROR] 예시와 같이 입력해주세요.");
        }
    }
}
