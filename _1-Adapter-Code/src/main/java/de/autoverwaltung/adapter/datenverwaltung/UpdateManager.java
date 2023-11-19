package de.autoverwaltung.adapter.datenverwaltung;

import de.autoverwaltung.domain.IEinzigartig;
import de.autoverwaltung.domain.IUpdate;

public class UpdateManager<T extends IEinzigartig> implements IUpdate<T> {
    private static UpdateManager<?> instance;

    private UpdateManager() {

    }

    public static synchronized <T extends IEinzigartig> UpdateManager<T> getInstance() {
        if (instance == null) {
            instance = new UpdateManager<>();
        }
        return (UpdateManager<T>) instance;
    }

    @Override
    public void update(T entity) {
        ReadManager.getInstance().readAlleDaten().put(entity.getID(), entity);
    }
}
