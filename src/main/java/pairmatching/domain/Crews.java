package pairmatching.domain;

import java.util.List;

public class Crews {
    private final List<String> crewNames;
    private final Level level;

    public Crews(List<String> crewNames, Level level) {
        this.crewNames = crewNames;
        this.level = level;
    }
}
