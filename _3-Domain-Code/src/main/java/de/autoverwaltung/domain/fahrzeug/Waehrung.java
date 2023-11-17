package de.autoverwaltung.domain.fahrzeug;

public enum Waehrung {
    EUR("Euro", "€"),
    USD("US-Dollar", "$"),
    GBP("Britisches Pfund", "£"),
    JPY("Japanischer Yen", "¥"),
    CHF("Schweizer Franken", "CHF"),
    CAD("Kanadischer Dollar", "CA$"),
    AUD("Australischer Dollar", "AU$"),
    CNY("Chinesischer Yuan", "¥");

    private final String name;
    private final String symbol;

    Waehrung(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
