package nl.hu.v1wac.firstapp.webservices;

public class Country {
	public String iso2Code;
	public String iso3Code;
	public String name;
	public String capital;
	public String continent;
	public String region;
	public double surface;
	public int population;
	public String government;
	public double latitude;
	public double longitude;

	public Country(String nm, String cap, String ct, String iso2cd, String gov, double lat, double lng, String iso3cd, int pop, String reg, double sur) {
		iso2Code = iso2cd;
		iso3Code = iso3cd;
		name = nm;
		capital = cap;
		continent = ct;
		region = reg;
		surface = sur;
		population = pop;
		government = gov;
		latitude = lat;
		longitude = lng;
	}

	public String getCode() {
		return iso2Code;
	}

	public String getIso3Code() {
		return iso3Code;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public String getContinent() {
		return continent;
	}

	public String getRegion() {
		return region;
	}

	public double getSurface() {
		return surface;
	}

	public int getPopulation() {
		return population;
	}

	public String getGovernment() {
		return government;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
