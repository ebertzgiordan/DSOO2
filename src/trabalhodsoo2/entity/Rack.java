/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhodsoo2.entity;

/**
 *
 * @author ebert
 */
public class Rack {

    private int id;
    private String nome;
    private String localizacao;
    

    public Rack(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public Rack() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return nome + " (" + localizacao + ")";
    }
}
