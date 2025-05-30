package com.foodexpress.microservice.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorPrinter {
    // Captura el status (201, 400 etc)
    private HttpStatus status;

    // Fecha y hora con formato mm/dd/yyyy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    // Mensaje que explica que lo produjo
    private String mensaje;

    // Mensaje del debug
    private String debugMensaje;

    public ErrorPrinter(HttpStatus status, String mensaje, Throwable ex) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.debugMensaje = ex.getLocalizedMessage();
    }
}
