package net.bakaar.sandbox.shared.domain.vo;


import java.util.regex.Pattern;

public final class PNumber {

    private final int value;

    private PNumber(String pnummer) {
        if (pnummer == null) {
            throw new IllegalArgumentException("PNumber should not be null");
        }
        Pattern pattern = Pattern.compile("P[0-9]{8}");
        if (!pattern.matcher(pnummer).matches()) {
            throw new IllegalArgumentException("PNumber should follow the pattern P[0-9]{8}");
        }
        this.value = Integer.parseInt(pnummer.substring(1));
    }

    public static PNumber of(String pnummer) {
        return new PNumber(pnummer);
    }

    public static PNumber of(long id) {
        return new PNumber("P" + id);
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

        PNumber pNumber = (PNumber) o;

        return value == pNumber.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
