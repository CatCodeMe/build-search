# Stage 02: Inverted Index

Goal: replace full document scanning with an index that maps each term to the documents that contain it.

## Planned Concepts

- Term dictionary.
- Posting list.
- Document frequency.
- Term frequency.
- AND / OR query execution.
- Comparing linear scan latency with indexed search latency.

## Planned Exercises

- `exercise-02`: build an in-memory inverted index.
- `exercise-03`: query the index with OR semantics.
- `exercise-04`: support AND semantics and compare candidate counts.

## Completion Criteria

- Explain why an inverted index avoids scanning every document.
- Print candidate document counts for each query term.
- Compare query latency against Stage 01 on the same corpus.
