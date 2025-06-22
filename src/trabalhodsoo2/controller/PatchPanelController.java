/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhodsoo2.controller;

import java.util.List;
import trabalhodsoo2.dao.PatchPanelDAO;
import trabalhodsoo2.entity.PatchPanel;

/**
 *
 * @author ebert
 */
public class PatchPanelController {

    public void excluir(int id) {
        dao.excluir(id);
    }

    public void atualizar(PatchPanel patch) {
        dao.atualizar(patch);
    }

    public void remover(int id) {
        dao.remover(id);
    }

    private PatchPanelDAO dao = new PatchPanelDAO();

    public void salvar(String codigo, String tipo, int rackId) {
        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("Código do patch panel é obrigatório");
        }
        PatchPanel p = new PatchPanel();
        p.setCodigo(codigo);
        p.setTipo(tipo);
        p.setRackId(rackId);
        dao.inserir(p);
    }

    public List<PatchPanel> listarPorRack(int rackId) {
        return dao.listarPorRack(rackId);
    }

    public List<PatchPanel> listarPorRackETipo(int rackId, String tipo) {
        return dao.listarPorRackETipo(rackId, tipo);
    }

    public List<PatchPanel> listar() {
    PatchPanelDAO dao = new PatchPanelDAO();
    return dao.listarTodos(); // ou o método que você tiver para listar todos os patch panels
}

}
