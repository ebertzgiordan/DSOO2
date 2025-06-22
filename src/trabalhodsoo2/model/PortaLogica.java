package trabalhodsoo2.model;


import trabalhodsoo2.model.Porta;

public class PortaLogica extends Porta {
    // Subclasse que representa apenas portas de rede lógica 
    private String tipoCabo;  // Tipo do cabo de rede (Cat5e, Cat6, Cat6a)
    private String uso;       // Finalidade da porta (ex: Pc, Impressora)

    public PortaLogica(String codigo, String localizacao, boolean ocupada, String usuarioFinal, String observacao, String tipoCabo, String uso, String porta) {
        super(codigo, localizacao, ocupada, usuarioFinal, observacao, porta);
        this.tipoCabo = tipoCabo;
        this.uso = uso;
    }

    @Override
    public String getTipo() {
        return "Lógica";
    }

    public String getDetalhes() {
        return String.format("[Lógica] Código: %s | Localização: %s | Tipo Cabo: %s | Uso: %s | Ocupada: %s | Usuário: %s | Obs: %s | Porta:  %s",
                codigo, localizacao, tipoCabo, uso, ocupada ? "Sim" : "Não", usuarioFinal, observacao, porta);
    }
}