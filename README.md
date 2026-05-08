# build-search
build `search` with codex

## Goal

Learn search engine algorithms and system design through hands-on projects.

## Current Track

The first practice track is `mini-search` in Java.

This repo is being shaped as a guided exercise project, similar in spirit to
rustlings/ziglings: run one small exercise, inspect the comments and logs, then
make the next focused implementation change.

Click-to-run entrypoint in an IDE:

- `me.catcodeme.search.app.Searchlings`

Shared IntelliJ IDEA run configurations are also included:

- `Searchlings - Default`
- `Searchlings - List`
- `Searchlings - Stage 01`
- `Searchlings - Stage 02`
- `Searchlings - Stage 03`
- `Searchlings - Stage 04`

These `.run` files are part of the project and should be pushed so the same
one-click workflow works on another machine.

The project uses SLF4J with Logback for console logging. Application code calls
the SLF4J API; Logback is configured in `src/main/resources/logback.xml`.

Source layout:

- `app`: command-line entrypoint.
- `core`: search models, tokenization, and search algorithms.
- `corpus`: sample documents and future data loaders.
- `exercises`: guided lessons and exercise catalog.

Exercise organization:

- `stage-01`: baseline search.
- `stage-02`: inverted index.
- `stage-03`: ranking models.
- `stage-04`: search evaluation.

Codex workflow rule:

- When adding a new exercise, register it in `ExerciseCatalog`.
- If a new stage is added, add a matching `.run/Searchlings - Stage XX.run.xml`.
- Keep `Searchlings` as the stable click-to-run entrypoint.

Learning notes:

- [Search engine learning path](docs/SEARCH_ENGINE_LEARNING_PATH.md)
- [Java mini search plan](docs/JAVA_MINI_SEARCH_PLAN.md)
