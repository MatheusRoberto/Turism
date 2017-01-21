package turism.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Cidade;
import turism.modelo.Estado;

public class CidadeDAO {

    public CidadeDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Cidade c) {
        String sql = "INSERT INTO cidade(nome,idestado) VALUES(?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdestado().getIdestado());
            stmt.execute();
            c.setIdcidade(this.selecionaUltimo().getIdcidade());
        } catch (SQLException u) {
            throw new RuntimeException(u);

        }
    }

    public void apagar(Cidade c) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM cidade WHERE idcidade = '" + c.getIdcidade() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar cidade" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void atualiza(Cidade c) {
        String sql = "UPDATE cidade SET nome = ?, idestado = ? WHERE cidade.idcidade = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdestado().getIdestado());
            stmt.setInt(3, c.getIdcidade());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Cidade> buscaCidades(String nome) {
        ArrayList<Cidade> list = new ArrayList<>();
        Cidade cidade;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cidade WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cidade = new Cidade();
                cidade.setIdcidade(result.getInt("idcidade"));
                cidade.setNome(result.getString("nome"));
                Estado estado;
                EstadoDAO eDAO = new EstadoDAO();
                estado = eDAO.buscaEstado(result.getInt("idestado"));
                cidade.setIdestado(estado);
                list.add(cidade);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro na cidade" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }

        return list;
    }

    public Cidade buscaCidade(int id) {
        Cidade cidade = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cidade WHERE idcidade = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cidade = new Cidade();
                cidade.setIdcidade(result.getInt("idcidade"));
                cidade.setNome(result.getString("nome"));
                Estado estado;
                EstadoDAO eDAO = new EstadoDAO();
                estado = eDAO.buscaEstado(result.getInt("idestado"));
                cidade.setIdestado(estado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro na cidade" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return cidade;
    }

    private Cidade selecionaUltimo() {
        Cidade cidade = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idcidade) from cidade;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cidade = buscaCidade(result.getInt("max(idcidade)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro na cidade" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return cidade;
    }
}
