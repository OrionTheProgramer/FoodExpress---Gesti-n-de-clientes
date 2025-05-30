package com.foodexpress.microservice.Exception;

import com.foodexpress.microservice.Exception.Exceptions.BadRequestException;
import com.foodexpress.microservice.Exception.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Captura la excetion: ResourceNotFoundException
     *
     * @return Informe de error json
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorPrinter> NoEncontrado(ResourceNotFoundException ex){
        ErrorPrinter error = new ErrorPrinter(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(error);
    }

    /**
     * HTTP 400,
     * que indica que el servidor no puede procesar la solicitud,
     * debido a un problema con la forma en que fue construida.
     *
     *
     * @return Informe de error json.
     */
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorPrinter> MalaRespuesta(BadRequestException ex){
        ErrorPrinter error = new ErrorPrinter(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        return buildResponseEntity(error);
    }

    /**
     * Una excepción de Spring MVC que se lanza antes de llegar al controlador,
     * cuando un @RequestBody anotado con @Valid o @Validated,
     * no cumple con las restricciones(@NotBlank, @NotNull, Size, etc).
     *
     * @return Informe de error json.
     */
    protected ResponseEntity<Object> ArgumentoNoValido(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        StringBuilder sb = new StringBuilder("Errores de validación:");
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> sb.append(" [").append(err.getField())
                        .append(": ").append(err.getDefaultMessage()).append("]"));
        ErrorPrinter error = new ErrorPrinter(HttpStatus.BAD_REQUEST, sb.toString(), ex);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorPrinter> ErroresGenerales(Exception ex) {
        ErrorPrinter error = new ErrorPrinter(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrió un error inesperado",
                ex
        );
        return buildResponseEntity(error);
    }

    /**
     * Responsable de crear el ResponseEntity de la clase ErrorPrinter
     */
    private ResponseEntity<ErrorPrinter> buildResponseEntity(ErrorPrinter errorPrinter) {
        return new ResponseEntity<>(errorPrinter, errorPrinter.getStatus());
    }
}
