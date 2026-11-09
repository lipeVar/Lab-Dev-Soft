/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing. JOptionPane;
/**
 *
 * @author lfvar
 */
public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/aula";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    
    public static Connection conectar() {
        try {
            Connection conexao = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados:\n" + ex.getMessage());
            return null;
        }
    }
}
