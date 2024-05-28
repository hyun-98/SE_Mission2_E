package Book;

//Book의 정보들을 멤버변수로 갖는 class.
public class Book {
	
	public int id;
	public String title;
	public String author;
	public int publicDate;
	
	public Book(int in_id, String in_title, String in_author, int in_publicDate) {
		id = in_id;
		title = in_title;
		author = in_author;
		publicDate = in_publicDate;
	}
}
