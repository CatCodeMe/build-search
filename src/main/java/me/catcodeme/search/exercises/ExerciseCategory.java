package me.catcodeme.search.exercises;

/**
 * High-level learning track for grouping exercises.
 *
 * <p>The id is intentionally short because it is used from the command line,
 * for example {@code sh scripts/run-exercise stage-01}.</p>
 */
public enum ExerciseCategory {
    STAGE_01_BASELINE("stage-01", "Baseline search"),
    STAGE_02_INDEXING("stage-02", "Inverted index"),
    STAGE_03_RANKING("stage-03", "Ranking models"),
    STAGE_04_EVALUATION("stage-04", "Search evaluation");

    private final String id;
    private final String title;

    ExerciseCategory(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }
}
