package net.bakaar.sandbox.person.data.repository;

import net.bakaar.sandbox.person.data.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
