package net.sanabria.paltov.management.response;

import lombok.Data;
import net.sanabria.paltov.management.model.Domain;

import java.util.List;

@Data
public class DomainResponse {
    private List<Domain> domains;
}
