package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchingResult {
    private final Course course;
    private final Level level;
    private final List<Pairs> matching;

    public MatchingResult(Course course, Level level) {
        this.course = course;
        this.level = level;
        this.matching = new ArrayList<>();
    }

    public boolean plusResult(Pairs pairs) {
        if (judgeDuplicate(pairs)) {
            return false;
        }
        matching.add(pairs);
        return true;
    }

    private boolean judgeDuplicate(Pairs pairs) {
        for (int i = 0; i < matching.size(); i++) {
            Pairs standard = matching.get(i);

            if (standard.duplicateValidate(pairs)) {
                return true;
            }
        }

        return false;
    }

    public boolean judgeCondition(Course course, Level level) {
        return this.course == course && this.level == level;
    }

    public List<String> writeMatchingResult() {
        Pairs pairs = matching.get(matching.size() - 1);
        return pairs.writeMatchingResult();
    }
}
