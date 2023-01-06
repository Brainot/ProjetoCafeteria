

package model.Dal;

    import java.sql.*;

public class ModuloConexaoJavastar {
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
        
        String url="jdbc:mysql://localhost:3306/dbjavastar";
        
        String user ="bruno";
        String password = "32332417a";
        try{
            // tratamento de conexao banco de dados
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
            } catch (Exception e) {
        return null;
        }
    }

    

}
