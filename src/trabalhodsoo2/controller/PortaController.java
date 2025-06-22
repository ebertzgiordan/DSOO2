package trabalhodsoo2.controller;

import java.util.List;
import trabalhodsoo2.dao.PortaDAO;
import trabalhodsoo2.entity.Porta;

public class PortaController {

    private PortaDAO dao = new PortaDAO();

    public void salvar(Porta porta) {
        if (porta.getCodigo() == null || porta.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("Código da porta obrigatório");
        }

        dao.inserir(porta);
    }

    public List<Porta> listarPorPatchPanelETipo(int patchPanelId, String tipo) {
        PortaDAO dao = new PortaDAO();
        return dao.listar(patchPanelId, tipo);
    }

    public boolean portaOcupada(String porta, int patchPanelId) {
        return dao.portaOcupada(porta, patchPanelId);
    }
}
