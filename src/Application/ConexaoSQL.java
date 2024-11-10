package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class ConexaoSQL {

    private final String url = "jdbc:mysql://localhost:3306/jogodamemoria";
    private final String user = "root";
    private final String password = "321206";

    // Método de Conexão
    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    // Método QUERY para buscar dados filtrados por nome
    public void query(String nome) {
        String sql = "SELECT nome, pontos, data FROM estatistica WHERE nome = ?";

        try (Connection conn = conectaBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome); // Define o parâmetro para o filtro por nome
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nomeResult = rs.getString("nome");
                int pontos = rs.getInt("pontos");
                Date data = rs.getDate("data");

                System.out.println("Nome: " + nomeResult + ", Pontos: " + pontos + ", Data: " + data);
            }

        } catch (SQLException e) {
            System.out.println("Erro na execução da query: " + e.getMessage());
        }
    }

    // Método INSERT para inserir dados
    public void insert(String nome, int pontos) {
        String sql = "INSERT INTO estatistica (nome, pontos, data) VALUES (?, ?, ?)";

        try (Connection conn = conectaBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setInt(2, pontos);
            pstmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro na inserção: " + e.getMessage());
        }
    }

    // Exemplo de uso
    public static void main(String[] args) {
        ConexaoSQL conexao = new ConexaoSQL();

        // Executa query para listar dados de um nome específico
        conexao.query("Nome Exemplo");

        // Insere um novo registro
        conexao.insert("Novo Jogador", 150);
    }
}
