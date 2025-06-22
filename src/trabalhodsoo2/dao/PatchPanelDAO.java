package trabalhodsoo2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import trabalhodsoo2.entity.PatchPanel;
import trabalhodsoo2.util.ConnectionFactory;

public class PatchPanelDAO {

    public void inserir(PatchPanel patch) {
        String sql = "INSERT INTO patch_panels (codigo, tipo, rack_id) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patch.getCodigo());
            stmt.setString(2, patch.getTipo());
            stmt.setInt(3, patch.getRackId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PatchPanel> listarTodos() {
        List<PatchPanel> lista = new ArrayList<>();
        String sql = "SELECT * FROM patch_panels";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PatchPanel patch = new PatchPanel();
                patch.setId(rs.getInt("id"));
                patch.setCodigo(rs.getString("codigo"));
                patch.setTipo(rs.getString("tipo"));
                patch.setRackId(rs.getInt("rack_id"));

                lista.add(patch);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar patch panels", e);
        }

        return lista;
    }

    public void atualizar(PatchPanel patch) {
        String sql = "UPDATE patch_panels SET codigo = ?, tipo = ?, rack_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patch.getCodigo());
            stmt.setString(2, patch.getTipo());
            stmt.setInt(3, patch.getRackId());
            stmt.setInt(4, patch.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar patch panel", e);
        }
    }

    public List<PatchPanel> listarPorRack(int rackId) {
        List<PatchPanel> lista = new ArrayList<>();
        String sql = "SELECT * FROM patch_panels WHERE rack_id = ? ORDER BY codigo ASC";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rackId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PatchPanel patch = new PatchPanel();
                patch.setId(rs.getInt("id"));
                patch.setCodigo(rs.getString("codigo"));
                patch.setTipo(rs.getString("tipo"));
                patch.setRackId(rs.getInt("rack_id"));
                lista.add(patch);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM patch_panels WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdByNome(String codigo) {
        String sql = "SELECT id FROM patch_panels WHERE codigo = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; // Não encontrado
    }

    public List<PatchPanel> listarPorRackETipo(int rackId, String tipo) {
        List<PatchPanel> lista = new ArrayList<>();
        String sql = "SELECT * FROM patch_panels WHERE rack_id = ? AND tipo = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rackId);
            stmt.setString(2, tipo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PatchPanel pp = new PatchPanel();
                pp.setId(rs.getInt("id"));
                pp.setCodigo(rs.getString("codigo"));
                pp.setTipo(rs.getString("tipo"));
                pp.setRackId(rs.getInt("rack_id"));

                lista.add(pp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public boolean portaOcupada(String porta, int patchPanelId) {
        String sql = "SELECT COUNT(*) FROM portas_logicas WHERE ocupada = ? AND patch_panel_id = ? AND ocupada = 1";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, porta);
            stmt.setInt(2, patchPanelId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void remover(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
