package me.catcodeme.search.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Minimal tokenizer for early exercises.
 *
 * <p>Tokenizer behavior has a direct impact on search quality. For now we only
 * lowercase text and keep letter/digit runs. This makes examples predictable,
 * but it is not enough for real Chinese segmentation, stemming, synonyms, or
 * punctuation-sensitive queries.</p>
 */
public final class SimpleTokenizer {
    public List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        String normalized = text.toLowerCase(Locale.ROOT);
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < normalized.length(); i++) {
            char ch = normalized.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                current.append(ch);
            } else if (!current.isEmpty()) {
                // A non-token character closes the current token. For example,
                // "BM25-ranking" becomes ["bm25", "ranking"].
                tokens.add(current.toString());
                current.setLength(0);
            }
        }

        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }
        return tokens;
    }
}
