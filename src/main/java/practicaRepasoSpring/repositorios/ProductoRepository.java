package practicaRepasoSpring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import practicaRepasoSpring.entidades.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecioBetween(double min, double max);
    
   /* @Query("SELECT p FROM Producto p JOIN p.categoria c WHERE c.nombre = :nombreCategoria")
    List<Producto> buscarPorNombreCategoria(@Param("nombreCategoria") String nombreCategoria);
    */
    List<Producto> findByCategoriaNombre(String nombre);
    
    @Query("SELECT p, COUNT(p) as cantidad FROM Pedido ped JOIN ped.productos p GROUP BY p ORDER BY cantidad DESC")
    List<Object[]> encontrarProductosMasVendidos();
}
