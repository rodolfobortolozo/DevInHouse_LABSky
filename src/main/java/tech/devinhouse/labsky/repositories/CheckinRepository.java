package tech.devinhouse.labsky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devinhouse.labsky.models.Checkin;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

  Checkin findByPassageiroCpf(String cpf);
}
