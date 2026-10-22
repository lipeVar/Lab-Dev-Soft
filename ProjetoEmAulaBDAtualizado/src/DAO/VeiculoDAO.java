/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Beans.Pessoa;
import Beans.Veiculo;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author laboratorio
 */
public class VeiculoDAO {
    private Conexao conexao;
    private Connection conn;

    public VeiculoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Veiculo veiculo) {
        String sql = "INSERT INTO VEICULO (MODELO, PLACA, ID_PESSOA) VALUES (?,?,?);";
        
         try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, veiculo.getModelo());
        stmt.setString(2, veiculo.getPlaca());
        stmt.setInt(3, veiculo.getPessoaid().getId());

        stmt.execute();
    } catch (java.sql.SQLException ex) {
        System.out.println("Erro ao inserir Veiculo: " + ex.getMessage());
    }
    }
    
    public void editar (Veiculo veiculo) {
        try {
            String sql = "UPDATE veiculo set modelo=?, placa=?, id_pessoa=? WHERE ID=?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getPessoaid().getId());
            stmt.setInt(4, veiculo.getId());
            stmt.execute();
        } catch (java.sql.SQLException ex) {
            System.out.println("ERRO ao atualizar veiculo: " + ex.getMessage());
        }
    }
    
    public void excluir (int id) {
        try {
            String sql = "delete from veiculo where id=?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (java.sql.SQLException ex){
            System.out.println("ERRO ao excluir veiculo: "+ ex.getMessage());
        }
    }
    
    public List<Veiculo> getVeiculos() {
        String sql = "select * from veiculo";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Veiculo> listaVeiculo = new ArrayList();
            
            while(rs.next()){
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                PessoaDAO pDAO = new PessoaDAO();
                
                Pessoa p = pDAO.getPessoa(rs.getInt("id_pessoa"));
                v.setPessoaid(p);
                listaVeiculo.add(v);
             
            }
            return listaVeiculo;
        } catch (SQLException ex) {
            System.out.println("ERRO ao consultar todas as pessoas" + ex.getMessage());
            return null;
        }
    }
    
    public Veiculo getVeiculo(int id) {
        String sql = "select * from veiculo where id=?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Veiculo v = new Veiculo();
            
            rs.first();
            
            v.setId(rs.getInt("id"));
            v.setModelo(rs.getString("modelo"));
            v.setPlaca(rs.getString("placa"));
            PessoaDAO pDAO = new PessoaDAO();
            
            Pessoa p = pDAO.getPessoa(rs.getInt("id_pessoa"));
            v.setPessoaid(p);
            return v;
        }catch (SQLException ex) {
            System.out.println("ERRO ao consultar pessoas:" + ex.getMessage());
            return null;
        }
    }
}
