package pairmatching.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static List<String> getCrewNames(Course course) {
        String name = course.getName();

        try {
            if (name.equals("백엔드")) {
                return Files.readAllLines(Paths.get("src/main/resources/backend-crew.md"));
            }
            return Files.readAllLines(Paths.get("src/main/resources/frontend-crew.md"));
        } catch(IOException exception) {
            throw new IllegalArgumentException("[ERROR] 크루 이름을 불러오는데 실패했습니다.");
        }
    }

    public static Course CourseSetting(String input) {
        Optional<Course> result = Arrays.stream(Course.values())
                .filter(course -> course.name.equals(input))
                .findAny();

        return result.orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 코스입니다."));
    }

    public String getName() {
        return name;
    }
}
