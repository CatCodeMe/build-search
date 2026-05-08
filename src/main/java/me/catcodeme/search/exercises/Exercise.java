package me.catcodeme.search.exercises;

/**
 * One guided learning step.
 *
 * <p>The project is intentionally organized like a small "lings" course:
 * each exercise introduces one search-engine idea, runs a tiny experiment,
 * and leaves a focused next task. Keeping exercises small makes it easier to
 * compare one concept against the previous baseline.</p>
 */
public interface Exercise {
    /**
     * Stable id used from the command line, for example {@code exercise-01}.
     */
    String id();

    /**
     * Category used to keep the catalog readable as exercises grow.
     */
    ExerciseCategory category();

    /**
     * Human-readable title shown in logs.
     */
    String title();

    /**
     * Query used when this exercise is run without command-line arguments.
     */
    String defaultQuery();

    /**
     * Runs the exercise. The method should be deterministic so that repeated
     * runs make performance and ranking changes easy to compare.
     */
    void run(String query);
}
