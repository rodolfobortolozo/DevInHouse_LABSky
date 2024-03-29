package tech.devinhouse.labsky.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.devinhouse.labsky.exceptions.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({PassagerioException.class, AssentoException.class})
  public ResponseEntity handleException(Exception ex){

    HttpStatus codigo = HttpStatus.BAD_REQUEST;
    ErrorResponse error = new ErrorResponse(ex.getMessage(),codigo.value(),codigo.toString(),ex.getClass().getSimpleName(), null);
    return ResponseEntity.status(codigo).body(error);
  }

  @ExceptionHandler({PassagerioNotFoundException.class, AssentoNotFoundException.class})
  public ResponseEntity handleNotFoundException(Exception ex){

    HttpStatus codigo = HttpStatus.NOT_FOUND;
    ErrorResponse error = new ErrorResponse(ex.getMessage(),codigo.value(),codigo.toString(),ex.getClass().getSimpleName(), null);
    return ResponseEntity.status(codigo).body(error);
  }

  @ExceptionHandler({PassagerioConflitException.class, AssentoConflitException.class})
  public ResponseEntity handleUnauthorizedException(Exception ex){

    HttpStatus codigo = HttpStatus.CONFLICT;
    ErrorResponse error = new ErrorResponse(ex.getMessage(),codigo.value(),codigo.toString(),ex.getClass().getSimpleName(), null);
    return ResponseEntity.status(codigo).body(error);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,  HttpStatusCode status, WebRequest request) {
    List<ErrorObject> errors = getErrors(ex);
    ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
    return new ResponseEntity<>(errorResponse, status);
  }

  private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatusCode status, List<ErrorObject> errors) {
    return new ErrorResponse("Requisição possui Campo(s) Inválido(s)", status.value(),
            status.toString(), ex.getBindingResult().getObjectName(), errors);
  }

  private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new ErrorObject(error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
            .collect(Collectors.toList());
  }

}