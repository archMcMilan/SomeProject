package kiev.ua.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
@Entity
public class Population implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_to_people_id")
    private long id;
    @NotNull
    private int year;
    @NotNull
    private long peopleAmount;
    @NotNull
    private City city;

    public Population(int year, long peopleAmount, City city) {
        this.year = year;
        this.peopleAmount = peopleAmount;
        this.city = city;
    }
}
