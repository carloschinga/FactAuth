package pe.fact.gestor.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.fact.gestor.comercial.entity.Producto;
import pe.fact.gestor.comercial.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public void crear(Producto producto) {
        // Validación de Negocio (Ejemplo de Ingeniero)
        if(producto.getNombProd() == null || producto.getNombProd().isEmpty()) {
            throw new RuntimeException("El nombre del producto es obligatorio");
        }

        // Llamada al repo
        repository.registrarProducto(producto.getNombProd());
    }
}