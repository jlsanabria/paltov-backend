package net.sanabria.paltov.management.service;

import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.repository.DomainRepository;
import net.sanabria.paltov.management.repository.ParameterRepository;
import net.sanabria.paltov.management.response.DomainResponseRest;
import net.sanabria.paltov.useful.UsefulDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomainService implements IDomainService {

    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<DomainResponseRest> createDomain(Domain domain) {
        if (domain == null)
            throw new RuntimeException("Domain can't be null");
        DomainResponseRest responseRest = new DomainResponseRest();
        List<Domain> domains = new ArrayList<Domain>();
        try {
            Domain domainSaved = domainRepository.save(domain);
            if (domainSaved != null) {
                domains.add(domainSaved);
                responseRest.getDomainResponse().setDomains(domains);
                responseRest.setMetadata("OK", "1", "Sucessful domain saved :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Domain not saved :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error saving domain :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<DomainResponseRest> readDomains() {
        DomainResponseRest responseRest = new DomainResponseRest();
        try {
            List<Domain> domains = (List<Domain>) domainRepository.findAll();
            responseRest.getDomainResponse().setDomains(domains);
            responseRest.setMetadata("OK", "1", "Sucessful return list domain :)", UsefulDate.dateTimeNow());
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error query list domain :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<DomainResponseRest> updateDomain(Domain domain, Long id) {
        DomainResponseRest responseRest = new DomainResponseRest();
        List<Domain> domains = new ArrayList<Domain>();
        try {
            Optional<Domain> domainUpdate = domainRepository.findById(id);
            if (domainUpdate.isPresent()) {
                domainUpdate.get().setNameDomain(domain.getNameDomain());
                Domain domainUpdated = domainRepository.save(domainUpdate.get());
                if (domainUpdated != null) {
                    domains.add(domainUpdated);
                    responseRest.getDomainResponse().setDomains(domains);
                    responseRest.setMetadata("OK", "1", "Sucessful update domain :)", UsefulDate.dateTimeNow());
                } else {
                    responseRest.setMetadata("ERROR", "0", "Domain not updated :(", UsefulDate.dateTimeNow());
                    return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseRest.setMetadata("ERROR", "0", "Domain not found :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("ERROR", "0", "Error updating domain :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<DomainResponseRest> deleteDomainById(Long id) {
        DomainResponseRest responseRest = new DomainResponseRest();
        List<Domain> domains = new ArrayList<Domain>();
        try {
            Optional<Domain> domainDelete = domainRepository.findById(id);
            if (domainDelete.isPresent()) {
                domainRepository.deleteById(id);
                domains.add(domainDelete.get());
                responseRest.getDomainResponse().setDomains(domains);
                responseRest.setMetadata("OK", "1", "Sucessful delete domain :)", UsefulDate.dateTimeNow());
            } else {
                responseRest.setMetadata("ERROR", "0", "Domain not deleted :(", UsefulDate.dateTimeNow());
                return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            responseRest.setMetadata("ERROR", "0", "Error deleting domain :(", UsefulDate.dateTimeNow());
            return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<DomainResponseRest>(responseRest, HttpStatus.OK);
    }


}
