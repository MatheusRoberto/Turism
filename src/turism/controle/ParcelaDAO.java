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
import turism.modelo.Contrato;
import turism.modelo.Parcela;
import turism.modelo.ParcelaPK;

/**
 *
 * @author matheus
 */
public class ParcelaDAO {

    public ParcelaDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiconar(Parcela p) {
        PreparedStatement stmt;
        String sql = "INSERT INTO parcela (valor, datavencimento, paga,  idcontrato) VALUES (?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, p.getValor());
            stmt.setDate(2, new java.sql.Date(p.getDatavencimento().getTime()));
            stmt.setBoolean(3, p.getPaga());
            stmt.setInt(4, p.getContrato().getIdcontrato());
            stmt.execute();
            ParcelaPK parcelaPK = new ParcelaPK();
            int idparcela = this.selecionaUltimo().getParcelaPK().getIdparcela();
            Integer idcontrato = p.getContrato().getIdcontrato();
            parcelaPK.setIdcontrato(idcontrato);
            parcelaPK.setIdparcela(idparcela);
            p.setParcelaPK(parcelaPK);
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(Parcela p) {
        PreparedStatement stmt;
        String sql = "UPDATE parcela SET valor = ?, datavencimento = ?, datapagamento = ?, paga = ? "
                + "WHERE parcela.idparcela = ? AND parcela.idcontrato = ? ;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, p.getValor());
            stmt.setDate(2, new java.sql.Date(p.getDatavencimento().getTime()));
            stmt.setDate(3, new java.sql.Date(p.getDatapagamento().getTime()));
            stmt.setBoolean(4, p.getPaga());
            stmt.setInt(5, p.getParcelaPK().getIdparcela());
            stmt.setInt(6, p.getParcelaPK().getIdcontrato());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Parcela buscaParcela(int idparcela) {
        Parcela parcela = null;
        String sql = "SELECT * FROM parcela WHERE idparcela = '" + idparcela + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                parcela = new Parcela();
                parcela.setDatavencimento(result.getDate("datavencimento"));
                parcela.setPaga(result.getBoolean("paga"));
                parcela.setValor(result.getDouble("valor"));
                parcela.setDatapagamento(result.getDate("datapagamento"));
                ContratoDAO cDAO = new ContratoDAO();
                Contrato contrato = cDAO.buscaContrato(result.getInt("idcontrato"));
                parcela.setContrato(contrato);
                ParcelaPK pK = new ParcelaPK();
                pK.setIdcontrato(contrato.getIdcontrato());
                pK.setIdparcela(result.getInt("idparcela"));
                parcela.setParcelaPK(pK);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parcela;
    }

    public ArrayList<Parcela> buscaParcelaViagem(int idviagem) {
        ArrayList<Parcela> list = new ArrayList<>();
        Parcela parcela;
        PreparedStatement stmt;
        String sql = "SELECT * FROM parcela "
                + "INNER JOIN contrato ON contrato.idcontrato = parcela.idcontrato "
                + "INNER JOIN viagem ON viagem.idviagem = contrato.idviagem "
                + "WHERE viagem.idviagem = '" + idviagem + "';";
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                parcela = new Parcela();
                parcela.setDatapagamento(result.getDate("datapagamento"));
                parcela.setDatavencimento(result.getDate("datavencimento"));
                parcela.setPaga(result.getBoolean("paga"));
                parcela.setValor(result.getDouble("valor"));
                ParcelaPK pK = new ParcelaPK();
                pK.setIdcontrato(result.getInt("idcontrato"));
                pK.setIdparcela(result.getInt("idparcela"));
                parcela.setParcelaPK(pK);
                list.add(parcela);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<Parcela> buscaParcelaViagemContrato(int idviagem, int idcontrato) {
        ArrayList<Parcela> list = new ArrayList<>();
        Parcela parcela;
        PreparedStatement stmt;
        String sql = "SELECT parcela.* FROM parcela "
                + "INNER JOIN contrato ON contrato.idcontrato = parcela.idcontrato "
                + "INNER JOIN viagem ON viagem.idviagem = contrato.idviagem "
                + "WHERE viagem.idviagem = '"+idviagem+"' AND contrato.idcontrato = '"+idcontrato+"' ;";
        ResultSet result;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                parcela = new Parcela();
                parcela.setDatapagamento(result.getDate("datapagamento"));
                parcela.setDatavencimento(result.getDate("datavencimento"));
                parcela.setPaga(result.getBoolean("paga"));
                parcela.setValor(result.getDouble("valor"));
                ParcelaPK pK = new ParcelaPK();
                pK.setIdcontrato(result.getInt("idcontrato"));
                pK.setIdparcela(result.getInt("idparcela"));
                parcela.setParcelaPK(pK);
                list.add(parcela);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Parcela selecionaUltimo() {
        Parcela parcela = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idparcela) from parcela;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                parcela = buscaParcela(result.getInt("max(idparcela)"));
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
        return parcela;
    }
}
