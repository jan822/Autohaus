package de.autoverwaltung.adapter.datenverwaltung;

import de.autoverwaltung.domain.IDelete;
import de.autoverwaltung.domain.IEinzigartig;

public class DeleteManager<T extends IEinzigartig> implements IDelete<T> {
    private static DeleteManager<?> instance;

    private DeleteManager() {
    }

    public static synchronized <T extends IEinzigartig> DeleteManager<T> getInstance() {
        if (instance == null) {
            instance = new DeleteManager<>();
        }
        return (DeleteManager<T>) instance;
    }

    @Override
    public void delete(T entity) {
        ReadManager.getInstance().readAlleDaten().remove(entity.getID());
    }
}

