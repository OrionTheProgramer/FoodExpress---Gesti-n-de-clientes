package com.foodexpress.microservice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Cliente")
public class Cliente {

    // Generando el ID de los clientes, solo será de lectura e inmodificable
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Schema(description = "Id del cliente, no se modifica", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // Validación del rut and make it unique
    @Column(unique = true, nullable = false)
    @Pattern(
            regexp = "\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dKk]",
            message = "El rut debe tener formato xx.xxx.xxx-D"
    )
    private String rut;

    // Sección del nombre y apellido del cliente
    @Column()
    @NotBlank @Schema(description = "Nombre del cliente", example = "Juan", nullable = false)
    private String nombre;
    @Column()
    @NotBlank @Schema(description = "Apellido del cliente", example = "Moralles", nullable = false)
    private String apellido;

    // Validando Email
    @Column(unique = true, nullable = false)
    @Email(message = "El correo debe tener un formato valido")
    private String email;

    // Fecha de nacimiento del cliente
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime fecha_nacimiento;

    @NotBlank(message = "La contraseña es obligatoria") @Schema(nullable = false, accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    // Fecha de Creación
    @CreationTimestamp
    private LocalDate fecha_create;

    // Fecha de Actualización de los datos del cliente
    @UpdateTimestamp
    private LocalDate fecha_update;
}
