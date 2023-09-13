package net.sanabria.paltov.management.response;

import lombok.Getter;
import lombok.Setter;
import net.sanabria.paltov.response.ResponseRest;

@Getter
@Setter
public class ParameterResponseRest extends ResponseRest {
    private ParameterResponse parameterResponse = new ParameterResponse();
}
