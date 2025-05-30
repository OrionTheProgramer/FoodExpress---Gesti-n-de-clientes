package com.foodexpress.microservice.Service;

import com.foodexpress.microservice.Exception.Exceptions.ResourceNotFoundException;
import com.foodexpress.microservice.Model.Cliente;
import com.foodexpress.microservice.Repository.ClienteRepository;
import com.foodexpress.microservice.Security.Password;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    // Inicializando el Repositorio y el Codificador
    private final Password password;
    private final ClienteRepository repo;
    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    public ClienteService(Password password, ClienteRepository repo) {
        this.password = password;
        this.repo = repo;
    }

    /**
     * Método que devuelve una lista de los clientes.
     *
     * @return Lista con los clientes.
     */
    public List<Cliente> ListarTodos(){
        return  repo.findAll();
    }

    /**
     * Método para obtener un cliente por su id.
     *
     * @param id ID del cliente que se quiere obtener.
     * @return Cliente encontrado.
     * @exception ResourceNotFoundException Id no existente.
     */
    public Cliente ObtenerPorID(Long id){
        Optional<Cliente> ClienteOPT = repo.findById(id);
        return ClienteOPT.orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado por el id: "+id));
    }

    /**
     * Método para agregar un cliente a la base de datos,
     * utiliza password para codificar la contraseña.
     *
     * @param cliente Datos del cliente nuevo.
     * @return Cliente añadido correctamente a la base de datos.
     */
    public Cliente AgregarCliente(Cliente cliente){
        String raw = cliente.getPassword();
        cliente.setPassword(password.encode(raw));
        return repo.save(cliente);
    }

    /**
     * Compara las contraseñas para poder validar la identidad del cliente.
     *
     * @param cliente Cliente.
     * @param password_utilizada Contraseña que se envio para comparar.
     * @return True or False.
     */
    public Boolean CompararPassword(Cliente cliente, String password_utilizada){
        return password.matches(password_utilizada, cliente.getPassword());
    }


    /**
     * Método utilizado para actualizar los datos de los clientes.
     *
     * @param id ID del cliente.
     * @param newDatos Datos actualizados.
     * @param Password_Validation Contraseña para efectuar el log-in.
     * @return Cliente.
     */
    public Cliente ActualizarDatos(Long id, Cliente newDatos, String Password_Validation){
        Cliente existente = ObtenerPorID(id);

        // Primero validamos que la contraseña sea la misma, si es el caso los datos nuevos se guardarán
        if (CompararPassword(existente, Password_Validation) == true){
            log.info("Cliente {} validado correctamente", existente.getRut());
            existente.setNombre(newDatos.getNombre());
            existente.setApellido(newDatos.getApellido());
            existente.setEmail(newDatos.getEmail());
            existente.setPassword(newDatos.getPassword());
        }
        else{
            log.info("Validación fallida del cliente {}", existente.getRut());
        }

        return repo.save(existente);
    }

    /**
     * Método para eliminar un cliente.
     *
     * @param id ID del cliente que se desea eliminar.
     */
    public void DeleteCliente(Long id){
        Cliente existente = ObtenerPorID(id);
        repo.delete(existente);
    }

}
