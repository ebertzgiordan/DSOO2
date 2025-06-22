package trabalhodsoo2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import trabalhodsoo2.entity.Telefone;
import trabalhodsoo2.model.PortaTelefone;
import trabalhodsoo2.util.ConnectionFactory;

public class TelefoneDAO {

    private Connection conn;

    public TelefoneDAO(Connection conn) {
        this.conn = conn;
    }

    public TelefoneDAO() {

    }

    public void inserir(PortaTelefone porta) {
        String sql = "INSERT INTO portas_telefone (codigo, localizacao, ocupada, usuarioFinal, observacao, ramal, corCabo, porta, patch_panel_id, rack_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, porta.getCodigo());
            stmt.setString(2, porta.getLocalizacao());
            stmt.setBoolean(3, porta.isOcupada());
            stmt.setString(4, porta.getUsuarioFinal());
            stmt.setString(5, porta.getObservacao());
            stmt.setString(6, porta.getRamal());
            stmt.setString(7, porta.getCorCabo());
            stmt.setString(8, porta.getPorta());
            stmt.setInt(9, porta.getPatchPanelId());
            stmt.setInt(10, porta.getRackId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir porta de telefone: " + e.getMessage(), e);
        }
    }

    public boolean inserirPortaTelefone(Telefone p) {
        String sql = "INSERT INTO portas_telefone (codigo, ocupada, localizacao, tipo, patch_panel_id, rack_id, usuarioFinal, observacao, tipoCabo, uso, ramal, corCabo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getCodigo());
            stmt.setBoolean(2, p.isOcupada());
            stmt.setString(3, p.getLocalizacao());
            stmt.setString(4, p.getTipo());
            stmt.setInt(5, p.getPatchPanelId());
            stmt.setInt(6, p.getRackId());
            stmt.setString(7, p.getUsuarioFinal());
            stmt.setString(8, p.getObservacao());
            stmt.setString(9, p.getTipoCabo());
            stmt.setString(10, p.getUso());
            stmt.setString(11, p.getRamal());
            stmt.setString(12, p.getCorCabo());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir telefone: " + ex.getMessage());
            return false;
        }
    }

    public List<Telefone> listar() {
        List<Telefone> lista = new ArrayList<>();

        String sql = "SELECT * FROM portas_telefone ORDER BY id";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Telefone t = new Telefone();
                t.setId(rs.getInt("id"));
                t.setCodigo(rs.getString("codigo"));
                t.setLocalizacao(rs.getString("localizacao"));
                t.setOcupada(rs.getBoolean("ocupada"));
                t.setUsuarioFinal(rs.getString("usuarioFinal"));
                t.setObservacao(rs.getString("observacao"));
                t.setTipoCabo(rs.getString("tipoCabo"));
                t.setRamal(rs.getString("ramal"));
                t.setCorCabo(rs.getString("corCabo"));
                t.setPorta(rs.getString("porta"));
                t.setRackId(rs.getInt("rack_id"));
                t.setPatchPanelId(rs.getInt("patch_panel_id"));
                lista.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar portas de telefone: " + e.getMessage(), e);
        }

        return lista;
    }

    public boolean portaOcupada(String portaCodigo, int patchPanelId) {
        String sql = "SELECT COUNT(*) FROM portas_telefone WHERE porta = ? AND patch_panel_id = ? AND ocupada = 1";

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

    public void atualizar(Telefone porta) {
        String sql = "UPDATE portas_logicas SET codigo=?, localizacao=?, ocupada=?, usuarioFinal=?, observacao=?, tipoCabo=?, uso=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, porta.getCodigo());
            stmt.setString(2, porta.getLocalizacao());
            stmt.setBoolean(3, porta.isOcupada());
            stmt.setString(4, porta.getUsuarioFinal());
            stmt.setString(5, porta.getObservacao());
            stmt.setString(6, porta.getTipoCabo());
            stmt.setString(7, porta.getUso());
            stmt.setInt(8, porta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar porta lógica: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM portas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir porta: " + e.getMessage(), e);
        }
    }
}
