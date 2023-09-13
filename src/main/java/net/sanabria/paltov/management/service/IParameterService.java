package net.sanabria.paltov.management.service;

import net.sanabria.paltov.management.model.Parameter;
import net.sanabria.paltov.management.response.ParameterResponseRest;
import org.springframework.http.ResponseEntity;

public interface IParameterService {

    public ResponseEntity<ParameterResponseRest> createParameter(Parameter parameter, Long domainId);

    public ResponseEntity<ParameterResponseRest> readParameters();

    public ResponseEntity<ParameterResponseRest> updateParameter(Parameter parameter, Long domainId, Long id);

    public ResponseEntity<ParameterResponseRest> deleteParameterById(Long id);
}
