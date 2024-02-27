package de.autoverwaltung.domain;

import java.util.List;
import java.util.Map;

public interface IRead<T> {
    T read(String id);

    Map<String, IEinzigartig> readAlleDaten();

    <T extends IEinzigartig> List<T> readAlleDatenNachKlasse(Class<T> c);
}
