package trabalhodsoo2.model;

public class PortaNaoEncontradaException extends Exception {
    // Exceção personalizada lançada quando uma porta não é encontrada pelo código
    public PortaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}