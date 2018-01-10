package kiev.ua.repository;

import kiev.ua.domain.City;
import kiev.ua.domain.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {
    List<Population> findAllByCity(City city);
}
