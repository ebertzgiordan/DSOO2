package trabalhodsoo2.model;

public class PatchPanel {

    private int id;
    private String nome;
    private int rackId;
    private String tipo;
    protected String codigo;
    private Rack rack;

    public PatchPanel() {
    }

    public PatchPanel(int id, String nome, int rackId, String codigo) {
        this.id = id;
        this.nome = nome;
        this.rackId = rackId;
        this.codigo = codigo;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return codigo + " - " + tipo;
    }
}
