package tech.devinhouse.labsky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.labsky.models.Passageiro;

@Repository
public interface PassagerioRepository extends JpaRepository<Passageiro, String> {

}
