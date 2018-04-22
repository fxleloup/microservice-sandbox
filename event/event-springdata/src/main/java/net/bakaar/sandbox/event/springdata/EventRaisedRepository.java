package net.bakaar.sandbox.event.springdata;

import org.springframework.data.repository.CrudRepository;

public interface EventRaisedRepository extends CrudRepository<DBEventRaised, Integer> {
}
