package pairmatching.domain;

public class Crew {
    private final Level level;
    private final String name;

    public Crew(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public String getCrewCode() {
        return String.format("%s%s", level.getName(), name);
    }

    public String getName() {
        return name;
    }
}
