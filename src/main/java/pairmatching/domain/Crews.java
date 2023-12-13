package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class Crews {
    private final List<String> crewNames;
    private final Level level;

    public Crews(List<String> crewNames, Level level) {
        this.crewNames = crewNames;
        this.level = level;
    }

    private List<String> shuffleNames() {
        return Randoms.shuffle(crewNames);
    }
}
