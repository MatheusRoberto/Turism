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
import turism.modelo.Pais;

public class EstadoDAO {

    public EstadoDAO() {
        // TODO Stub de construtor gerado automaticamente
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Estado e) {
        String sql = "INSERT INTO estado(nome,uf, idpais) VALUES(?,?,?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, e.getNome());
                stmt.setString(2, e.getUf());
                stmt.setInt(3, e.getIdpais().getIdpais());
                stmt.execute();
                e.setIdestado(this.selecionaUltimo().getIdestado());
            }
            System.out.println("Estado cadastrado com sucesso");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualizar(Estado e) {
        String sql = "UPDATE estado SET nome = ?, uf = ?, idpais = ? WHERE estado.idestado = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getUf());
            stmt.setInt(3, e.getIdpais().getIdpais());
            stmt.setInt(4, e.getIdestado());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void apagar(Estado es) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM estado WHERE idestado = '" + es.getIdestado() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar estado" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Estado> buscaEstado(String nome, String uf) {
        ArrayList<Estado> list = new ArrayList<>();
        Estado estado;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM estado WHERE nome LIKE '" + nome + "%' OR '" + uf + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                estado = new Estado();
                estado.setIdestado(result.getInt("idestado"));
                estado.setNome(result.getString("nome"));
                estado.setUf(result.getString("uf"));
                Pais pais;
                PaisDAO pDAO = new PaisDAO();
                pais = pDAO.buscaPais(result.getInt("idpais"));
                estado.setIdpais(pais);
                list.add(estado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no estado" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }

        return list;
    }

    public Estado buscaEstado(int id) {
        Estado estado = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM estado WHERE idestado = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                estado = new Estado();
                estado.setIdestado(result.getInt("idestado"));
                estado.setNome(result.getString("nome"));
                estado.setUf(result.getString("uf"));
                Pais pais;
                PaisDAO pDAO = new PaisDAO();
                pais = pDAO.buscaPais(result.getInt("idpais"));
                estado.setIdpais(pais);
                estado.setCidadeList(this.carregaCidades(estado));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no estado" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return estado;
    }

    public ArrayList<Cidade> carregaCidades(Estado es) {
        ArrayList<Cidade> list = new ArrayList<>();
        Cidade cidade;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cidade WHERE idEstado = '" + es.getIdestado() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cidade = new Cidade();
                cidade.setIdcidade(result.getInt("idcidade"));
                cidade.setNome(result.getString("nome"));
                cidade.setIdestado(es);
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

    private Estado selecionaUltimo() {
        Estado estado = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idestado) from estado;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                estado = buscaEstado(result.getInt("max(idestado)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no estado " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return estado;
    }

}
