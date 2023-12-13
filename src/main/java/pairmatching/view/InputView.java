package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

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

    private void selectValidate(String input) {
        if (!validate.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 선택지에 적힌 숫자 혹은 문자를 입력해주세요.");
        }
    }
}
