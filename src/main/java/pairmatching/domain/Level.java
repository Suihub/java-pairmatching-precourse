package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level levelSetting(String input) {
        validate(input);

        Optional<Level> result = Arrays.stream(Level.values())
                .filter(level -> Objects.equals(level.name, input))
                .findAny();

        return result.orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 레벨입니다."));
    }

    public String getName() {
        return name;
    }

    private static void validate(String input) {
        if (input.equals("레벨3") || input.equals("레벨5")) {
            throw new IllegalArgumentException("[ERROR] 레벨3, 레벨5는 페어 매칭이 불가능합니다.");
        }
    }
}
