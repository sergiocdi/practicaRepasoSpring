package practicaRepasoSpring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practicaRepasoSpring.entidades.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
