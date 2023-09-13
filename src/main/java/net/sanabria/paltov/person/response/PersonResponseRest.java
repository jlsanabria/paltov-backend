package net.sanabria.paltov.person.response;

import lombok.Getter;
import lombok.Setter;
import net.sanabria.paltov.response.ResponseRest;

@Getter
@Setter
public class PersonResponseRest extends ResponseRest {
    private PersonResponse personResponse = new PersonResponse();
}
