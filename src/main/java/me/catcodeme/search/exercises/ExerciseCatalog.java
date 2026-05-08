package me.catcodeme.search.exercises;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Central list of available exercises.
 *
 * <p>When a new lesson is added, register it here. This mirrors the style of
 * rustlings/ziglings: learners run the catalog, pick the next failing or
 * incomplete step, then make a small code change.</p>
 */
public final class ExerciseCatalog {
    private final List<Exercise> exercises;

    public ExerciseCatalog() {
        this.exercises = List.of(
                new Exercise01LinearScan()
        );
    }

    public List<Exercise> all() {
        return exercises;
    }

    public Exercise first() {
        return exercises.getFirst();
    }

    public Optional<Exercise> find(String id) {
        return exercises.stream()
                .filter(exercise -> exercise.id().equals(id))
                .findFirst();
    }

    public Optional<ExerciseCategory> findCategory(String id) {
        for (ExerciseCategory category : ExerciseCategory.values()) {
            if (category.id().equals(id)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }

    public List<Exercise> byCategory(ExerciseCategory category) {
        return exercises.stream()
                .filter(exercise -> exercise.category() == category)
                .toList();
    }

    public Map<ExerciseCategory, List<Exercise>> groupedByCategory() {
        return exercises.stream()
                .collect(Collectors.groupingBy(Exercise::category));
    }
}
