package net.bakaar.sandbox.person.data.jpa.repository;

import net.bakaar.sandbox.person.data.jpa.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
