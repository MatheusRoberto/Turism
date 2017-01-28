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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Usuario;

/**
 *
 * @author matheus
 */
public class UsuarioDAO {

    public UsuarioDAO() {
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adicionar(Usuario u) {
        String sql = "INSERT INTO (nome, login, senha) VALUES (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.execute();
            u.setIdusuario(this.buscaUltimo().getIdusuario());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(Usuario u){
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE usuario.idusuario = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getIdusuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void apagar(Usuario u) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM usuario WHERE idusuario = '" + u.getIdusuario() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar usuario " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public boolean loginUsuario(String login, String senha) {
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(result.getInt("idusuario"));
                usuario.setLogin(result.getString("login"));
                usuario.setNome(result.getString("nome"));
                usuario.setSenha(result.getString("senha"));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return false;
    }

    public Usuario buscaUsuario(int id) {
        Usuario usuario = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM usuario WHERE idusuario = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                usuario = new Usuario();
                usuario.setIdusuario(result.getInt("idusuario"));
                usuario.setLogin(result.getString("login"));
                usuario.setNome(result.getString("nome"));
                usuario.setSenha(result.getString("senha"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro no usuario " + ex.getMessage());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return usuario;
    }

    public Usuario buscaUltimo() {
        Usuario usuario = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idusuario) from usuario;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                usuario = buscaUsuario(result.getInt("max(idusuario)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no usuario " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return usuario;
    }

}
