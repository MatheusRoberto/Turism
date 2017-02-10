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
import turism.modelo.Assento;
import turism.modelo.AssentoPK;

/**
 *
 * @author matheus
 */
public class AssentoDAO {

    public AssentoDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Assento a) {
        PreparedStatement stmt;
        String sql = "INSERT INTO assento (idassento, idcontrato, idveiculo, nome, rg) VALUES (?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, a.getAssentoPK().getIdassento());
            stmt.setInt(2, a.getAssentoPK().getIdcontrato());
            stmt.setInt(3, a.getAssentoPK().getIdveiculo());
            stmt.setString(4, a.getNome());
            stmt.setString(5, a.getRg());
            System.out.println(stmt.execute());
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void atualizarNomeRGContrato(Assento a, int idcontrato){
        PreparedStatement stmt;
        String sql = "UPDATE assento SET nome = ?, rg = ?, idcontrato = ? WHERE assento.idassento = ? "
                + "AND assento.idcontrato = ? AND assento.idveiculo = ?;";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getRg());
            stmt.setInt(3, idcontrato);
            stmt.setInt(4, a.getAssentoPK().getIdassento());
            stmt.setInt(5, a.getAssentoPK().getIdcontrato());
            stmt.setInt(6, a.getAssentoPK().getIdveiculo());
            System.err.println(stmt);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void apagar(Assento a){
        PreparedStatement stmt;
        String sql = "DELETE FROM assento WHERE assento.idassento = "+a.getAssentoPK().getIdassento()
                +" AND assento.idcontrato = "+a.getAssentoPK().getIdcontrato()
                +" AND assento.idveiculo = "+a.getAssentoPK().getIdveiculo()+" ;";
        try {
            stmt = connection.prepareStatement(sql);
            System.err.println(stmt.execute());
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<AssentoPK> buscaAssento(int idviagem) {
        ArrayList<AssentoPK> list = new ArrayList<>();
        //Assento assento = null;
        String sql = "SELECT assento.* FROM assento, contrato WHERE contrato.idviagem = '" + idviagem + "' ;";//AND assento.idveiculo = '"+idveiculo+"' ;";
        PreparedStatement stmt;
        ResultSet result;
        AssentoPK assentoPK;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                assentoPK = new AssentoPK();
                assentoPK.setIdassento(result.getInt("idassento"));
                assentoPK.setIdcontrato(result.getInt("idcontrato"));
                assentoPK.setIdveiculo(result.getInt("idveiculo"));
                //assento.setAssentoPK(assentoPK);
                //assento.setNome(result.getString("nome"));
                //assento.setRg(result.getString("rg"));
                list.add(assentoPK);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<AssentoPK> buscaAssentoContrato(int idcontrato) {
        ArrayList<AssentoPK> list = new ArrayList<>();
        //Assento assento = null;
        String sql = "SELECT * FROM assento WHERE assento.idcontrato = '" + idcontrato + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        AssentoPK assentoPK;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                assentoPK = new AssentoPK();
                assentoPK.setIdassento(result.getInt("idassento"));
                assentoPK.setIdcontrato(result.getInt("idcontrato"));
                assentoPK.setIdveiculo(result.getInt("idveiculo"));
                //assento.setAssentoPK(assentoPK);
                //assento.setNome(result.getString("nome"));
                //assento.setRg(result.getString("rg"));
                list.add(assentoPK);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Assento> buscaAssentoVeiculo(int idveiculo) {
        ArrayList<Assento> list = new ArrayList<>();
        Assento assento = null;
        String sql = "SELECT * FROM assento WHERE assento.idveiculo = '" + idveiculo + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        AssentoPK assentoPK;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                //assentoPK = new AssentoPK(result.getInt("idassento"), result.getInt("idcontrato"), result.getInt("idveiculo"));
                assentoPK = new AssentoPK();
                assentoPK.setIdassento(result.getInt("idassento"));
                assentoPK.setIdcontrato(result.getInt("idcontrato"));
                assentoPK.setIdveiculo(result.getInt("idveiculo"));
                if (assentoPK.getIdassento() > 0 && assentoPK.getIdcontrato() > 0 && assentoPK.getIdveiculo() > 0) {
                    assento = new Assento(assentoPK);
                    assento.setNome(result.getString("nome"));
                    assento.setRg(result.getString("rg"));
                }
                list.add(assento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ArrayList<Assento> buscaArrayAssentoContrato(int idcontrato) {
        ArrayList<Assento> list = new ArrayList<>();
        Assento assento = null;
        String sql = "SELECT * FROM assento WHERE assento.idcontrato = '" + idcontrato + "' ;";
        PreparedStatement stmt;
        ResultSet result;
        AssentoPK assentoPK;
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                //assentoPK = new AssentoPK(result.getInt("idassento"), result.getInt("idcontrato"), result.getInt("idveiculo"));
                assentoPK = new AssentoPK();
                assentoPK.setIdassento(result.getInt("idassento"));
                assentoPK.setIdcontrato(result.getInt("idcontrato"));
                assentoPK.setIdveiculo(result.getInt("idveiculo"));
                if (assentoPK.getIdassento() > 0 && assentoPK.getIdcontrato() > 0 && assentoPK.getIdveiculo() > 0) {
                    assento = new Assento(assentoPK);
                    assento.setNome(result.getString("nome"));
                    assento.setRg(result.getString("rg"));
                }
                list.add(assento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
}
