package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;


public class TagDAOImpl implements TagDAO {
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;
	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<BookTag> getTags() {
		List<BookTag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			//-----------------UPDATE-----------------------------------------------------------------------------

			String sql = "Select * from Books_tags";			
			//---------------------------------------------------------------------------------A.G.

			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				BookTag tag = new BookTag();

				// Each variable in our Book object maps to a column in a row from our results.
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag"));
				
				
				

				
				// Finally we add it to the list of Book objects returned by this query.
				tags.add(tag);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return tags;
	}
	/*-------------------------------------------------------------------------------------*/
	
		//---------------------------UPDATE--------------------------------------
		@Override
		public boolean addTag(BookTag tag)  {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO Books_Tags VALUES (?, ?)"; 
				stmt = connection.prepareStatement(sql);
				
				// But that's okay, we can set them all before we execute
				stmt.setString(1, tag.getIsbn13());
				stmt.setString(2,tag.getTag());
				
				
				// If we were able to add our book to the DB, we want to return true. 
				// This if statement both executes our query, and looks at the return 
				// value to determine how many rows were changed
				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
		}
	
/*--------------------------------------------------------------------------------------------------------*/
		@Override
		public boolean deleteTag(String isbn) {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "DELETE from Books_tags WHERE isbn_13=?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, isbn);

				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
		}

		
		/*------------------------------------------------------------------------------------------------*/
		// Closing all resources is important, to prevent memory leaks. 
		// Ideally, you really want to close them in the reverse-order you open them
		private void closeResources() {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println("Could not close statement!");
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close connection!");
				e.printStackTrace();
			}
		}
		 /*------------------------------------------------------------------------------------------------*/
@Override
public BookTag getTagByIsbn(String isbn) {
	BookTag tag = null;

	try {
		connection = DAOUtilities.getConnection();
		String sql = "SELECT * FROM Books_tags WHERE isbn_13 = ?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, isbn);
		
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			tag = new BookTag();
			tag.setIsbn13(rs.getString("isbn_13"));
			tag.setTag(rs.getString("tag"));			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		closeResources();
	}
	
	return tag;
}


		
	
		
}			
		
