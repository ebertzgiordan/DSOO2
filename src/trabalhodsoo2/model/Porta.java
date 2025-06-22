package trabalhodsoo2.model;

public class Porta {
    private int id;
    protected String codigo;
    protected String localizacao;
    protected boolean ocupada;
    protected String usuarioFinal;
    protected String observacao;
    protected String porta;
    private String tipo;
    private String tipoCabo;
    private String uso;
    private String ramal;
    private String corCabo;
    private int rackId;
    private int patchPanelId;

    public Porta(String codigo, String localizacao, boolean ocupada, String usuarioFinal, String observacao, String porta) {
        this.codigo = codigo;
        this.localizacao = localizacao;
        this.ocupada = ocupada;
        this.usuarioFinal = usuarioFinal;
        this.observacao = observacao;
        this.porta = porta;
    }

    public Porta() {
    }    

    public int getId() {
        return id;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getUsuarioFinal() {
        return usuarioFinal;
    }

    public void setUsuarioFinal(String usuarioFinal) {
        this.usuarioFinal = usuarioFinal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoCabo() {
        return tipoCabo;
    }

    public void setTipoCabo(String tipoCabo) {
        this.tipoCabo = tipoCabo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getCorCabo() {
        return corCabo;
    }

    public void setCorCabo(String corCabo) {
        this.corCabo = corCabo;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }

    public int getPatchPanelId() {
        return patchPanelId;
    }

    public void setPatchPanelId(int patchPanelId) {
        this.patchPanelId = patchPanelId;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    @Override
    public String toString() {
        return "Porta{" + "id=" + id + ", codigo=" + codigo + ", localizacao=" + localizacao + ", ocupada=" + ocupada + ", usuarioFinal=" + usuarioFinal + ", observacao=" + observacao + ", porta=" + porta + ", tipo=" + tipo + ", tipoCabo=" + tipoCabo + ", uso=" + uso + ", ramal=" + ramal + ", corCabo=" + corCabo + ", rackId=" + rackId + ", patchPanelId=" + patchPanelId + '}';
    }
}
