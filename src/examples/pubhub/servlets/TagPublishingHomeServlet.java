package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class TagPublishingHomeServlet
 */
@WebServlet("/TagPublishingHomeServlet")
public class TagPublishingHomeServlet extends HttpServlet {

		
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			// Grab the list of Books from the Database
			TagDAO dao = DAOUtilities.getTagDAO();
			List<BookTag> tagList = dao.getTags();

			// Populate the list into a variable that will be stored in the session
			request.getSession().setAttribute("tags", tagList);
			
			request.getRequestDispatcher("tabPublishingHome.jsp").forward(request, response);
		}
	


}
