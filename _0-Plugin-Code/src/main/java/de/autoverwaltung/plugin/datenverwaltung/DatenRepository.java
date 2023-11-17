package de.autoverwaltung.plugin.datenverwaltung;

import de.autoverwaltung.domain.IEinzigartig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatenRepository<T extends IEinzigartig>  {
    private static DatenRepository<?> instance;
    private final Map<Object, IEinzigartig> repository = new HashMap<>();

    public static synchronized <T extends IEinzigartig> DatenRepository<T> getInstance() {
        if (instance == null) {
            instance = new DatenRepository();
        }
        return (DatenRepository<T>) instance;
    }

    public Map<Object, IEinzigartig> getRepository() {
        return repository;
    }

    public DatenRepository() {
    }
}

