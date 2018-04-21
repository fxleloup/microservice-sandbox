package net.bakaar.sandbox.cas.domain.util;

import net.bakaar.sandbox.cas.domain.provider.BussinessIdProvider;

import java.util.UUID;

public class UUIDIdProvider implements BussinessIdProvider {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
