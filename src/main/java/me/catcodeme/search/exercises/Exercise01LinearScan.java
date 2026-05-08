package me.catcodeme.search.exercises;

import me.catcodeme.search.core.Document;
import me.catcodeme.search.core.LinearSearchEngine;
import me.catcodeme.search.core.SearchResult;
import me.catcodeme.search.core.SimpleTokenizer;
import me.catcodeme.search.corpus.SampleDocuments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Exercise 01: build the slowest useful search engine first.
 *
 * <p>This exercise scans every document for every query. That is deliberately
 * inefficient, but it gives us a baseline. Later, when we add an inverted
 * index, we can prove the index helped by comparing both result quality and
 * latency against this class.</p>
 *
 * <p>Try changing the query, adding more documents in {@link SampleDocuments},
 * and observing how the hit count and latency move.</p>
 */
public final class Exercise01LinearScan implements Exercise {
    private static final Logger log = LoggerFactory.getLogger(Exercise01LinearScan.class);

    @Override
    public String id() {
        return "exercise-01";
    }

    @Override
    public ExerciseCategory category() {
        return ExerciseCategory.STAGE_01_BASELINE;
    }

    @Override
    public String title() {
        return "Linear scan baseline";
    }

    @Override
    public String defaultQuery() {
        return "bm25 index";
    }

    @Override
    public void run(String query) {
        LinearSearchEngine engine = new LinearSearchEngine(new SimpleTokenizer());
        SampleDocuments.loadInto(engine);

        log.info("Goal: scan every document, score keyword matches, and return the best hits.");
        log.info("Query: '{}'", query);

        long startNanos = System.nanoTime();
        List<SearchResult> results = engine.search(query, 10);
        long tookMicros = (System.nanoTime() - startNanos) / 1_000;

        log.info("Search finished: took={} micros, hits={}", tookMicros, results.size());
        for (int rank = 0; rank < results.size(); rank++) {
            SearchResult result = results.get(rank);
            Document document = result.document();
            log.info("#{} doc={} score={} title='{}'",
                    rank + 1,
                    document.id(),
                    result.score(),
                    document.title());
        }

        log.info("Next task: replace repeated full scans with an inverted index: term -> postings.");
    }
}
