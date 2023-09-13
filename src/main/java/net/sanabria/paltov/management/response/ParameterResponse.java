package net.sanabria.paltov.management.response;

import lombok.Data;
import net.sanabria.paltov.management.model.Parameter;

import java.util.List;

@Data
public class ParameterResponse {
    private List<Parameter> parameters;
}
