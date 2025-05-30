package com.foodexpress.microservice.Controller;

import com.foodexpress.microservice.Model.Cliente;
import com.foodexpress.microservice.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
@Tag(name = "Controlador de los Clientes", description = "Hace las gestiones de los clientes")
public class ClienteController {

    // Inicializando el ClienteService
    private final ClienteService service;
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    /**
     * GET /api/clientes
     *
     * @return Lista con los clientes.
     */
    @Operation(summary = "Método que se utiliza para listar todos los clientes en la db")
    @GetMapping
    public List<Cliente> ListarTodos(){
        return service.ListarTodos();
    }

    /**
     * GET /api/clientes/{id}
     *
     * @param id ID del cliente.
     * @return Cliente encontrado.
     */
    @Operation(summary = "Método que permite encontrar a un clientes por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ObtenerPorId(Long id){
        Cliente obtenido = service.ObtenerPorID(id);
        return ResponseEntity.ok(obtenido);
    }

    /**
     * POST /api/clientes/make
     *
     * @param cliente Datos del cliente.
     * @return Cliente agregado a la db.
     */
    @Operation(summary = "Método que se utiliza para agregar clientes a la db")
    @PostMapping("/make")
    public ResponseEntity<Cliente> AgregarCliente(@RequestBody @Validated Cliente cliente){
        Cliente creado = service.AgregarCliente(cliente);
        return ResponseEntity.status((201)).body(creado);
    }

    /**
     * PUT /api/clientes/update/{id}
     *
     * @param id ID del cliente que se actualizara.
     * @param newDatos Datos actualizados.
     * @param password Contraseña para el log.in.
     * @return Cliente actualizado correctamente.
     */
    @Operation(summary = "Método para actualizar los datos de los clientes")
    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> ActualizarDatos(@PathVariable Long id, @RequestBody @Validated Cliente newDatos, @RequestBody String password){
        Cliente actualizado = service.ActualizarDatos(id,newDatos,password);
        return  ResponseEntity.ok(actualizado);
    }

    /**
     * DELETE /api/clientes/trash/{id}
     *
     * @param id ID del cliente.
     * @return No content.
     */
    @Operation(summary = "Método para eliminar los clientes")
    @DeleteMapping("/trash/{id}")
    public ResponseEntity<Cliente>Eliminar(@PathVariable Long id){
        service.DeleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
