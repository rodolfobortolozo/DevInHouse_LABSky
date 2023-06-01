package tech.devinhouse.labsky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devinhouse.labsky.models.Assento;

import java.util.Optional;

public interface AssentoRepository extends JpaRepository<Assento, Long> {

    Optional<Assento> findByNroAssento(String nroAssento);
}
