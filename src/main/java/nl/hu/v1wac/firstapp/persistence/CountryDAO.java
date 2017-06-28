package nl.hu.v1wac.firstapp.persistence;

import nl.hu.v1wac.firstapp.webservices.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends BaseDAO {
	public Country save(Country country) throws SQLException {
		System.out.print(country);
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement(
				"INSERT INTO country (code, name, continent, region, surfacearea, population, localname, governmentform, code2, latitude, longitude, capital) VALUES (?,?,CAST(? AS continenttype),?,?,?,?,?,?,?,?,?)");

		stmt.setString(1, country.getIso3Code());
		stmt.setString(2, country.getName());
		stmt.setString(3, country.getContinent());
		stmt.setString(4, country.getRegion());
		stmt.setDouble(5, country.getSurface());
		stmt.setInt(6, country.getPopulation());
		stmt.setString(7, country.getName());
		stmt.setString(8, country.getGovernment());
		stmt.setString(9, country.getCode());
		stmt.setDouble(10, country.getLatitude());
		stmt.setDouble(11, country.getLongitude());
		stmt.setString(12, country.getCapital());

		stmt.executeUpdate();
		con.close();
		return country;

	}

	public List<Country> findAll() throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM country");
		ResultSet res = stmt.executeQuery();
		List<Country> countryList = new ArrayList<Country>();
		while (res.next()) {

			String name = res.getString("name");
			String capital = res.getString("capital");
			String continent = res.getString("continent");
			String code2 = res.getString("code2");
			String govern = res.getString("governmentform");
			double lat = res.getDouble("latitude");
			double lon = res.getDouble("longitude");
			String code3 = res.getString("code");
			int population = res.getInt("population");
			String region = res.getString("region");
			double surface = res.getDouble("surfacearea");

			Country c = new Country(name, capital, continent, code2, govern, lat, lon, code3, population, region,
					surface);
			countryList.add(c);
		}
		res.close();
		con.close();
		return countryList;
	}

	public Country findByCode(String code) throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM country WHERE code2='" + code + "'");
		ResultSet res = stmt.executeQuery();
		List<Country> countryList = new ArrayList<Country>();
		while (res.next()) {

			String name = res.getString("name");
			String capital = res.getString("capital");
			String continent = res.getString("continent");
			String code2 = res.getString("code2");
			String govern = res.getString("governmentform");
			double lat = res.getDouble("latitude");
			double lon = res.getDouble("longitude");
			String code3 = res.getString("code");
			int population = res.getInt("population");
			String region = res.getString("region");
			double surface = res.getDouble("surfacearea");

			Country c = new Country(name, capital, continent, code2, govern, lat, lon, code3.toUpperCase(), population, region,
					surface);
			countryList.add(c);

		}
		res.close();
		con.close();
		return countryList.get(0);

	}

	public Country update(Country country) throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("UPDATE country SET code= ? , name= ? , "
				+ "continent= (cast(? as continenttype)), region= ? , surfacearea= ? , population= ? , localname= ? , "
				+ "governmentform= ? , code2= ? , latitude= ? , longitude= ? , capital= ? WHERE code= ? ");
		
		stmt.setString(1, country.getIso3Code());
		stmt.setString(2, country.getName());
		stmt.setString(3, country.getContinent());
		stmt.setString(4, country.getRegion());
		stmt.setDouble(5, country.getSurface());
		stmt.setInt(6, country.getPopulation());
		stmt.setString(7, country.getName());
		stmt.setString(8, country.getGovernment());
		stmt.setString(9, country.getCode());
		stmt.setDouble(10, country.getLatitude());
		stmt.setDouble(11, country.getLongitude());
		stmt.setString(12, country.getCapital());
		stmt.setString(13, country.getIso3Code());
		
		stmt.executeUpdate();
		con.close();
		return country;
	}

	public boolean delete(Country country) throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("DELETE FROM country WHERE name='" + country.getName() + "'");
		if (stmt.execute()) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
		
	}

	public List<Country> find10LargestPopulations() throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM country order by population desc limit 10");
		ResultSet res = stmt.executeQuery();
		List<Country> countryList = new ArrayList<Country>();
		while (res.next()) {

			String name = res.getString("name");
			String capital = res.getString("capital");
			String continent = res.getString("continent");
			String code2 = res.getString("code2");
			String govern = res.getString("governmentform");
			double lat = res.getDouble("latitude");
			double lon = res.getDouble("longitude");
			String code3 = res.getString("code");
			int population = res.getInt("population");
			String region = res.getString("region");
			double surface = res.getDouble("surfacearea");

			Country c = new Country(name, capital, continent, code2, govern, lat, lon, code3, population, region,
					surface);
			countryList.add(c);

		}
		res.close();
		con.close();
		return countryList;
	}

	public List<Country> find10LargestSurfaces() throws SQLException {
		Connection con = super.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM country order by surfacearea desc limit 10");
		ResultSet res = stmt.executeQuery();
		List<Country> countryList = new ArrayList<Country>();
		while (res.next()) {

			String name = res.getString("name");
			String capital = res.getString("capital");
			String continent = res.getString("continent");
			String code2 = res.getString("code2");
			String govern = res.getString("governmentform");
			double lat = res.getDouble("latitude");
			double lon = res.getDouble("longitude");
			String code3 = res.getString("code");
			int population = res.getInt("population");
			String region = res.getString("region");
			double surface = res.getDouble("surfacearea");

			Country c = new Country(name, capital, continent, code2, govern, lat, lon, code3, population, region,
					surface);
			countryList.add(c);

		}
		res.close();
		con.close();
		return countryList;
	}
}
