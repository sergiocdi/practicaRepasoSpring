package practicaRepasoSpring.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practicaRepasoSpring.entidades.Categoria;
import practicaRepasoSpring.entidades.Cliente;
import practicaRepasoSpring.entidades.Pedido;
import practicaRepasoSpring.entidades.Producto;
import practicaRepasoSpring.repositorios.CategoriaRepository;
import practicaRepasoSpring.repositorios.ClienteRepository;
import practicaRepasoSpring.repositorios.PedidoRepository;
import practicaRepasoSpring.repositorios.ProductoRepository;

@Service
public class TiendaService {

    @Autowired
    private CategoriaRepository categoriaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private PedidoRepository pedidoRepo;

    public void crearCategoria(String nombre) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoriaRepo.save(categoria);
    }

    public void crearProducto(String nombre, double precio, Long categoriaId) {
        Categoria categoria = categoriaRepo.findById(categoriaId).orElseThrow();
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        productoRepo.save(producto);
    }

    public void crearCliente(String nombre, String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        clienteRepo.save(cliente);
    }

    public void crearPedido(Long clienteId, List<Long> productosIds) {
        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow();
        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setProductos(productoRepo.findAllById(productosIds));
        pedidoRepo.save(pedido);
    }

    public void listarProductosPorCategoria(String nombreCategoria) {
        List<Producto> productos = productoRepo.findByCategoriaNombre(nombreCategoria);
        productos.forEach(p -> System.out.println(p.getNombre() + " - " + p.getPrecio() + "€"));
    }

    public void listarPedidosPorCliente(String nombreCliente) {
        List<Pedido> pedidos = pedidoRepo.findByClienteNombre(nombreCliente);
        pedidos.forEach(p -> {
            System.out.println("Pedido ID: " + p.getId() + ", Fecha: " + p.getFecha());
            p.getProductos().forEach(prod -> System.out.println("  - " + prod.getNombre()));
        });
    }
}
