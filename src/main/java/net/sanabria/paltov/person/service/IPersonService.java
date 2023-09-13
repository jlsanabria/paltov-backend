package net.sanabria.paltov.person.service;

import net.sanabria.paltov.person.model.Person;
import net.sanabria.paltov.person.response.PersonResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPersonService {
    public ResponseEntity<PersonResponseRest> createPerson(Person person, Long genderId);

    public ResponseEntity<PersonResponseRest> readPersons();

    public ResponseEntity<PersonResponseRest> updatePerson(Person person, Long genderId, Long id);
    public ResponseEntity<PersonResponseRest> deletePerson(Long id);
}
