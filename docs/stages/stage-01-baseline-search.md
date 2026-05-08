# Stage 01: Baseline Search

Goal: build the slowest useful search engine first, then use it as the baseline for every later optimization.

## Current Exercise

- `exercise-01`: linear scan baseline.

## Implemented Concepts

- `Document`: document model.
- `SimpleTokenizer`: lowercase and letter/digit tokenization.
- `LinearSearchEngine`: scan all documents, score keyword matches, and return top results.
- `SearchResult`: deterministic score ordering.
- `SampleDocuments`: tiny in-memory corpus.

## Scoring Rule

- Title match: `+5`.
- Body match: `+1`.

This rule is intentionally simple. Later stages will replace it with TF-IDF, BM25, and explainable scoring.

## Why This Stage Matters

A baseline makes improvements measurable. Before building an inverted index, you should be able to explain why full scanning is simple, correct for tiny data, and too slow for larger corpora.

## Completion Status

Exercise 01 is understood and complete.

## Next Step

Move to Stage 02 and replace repeated full scans with an inverted index:

```text
term -> postings
```
