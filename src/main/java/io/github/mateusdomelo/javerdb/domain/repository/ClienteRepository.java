package io.github.mateusdomelo.javerdb.domain.repository;

import io.github.mateusdomelo.javerdb.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
