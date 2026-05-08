package me.catcodeme.search.core;

/**
 * Searchable unit in our learning engine.
 *
 * <p>Production search systems usually have many fields: title, body, category,
 * tags, price, publish time, permissions, and more. We start with only title
 * and body so the scoring math stays visible.</p>
 */
public record Document(int id, String title, String body) {
}
