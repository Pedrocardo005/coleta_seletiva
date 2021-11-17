package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    /* Atributos ---------------------------------- */
    private static Connection conexao = null;
    private static final String DATABASE_NAME = "NOME_DO_SCHEMA";
    private static final String URL_CONNECTION = "jdbc:mysql://localhost/"+NOME_DO_SCHEMA+"?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    /* Construtores ---------------------------------- */
    private FabricaConexao(){}

    /* Métodos ---------------------------------- */
    public static Connection getInstance() {
        // verifica se a conexão ainda não foi criada
        if(conexao == null){
            // tenta criar uma nova conexão com o Mysql
            try{
                Class.forName("com.mysql.cj.jdbc.Driver"); // nome do driver Mysql do Java
                // tenta estabelecer a conexão
                conexao = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return conexao;
    }
}