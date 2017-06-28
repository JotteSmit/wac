package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DynamicServlet.d")
public class DynamicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		int getal1 = Integer.parseInt(req.getParameter("getal1"));
		int getal2 = Integer.parseInt(req.getParameter("getal2"));
		int antwoord = 0;
		String keuze = req.getParameter("keuze");
		if (keuze == "optellen") {
			antwoord = getal1 + getal2;
		} else if (keuze == "aftrekken") {
			antwoord = getal1 - getal2;
		} else if (keuze == "delenk") {
			antwoord = getal1 / getal2;
		} else {
			antwoord = getal1 * getal2;
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>Hello " + name + "!</h2>");
		out.println("<h2> je antwoord is: " + antwoord + "!</h2>");
		out.println(" </body>");
		out.println("</html>");
	}
}