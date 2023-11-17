package de.autoverwaltung.domain;

import java.util.List;

public interface ISearch<T> {
    List<T> search(String criteria);
}
