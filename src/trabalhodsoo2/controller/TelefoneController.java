/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhodsoo2.controller;

import java.util.List;
import trabalhodsoo2.dao.TelefoneDAO;
import trabalhodsoo2.entity.Telefone;
import trabalhodsoo2.model.PortaTelefone;

/**
 *
 * @author ebert
 */
public class TelefoneController {

    private TelefoneDAO dao = new TelefoneDAO();

    public void salvar(Telefone telefone) {
        if (telefone.getCodigo() == null || telefone.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("Código da porta obrigatório");
        }

        dao.inserirPortaTelefone(telefone);
    }

    public List<Telefone> listar() {
        return dao.listar();
    }

    public boolean portaOcupada(String porta, int patchPanelId) {
        return dao.portaOcupada(porta, patchPanelId);
    }

    public void salvar(PortaTelefone telefone) {
        new TelefoneDAO().inserir(telefone);
    }

}