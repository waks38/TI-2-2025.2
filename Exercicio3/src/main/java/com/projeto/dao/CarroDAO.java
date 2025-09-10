package com.projeto.dao;

import com.projeto.model.Carro;
import com.projeto.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CarroDAO {
	
	 public void adicionarCarro(Carro carro) {
	        String sql = "INSERT INTO carros (marca, modelo, ano) VALUES (?, ?, ?)";
	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, carro.getMarca());
	            pstmt.setString(2, carro.getModelo());
	            pstmt.setInt(3, carro.getAno());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // READ (All)
	    public List<Carro> listarTodos() {
	        List<Carro> carros = new ArrayList<>();
	        String sql = "SELECT * FROM carros ORDER BY id";
	        try (Connection conn = DataBase.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                carros.add(new Carro(
	                    rs.getInt("id"),
	                    rs.getString("marca"),
	                    rs.getString("modelo"),
	                    rs.getInt("ano")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return carros;
	    }

	    // READ (By ID)
	    public Carro buscarPorId(int id) {
	        String sql = "SELECT * FROM carros WHERE id = ?";
	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return new Carro(
	                    rs.getInt("id"),
	                    rs.getString("marca"),
	                    rs.getString("modelo"),
	                    rs.getInt("ano")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    // UPDATE
	    public void atualizarCarro(Carro carro) {
	        String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ? WHERE id = ?";
	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, carro.getMarca());
	            pstmt.setString(2, carro.getModelo());
	            pstmt.setInt(3, carro.getAno());
	            pstmt.setInt(4, carro.getId());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // DELETE
	    public void deletarCarro(int id) {
	        String sql = "DELETE FROM carros WHERE id = ?";
	        try (Connection conn = DataBase.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
