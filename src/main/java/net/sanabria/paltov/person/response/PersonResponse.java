package net.sanabria.paltov.person.response;

import lombok.Data;
import net.sanabria.paltov.person.model.Person;

import java.util.List;

@Data
public class PersonResponse {
    private List<Person> persons;
}
