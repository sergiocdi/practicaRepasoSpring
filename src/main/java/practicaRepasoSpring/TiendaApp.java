package practicaRepasoSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import practicaRepasoSpring.servicios.TiendaService;

@Component
public class TiendaApp implements CommandLineRunner {

    @Autowired
    private TiendaService tiendaService;

    @Override
    public void run(String... args) {
        // Insertar datos de prueba
        tiendaService.crearCategoria("Electrónica");
        tiendaService.crearCategoria("Ropa");

        tiendaService.crearProducto("Laptop", 900, 1L);
        tiendaService.crearProducto("Camiseta", 20, 2L);

        tiendaService.crearCliente("Juan Pérez", "juan@mail.com");
        tiendaService.crearCliente("María Gómez", "maria@mail.com");

        tiendaService.crearPedido(1L, List.of(1L, 2L));

        // Consultas
        System.out.println("Productos en Electrónica:");
        tiendaService.listarProductosPorCategoria("Electrónica");

        System.out.println("Pedidos de Juan Pérez:");
        tiendaService.listarPedidosPorCliente("Juan Pérez");
    }
}
