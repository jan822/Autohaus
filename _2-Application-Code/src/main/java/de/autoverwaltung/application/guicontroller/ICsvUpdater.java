package de.autoverwaltung.application.guicontroller;

public interface ICsvUpdater<T> {
    void eintragLoeschen(T object);
    void eintragHinzufuegen(T object);
    void eintragAendern(Object obj);
}

