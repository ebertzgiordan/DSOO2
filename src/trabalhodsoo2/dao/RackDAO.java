package trabalhodsoo2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import trabalhodsoo2.model.Rack;
import trabalhodsoo2.entity.Rack;
import trabalhodsoo2.util.ConnectionFactory;

public class RackDAO {

    public void inserir(Rack rack) {
        String sql = "INSERT INTO racks (nome, localizacao) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rack.getNome());
            stmt.setString(2, rack.getLocalizacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir rack: " + e.getMessage(), e);
        }
    }

    public void atualizar(Rack rack) {
        String sql = "UPDATE racks SET nome=?, localizacao=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rack.getNome());
            stmt.setString(2, rack.getLocalizacao());
            stmt.setInt(3, rack.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar rack: " + e.getMessage(), e);
        }
    }

    public void excluir(int rackId) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            // Excluir portas lógicas
            String sqlPortasLogicas = "DELETE FROM portas_logicas WHERE rack_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlPortasLogicas)) {
                stmt.setInt(1, rackId);
                stmt.executeUpdate();
            }

            // Excluir portas de telefone
            String sqlPortasTelefone = "DELETE FROM portas_telefone WHERE rack_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlPortasTelefone)) {
                stmt.setInt(1, rackId);
                stmt.executeUpdate();
            }

            // Excluir patch panels
            String sqlPatch = "DELETE FROM patch_panels WHERE rack_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlPatch)) {
                stmt.setInt(1, rackId);
                stmt.executeUpdate();
            }

            // Excluir rack
            String sqlRack = "DELETE FROM racks WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlRack)) {
                stmt.setInt(1, rackId);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir rack e seus dados vinculados", e);
        }
    }

    public List<Rack> listar() {
        List<Rack> lista = new ArrayList<>();
        String sql = "SELECT * FROM racks ORDER BY id ASC";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rack r = new Rack();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setLocalizacao(rs.getString("localizacao"));
                lista.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public int getIdByNome(String nome) {
        String sql = "SELECT id FROM racks WHERE nome = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; // Não encontrado
    }
}
