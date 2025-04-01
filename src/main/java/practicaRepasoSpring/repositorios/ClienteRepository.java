package practicaRepasoSpring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practicaRepasoSpring.entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
