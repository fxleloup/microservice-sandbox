package net.bakaar.sandbox.cas.infra.repository;

import javax.persistence.Entity;

@Entity
public class EventEntity {
    private String payload;


    public String getPayload() {

        return payload;
    }

    public EventEntity setPayload(String payload) {
        this.payload = payload;
        return this;
    }
}
