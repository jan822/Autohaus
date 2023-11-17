package de.autoverwaltung.plugin.datenverwaltung;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IRead;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadManager<T extends IEinzigartig> implements IRead<T> {
    private static ReadManager<?> instance;

    private ReadManager() {

    }

    public static synchronized <T extends IEinzigartig> ReadManager<T> getInstance() {
        if (instance == null) {
            instance = new ReadManager<>();
        }
        return (ReadManager<T>) instance;
    }

    @Override
    public T read(String id) {
        return (T) ReadManager.getInstance().readAlleDaten().get(id);
    }

    @Override
    public Map<Object, IEinzigartig> readAlleDaten() {
        return DatenRepository.getInstance().getRepository();
    }

    @Override
    public <T extends IEinzigartig> List<T> readAlleDatenNachKlasse(Class<T> c) {
        return DatenRepository.getInstance().getRepository().values().stream()
                .filter(c::isInstance)
                .map(c::cast)
                .collect(Collectors.toList());
    }
    
    
}
