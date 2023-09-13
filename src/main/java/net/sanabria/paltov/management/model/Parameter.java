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
@Table(name = "paltov_parameter")
public class Parameter implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_parameter")
    private Long idParameter;

    @Column(name = "name_parameter", length = 100)
    private String nameParameter;

    @Column(name = "value_parameter")
    private String valueParameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domain", foreignKey = @ForeignKey(name = "fk_parameter_domain"))
    private Domain domain;
}
