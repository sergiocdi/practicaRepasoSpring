package practicaRepasoSpring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practicaRepasoSpring.entidades.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecioBetween(double min, double max);
    List<Producto> findByCategoriaNombre(String nombre);
}
