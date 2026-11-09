/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;

/**
 *
 * @author lfvar
 */
public class CategoriaDAO {
    // metodo create
   public void cadastrar(Categoria categoria) {
        String sql = "INSERT INTO Categorias (nome) VALUES (?)";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria:\n" + e.getMessage());
        }
    }

    // Método para Listar (Read)
    public List<Categoria> listar() {
        String sql = "SELECT * FROM Categorias";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> listaCategorias = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                
                listaCategorias.add(categoria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar categorias:\n" + e.getMessage());
        }
        
        return listaCategorias;
    }

    // Método para Atualizar (Update)
    public void atualizar(Categoria categoria) {
        String sql = "UPDATE Categorias SET nome = ? WHERE id = ?";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria:\n" + e.getMessage());
        }
    }

    // Método para Deletar (Delete)
    public void excluir(int id) {
        String sql = "DELETE FROM Categorias WHERE id = ?";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir categoria:\n" + e.getMessage());
        }
    }
    
    
}
