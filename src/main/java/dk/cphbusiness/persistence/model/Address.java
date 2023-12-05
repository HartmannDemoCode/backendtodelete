package dk.cphbusiness.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Address implements IJPAEntity<String>{

    @Id
    @Column(name = "street", nullable = false)
    private String street;

    @ManyToOne
    private Zip zip;

    @OneToMany(mappedBy = "address")
    @ToString.Exclude
    private final Set<Person> person = new HashSet<>();

    public String getId() {
        return street;
    }
    public Address(String street, Zip zip) {
        this.street = street;
        this.zip = zip;
    }
}