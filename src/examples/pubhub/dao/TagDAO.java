package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.BookTag;

public interface TagDAO {
	public List<BookTag> getTags();
	public BookTag getTagByIsbn(String isbn);
	boolean addTag(BookTag tag);
	public boolean deleteTag(String isbn);
}
