package me.catcodeme.search.core;

/**
 * A ranked hit returned by the search engine.
 *
 * <p>The score is intentionally an integer in the first exercise. Later, TF-IDF
 * and BM25 will move this to a floating-point score with an explain tree.</p>
 */
public record SearchResult(Document document, int score) implements Comparable<SearchResult> {
    @Override
    public int compareTo(SearchResult other) {
        // Higher scores rank first. Stable tie-breaking by document id keeps
        // repeated runs deterministic, which matters when comparing exercises.
        int byScore = Integer.compare(other.score, score);
        if (byScore != 0) {
            return byScore;
        }
        return Integer.compare(document.id(), other.document.id());
    }
}
