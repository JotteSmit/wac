package nl.hu.v1wac.firstapp.webservices;

import java.security.*;
import java.util.Calendar;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.firstapp.persistence.UserDAO;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			// Authenticate the user against the database
			UserDAO dao = new UserDAO();
			String role = dao.findRoleForUsernameAndPassword(username, password);

			if (role == null) {
				throw new IllegalArgumentException("No user found!");
			}

			// Issue a token for the user
			Calendar expiration = Calendar.getInstance();
			expiration.add(Calendar.MINUTE, 30);

			String token = Jwts.builder().setSubject(username).claim("role", role).setExpiration(expiration.getTime())
					.signWith(SignatureAlgorithm.HS512, key).compact();
			// Return the token on the response
			return Response.ok(token).build();
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}