package net.sanabria.paltov.management.webservice;

import lombok.RequiredArgsConstructor;
import net.sanabria.paltov.management.model.Domain;
import net.sanabria.paltov.management.response.DomainResponseRest;
import net.sanabria.paltov.management.service.IDomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DomainController {
    private final IDomainService domainService;

    /**
     * Save a domain
     * @param domain
     * @return
     */
    @PostMapping(value = "/domain")
    public ResponseEntity<DomainResponseRest> createDomain(@RequestBody Domain domain) {
        ResponseEntity<DomainResponseRest> responseRest = domainService.createDomain(domain);
        return responseRest;
    }

    /**
     * List all domain
     * @return
     */
    @GetMapping(value = "/domain")
    public ResponseEntity<DomainResponseRest> readDomain() {
        ResponseEntity<DomainResponseRest> responseRest = domainService.readDomains();
        return responseRest;
    }

    /**
     * Update a domain
     * @param domain
     * @param id
     * @return
     */
    @PutMapping(value = "domain/{id}")
    public ResponseEntity<DomainResponseRest> updateDomain(@RequestBody Domain domain, @PathVariable Long id) {
        ResponseEntity<DomainResponseRest> responseRest = domainService.updateDomain(domain, id);
        return responseRest;
    }

    /**
     * Delete a domain
     * @param id
     * @return
     */
    @DeleteMapping(value = "domain/{id}")
    public ResponseEntity<DomainResponseRest> deleteDomain(@PathVariable Long id) {
        ResponseEntity<DomainResponseRest> responseRest = domainService.deleteDomainById(id);
        return responseRest;
    }
}
