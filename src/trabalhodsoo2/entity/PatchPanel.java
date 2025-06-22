/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhodsoo2.entity;

/**
 *
 * @author ebert
 */
public class PatchPanel {

    private int id;
    private String codigo;
    private String tipo;
    private int rackId;
    private String rackNome;
    private Rack rack;

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public int getId() {
        return id;
    }

    public void setRackNome(String rackNome) {
        this.rackNome = rackNome;
    }

    public String getRackNome() {
        return rackNome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }

    @Override
    public String toString() {
        return codigo + " [" + tipo + "]";
    }

}
