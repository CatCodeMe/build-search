package me.catcodeme.search.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Baseline search engine that scans every document for every query.
 *
 * <p>This is not how production search works. Its value is educational: it is
 * simple enough to reason about, and it gives us a control group before we
 * introduce the inverted index.</p>
 */
public final class LinearSearchEngine {
    private final SimpleTokenizer tokenizer;
    private final List<Document> documents = new ArrayList<>();

    public LinearSearchEngine(SimpleTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void add(Document document) {
        documents.add(document);
    }

    public List<SearchResult> search(String query, int limit) {
        // Query analysis and document analysis must use the same tokenizer.
        // If they differ, the query term and indexed/stored terms may never
        // match even when the visible text looks related to a human.
        List<String> queryTerms = tokenizer.tokenize(query);
        List<SearchResult> results = new ArrayList<>();

        for (Document document : documents) {
            int score = score(document, queryTerms);
            if (score > 0) {
                results.add(new SearchResult(document, score));
            }
        }

        results.sort(SearchResult::compareTo);
        if (results.size() <= limit) {
            return results;
        }
        return List.copyOf(results.subList(0, limit));
    }

    private int score(Document document, List<String> queryTerms) {
        List<String> titleTerms = tokenizer.tokenize(document.title());
        List<String> bodyTerms = tokenizer.tokenize(document.body());

        int score = 0;
        for (String queryTerm : queryTerms) {
            // Titles are usually a stronger relevance signal than body text.
            // This hand-written boost is a primitive version of field weighting.
            score += countMatches(titleTerms, queryTerm) * 5;
            score += countMatches(bodyTerms, queryTerm);
        }
        return score;
    }

    private int countMatches(List<String> terms, String queryTerm) {
        int count = 0;
        for (String term : terms) {
            if (term.equals(queryTerm)) {
                count++;
            }
        }
        return count;
    }
}
