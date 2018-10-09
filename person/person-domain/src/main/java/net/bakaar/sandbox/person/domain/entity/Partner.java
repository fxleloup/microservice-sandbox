package net.bakaar.sandbox.person.domain.entity;

import lombok.Getter;
import lombok.NonNull;
import net.bakaar.sandbox.shared.domain.vo.PNummer;

import java.time.LocalDate;

@Getter
public class Partner {

    private final PNummer id;
    private final String forename;
    private final String name;
    private final LocalDate birthDate;

    public Partner(PNummer id, String name, String forename, LocalDate birthDate) {
        validate(name, "name");
        validate(forename, "forename");
        validate(birthDate);
        this.id = id;
        this.forename = forename;
        this.name = name;
        this.birthDate = birthDate;
    }


    public static Partner of(long id, @NonNull String name, @NonNull String forename, @NonNull LocalDate birthDate) {

        return new Partner(PNummer.of(id), name, forename, birthDate);
    }

    private void validate(LocalDate date) {
        if (LocalDate.now().isBefore(date)) {
            throw new IllegalArgumentException(String.format("The birthDate (%s) should not be in the future", date.toString()));
        }
    }

    private void validate(String input, String paramName) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("The parameter %s should not be empty", paramName));
        }
    }
}
