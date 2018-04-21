package net.bakaar.sandbox.event.db;

import org.springframework.data.repository.CrudRepository;

public interface EventRaisedRepository extends CrudRepository<DBEventRaised, Integer> {
}
