package net.sanabria.paltov.person.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sanabria.paltov.management.model.Parameter;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paltov_person")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_person")
    private Long idPerson;

    @Column(name = "ci", length = 20)
    private String ci;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "pat_surname", length = 50)
    private String patSurname;

    @Column(name = "mat_surname", length = 50)
    private String matSurname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gender", foreignKey = @ForeignKey(name = "fk_person_gender"))
    private Parameter gender;
}
