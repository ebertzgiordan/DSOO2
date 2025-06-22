package trabalhodsoo2.model;


import trabalhodsoo2.model.Porta;

public class PortaTelefone extends Porta {
    // Subclasse que representa portas destinadas a ramais telefônicos
    private String ramal;    // Número do ramal de telefone
    private String corCabo;  // Cor do cabo de telefone (ex: vermelho)
    
    public PortaTelefone(String codigo, String localizacao, boolean ocupada, String usuarioFinal, String observacao, String ramal, String corCabo, String porta) {
        super(codigo, localizacao, ocupada, usuarioFinal, observacao, porta);
        this.ramal = ramal;
        this.corCabo = corCabo;
    }

    public PortaTelefone() {
       
    }

    @Override
    public String getTipo() {
        return "Telefone";
    }

    // Retorna uma string com todas as informações detalhadas da porta de telefone
    public String getDetalhes() {
        return String.format("[Telefone] Código: %s | Localização: %s | Ramal: %s | Cor Cabo: %s | Ocupada: %s | Usuário: %s | Obs: %s | Porta: %s",
                codigo, localizacao, ramal, corCabo, ocupada ? "Sim" : "Não", usuarioFinal, observacao, porta);
    }
}