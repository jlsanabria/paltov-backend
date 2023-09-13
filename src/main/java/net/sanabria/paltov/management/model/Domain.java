package net.sanabria.paltov.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "paltov_domain")
public class Domain implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_domain")
    private Long idDomain;

    @Column(name = "name_domain", length = 100)
    private String nameDomain;
}
