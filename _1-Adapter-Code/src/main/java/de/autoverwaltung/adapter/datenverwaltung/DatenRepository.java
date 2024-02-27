package de.autoverwaltung.adapter.datenverwaltung;

import de.autoverwaltung.domain.IEinzigartig;

import java.util.HashMap;
import java.util.Map;

public class DatenRepository<T extends IEinzigartig>  {
    private static DatenRepository<?> instance;
    private final Map<String, IEinzigartig> repository = new HashMap<>();

    public static synchronized <T extends IEinzigartig> DatenRepository<T> getInstance() {
        if (instance == null) {
            instance = new DatenRepository();
        }
        return (DatenRepository<T>) instance;
    }

    public Map<String, IEinzigartig> getRepository() {
        return repository;
    }

    // public Map<Object, IEinzigartig> Repository() {
    //     return repository;
    // }



    public DatenRepository() {
    }
}

