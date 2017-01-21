package turism.conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	 private static final String USUARIO = "root";
	 private static final String SENHA = "madsr1411";
	 private static final String URL = "jdbc:mysql://127.0.0.1:3306/turism";
	 private static final String DRIVER = "com.mysql.jdbc.Driver";
	 private static Connection conexao = null;

	    // Conectar ao banco
	 public static Connection getConnection() {
	        System.out.println(">>Conectando ao banco");
	        try {
	            Class.forName(DRIVER);
	            if(conexao == null || conexao.isClosed()){
	                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
	            }
	            return conexao; 
	        }catch (ClassNotFoundException e) {    
	            throw new RuntimeException(e);    
	        }catch (SQLException e) {

	            closeConnection();
	            throw new RuntimeException(e);
	        }

	    }

	    public static void closeConnection(){
	        try{
	            if(conexao!=null && !conexao.isClosed()){
	                conexao.close();
	                System.out.println(">>Conexao encerrada com sucesso");
	            }
	        }catch (SQLException e) {
	        }
	    }


}