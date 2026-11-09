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
import model.Produto;
/**
 *
 * @author lfvar
 */
public class ProdutoDAO {
    public void cadastra(Produto produto) {
        String sql = "INSERT INT PRODUTOS (NOME, PRECO, QUANTIDADE, CATEGORIA_ID) VALUES (?, ?, ?, ?)";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar procuto" + ex.getMessage());
        }
    }
    
    public List<Produto> listar() {
        String sql = "SELECT P.*, C.NOME AS CATEGORIA_NOME FROM PRODUTOS P" + "INNER JOIN CATEGORIAS C ON P.CATEGORIA_ID = C.ID";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProduto = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("Nome"));
                produto.setPreco(rs.getDouble("Preco"));
                produto.setQuantidade(rs.getInt("Quantidade"));
                
                //motando o obj categoria para colocar dentro do produto
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNome(rs.getString("categoria_nome"));
                
                produto.setCategoria(categoria);
                listaProduto.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos" + ex.getMessage());
        }
        return listaProduto;
    }
    
    public void atualizar(Produto produto) {
        String sql = "UPDATE PRODUTOS SET NOME = ?, PRECO = ?, QUANTIDADE = ?, CATEGORIA_ID = ? WHERE ID = ?";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualidado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto" + ex);
        }
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM PRODUTOS WHERE ID = ?";
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto" + ex);
        }
    }
    
    public List<Produto> buscaProdutoNomeCat(String nome) {
        String sql = "SELECT P.*, C.* AS CATEGORIA_NOME FROM PRODUTOS P " +
                    "INNER JOIN CATEGORIA C ON P.CATEGORIA_ID = C.ID " + 
                    "WHERE P.NOME LIKE ? OR C.NOME LIKE ?";
        
        Connection con = (Connection) Conexao.ConexaoBD.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProduto = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
            }
        }
    }
}
