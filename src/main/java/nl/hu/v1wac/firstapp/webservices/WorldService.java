package nl.hu.v1wac.firstapp.webservices;

import nl.hu.v1wac.firstapp.persistence.CountryDAO;

import java.sql.SQLException;
import java.util.List;

public class WorldService {
	private CountryDAO CountryDAO = new CountryDAO();

	public List<Country> getAllCountries() throws SQLException {
		return CountryDAO.findAll();
	}

	public List<Country> get10LargestPopulations() throws SQLException {
		return CountryDAO.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() throws SQLException {
		return CountryDAO.find10LargestSurfaces();
	}

	public Country getCountryByCode(String code) throws SQLException {
		return CountryDAO.findByCode(code);
	}

	public boolean delete(Country country) throws SQLException {
		return CountryDAO.delete(country);
	}

	public void addCountry(Country country) throws SQLException {
		CountryDAO.save(country);
	}

	public Country updateCountry(Country country) throws SQLException {
		return CountryDAO.update(country);
	}
}