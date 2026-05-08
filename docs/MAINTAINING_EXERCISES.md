# Exercise Maintenance Rules

Use this checklist when Codex adds or changes exercises.

## New Exercise

1. Implement `Exercise`.
2. Put search concepts and algorithms under `core`, not inside the exercise class.
3. Register the exercise in `ExerciseCatalog`.
4. Assign it to an `ExerciseCategory`.
5. Add or update the matching stage document in `docs/stages/`.
6. Keep `Searchlings` as the stable click-to-run entrypoint.

## New Stage

1. Add a value to `ExerciseCategory`.
2. Add a shared IDEA run configuration under `.run/Searchlings - Stage XX.run.xml`.
3. Add a stage document under `docs/stages/`.
4. Link the stage from `docs/README.md`.

## Project Conventions

- Prefer IntelliJ IDEA `.run` configurations over shell runners.
- Do not add JBang or shell entrypoints unless explicitly requested.
- Keep the root `README.md` short.
- Put long explanations in `docs/`.
- Put stage-specific learning notes in `docs/stages/`.
