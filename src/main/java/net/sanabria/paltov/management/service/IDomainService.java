package net.sanabria.paltov.management.service;

import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.response.DomainResponseRest;
import org.springframework.http.ResponseEntity;

public interface IDomainService {

    public ResponseEntity<DomainResponseRest> createDomain(Domain domain);

    public ResponseEntity<DomainResponseRest> readDomains();

    public ResponseEntity<DomainResponseRest> updateDomain(Domain domain, Long id);

    public ResponseEntity<DomainResponseRest> deleteDomainById(Long id);
}
