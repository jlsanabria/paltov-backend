package net.sanabria.paltov.person.repository;

import net.sanabria.paltov.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
