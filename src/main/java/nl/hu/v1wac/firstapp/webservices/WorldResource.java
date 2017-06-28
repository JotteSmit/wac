package nl.hu.v1wac.firstapp.webservices;

import java.io.InputStream;
import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/countries")
public class WorldResource {

	@GET
	@Produces("application/json")
	public String getCountries() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("code", c.getCode());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			job.add("iso3", c.getIso3Code());
			job.add("population", c.getPopulation());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("continent", c.getContinent());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountryInfo(@PathParam("code") String code) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		Country country = service.getCountryByCode(code);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("name", country.getName());
		job.add("capital", country.getCapital());
		job.add("code", country.getCode());
		job.add("government", country.getGovernment());
		job.add("lat", country.getLatitude());
		job.add("lon", country.getLongitude());
		job.add("iso3", country.getIso3Code());
		job.add("population", country.getPopulation());
		job.add("region", country.getRegion());
		job.add("surface", country.getSurface());
		job.add("continent", country.getContinent());

		return job.build().toString();
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getLargestPopulations() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("population", c.getPopulation());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@DELETE
	@RolesAllowed("user")
	@Path("/delete/{code}")
	public Response deleteCustomer(@PathParam("code") String code) throws SQLException {
		Country country = null;
		WorldService service = ServiceProvider.getWorldService();
		System.out.println(code);
		country = service.getCountryByCode(code);
		System.out.println(country);
		if (country == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			service.delete(country);
			return Response.ok().build();
		}
	}

	@POST
	@RolesAllowed("user")
	@Path("/addCountry")
	@Produces("application/json")
	public String createCountry(InputStream is) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonObject object = Json.createReader(is).readObject();
		String code3 = object.getString("code3");
		String name = object.getString("name");
		String continent = object.getString("continent");
		String region = object.getString("region");
		double surfacearea = Double.parseDouble(object.getString("surfacearea"));
		int population = Integer.parseInt(object.getString("population"));
		String governmentform = object.getString("governmentform");
		String code2 = object.getString("code2");
		double latitude = Double.parseDouble(object.getString("latitude"));
		double longitude = Double.parseDouble(object.getString("longitude"));
		String capital = object.getString("capital");

		Country newCountry = new Country(name, capital, continent, code2, governmentform, latitude, longitude, code3,
				population, region, surfacearea);
		service.addCountry(newCountry);
		return countryToJson(newCountry).build().toString();
	}

	private JsonObjectBuilder countryToJson(Country c) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("code3", c.getIso3Code());
		job.add("name", c.getName());
		job.add("continent", c.getContinent());
		job.add("region", c.getRegion());
		job.add("surfacearea", c.getSurface());
		job.add("population", c.getPopulation());
		job.add("governmentform", c.getGovernment());
		job.add("code2", c.getCode());
		job.add("latitude", c.getLatitude());
		job.add("longitude", c.getLongitude());
		job.add("capital", c.getCapital());
		return job;
	}

	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response updateCountry(@PathParam("code") String code3, InputStream is) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonObject object = Json.createReader(is).readObject();

		String name = object.getString("name");
		String continent = object.getString("continent");
		String region = object.getString("region");
		double surfacearea = Double.parseDouble(object.getString("surfacearea"));
		int population = Integer.parseInt(object.getString("population"));
		String governmentform = object.getString("governmentform");
		String code2 = object.getString("code2");
		double latitude = Double.parseDouble(object.getString("latitude"));
		double longitude = Double.parseDouble(object.getString("longitude"));
		String capital = object.getString("capital");

		Country country = service.getCountryByCode(code2);

		country.name = name;
		country.capital = capital;
		country.continent = continent;
		country.region = region;
		country.surface = surfacearea;
		country.population = population;
		country.government = governmentform;
		country.latitude = latitude;
		country.longitude = longitude;
		country.iso2Code = code2;

		if (service.updateCountry(country) != null) {
			System.out.println("succes");
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}