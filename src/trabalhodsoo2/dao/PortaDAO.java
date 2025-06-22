package trabalhodsoo2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import trabalhodsoo2.entity.PatchPanel;
import trabalhodsoo2.entity.Porta;
import trabalhodsoo2.entity.Rack;
import trabalhodsoo2.util.ConnectionFactory;

public class PortaDAO {

    public void inserir(Porta porta) {
        String sql = "INSERT INTO portas_logicas (codigo, localizacao, ocupada, usuarioFinal, observacao, tipoCabo, uso, porta, patch_panel_id, rack_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, porta.getCodigo());
            stmt.setString(2, porta.getLocalizacao());
            stmt.setBoolean(3, porta.isOcupada());
            stmt.setString(4, porta.getUsuarioFinal());
            stmt.setString(5, porta.getObservacao());
            stmt.setString(6, porta.getTipoCabo());
            stmt.setString(7, porta.getUso());
            stmt.setString(8, porta.getPorta());
            stmt.setInt(9, porta.getPatchPanelId());
            stmt.setInt(10, porta.getRackId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir porta: " + e.getMessage(), e);
        }
    }

    public Porta buscarPorId(int id, String tipo) {
    String tabela = tipo.equalsIgnoreCase("Telefone") ? "portas_telefone" : "portas_logicas";

    String sql = "SELECT p.*, pp.codigo AS patch_codigo, pp.id AS patch_id, "
               + "r.id AS rack_id, r.nome AS rack_nome "
               + "FROM " + tabela + " p "
               + "JOIN patch_panels pp ON p.patch_panel_id = pp.id "
               + "JOIN racks r ON pp.rack_id = r.id "
               + "WHERE p.id = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Porta porta = new Porta();
            porta.setId(rs.getInt("id"));
            porta.setCodigo(rs.getString("codigo"));
            porta.setLocalizacao(rs.getString("localizacao"));
            porta.setOcupada(rs.getBoolean("ocupada"));
            porta.setUsuarioFinal(rs.getString("usuarioFinal"));
            porta.setObservacao(rs.getString("observacao"));
            porta.setPorta(rs.getString("porta")); // número da porta (ex: 01)

            porta.setRackId(rs.getInt("rack_id"));
            porta.setPatchPanelId(rs.getInt("patch_panel_id"));

            // Campos específicos de tipo
            if (tipo.equalsIgnoreCase("Telefone")) {
                porta.setRamal(rs.getString("ramal"));
                porta.setCorCabo(rs.getString("corCabo"));
            } else {
                porta.setTipoCabo(rs.getString("tipoCabo"));
                porta.setUso(rs.getString("uso"));
            }

            // Patch Panel
            PatchPanel patch = new PatchPanel();
            patch.setId(rs.getInt("patch_id"));
            patch.setCodigo(rs.getString("patch_codigo"));

            // Rack
            Rack rack = new Rack();
            rack.setId(rs.getInt("rack_id"));
            rack.setNome(rs.getString("rack_nome"));

            patch.setRack(rack);
            porta.setPatchPanel(patch);

            return porta;
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar porta por ID: " + e.getMessage(), e);
    }

    return null;
}


    public List<Porta> pesquisarPorCodigo(String tipo, String patchCodigo, String numeroPorta) {
    List<Porta> portas = new ArrayList<>();

    String tabela = tipo.equalsIgnoreCase("Telefone") ? "portas_telefone" : "portas_logicas";
    
    // 🟢 Aqui montamos o código conforme o padrão do banco (ex: LG02, TL01)
    String codigoPadrao = (tipo.equalsIgnoreCase("Telefone") ? "TL" : "LG") + patchCodigo;

    String sql = "SELECT p.*, p.patch_panel_id AS id_patch_panel, pp.codigo AS patch_codigo, "
               + "r.id AS rack_id, r.nome AS rack_nome "
               + "FROM " + tabela + " p "
               + "JOIN patch_panels pp ON p.patch_panel_id = pp.id "
               + "JOIN racks r ON pp.rack_id = r.id "
               + "WHERE TRIM(pp.codigo) = ? AND TRIM(p.porta) = ?";

    try (Connection conn = ConnectionFactory.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, codigoPadrao); // ex: LG02
        stmt.setString(2, numeroPorta);  // ex: 01

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Porta porta = new Porta();
            porta.setId(rs.getInt("id"));
            porta.setCodigo(rs.getString("codigo"));
            porta.setLocalizacao(rs.getString("localizacao"));
            porta.setOcupada(rs.getBoolean("ocupada"));
            porta.setUsuarioFinal(rs.getString("usuarioFinal"));
            porta.setObservacao(rs.getString("observacao"));

            if (tipo.equalsIgnoreCase("Telefone")) {
                porta.setRamal(rs.getString("ramal"));
                porta.setCorCabo(rs.getString("corCabo"));
            } else {
                porta.setTipoCabo(rs.getString("tipoCabo"));
                porta.setUso(rs.getString("uso"));
            }

            PatchPanel patch = new PatchPanel();
            patch.setId(rs.getInt("id_patch_panel"));
            patch.setCodigo(rs.getString("patch_codigo"));

            Rack rack = new Rack();
            rack.setId(rs.getInt("rack_id"));
            rack.setNome(rs.getString("rack_nome"));

            patch.setRack(rack);
            porta.setPatchPanel(patch);

            portas.add(porta);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao pesquisar porta por código: " + e.getMessage(), e);
    }

    return portas;
}

    public List<Porta> listar(int patchPanelId, String tipo) {
        List<Porta> lista = new ArrayList<>();
        String tabela = tipo.equals("Lógico") ? "portas_logicas" : "portas_telefone";
        String sql = "SELECT * FROM " + tabela + " WHERE patch_panel_id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patchPanelId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Porta p = new Porta();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setLocalizacao(rs.getString("localizacao"));
                p.setOcupada(rs.getBoolean("ocupada"));
                p.setObservacao(rs.getString("observacao"));
                p.setUsuarioFinal(rs.getString("usuarioFinal"));

                if (tipo.equals("Lógico")) {
                    p.setTipoCabo(rs.getString("tipoCabo"));
                    p.setUso(rs.getString("uso"));
                } else {
                    p.setRamal(rs.getString("ramal"));
                    p.setCorCabo(rs.getString("corCabo"));
                }

                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Porta porta, String tipo) {
        String sql;

        if (tipo.equalsIgnoreCase("Telefone")) {
            sql = "UPDATE portas_telefone SET codigo=?, localizacao=?, ocupada=?, usuarioFinal=?, observacao=?, corCabo=?, ramal=? WHERE id=?";
        } else {
            sql = "UPDATE portas_logicas SET codigo=?, localizacao=?, ocupada=?, usuarioFinal=?, observacao=?, tipoCabo=?, uso=? WHERE id=?";
        }

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, porta.getCodigo());
            stmt.setString(2, porta.getLocalizacao());
            stmt.setBoolean(3, porta.isOcupada());
            stmt.setString(4, porta.getUsuarioFinal());
            stmt.setString(5, porta.getObservacao());

            if (tipo.equalsIgnoreCase("Telefone")) {
                stmt.setString(6, porta.getCorCabo());
                stmt.setString(7, porta.getRamal());
                stmt.setInt(8, porta.getId());
            } else {
                stmt.setString(6, porta.getTipoCabo());
                stmt.setString(7, porta.getUso());
                stmt.setInt(8, porta.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar porta: " + e.getMessage(), e);
        }
    }

    public boolean portaOcupada(String portaCodigo, int patchPanelId) {
        String sql = "SELECT COUNT(*) FROM portas_logicas WHERE porta = ? AND patch_panel_id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, portaCodigo);
            stmt.setInt(2, patchPanelId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se porta está ocupada: " + e.getMessage(), e);
        }

        return false;
    }

    public void excluir(int id, String tipo) {
        String tabela = tipo.equalsIgnoreCase("Telefone") ? "portas_telefone" : "portas_logicas";
        String sql = "DELETE FROM " + tabela + " WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir porta: " + e.getMessage(), e);
        }
    }

}
