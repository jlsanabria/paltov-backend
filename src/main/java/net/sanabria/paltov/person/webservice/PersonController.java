package net.sanabria.paltov.person.webservice;

import lombok.RequiredArgsConstructor;
import net.sanabria.paltov.management.model.Parameter;
import net.sanabria.paltov.management.response.ParameterResponseRest;
import net.sanabria.paltov.person.model.Person;
import net.sanabria.paltov.person.response.PersonResponseRest;
import net.sanabria.paltov.person.service.IPersonService;
import net.sanabria.paltov.useful.UsefulDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PersonController {
    private final IPersonService personService;

    /**
     * Save a person
     * @param ci
     * @param name
     * @param patSurname
     * @param matSurname
     * @param birthdate
     * @param genderId
     * @return
     */
    @PostMapping(value = "/person")
    public ResponseEntity<PersonResponseRest> createPerson(@RequestParam("ci") String ci,
                                                              @RequestParam("name") String name,
                                                              @RequestParam("patSurname") String patSurname,
                                                              @RequestParam("matSurname") String matSurname,
                                                              @RequestParam("birthdate") String birthdate,
                                                              @RequestParam("genderId") Long genderId) {
        Person person = new Person();
        person.setCi(ci);
        person.setName(name);
        person.setPatSurname(patSurname);
        person.setMatSurname(matSurname);
        person.setBirthdate(UsefulDate.convertLocalDateToDate(LocalDate.parse(birthdate)));

        ResponseEntity<PersonResponseRest> responseRest = personService.createPerson(person, genderId);
        return responseRest;
    }

    /**
     * List person
     * @return
     */
    @GetMapping(value = "/person")
    public ResponseEntity<PersonResponseRest> readPerson() {
        ResponseEntity<PersonResponseRest> responseRest = personService.readPersons();
        return responseRest;
    }

    /**
     * Update a person
     * @param ci
     * @param name
     * @param patSurname
     * @param matSurname
     * @param birthdate
     * @param genderId
     * @param id
     * @return
     */
    @PutMapping(value = "person/{id}")
    public ResponseEntity<PersonResponseRest> updatePerson(@RequestParam("ci") String ci,
                                                              @RequestParam("name") String name,
                                                              @RequestParam("patSurname") String patSurname,
                                                              @RequestParam("matSurname") String matSurname,
                                                              @RequestParam("birthdate") String birthdate,
                                                              @RequestParam("genderId") Long genderId,
                                                              @PathVariable Long id) {

        Person person = new Person();
        person.setCi(ci);
        person.setName(name);
        person.setPatSurname(patSurname);
        person.setMatSurname(matSurname);
        person.setBirthdate(UsefulDate.convertLocalDateToDate(LocalDate.parse(birthdate)));

        ResponseEntity<PersonResponseRest> responseRest = personService.updatePerson(person, genderId, id);
        return responseRest;
    }

    /**
     * Delete a person
     * @param id
     * @return
     */
    @DeleteMapping(value = "person/{id}")
    public ResponseEntity<PersonResponseRest> deletePerson(@PathVariable Long id) {
        ResponseEntity<PersonResponseRest> responseRest = personService.deletePerson(id);
        return responseRest;
    }
}
