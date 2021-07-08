package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class DeleteTagServlet
 */
@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("deleteTag.jsp").forward(request, response);
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn13 = request.getParameter("isbn13");
		TagDAO database = DAOUtilities.getTagDAO();
		BookTag tempbooktag = database.getTagByIsbn(isbn13);
		
		if (tempbooktag != null) {
			
			boolean isSuccess = database.deleteTag(isbn13);
			
			if(isSuccess){
				request.getSession().setAttribute("message", "Tag successfully deleted");
				request.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				response.sendRedirect(request.getContextPath() + "/DeleteTag");
			}else {
				request.getSession().setAttribute("message", "There was a problem deleting the tag");
				request.getSession().setAttribute("messageClass", "alert-danger");
				request.getRequestDispatcher("deleteTag.jsp").forward(request, response);
				
			}
		
		} else {
			request.getSession().setAttribute("message", "ISBN of " + isbn13 + " does not have a tag in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("deleteTag.jsp").forward(request, response);
		
		}

	}
}