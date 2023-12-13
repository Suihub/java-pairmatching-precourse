package pairmatching.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class Pair {
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    public String writeMatchingResult() {
        return crews.stream()
                .map(Crew::getName)
                .collect(joining(" : "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(getCrewCode(), pair.getCrewCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCrewCode());
    }

    public String getCrewCode() {
        List<Crew> result = crewSort();

        return result.stream()
                .map(Crew::getCrewCode)
                .collect(joining());
    }

    private List<Crew> crewSort() {
        ArrayList<Crew> target = new ArrayList<>(crews);

        target.sort(Comparator.comparing(Crew::getName));

        return target;
    }
}
