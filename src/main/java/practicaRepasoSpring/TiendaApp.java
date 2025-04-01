package practicaRepasoSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import practicaRepasoSpring.entidades.Producto;
import practicaRepasoSpring.servicios.TiendaService;


@SpringBootApplication
public class TiendaApp implements CommandLineRunner {

    @Autowired
    private TiendaService tiendaService;
    
    public static void main(String[] args) {
        SpringApplication.run(TiendaApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Insertar datos de prueba
        tiendaService.crearCategoria("Electrónica");
        tiendaService.crearCategoria("Ropa");

        tiendaService.crearProducto("Laptop", 900, 1L);
        tiendaService.crearProducto("Camiseta", 20, 2L);

        tiendaService.crearProducto("Camiseta", 90, 1L);

        tiendaService.crearCliente("Juan Pérez", "juan@mail.com");
        tiendaService.crearCliente("María Gómez", "maria@mail.com");

        tiendaService.crearPedido(1L, List.of(1L, 2L));

        // Consultas
        System.out.println("Productos en Electrónica:");
        tiendaService.listarProductosPorCategoria("Electrónica");

        System.out.println("Pedidos de Juan Pérez:");
        tiendaService.listarPedidosPorCliente("Juan Pérez");
        
        // Modificar un producto
        tiendaService.modificarProducto(1L, "Laptop Gamer", 1200);
        
        // Modificar un cliente
        tiendaService.modificarCliente(1L, "Juan Pérez López", "juanperez@mail.com");

        // Modificar un pedido (cambiar productos)
        tiendaService.modificarPedido(1L, List.of(2L));

        // Consultas después de modificar
        System.out.println("Productos después de modificaciones:");
        tiendaService.listarProductosPorCategoria("Electrónica");
        
        System.out.println("Productos en el rango de precio 50 - 1000:");
        List<Producto> productosEnRango = tiendaService.buscarProductosPorRangoDePrecio(50, 1000);

        for (Producto p : productosEnRango) {
            System.out.println(" - " + p.getNombre() + " | Precio: " + p.getPrecio());
        }

        
        System.out.println("\n Listando los productos más vendidos:");
        tiendaService.listarProductosMasVendidos();
        
        // Eliminar un pedido
     //   tiendaService.eliminarPedido(2L);
      //  tiendaService.eliminarPedido(2L);
      //  tiendaService.eliminarPedido(2L);

        
        // Eliminar un producto
      // tiendaService.eliminarProducto(2L);

        // Eliminar un cliente
       tiendaService.eliminarCliente(2L);

     

        System.out.println("Operaciones de modificación y eliminación realizadas.");
    }
}
