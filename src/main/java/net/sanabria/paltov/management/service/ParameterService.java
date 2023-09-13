package net.sanabria.paltov.management.service;

import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.model.Parameter;
import net.sanabria.paltov.management.repository.DomainRepository;
import net.sanabria.paltov.management.repository.ParameterRepository;
import net.sanabria.paltov.management.response.ParameterResponseRest;
import net.sanabria.paltov.useful.UsefulDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParameterService implements IParameterService {
    private final DomainRepository domainRepository;

    private final ParameterRepository parameterRepository;

    public ParameterService(DomainRepository domainRepository, ParameterRepository parameterRepository) {
        this.domainRepository = domainRepository;
        this.parameterRepository = parameterRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<ParameterResponseRest> createParameter(Parameter parameter, Long domainId) {
        if (parameter == null)
            throw new RuntimeException("Parameter can't be null");
        ParameterResponseRest responseRest = new ParameterResponseRest();
        List<Parameter> parameters = new ArrayList<Parameter>();
        try {
            // Search domain to set in a domain object
            Optional<Domain> domain = domainRepository.findById(domainId);
            if (domain.isPresent()) {
                parameter.setDomain(domain.get());
            } else {
                responseRest.setMetadata("ERROR", "0", "Error, domain not found for parameter :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

            // Save parameter
            Parameter parameterSaved = parameterRepository.save(parameter);
            if (parameterSaved != null) {
                parameters.add(parameterSaved);
                responseRest.getParameterResponse().setParameters(parameters);
                responseRest.setMetadata("OK", "1", "Sucessful parameter saved :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Parameter not saved :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error saving parameter :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ParameterResponseRest> readParameters() {
        ParameterResponseRest responseRest = new ParameterResponseRest();
        try {
            List<Parameter> parameters = (List<Parameter>) parameterRepository.findAll();
            if (parameters != null && !parameters.isEmpty()) {
                responseRest.getParameterResponse().setParameters(parameters);
                responseRest.setMetadata("OK", "1", "Sucessful return list domain :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Error, parameters not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error query list domain :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ParameterResponseRest> updateParameter(Parameter parameter, Long domainId, Long id) {
        ParameterResponseRest responseRest = new ParameterResponseRest();
        List<Parameter> parameters = new ArrayList<Parameter>();

        try {
            // Search domain to set in a parameter object
            Optional<Domain> domain = domainRepository.findById(domainId);
            if (domain.isPresent()) {
                parameter.setDomain(domain.get());
            } else {
                responseRest.setMetadata("ERROR", "0", "Query error, domain not found");
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

            // Update parameter
            Optional<Parameter> parameterUpdate = parameterRepository.findById(id);
            if (parameterUpdate.isPresent()) {
                parameterUpdate.get().setNameParameter(parameter.getNameParameter());
                parameterUpdate.get().setValueParameter(parameter.getValueParameter());
                parameterUpdate.get().setDomain(parameter.getDomain());

                Parameter parameterUpdated = parameterRepository.save(parameterUpdate.get());
                if (parameterUpdated != null) {
                    parameters.add(parameterUpdated);
                    responseRest.getParameterResponse().setParameters(parameters);
                    responseRest.setMetadata("OK", "1", "Successful parameter updated :)", UsefulDate.dateTimeNow());
                } else {
                    responseRest.setMetadata("ERROR", "0", "Parameter not updated :(", UsefulDate.dateTimeNow());
                    return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseRest.setMetadata("ERROR", "0", "Parameters not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error updating parameter :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ParameterResponseRest> deleteParameterById(Long id) {
        ParameterResponseRest responseRest = new ParameterResponseRest();
        List<Parameter> parameters = new ArrayList<Parameter>();
        try {
            Optional<Parameter> parameterDeleted = parameterRepository.findById(id);
            if (parameterDeleted.isPresent()) {
                parameterRepository.deleteById(id);
                parameters.add(parameterDeleted.get());
                responseRest.getParameterResponse().setParameters(parameters);
                responseRest.setMetadata("OK", "1", "Successful parameter deleted :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Parameter not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error deleting parameter by ID :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ParameterResponseRest>(responseRest, HttpStatus.OK);
    }
}
