package brcomncn.dao;

import brcomncn.model.Receita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public void salvarReceita(Receita receita) throws SQLException {
        String sql = "INSERT INTO receitas (titulo, categoria, tempo_preparo, porcoes, ingredientes, modo_preparo, fonte) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receita.getTitulo());
            stmt.setString(2, receita.getCategoria());
            stmt.setString(3, receita.getTempoPreparo());
            stmt.setInt(4, receita.getPorcoes());
            stmt.setString(5, receita.getIngredientes());
            stmt.setString(6, receita.getModoPreparo());
            stmt.setString(7, receita.getFonte());

            stmt.executeUpdate();
        }
    }

    public List<Receita> buscarTodasReceitas() throws SQLException {
        List<Receita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM receitas";

        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getInt("id"));
                receita.setTitulo(rs.getString("titulo"));
                receita.setCategoria(rs.getString("categoria"));
                receita.setTempoPreparo(rs.getString("tempo_preparo"));
                receita.setPorcoes(rs.getInt("porcoes"));
                receita.setIngredientes(rs.getString("ingredientes"));
                receita.setModoPreparo(rs.getString("modo_preparo"));
                receita.setFonte(rs.getString("fonte"));
                receita.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());

                receitas.add(receita);
            }
        }
        return receitas;
    }
}