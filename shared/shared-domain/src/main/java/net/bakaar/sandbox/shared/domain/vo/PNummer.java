package net.bakaar.sandbox.shared.domain.vo;


import java.util.regex.Pattern;

public final class PNummer {

    private final int value;

    private PNummer(String pnummer) {
        if (pnummer == null) {
            throw new IllegalArgumentException("PNummer should not be null");
        }
        Pattern pattern = Pattern.compile("P[0-9]{8}");
        if (!pattern.matcher(pnummer).matches()) {
            throw new IllegalArgumentException("PNummer should follow the pattern P[0-9]{8}");
        }
        this.value = Integer.parseInt(pnummer.substring(1));
    }

    public static PNummer of(String pnummer) {
        return new PNummer(pnummer);
    }

    public static PNummer of(long id) {
        return new PNummer("P" + id);
    }

    public String format() {
        return "P" + value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PNummer pNummer = (PNummer) o;

        return value == pNummer.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
