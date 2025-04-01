package practicaRepasoSpring.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
    
    // Método para modificar un producto
    @Transactional
    public void modificarProducto(Long productoId, String nuevoNombre, double nuevoPrecio) {
        Producto producto = productoRepo.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(nuevoNombre);
        producto.setPrecio(nuevoPrecio);
        productoRepo.save(producto);
        System.out.println("Producto modificado: " + producto.getNombre());
    }

    // Método para eliminar un producto
    @Transactional
    public void eliminarProducto(Long productoId) {
        productoRepo.deleteById(productoId);
        System.out.println("Producto eliminado con ID: " + productoId);
    }

    // Método para modificar un cliente
    @Transactional
    public void modificarCliente(Long clienteId, String nuevoNombre, String nuevoEmail) {
        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setNombre(nuevoNombre);
        cliente.setEmail(nuevoEmail);
        clienteRepo.save(cliente);
        System.out.println("Cliente modificado: " + cliente.getNombre());
    }

    // Método para eliminar un cliente
    @Transactional
    public void eliminarCliente(Long clienteId) {
        clienteRepo.deleteById(clienteId);
        System.out.println("Cliente eliminado con ID: " + clienteId);
    }

    // Método para modificar un pedido (añadir o quitar productos)
    @Transactional
    public void modificarPedido(Long pedidoId, List<Long> nuevosProductosIds) {
        Pedido pedido = pedidoRepo.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setProductos(productoRepo.findAllById(nuevosProductosIds));
        pedidoRepo.save(pedido);
        System.out.println("Pedido modificado con ID: " + pedidoId);
    }
    
    public List<Producto> buscarProductosPorRangoDePrecio(double precioMin, double precioMax) {
        return productoRepo.findByPrecioBetween(precioMin, precioMax);
    }

    // Método para eliminar un pedido
    @Transactional
    public void eliminarPedido(Long pedidoId) {
        pedidoRepo.deleteById(pedidoId);
        System.out.println("Pedido eliminado con ID: " + pedidoId);
    }
    
    @Transactional
    public void listarProductosMasVendidos() {
        List<Object[]> resultados = productoRepo.encontrarProductosMasVendidos();

        System.out.println(" Productos más vendidos:");
        for (Object[] fila : resultados) {
            Producto producto = (Producto) fila[0];
            Long cantidad = (Long) fila[1];
            System.out.println(" - " + producto.getNombre() + " | Vendido: " + cantidad + " veces");
        }
    }
}
