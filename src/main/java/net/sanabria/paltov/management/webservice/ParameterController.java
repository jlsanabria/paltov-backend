package net.sanabria.paltov.management.webservice;

import lombok.RequiredArgsConstructor;
import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.model.Parameter;
import net.sanabria.paltov.management.response.DomainResponseRest;
import net.sanabria.paltov.management.response.ParameterResponseRest;
import net.sanabria.paltov.management.service.IDomainService;
import net.sanabria.paltov.management.service.IParameterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParameterController {
    private final IParameterService parameterService;

    /**
     * Save a parameter
     * @param nameParameter
     * @param valueParameter
     * @param domainId
     * @return
     */
    @PostMapping(value = "/parameter")
    public ResponseEntity<ParameterResponseRest> createParameter(@RequestParam("nameParameter") String nameParameter,
                                                                 @RequestParam("valueParameter") String valueParameter,
                                                                 @RequestParam("domainId") Long domainId) {
        Parameter parameter = new Parameter();
        parameter.setNameParameter(nameParameter);
        parameter.setValueParameter(valueParameter);

        ResponseEntity<ParameterResponseRest> responseRest = parameterService.createParameter(parameter, domainId);
        return responseRest;
    }

    /**
     * List all parameter
     * @return
     */
    @GetMapping(value = "/parameter")
    public ResponseEntity<ParameterResponseRest> readDomain() {
        ResponseEntity<ParameterResponseRest> responseRest = parameterService.readParameters();
        return responseRest;
    }

    /**
     * Update a parameter
     * @param nameParameter
     * @param valueParameter
     * @param domainId
     * @param id
     * @return
     */
    @PutMapping(value = "parameter/{id}")
    public ResponseEntity<ParameterResponseRest> updateDomain(@RequestParam("nameParameter") String nameParameter,
                                                              @RequestParam("valueParameter") String valueParameter,
                                                              @RequestParam("domainId") Long domainId,
                                                              @PathVariable Long id) {

        Parameter parameter = new Parameter();
        parameter.setNameParameter(nameParameter);
        parameter.setValueParameter(valueParameter);

        ResponseEntity<ParameterResponseRest> responseRest = parameterService.updateParameter(parameter, domainId, id);
        return responseRest;
    }

    /**
     * Delete a parameter
     * @param id
     * @return
     */
    @DeleteMapping(value = "parameter/{id}")
    public ResponseEntity<ParameterResponseRest> deleteParameter(@PathVariable Long id) {
        ResponseEntity<ParameterResponseRest> responseRest = parameterService.deleteParameterById(id);
        return responseRest;
    }
}
