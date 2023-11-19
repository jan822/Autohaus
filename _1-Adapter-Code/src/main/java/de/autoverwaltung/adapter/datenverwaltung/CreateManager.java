package de.autoverwaltung.adapter.datenverwaltung;

import de.autoverwaltung.domain.ICreate;
import de.autoverwaltung.domain.IEinzigartig;

public class CreateManager<T extends IEinzigartig> implements ICreate<T> {
    private static CreateManager<?> instance;

    private CreateManager() {
    }

    public static synchronized <T extends IEinzigartig> CreateManager<T> getInstance() {
        if (instance == null) {
            instance = new CreateManager<>();
        }
        return (CreateManager<T>) instance;
    }

    @Override
    public void create(T entity) {
        ReadManager.getInstance().readAlleDaten().put(entity.getID(), entity);
    }
}

