/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhodsoo2.controller;

import java.util.List;
import trabalhodsoo2.dao.RackDAO;
import trabalhodsoo2.entity.Rack;

/**
 *
 * @author ebert
 */
public class RackController {

    private RackDAO dao = new RackDAO();

    public void salvar(String nome, String localizacao) {
        if (nome.isEmpty() || localizacao.isEmpty()) {
            throw new IllegalArgumentException("Nome e localização não podem estar vazios");
        }
        Rack rack = new Rack();
        rack.setNome(nome);
        rack.setLocalizacao(localizacao);
        dao.inserir(rack);
    }

    public List<trabalhodsoo2.entity.Rack> listar() {
        return dao.listar();
    }

    public void excluir(int id) {
        dao.excluir(id);
    }
}
