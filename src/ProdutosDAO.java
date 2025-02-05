/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) values (?, ?, ?);");

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());          
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar o produto");
            System.err.println("ERROR! " + e);
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        listagem = new ArrayList<>();
        
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            
            while(resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch (SQLException e) {
            System.err.println("ERROR! " + e);
        }
        
        return listagem;
    }
    
    public void venderProduto(int id) {
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?;");

            prep.setInt(1, id);        
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar vender o produto");
            System.err.println("ERROR! " + e);
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        listagem = new ArrayList<>();
        
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");
            resultset = prep.executeQuery();
            
            while(resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch (SQLException e) {
            System.err.println("ERROR! " + e);
        }
        
        return listagem;
    }
        
}

