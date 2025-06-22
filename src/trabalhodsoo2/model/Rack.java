package trabalhodsoo2.model;

public class Rack {
    private int id;
    private String nome;
    protected String localizacao;

    public Rack() {
    }

    public Rack(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    @Override
    public String toString() {
        return nome+(" - ") + localizacao;
    }
}
