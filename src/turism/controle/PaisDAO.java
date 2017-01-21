package turism.controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Estado;
import turism.modelo.Pais;

public class PaisDAO {

    public PaisDAO() {
        // TODO Stub de construtor gerado automaticamente
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Pais p) {
        String sql = "INSERT INTO pais(nome, sigla) VALUES(?,?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, p.getNome());
                stmt.setString(2, p.getSigla());
                stmt.execute();
                p.setIdpais(this.selecionaUltimo().getIdpais());
            }
            System.out.println("Pais cadastrado com sucesso");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualiza(Pais pais) {
        PreparedStatement stmt;
        String sql = "UPDATE pais SET nome = ?, sigla = ? WHERE pais.idpais = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pais.getNome());
            stmt.setString(2, pais.getSigla());
            stmt.setInt(3, pais.getIdpais());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Pais pais) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM pais WHERE nome = '" + pais.getIdpais() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar pais" + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                e.printStackTrace();
            }
        }
    }

    public Pais buscaPais(int id) {
        Pais pais = new Pais();
        PreparedStatement stmt;
        ResultSet result;
        String sql = "SELECT * FROM pais WHERE idpais = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                pais.setIdpais(result.getInt("idpais"));
                pais.setNome(result.getString("nome"));
                pais.setSigla(result.getString("sigla"));
                pais.setEstadoList(this.carregaEstado(pais));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pais;
    }

    public ArrayList<Pais> buscaPaises(String nome) {
        ArrayList<Pais> list = new ArrayList<>();
        Pais pais;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM pais WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                pais = new Pais();
                pais.setIdpais(result.getInt("idpais"));
                pais.setNome(result.getString("nome"));
                pais.setSigla(result.getString("sigla"));
                list.add(pais);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no pais" + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }

        return list;
    }

    public ArrayList<Estado> carregaEstado(Pais p) {
        ArrayList<Estado> list = new ArrayList<>();
        Estado estado;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM estado WHERE idpais = '" + p.getIdpais() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                estado = new Estado();
                estado.setIdestado(result.getInt("idestado"));
                estado.setNome(result.getString("nome"));
                estado.setUf(result.getString("uf"));
                estado.setIdpais(p);
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

    private Pais selecionaUltimo() {
        Pais pais = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idpais) from pais;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                pais = buscaPais(result.getInt("max(idpais)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no pais " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return pais;
    }
}
