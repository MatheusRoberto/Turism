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
import turism.conexao.Conexao;
import turism.modelo.Cliente;
import turism.modelo.Depedente;

/**
 *
 * @author matheus
 */
public class DepedenteDAO {

    public DepedenteDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Depedente d) {
        PreparedStatement stmt;
        String sql = "INSERT INTO depedente (idcliente, nome, rg) VALUES (?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, d.getIdcliente().getIdcliente());
            stmt.setString(2, d.getNome());
            stmt.setString(3, d.getRg());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DepedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Depedente d) {
        PreparedStatement stmt;
        String sql = "UPDATE depedente idcliente = ?, nome = ?, rg = ? WHERE iddepedente = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, d.getIdcliente().getIdcliente());
            stmt.setString(2, d.getNome());
            stmt.setString(3, d.getRg());
            stmt.setInt(4, d.getIddepedente());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DepedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Depedente d) {
        PreparedStatement stmt;
        String sql = "DELETE FROM depedente WHERE depedente.iddepedente = '" + d.getIddepedente() + "' ;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DepedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Depedente> buscaDepedente(String nome) {
        ArrayList<Depedente> list = new ArrayList<>();
        Depedente depedente;
        PreparedStatement stmt;
        ResultSet result;
        String sql = "SELECT * FROM depedente WHERE nome LIKE '%" + nome + "%' ;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                depedente = new Depedente();
                depedente.setIddepedente(result.getInt("iddepedente"));
                depedente.setNome(result.getString("nome"));
                depedente.setRg(result.getString("rg"));
                ClienteDAO cDAO = new ClienteDAO();
                Cliente c = cDAO.buscaCliente(result.getInt("idcliente"));
                depedente.setIdcliente(c);
                list.add(depedente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Depedente> buscaDepedenteCliente(int idcliente) {
        ArrayList<Depedente> list = new ArrayList<>();
        Depedente depedente;
        PreparedStatement stmt;
        ResultSet result;
        String sql = "SELECT * FROM depedente WHERE idcliente = '" + idcliente + "' ;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                depedente = new Depedente();
                depedente.setIddepedente(result.getInt("iddepedente"));
                depedente.setNome(result.getString("nome"));
                depedente.setRg(result.getString("rg"));
                ClienteDAO cDAO = new ClienteDAO();
                Cliente c = cDAO.buscaCliente(result.getInt("idcliente"));
                depedente.setIdcliente(c);
                list.add(depedente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
