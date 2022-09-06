package com.io.sports.brasileiraoapp.advice;

import com.io.sports.brasileiraoapp.error.ApiErroMessage;
import com.io.sports.brasileiraoapp.exception.RecursoInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RecursoInexistenteException.class)
    public ResponseEntity<ApiErroMessage> resourceNotFound(RecursoInexistenteException e, HttpServletRequest req){
        ApiErroMessage apiErroMessage = new ApiErroMessage();
        apiErroMessage.setTimestamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("Recurso Inexistente")
                .setMessage(e.getMessage())
                .setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErroMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErroMessage> requestParameterException(MissingServletRequestParameterException e, HttpServletRequest req){
        ApiErroMessage apiErroMessage = new ApiErroMessage();
        apiErroMessage.setTimestamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("parametros inválidos")
                .setMessage(e.getMessage())
                .setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErroMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErroMessage> methodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest req){
        ApiErroMessage apiErroMessage = new ApiErroMessage();
        apiErroMessage.setTimestamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("método não suportado")
                .setMessage(e.getMessage())
                .setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErroMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErroMessage> typeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest req){
        ApiErroMessage apiErroMessage = new ApiErroMessage();
        apiErroMessage.setTimestamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("url mal formada")
                .setMessage(e.getMessage())
                .setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErroMessage);
    }


}
