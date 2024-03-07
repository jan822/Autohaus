package de.autoverwaltung.adapter.csvloader;

public interface ParserInterface<T> {
    T parse(String[] values);
}
