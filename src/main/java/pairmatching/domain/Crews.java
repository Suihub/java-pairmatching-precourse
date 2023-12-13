package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Crews {
    private final List<String> crewNames;
    private final Level level;

    public Crews(List<String> crewNames, Level level) {
        this.crewNames = crewNames;
        this.level = level;
    }

    public Pairs pairMatching() {
        List<Crew> crews = convertCrews(shuffleNames());
        return new Pairs(convertPairs(crews));
    }

    private List<String> shuffleNames() {
        return Randoms.shuffle(crewNames);
    }

    private List<Crew> convertCrews(List<String> shuffleNames) {
        return shuffleNames.stream()
                .map(name -> new Crew(level, name))
                .collect(Collectors.toList());
    }

    private List<Pair> convertPairs(List<Crew> crews) {
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < crews.size(); i+=2) {
            if (crews.size() - i == 3) {
                Pair pair = new Pair(crews.subList(i, i + 2));
                pairs.add(pair);
                break;
            }
            Pair pair = new Pair(crews.subList(i, i + 1));
            pairs.add(pair);
        }
        return pairs;
    }
}
