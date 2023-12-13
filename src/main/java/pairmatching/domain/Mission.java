package pairmatching.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Mission {
    CAR_RACE("자동차경주", "레벨1"),
    LOTTO("로또", "레벨1"),
    NUM_BASEBALL("숫자야구게임", "레벨1"),
    SHOPPING_BASKET("장바구니", "레벨2"),
    PAYMENT("결제", "레벨2"),
    SUBWAY_MAP("지하철노선도", "레벨2"),
    IMPROVEMENT("성능개선", "레벨4"),
    DISTRIBUTION("배포", "레벨4");

    private final String name;
    private final String level;

    Mission(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public static Mission missionSetting(String inputLevel, String missionName) {
        Optional<Mission> result = Arrays.stream(Mission.values())
                .filter(mission -> mission.level.equals(inputLevel))
                .filter(mission -> mission.name.equals(missionName))
                .findAny();

        return result.orElseThrow(() -> {
            throw new IllegalArgumentException("[ERROR] 해당 레벨에 존재하지 않는 미션입니다.");
        });
    }

    public String getName() {
        return name;
    }
}
