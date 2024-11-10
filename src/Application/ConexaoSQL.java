
package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexaoSQL {

    public Connection conectaBD(){
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/jogodamemoria?user=root&password=321206";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão bem-sucedida!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoSQL" + erro);
        }
        return conn;
    }
}
