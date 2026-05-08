package me.catcodeme.search.app;

import me.catcodeme.search.exercises.Exercise;
import me.catcodeme.search.exercises.ExerciseCategory;
import me.catcodeme.search.exercises.ExerciseCatalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public final class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private Main() {
    }

    public static void main(String[] args) {
        ExerciseCatalog catalog = new ExerciseCatalog();

        if (args.length == 0) {
            runClickDefault(catalog);
            return;
        }

        String command = args[0];
        if ("list".equals(command)) {
            listCatalog(catalog);
            return;
        }

        catalog.find(command)
                .ifPresentOrElse(
                        exercise -> run(exercise, queryOrDefault(args, exercise)),
                        () -> runCategoryOrList(catalog, command, args)
                );
    }

    private static void runClickDefault(ExerciseCatalog catalog) {
        Exercise exercise = catalog.first();
        log.info("No arguments provided. Running the first guided exercise.");
        log.info("Use 'list' to view categories, or pass a stage/exercise id to run a specific target.");
        run(exercise, exercise.defaultQuery());
    }

    private static void run(Exercise exercise, String query) {
        log.info("Running {} / {} - {}",
                exercise.category().id(),
                exercise.id(),
                exercise.title());
        exercise.run(query);
    }

    private static void runCategoryOrList(ExerciseCatalog catalog, String command, String[] args) {
        catalog.findCategory(command)
                .ifPresentOrElse(
                        category -> runCategory(catalog, category, args),
                        () -> {
                            log.warn("Unknown command '{}'.", command);
                            listCatalog(catalog);
                        }
                );
    }

    private static void runCategory(ExerciseCatalog catalog, ExerciseCategory category, String[] args) {
        List<Exercise> exercises = catalog.byCategory(category);
        if (exercises.isEmpty()) {
            log.warn("{} has no exercises yet.", category.id());
            return;
        }

        log.info("Running category {} - {}", category.id(), category.title());
        for (Exercise exercise : exercises) {
            run(exercise, queryOrDefault(args, exercise));
        }
    }

    private static void listCatalog(ExerciseCatalog catalog) {
        log.info("Available exercise categories:");
        Map<ExerciseCategory, List<Exercise>> grouped = catalog.groupedByCategory();
        for (ExerciseCategory category : ExerciseCategory.values()) {
            List<Exercise> exercises = grouped.getOrDefault(category, List.of());
            log.info("{} - {} ({} exercise(s))", category.id(), category.title(), exercises.size());
            for (Exercise exercise : exercises) {
                log.info("  {} - {}", exercise.id(), exercise.title());
            }
        }
    }

    private static String queryOrDefault(String[] args, Exercise exercise) {
        if (args.length >= 2) {
            return joinTail(args);
        }
        return exercise.defaultQuery();
    }

    private static String joinTail(String[] args) {
        StringBuilder query = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (!query.isEmpty()) {
                query.append(' ');
            }
            query.append(args[i]);
        }
        return query.toString();
    }
}
