package org.pj.library_management;


import org.pj.library_management.dao.entities.*;
import org.pj.library_management.dao.repository.PersonRepository;
import org.pj.library_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner{

	@Autowired
	private BookManager bookManager;

	@Autowired
	private StudentManager studentManager;

	@Autowired
	private AuthorManager authorManager;

	@Autowired
	private TransactionManager transactionManager;

	@Autowired
	private AdminManager adminManager;
	@Autowired
	private PersonRepository personRepository;


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author();
		author1.setFullName("badr abou lfath idrissi");
		author1.setEmail("badr@gmail.com");
		author1.setAddress("673 bd mohammed vi, casablanca");
		author1.setAge(22);
		author1.setPhoneNumber("0628844556");

		Author author2 = new Author();
		author2.setFullName("John Doe");
		author2.setEmail("john@example.com");
		author2.setAddress("123 Main St, Anytown");
		author2.setAge(30);
		author2.setPhoneNumber("1234567890");

		Author author3 = new Author();
		author3.setFullName("Jane Smith");
		author3.setEmail("jane@example.com");
		author3.setAddress("456 Elm St, Anytown");
		author3.setAge(28);
		author3.setPhoneNumber("0987654321");

		Author author4 = new Author();
		author4.setFullName("Alice Johnson");
		author4.setEmail("alice@example.com");
		author4.setAddress("789 Oak St, Anytown");
		author4.setAge(35);
		author4.setPhoneNumber("1122334455");

		Author author5 = new Author();
		author5.setFullName("Harper Lee");
		author5.setEmail("harper@example.com");
		author5.setAddress("456 Southern Lane, Monroeville");
		author5.setAge(89);
		author5.setPhoneNumber("5554443333");

		Author author6 = new Author();
		author6.setFullName("Stephen Hawking");
		author6.setEmail("stephen@example.com");
		author6.setAddress("789 Cosmos Blvd, Cambridge");
		author6.setAge(76);
		author6.setPhoneNumber("7778889999");

		Author author7 = new Author();
		author7.setFullName("William Shakespeare");
		author7.setEmail("william.shakespeare@globe.com");
		author7.setAddress("Stratford-upon-Avon, England");
		author7.setAge(45);
		author7.setPhoneNumber("+212-678-457-124");

		Author author8 = new Author();
		author8.setFullName("Chimamanda Ngozi Adichie");
		author8.setEmail("chimamanda.adichie@author.com");
		author8.setAddress("Enugu, Nigeria");
		author8.setAge(49);
		author8.setPhoneNumber("+234-334-458-448");

		Author author9 = new Author();
		author9.setFullName("Haruki Murakami");
		author9.setEmail("haruki.murakami@japan.com");
		author9.setAddress("Kyoto, Japan");
		author9.setAge(73);
		author9.setPhoneNumber("+81-548-669-5");

		List<Author> authors = Arrays.asList(author1, author2, author3, author4, author5, author6, author7, author8, author9);
		authors.forEach(authorManager::addAuthor);

		Book book1 = new Book();
		book1.setTitle("The Lord of the Rings");
		book1.setGenre(Genre.GEOGRAPHY);
		book1.setAvailable(true);
		book1.setPublicationYear(LocalDate.of(1954, 7, 29));
		book1.setDescription("Epic fantasy novel by J. R. R. Tolkien.");
		book1.setImageFileName("9780618645619.jpg");
		book1.setAuthor(author1);

		Book book2 = new Book();
		book2.setTitle("The Hitchhiker's Guide to the Galaxy");
		book2.setGenre(Genre.BOTANY);
		book2.setAvailable(true);
		book2.setPublicationYear(LocalDate.of(1979, 10, 12));
		book2.setDescription("Comic science fiction series created by Douglas Adams.");
		book2.setImageFileName("R.png");
		book2.setAuthor(author2);

		Book book3 = new Book();
		book3.setTitle("To Kill a Mockingbird");
		book3.setGenre(Genre.FICTIONAL);
		book3.setAvailable(true);
		book3.setPublicationYear(LocalDate.of(1960, 7, 11));
		book3.setDescription("Novel by Harper Lee.");
		book3.setImageFileName("image4.jpg");
		book3.setAuthor(author5);

		Book book4 = new Book();
		book4.setTitle("A Brief History of Time");
		book4.setGenre(Genre.PHYSICS);
		book4.setAvailable(true);
		book4.setPublicationYear(LocalDate.of(1988, 6, 2));
		book4.setDescription("Popular science book by Stephen Hawking.");
		book4.setImageFileName("image3.jpg");
		book4.setAuthor(author6);

		Book book5 = new Book();
		book5.setTitle("Sapiens: A Brief History of Humankind");
		book5.setGenre(Genre.HISTORY);
		book5.setAvailable(true);
		book5.setPublicationYear(LocalDate.of(2011, 12, 10));
		book5.setDescription("Book by Yuval Noah Harari.");
		book5.setImageFileName("image2.png");
		book5.setAuthor(author3);

		Book book6 = new Book();
		book6.setTitle("The Botany of Desire");
		book6.setGenre(Genre.BOTANY);
		book6.setAvailable(true);
		book6.setPublicationYear(LocalDate.of(2001, 8, 25));
		book6.setDescription("Book by Michael Pollan.");
		book6.setImageFileName("image1.jpg");
		book6.setAuthor(author2);

		Book book7 = new Book();
		book7.setTitle("Pride and Prejudice");
		book7.setGenre(Genre.HISTORY);
		book7.setAvailable(true);
		book7.setPublicationYear(LocalDate.of(1813, 8, 25));
		book7.setDescription("Novel by Jane Austen.");
		book7.setImageFileName("image5.jpg");
		book7.setAuthor(author9);

		Book book8 = new Book();
		book8.setTitle("A Confederacy of Dunces");
		book8.setGenre(Genre.NON_FICTIONAL);
		book8.setAvailable(true);
		book8.setPublicationYear(LocalDate.of(1980, 10, 25));
		book8.setDescription("Novel by John Kennedy Toole.");
		book8.setImageFileName("image6.jpg");
		book8.setAuthor(author7);

		Book book9 = new Book();
		book9.setTitle("Things Fall Apart");
		book9.setGenre(Genre.FICTIONAL);
		book9.setAvailable(true);
		book9.setPublicationYear(LocalDate.of(1950, 10, 12));
		book9.setDescription("Novel by Chinua Achebe.");
		book9.setImageFileName("image7.jpg");
		book9.setAuthor(author8);

		Book book10 = new Book();
		book10.setTitle("The Secret Garden");
		book10.setGenre(Genre.BOTANY);
		book10.setAvailable(true);
		book10.setPublicationYear(LocalDate.of(1911, 5, 12));
		book10.setDescription("Novel by Frances Hodgson Burnett.");
		book10.setImageFileName("image9.jpg");
		book10.setAuthor(author9);

		Book book11 = new Book();
		book11.setTitle("Frankenstein");
		book11.setGenre(Genre.POLITICAL_SCIENCE);
		book11.setAvailable(true);
		book11.setPublicationYear(LocalDate.of(1818, 2, 12));
		book11.setDescription("Novel by Mary Shelley.");
		book11.setImageFileName("image8.jpg");
		book11.setAuthor(author9);

		List<Book> books = Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11);
		books.forEach(bookManager::addBook);

		author1.setBooks(List.of(book1));
		author2.setBooks(List.of(book2,book6));
		author3.setBooks(List.of(book5));
		author5.setBooks(List.of(book3));
		author6.setBooks(List.of(book4));
		author7.setBooks(List.of(book8));
		author8.setBooks(List.of(book9));
		author9.setBooks(List.of(book7,book10,book11));
		for (Author author :authors){authorManager.updateAuthor(author);}

		Student student1 = new Student();
		student1.setFullName("Alice Smith");
		student1.setEmail("alice.smith@example.com");
		student1.setAddress("123 Main St");
		student1.setSchool("Springfield High School");
		student1.setYearLevel("Senior");
		student1.setUsername("smith");
		student1.setPassword("12345");
		student1.setPhoneNumber("555-123-4567");
		student1.setAge(18);
		student1.setRoles(Set.of("USER"));


		Student student2 = new Student();
		student2.setFullName("Bob Johnson");
		student2.setEmail("bob.johnson@example.com");
		student2.setAddress("456 Elm St");
		student2.setSchool("Riverside Middle School");
		student2.setYearLevel("7th Grade");
		student2.setPhoneNumber("555-789-0123");
		student2.setAge(13);

		Student student3 = new Student();
		student3.setFullName("Charlie Brown");
		student3.setEmail("charlie.brown@example.com");
		student3.setAddress("789 Maple Ave");
		student3.setSchool("Woodstock Elementary School");
		student3.setYearLevel("3rd Grade");
		student3.setPhoneNumber("555-456-7890");
		student3.setAge(8);

		Student student4 = new Student();
		student4.setFullName("Diana Garcia");
		student4.setEmail("dianagarcia@gmail.com");
		student4.setAddress("1011 Oak St");
		student4.setSchool("Riverside High School");
		student4.setYearLevel("Sophomore");
		student4.setUsername("diana");
		student4.setPassword("12345");
		student4.setRoles(Set.of("USER"));
		student4.setAge(16);

		Student student5 = new Student();
		student5.setFullName("Ethan Lee");
		student5.setEmail("ethanlee@example.com");
		student5.setAddress("1234 Pine Blvd");
		student5.setSchool("Central Middle School");
		student5.setYearLevel("8th Grade");
		student5.setPhoneNumber("555-321-9087");
		student5.setAge(14);

		List<Student> students = Arrays.asList(student1,student2, student3, student4, student5);
		for (Student student : students){studentManager.addStudent(student);}

		Transaction transaction1 = new Transaction(
				null,null,TransactionStatus.BORROWED,LocalDate.now(),LocalDate.now().plusDays(10),
				student1,book2
		);
		Transaction transaction2 = new Transaction(
				null,null,TransactionStatus.BORROWED,LocalDate.now(),LocalDate.now().plusDays(14),
				student1,book3
		);
		Transaction transaction3 = new Transaction(
				null,null,TransactionStatus.BORROWED,LocalDate.now(),LocalDate.now().plusDays(20),
				student4,book5
		);Transaction transaction4 = new Transaction(
				null,null,TransactionStatus.BORROWED,LocalDate.now(),LocalDate.now().plusDays(20),
				student4,book9
		);

		List<Transaction> transactions = Arrays.asList(transaction1, transaction2,transaction3, transaction4);
		for (Transaction t:transactions){transactionManager.addTransaction(t);}

		student1.setTransactions(List.of(transaction1,transaction2));
		studentManager.updateStudent(student1);
		book2.setTransactions(List.of(transaction1));
		bookManager.updateBook(book2);
		book3.setTransactions(List.of(transaction1));
		bookManager.updateBook(book3);
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setFullName("abou lfath idrissi badr");
		admin.setRoles(Set.of("ADMIN"));
		adminManager.addAdmin(admin);



	}



}
