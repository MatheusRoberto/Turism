/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import turism.modelo.Empresa;
import turism.modelo.Veiculo;

/**
 *
 * @author matheus
 */
public class EmpresaDAO {

    public EmpresaDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Empresa em) {
        String sql = "INSERT INTO empresa(nome) VALUES(?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, em.getNome());
                stmt.execute();
                em.setIdempresa(this.selecionaUltimo().getIdempresa());
            }
            System.out.println("Empresa cadastrado sucesso");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualizar(Empresa em) {
        String sql = "UPDATE empresa SET nome = ? WHERE empresa.idempresa = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, em.getNome());
            stmt.setInt(2, em.getIdempresa());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void apagar(Empresa es) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM empresa WHERE idempresa = '" + es.getIdempresa() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar empresa " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ArrayList<Empresa> buscaEmpresas(String nome) {
        ArrayList<Empresa> list = new ArrayList<>();
        Empresa empresa;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM empresa WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                empresa = new Empresa();
                empresa.setIdempresa(result.getInt("idempresa"));
                empresa.setNome(result.getString("nome"));
                list.add(empresa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no empresa " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }

        return list;
    }

    public Empresa buscaEmpresa(int id) {
        Empresa empresa = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM empresa WHERE idempresa = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                empresa = new Empresa();
                empresa.setIdempresa(result.getInt("idempresa"));
                empresa.setNome(result.getString("nome"));
                empresa.setVeiculoList(this.carregaVeiculos(empresa));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no empresa " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return empresa;
    }

    private Empresa selecionaUltimo() {
        Empresa empresa = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idempresa) from empresa;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                empresa = buscaEmpresa(result.getInt("max(idempresa)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no empresa " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return empresa;
    }

    public ArrayList<Veiculo> carregaVeiculos(Empresa em) {
        ArrayList<Veiculo> list = new ArrayList<>();
        Veiculo veiculo;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM veiculo WHERE idEmpresa = '" + em.getIdempresa() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                veiculo = new Veiculo();
                veiculo.setIdveiculo(result.getInt("idveiculo"));
                veiculo.setNome(result.getString("nome"));
                veiculo.setLotacao(result.getInt("lotacao"));
                veiculo.setTipo(result.getString("tipo"));
                veiculo.setIdempresa(em);
                list.add(veiculo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no Veiculo " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar" + e.getMessage());
            }
        }
        return list;
    }

}
