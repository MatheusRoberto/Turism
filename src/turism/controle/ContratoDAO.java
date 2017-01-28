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
import turism.modelo.Cliente;
import turism.modelo.Contrato;
import turism.modelo.Formapagamento;
import turism.modelo.Usuario;
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class ContratoDAO {

    public ContratoDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Contrato contrato) {
        PreparedStatement stmt;
        String sql = "INSERT INTO contrato (idforma, idcliente, idusuario, idviagem, dataemissao, quantidade, paga, desconto)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, contrato.getIdforma().getIdformapagamento());
            stmt.setInt(2, contrato.getIdcliente().getIdcliente());
            stmt.setInt(3, contrato.getIdusuario().getIdusuario());
            stmt.setInt(4, contrato.getIdviagem().getIdviagem());
            stmt.setDate(5, new java.sql.Date(contrato.getDataemissao().getTime()));
            stmt.setInt(6, contrato.getQuantidade());
            stmt.setBoolean(7, contrato.getPaga());
            stmt.setDouble(8, contrato.getDesconto());
            stmt.execute();
            contrato.setIdcontrato(this.selecionaUltimo().getIdcontrato());
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Contrato contrato) {
        PreparedStatement stmt;
        String sql = "UPDATE contrato  SET idforma = ?, idcliente = ?, idusuario = ?, idviagem = ?, dataemissao = ?,"
                + "quantidade = ?, paga = ?, desconto = ? WHERE contrato.idcontrato = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, contrato.getIdforma().getIdformapagamento());
            stmt.setInt(2, contrato.getIdcliente().getIdcliente());
            stmt.setInt(3, contrato.getIdusuario().getIdusuario());
            stmt.setInt(4, contrato.getIdviagem().getIdviagem());
            stmt.setDate(5, new java.sql.Date(contrato.getDataemissao().getTime()));
            stmt.setInt(6, contrato.getQuantidade());
            stmt.setBoolean(7, contrato.getPaga());
            stmt.setDouble(8, contrato.getDesconto());
            stmt.setInt(9, contrato.getIdcontrato());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void apagar(Contrato contrato) {
        PreparedStatement stmt;
        String sql = "DELETE FROM contrato WHERE contrato.idcontrato = '" + contrato.getIdcontrato() + "' ;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Contrato buscaContrato(int idcontrato) {
        Contrato contrato = null;
        String sql = "SELECT * FROM contrato WHERE idcontrato = '" + idcontrato + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                contrato = new Contrato();
                contrato.setDataemissao(result.getDate("dataemissao"));
                contrato.setPaga(result.getBoolean("paga"));
                contrato.setQuantidade(result.getInt("quantidade"));
                contrato.setIdcontrato(result.getInt("idcontrato"));
                contrato.setDesconto(result.getDouble("desconto"));
                FormaPGDAO fDAO = new FormaPGDAO();
                Formapagamento fp = fDAO.buscaForma(result.getInt("idforma"));
                contrato.setIdforma(fp);
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario u = uDAO.buscaUsuario(result.getInt("idusuario"));
                contrato.setIdusuario(u);
                ViagemDAO vDAO = new ViagemDAO();
                Viagem v = vDAO.buscaViagem(result.getInt("idviagem"));
                contrato.setIdviagem(v);
                ClienteDAO cDAO = new ClienteDAO();
                Cliente c = cDAO.buscaCliente(result.getInt("idcliente"));
                contrato.setIdcliente(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrato;
    }

    public ArrayList<Contrato> buscaContratoViagem(int idviagem) {
        ArrayList<Contrato> list = new ArrayList<>();
        Contrato contrato;
        String sql = "SELECT * FROM contrato WHERE idviagem = '" + idviagem + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                contrato = new Contrato();
                contrato.setDataemissao(result.getDate("dataemissao"));
                contrato.setPaga(result.getBoolean("paga"));
                contrato.setQuantidade(result.getInt("quantidade"));
                contrato.setIdcontrato(result.getInt("idcontrato"));
                contrato.setDesconto(result.getDouble("desconto"));
                FormaPGDAO fDAO = new FormaPGDAO();
                Formapagamento fp = fDAO.buscaForma(result.getInt("idforma"));
                contrato.setIdforma(fp);
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario u = uDAO.buscaUsuario(result.getInt("idusuario"));
                contrato.setIdusuario(u);
                ViagemDAO vDAO = new ViagemDAO();
                Viagem v = vDAO.buscaViagem(result.getInt("idviagem"));
                contrato.setIdviagem(v);
                ClienteDAO cDAO = new ClienteDAO();
                Cliente c = cDAO.buscaCliente(result.getInt("idcliente"));
                contrato.setIdcliente(c);
                list.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Contrato> buscaContratoViagemParcelado(int idviagem) {
        ArrayList<Contrato> list = new ArrayList<>();
        Contrato contrato;
        String sql = "SELECT * FROM contrato WHERE idviagem = '" + idviagem + "' AND idforma = 1;";
        PreparedStatement stmt;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                contrato = new Contrato();
                contrato.setDataemissao(result.getDate("dataemissao"));
                contrato.setPaga(result.getBoolean("paga"));
                contrato.setQuantidade(result.getInt("quantidade"));
                contrato.setIdcontrato(result.getInt("idcontrato"));
                contrato.setDesconto(result.getDouble("desconto"));
                FormaPGDAO fDAO = new FormaPGDAO();
                Formapagamento fp = fDAO.buscaForma(result.getInt("idforma"));
                contrato.setIdforma(fp);
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario u = uDAO.buscaUsuario(result.getInt("idusuario"));
                contrato.setIdusuario(u);
                ViagemDAO vDAO = new ViagemDAO();
                Viagem v = vDAO.buscaViagem(result.getInt("idviagem"));
                contrato.setIdviagem(v);
                ClienteDAO cDAO = new ClienteDAO();
                Cliente c = cDAO.buscaCliente(result.getInt("idcliente"));
                contrato.setIdcliente(c);
                list.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Contrato selecionaUltimo() {
        Contrato contrato = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idcontrato) from contrato;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                contrato = buscaContrato(result.getInt("max(idcontrato)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no contrato " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return contrato;
    }
}
