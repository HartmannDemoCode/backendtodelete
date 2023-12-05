package dk.cphbusiness.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Zip implements IJPAEntity<Integer> {

    @Id
    @Column(name = "zip")
    private Integer zip;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "zip")
    @ToString.Exclude
    private Set<Address> address = new HashSet<>();

    public Integer getId() {
        return zip;
    }

    public Zip(Integer zip, String city) {
        this.zip = zip;
        this.cityName = city;
    }
}