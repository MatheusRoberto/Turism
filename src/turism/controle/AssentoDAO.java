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
import turism.modelo.Viagem;

/**
 *
 * @author matheus
 */
public class AssentoDAO {

    public AssentoDAO() {
        this.connection = Conexao.getConnection();
    }
    
    private final Connection connection;
    
    public void adicionar(Assento a){
        PreparedStatement stmt;
        String sql = "INSERT INTO assento (idassento, idcontrato, idveiculo, nome, rg) VALUES (?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, a.getAssentoPK().getIdassento());
            stmt.setInt(2, a.getAssentoPK().getIdcontrato());
            stmt.setInt(3, a.getAssentoPK().getIdveiculo());
            stmt.setString(4, a.getNome());
            stmt.setString(5, a.getRg());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AssentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public ArrayList<AssentoPK> buscaAssento(int idviagem){
        ArrayList<AssentoPK> list = new ArrayList<>();
        //Assento assento = null;
        String sql = "SELECT assento.* FROM assento, contrato WHERE contrato.idviagem = '"+idviagem+"' ;";//AND assento.idveiculo = '"+idveiculo+"' ;";
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
}
