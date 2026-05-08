package me.catcodeme.search.corpus;

import me.catcodeme.search.core.Document;
import me.catcodeme.search.core.LinearSearchEngine;

/**
 * Tiny in-memory corpus used by the first exercises.
 *
 * <p>A real search system needs a loader for files, JSONL, databases, and
 * incremental updates. Starting with a hard-coded corpus removes those
 * distractions while we learn the ranking and indexing mechanics.</p>
 */
public final class SampleDocuments {
    private SampleDocuments() {
    }

    public static void loadInto(LinearSearchEngine engine) {
        engine.add(new Document(
                1,
                "Inverted index basics",
                "An inverted index maps each term to the documents that contain the term."));
        engine.add(new Document(
                2,
                "BM25 ranking",
                "BM25 improves keyword search by considering term frequency and document length."));
        engine.add(new Document(
                3,
                "Vector search",
                "Dense vector retrieval is useful for semantic search and hybrid ranking."));
        engine.add(new Document(
                4,
                "Search engine evaluation",
                "Precision, recall, MRR, and NDCG help measure search quality."));
    }
}
