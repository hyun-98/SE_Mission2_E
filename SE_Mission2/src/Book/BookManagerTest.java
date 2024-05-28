package Book;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//BookManager를 test하기 위한 class.
class BookManagerTest {
	//각 test전 bookManager에 입력되는 book의 개수.
	final int NUMB_OF_TEST_CASES = 10000;
	
	//Test하기 위한 book manager instance.
	BookManager bookManager;
	
	//Test 전 bookManager에 추가되는 book 중 하나.
	Book bookInBookManager;
	
	//Test 전 bookManager에 추가되지 않은 book 하나.
	Book bookNotInBookManager;
	
	@BeforeEach
	void setUp() throws Exception {
		bookManager = new BookManager();
		
		//bookInBookManager 및 bookNotInBookManager의 ID를 난수로 지정.
		long ID_bookInBookManager = (System.currentTimeMillis() % NUMB_OF_TEST_CASES);
		long ID_bookNotInBookManager = (System.currentTimeMillis() % NUMB_OF_TEST_CASES);
		
		//bookInBookManager != bookNotInBookManager를 위해 loop문을 활용.
		while(ID_bookInBookManager == ID_bookNotInBookManager) {
			ID_bookNotInBookManager = (System.currentTimeMillis() % NUMB_OF_TEST_CASES);
		}
		
		System.out.printf("ID of a Book In BookManager = %d\n", ID_bookInBookManager);
		System.out.printf("ID of a Book Not In BookManager = %d\n", ID_bookNotInBookManager);

		//Test 전 setup 과정. NUMB_OF_TEST_CASES의 개수 만큼 bookManager에 book추가.
		for(int bookID = 0; bookID < NUMB_OF_TEST_CASES; bookID++) {
			//Book의 publicDate는 bookID에 2024를 더한 값.
			int bookPublicDate = bookID + 2024;
			String bookTitle = Integer.toString(bookID) + "번째 책";
			String bookAuthor = Integer.toString(bookID) + "번째 책 저자";
			
			
			Book bookInCurrentLoop = new Book(bookID, bookTitle, bookAuthor, bookPublicDate);
			if(bookID != ID_bookNotInBookManager) {
				bookManager.AddBook(bookInCurrentLoop.id, bookInCurrentLoop.title, bookInCurrentLoop.author, bookInCurrentLoop.publicDate);
				if(bookID == ID_bookInBookManager) {
					bookInBookManager = bookInCurrentLoop;
				}
			} else {
				bookNotInBookManager = bookInCurrentLoop;
			}
		}
		
		
	}

	@Test
	void testAddBooks() {
		try {
			//bookNotInBookManager를 bookManager에 추가. -> 정상적인 return.
			Book retBook = bookManager.AddBook(bookNotInBookManager.id, bookNotInBookManager.title, bookNotInBookManager.author, bookNotInBookManager.publicDate);
			assertEquals(bookNotInBookManager.id, retBook.id, "bookNotInBookManager, retBook ID is not equal");
			System.out.printf("{id: '%d', 제목: '%s', 저자 : '%s', 출판년도 : '%d'}도서가 추가되었습니다.\n", retBook.id, retBook.title, retBook.author, retBook.publicDate);		

			//bookInBookManager를 bookManager에 추가. -> throws exception.
			BookManagerException ex = assertThrows(BookManagerException.class, () -> bookManager.AddBook(bookInBookManager.id, bookInBookManager.title, bookInBookManager.author, bookInBookManager.publicDate));
			System.out.println(ex.getMessage());
		} catch(BookManagerException exc){
		}
	}

	@Test
	void testSearchBooks() {
		try {
			//bookInBookManager를 bookManager에서 탐색. -> 정상적인 return.
			Book retBook = bookManager.SearchBook(bookInBookManager.id);
			assertEquals(bookInBookManager.id, retBook.id, "testCase, retBook ID is not equal");
			System.out.printf("검색결과:\n");
			System.out.printf("{id: '%d', 제목: '%s', 저자 : '%s', 출판년도 : '%d'}\n", retBook.id, retBook.title, retBook.author, retBook.publicDate);			
			
			//bookNotInBookManager를 bookManager에서 탐색. -> throws exception.
			BookManagerException ex = assertThrows(BookManagerException.class, () -> bookManager.SearchBook(bookNotInBookManager.id));
			System.out.println(ex.getMessage());

		} catch(BookManagerException exc){
		}
		
	}

	@Test
	void testRemoveBooks() {
		try {
			//bookInBookManager를 bookManager에서 제거. -> 정상적인 return.
			Book retBook = bookManager.RemoveBook(bookInBookManager.id);
			assertEquals(bookInBookManager.id, retBook.id, "testCase, retBook ID is not equal");
			System.out.printf("{id: '%d', 제목: '%s', 저자 : '%s', 출판년도 : '%d'}도서가 삭제되었습니다.\n", retBook.id, retBook.title, retBook.author, retBook.publicDate);		
			
			
			//bookNotInBookManager를 bookManager에서 제거. -> throws exception.
			BookManagerException ex = assertThrows(BookManagerException.class, () -> bookManager.RemoveBook(bookNotInBookManager.id));
			System.out.println(ex.getMessage());

		} catch(BookManagerException exc){
		}
		
	}

}
