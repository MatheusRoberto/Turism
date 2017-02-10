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
import turism.modelo.Contrato;
import turism.modelo.Ocupantes;
import turism.modelo.OcupantesPK;
import turism.modelo.Quarto;

/**
 *
 * @author matheus
 */
public class OcupanteDAO {

    public OcupanteDAO() {
        this.connection = Conexao.getConnection();
    }
    
    
    private final Connection connection;
    
    public void adicionar(Ocupantes o){
        PreparedStatement stmt;
        String sql = "INSERT INTO  ocupantes (contrato_idcontrato, quarto_idquarto, nome) VALUES (?,?,?)";
        
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, o.getContrato().getIdcontrato());
            stmt.setInt(2, o.getQuarto().getIdquarto());
            stmt.setString(3, o.getNome());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OcupanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void apagar(Ocupantes o){
        PreparedStatement stmt;
        String sql = "DELETE FROM ocupantes WHERE idocupantes = "+o.getOcupantesPK().getIdocupantes()+
                " AND contrato_idcontrato = "+o.getOcupantesPK().getContratoIdcontrato()+" AND quarto_idquarto = "+o.getOcupantesPK().getQuartoIdquarto()+";";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OcupanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Ocupantes> buscaOcupantes(int idquarto){
        ArrayList<Ocupantes> list = null;
        Ocupantes o;
        PreparedStatement stmt;
        ResultSet rs;
        String sql = "SELECT * FROM ocupantes WHERE quarto_idquarto = "+idquarto+" ;";
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                OcupantesPK ocupantesPK = new OcupantesPK();
                ocupantesPK.setIdocupantes(rs.getInt("idocupantes"));
                ocupantesPK.setContratoIdcontrato(rs.getInt("contrato_idcontrato"));
                ocupantesPK.setQuartoIdquarto(rs.getInt("quarto_idquarto"));
                o = new  Ocupantes(ocupantesPK);
                o.setNome(rs.getString("nome"));
                QuartoDAO qDAO = new QuartoDAO();
                Quarto q = qDAO.buscaQuarto(rs.getInt("quarto_idquarto"));
                o.setQuarto(q);
                ContratoDAO cDAO = new ContratoDAO();
                Contrato c = cDAO.buscaContrato(rs.getInt("contrato_idcontrato"));
                o.setContrato(c);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OcupanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
