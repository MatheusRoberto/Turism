package turism.controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import turism.conexao.Conexao;
import turism.modelo.Cliente;

public class ClienteDAO {

    public ClienteDAO() {
        // TODO Stub de construtor gerado automaticamente
        this.connection = Conexao.getConnection();
    }

    private final Connection connection;

    public void adiciona(Cliente c) {
        String sql = "INSERT INTO cliente(nome,rg, telefone) VALUES(?,?,?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getRg());
                stmt.setString(3, c.getTelefone());
                stmt.execute();
                c.setIdcliente(this.selecionaUltimo().getIdcliente());
            }
            System.out.println("Cliente cadastrado sucesso");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void atualizar(Cliente c) {
        String sql = "UPDATE cliente SET nome = ?, rg = ?, telefone = ? WHERE cliente.idcliente = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getRg());
            stmt.setString(3, c.getTelefone());
            stmt.setInt(4, c.getIdcliente());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagar(Cliente c) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM cliente WHERE idcliente = '" + c.getIdcliente() + "';";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar cliente " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Bloco catch gerado automaticamente
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ArrayList<Cliente> buscaClientes(String nome) {
        ArrayList<Cliente> list = new ArrayList<>();
        Cliente cliente;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cliente WHERE nome LIKE '" + nome + "%';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cliente = new Cliente();
                cliente.setIdcliente(result.getInt("idcliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setRg(result.getString("rg"));
                cliente.setTelefone(result.getString("telefone"));
                list.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no cliente " + e.getMessage());
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }

        return list;
    }

    public Cliente buscaCliente(int id) {
        Cliente cliente = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cliente WHERE idcliente = '" + id + "';";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cliente = new Cliente();
                cliente.setIdcliente(result.getInt("idcliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setRg(result.getString("rg"));
                cliente.setTelefone(result.getString("telefone"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no cliente " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return cliente;
    }

    private Cliente selecionaUltimo() {
        Cliente cliente = null;
        ResultSet result;
        PreparedStatement stmt = null;
        String sql = "select max(idcliente) from cliente;";
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                cliente = buscaCliente(result.getInt("max(idcliente)"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no cliente " + e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro ao fechar " + e.getMessage());
            }
        }
        return cliente;
    }

}
