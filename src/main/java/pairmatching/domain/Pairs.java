package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pairs {
    private final List<Pair> pairs;

    public Pairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<String> writeMatchingResult() {
        return pairs.stream()
                .map(Pair::writeMatchingResult)
                .collect(Collectors.toList());
    }

    public boolean duplicateValidate(Pairs targetPairs) {
        List<Pair> subPairs = new ArrayList<>(pairs);
        subPairs.removeAll(targetPairs.getPairs());
        return pairs.size() != subPairs.size();
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }
}
