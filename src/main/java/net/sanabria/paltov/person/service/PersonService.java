package net.sanabria.paltov.person.service;

import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.model.Parameter;
import net.sanabria.paltov.management.repository.ParameterRepository;
import net.sanabria.paltov.management.response.ParameterResponseRest;
import net.sanabria.paltov.management.service.ParameterService;
import net.sanabria.paltov.person.model.Person;
import net.sanabria.paltov.person.repository.PersonRepository;
import net.sanabria.paltov.person.response.PersonResponseRest;
import net.sanabria.paltov.useful.UsefulDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {
    private final ParameterRepository parameterRepository;

    private final PersonRepository personRepository;

    public PersonService(ParameterRepository parameterRepository, PersonRepository personRepository) {
        this.parameterRepository = parameterRepository;
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<PersonResponseRest> createPerson(Person person, Long genderId) {
        if (person == null)
            throw new RuntimeException("Person can't be null");
        PersonResponseRest responseRest = new PersonResponseRest();
        List<Person> persons = new ArrayList<Person>();
        try {
            // Search parameter gender to set in a person object
            Optional<Parameter> gender = parameterRepository.findById(genderId);
            if (gender.isPresent()) {
                person.setGender(gender.get());
            } else {
                responseRest.setMetadata("ERROR", "0", "Error, gender not found for person :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

            // Save person
            Person personSaved = personRepository.save(person);
            if (personSaved != null) {
                persons.add(personSaved);
                responseRest.getPersonResponse().setPersons(persons);
                responseRest.setMetadata("OK", "1", "Sucessful person saved :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Person not saved :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error saving person :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PersonResponseRest> readPersons() {
        PersonResponseRest responseRest = new PersonResponseRest();
        try {
            List<Person> persons = (List<Person>) personRepository.findAll();
            if (persons != null && !persons.isEmpty()) {
                responseRest.getPersonResponse().setPersons(persons);
                responseRest.setMetadata("OK", "1", "Sucessful return list person :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Error, persons not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error query list person :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonResponseRest> updatePerson(Person person, Long genderId, Long id) {
        PersonResponseRest responseRest = new PersonResponseRest();
        List<Person> persons = new ArrayList<Person>();

        try {
            // Search parameter gender to set in a person object
            Optional<Parameter> gender = parameterRepository.findById(genderId);
            if (gender.isPresent()) {
                person.setGender(gender.get());
            } else {
                responseRest.setMetadata("ERROR", "0", "Query error, gender not found");
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

            // Update person
            Optional<Person> personUpdate = personRepository.findById(id);
            if (personUpdate.isPresent()) {
                personUpdate.get().setCi(person.getCi());
                personUpdate.get().setName(person.getName());
                personUpdate.get().setPatSurname(person.getPatSurname());
                personUpdate.get().setMatSurname(person.getMatSurname());
                personUpdate.get().setBirthdate(person.getBirthdate());
                personUpdate.get().setGender(person.getGender());

                Person personUpdated = personRepository.save(personUpdate.get());
                if (personUpdated != null) {
                    persons.add(personUpdated);
                    responseRest.getPersonResponse().setPersons(persons);
                    responseRest.setMetadata("OK", "1", "Successful person updated :)", UsefulDate.dateTimeNow());
                } else {
                    responseRest.setMetadata("ERROR", "0", "Person not updated :(", UsefulDate.dateTimeNow());
                    return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseRest.setMetadata("ERROR", "0", "Person not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error updating person :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PersonResponseRest> deletePerson(Long id) {
        PersonResponseRest responseRest = new PersonResponseRest();
        List<Person> persons = new ArrayList<Person>();
        try {
            Optional<Person> personDeleted = personRepository.findById(id);
            if (personDeleted.isPresent()) {
                personRepository.deleteById(id);
                persons.add(personDeleted.get());
                responseRest.getPersonResponse().setPersons(persons);
                responseRest.setMetadata("OK", "1", "Successful person deleted :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Person not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error deleting person by ID :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonResponseRest>(responseRest, HttpStatus.OK);
    }
}
