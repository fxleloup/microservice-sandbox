package net.bakaar.sandbox.cas.infra.event.db;

import org.springframework.data.repository.CrudRepository;

public interface EventRaisedRepository extends CrudRepository<EventRaised,Integer> {
}
