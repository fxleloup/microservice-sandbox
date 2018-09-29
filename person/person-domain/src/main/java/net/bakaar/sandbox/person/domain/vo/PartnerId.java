package net.bakaar.sandbox.person.domain.vo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PartnerId {

    private final long number;

    public static PartnerId of(long id) {
        return new PartnerId(id);
    }
}
