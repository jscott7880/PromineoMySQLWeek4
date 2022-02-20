package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cars;

public class CarsDao {
	
	private Connection connection;
	private final String GET_ALL_CARS_QUERY = "SELECT * FROM cars";
	private final String CREATE_NEW_CAR_QUERY = "INSERT INTO cars (name, price) VALUES (?,?)";
	private final String UPDATE_PRICE_QUERY = "UPDATE cars SET name = ?, price = ? WHERE id = ?";
	private final String DELETE_CAR_QUERY = "DELETE FROM cars WHERE id = ?";
	
	public CarsDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Cars> getAllCars() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ALL_CARS_QUERY).executeQuery();
		List<Cars> allCars = new ArrayList<>();
		while (rs.next()) {
			allCars.add(new Cars( rs.getInt(1), rs.getString(2), rs.getDouble(3)));
		}
		return allCars;
	}
		
	public void createNewCar(int id, String name, Double price) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CAR_QUERY);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setDouble(3, price);
		ps.executeUpdate();
		
	}
	
	public void updatePrice(String name, Double price) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_PRICE_QUERY);
		ps.setString(2, name);
		ps.setDouble(3, price);
	}
	
	public void deleteCar(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CAR_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
