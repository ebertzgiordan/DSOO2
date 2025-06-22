package trabalhodsoo2.model;

import java.util.List;

public interface Pesquisa {
    // Interface que define métodos de pesquisa no sistema
    Porta buscarPorCodigo(String codigo) throws PortaNaoEncontradaException;

    List<Porta> buscarPorUsuario(String nome);

    List<Porta> listarPortasLivres();
}