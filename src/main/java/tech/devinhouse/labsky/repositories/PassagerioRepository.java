package tech.devinhouse.labsky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.labsky.models.Passageiro;

import java.util.Optional;

@Repository
public interface PassagerioRepository extends JpaRepository<Passageiro, Long> {

    Optional<Passageiro> findByCpf(String cpf);

}
