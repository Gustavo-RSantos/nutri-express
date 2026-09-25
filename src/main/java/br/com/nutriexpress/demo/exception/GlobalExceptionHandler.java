package br.com.nutriexpress.demo.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // Excessão customizada para lidar com a busca de algum dado que não existe no banco de dados
    @ExceptionHandler(DadoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Retorna 404 -> Recurso não encontrado
    public Map<String, String> handleResourceNotFount(DadoNaoEncontradoException ex){
        Map<String, String> error = new java.util.HashMap<>();
        error.put("message", ex.getMessage()); // Busca a mensagem de erro escrita no Service para retornar junto com o 404
        return error;
    }

    // Excessão para tratar error não falta de parametros em uma requisição
    // Ex: Falta do numero maximo de calorias no metodo getAllPratoByMaxCalorias()
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestParamMissing(MissingServletRequestParameterException ex){
        Map<String, String> error = new java.util.HashMap<>();
        String parameterName = ex.getParameterName();
        String errorMessage = ex.getMessage();
        error.put(parameterName, errorMessage);
        return error;
    }
}
