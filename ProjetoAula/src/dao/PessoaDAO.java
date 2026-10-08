/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Pessoa;
import java.sql.Connection;
import conexao.Conexao;
import java.util.List;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PessoaDAO {
    private Conexao conexao;
    private Connection conn;

    public PessoaDAO() {
        this.conexao = new Conexao();
        this.conn = conexao.getConexao();
    }
    
    public void inserir (Pessoa pessoa){
        try {
            String sql = "INSERT INTO pessoa(nome, sexo, idioma) VALUES (?,?,?);";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,pessoa.getNome());
            stmt.setString(2,pessoa.getSexo());
            stmt.setString(3,pessoa.getIdioma());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir pessoa:"+ex.getMessage());
        }
    } 
    
    public Pessoa getPessoa(int id) {
        String sql = "SELECT * FROM PESSOA WHERE ID = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pessoa p = new Pessoa();
            
            rs.first();
            p.setId(id);
            p.setNome(rs.getString("nome"));
            p.setSexo(rs.getString("sexo"));
            p.setIdioma(rs.getString("idioma"));
            return p;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar pessoas: " + ex.getMessage());
            return null;
        }
    }
    
    public void editar(Pessoa pessoa) {
        try {
            String sql = "UPDATE PESSOA SET NOME=?, SEXO=?, IDIOMA=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pessoa,getNome());
        } catch (Exception e) {
        }
    }
    
    public List<Pessoa> getPessoas(){
        String sql = "SELECT * FROM PESSOA";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList();
            
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        } catch (SQLException ex){
            System.out.println("Erro ao consultar todas as pessoas" + ex.getMessage());
            return null;
        }
    }
    
    public List<Pessoa> getPessoaNome(String nome) {
        String sql = "SELECT * FROM PESSOA WHERE NOME LIKE ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%"+ nome +"%");
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList();
            
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todas  as pessoas: "+ ex.getMessage());
            return null;
        }
    }
    
    public List<Pessoa> getPessoaNome(String nome, String sexo) {
        String sql = "SELECT * FROM PESSOA WHERE NOME LIKE ? AND SEXO LIKE ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%"+ nome +"%");
            stmt.setString(2, "%"+ sexo +"%");
            ResultSet rs = stmt.executeQuery();
            List<Pessoa> listaPessoas = new ArrayList();
            
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSexo(rs.getString("sexo"));
                p.setIdioma(rs.getString("idioma"));
                listaPessoas.add(p);
            }
            return listaPessoas;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todas  as pessoas: "+ ex.getMessage());
            return null;
        }
    }
}
    
