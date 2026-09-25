package br.com.nutriexpress.demo.exception;

public class DadoNaoEncontradoException extends RuntimeException {
    public DadoNaoEncontradoException(String message) {
        super(message);
    }
}
