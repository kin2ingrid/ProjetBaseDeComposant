package bf.ibam.masterinformatique.controlleur;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bf.ibam.masterinformatique.model.Author;
import bf.ibam.masterinformatique.model.Book;
import bf.ibam.masterinformatique.service.AuthorService;
import bf.ibam.masterinformatique.service.BookService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("apiBook")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

	
	  @PostMapping("/enregistrer") public ResponseEntity<List<Book>>
	 createBook(@RequestBody List<Book> book) { //
	 List<Book> creatBook = bookService.saveAll(book);
	 return ResponseEntity.ok(creatBook); }
	 
    
    /*@PostMapping("/enregistrer")
    public ResponseEntity<Book>  createBook(@RequestBody Book book) {
      //  System.out.println("Received book: " + book.getBook());
        System.out.println("Received bookLabel: " + book.getTitre());
      //  System.out.println("Received author ID: " + book.getAuthor().getAuthor());
       Book creatBook = bookService.save(book);
        return ResponseEntity.ok(creatBook);
    }*/
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
           return ResponseEntity.notFound().build();
    }
    }       

    //recupere tout les livres
   @GetMapping("/listLivres")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
   }

//recuperer un book par son id
 /*  @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitre(bookDetails.getTitre());
            updatedBook.setIsbn(bookDetails.getIsbn());
            return ResponseEntity.ok(bookService.save(updatedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
   }*/
   
	
	  @PutMapping("/{id}") public ResponseEntity<Book> updateBook(@PathVariable
	 Long id, @RequestBody Book bookDetails) {
		  Book updatedBook = bookService.updateBook(id, bookDetails); 
		  if (updatedBook != null) {
			  return ResponseEntity.ok(updatedBook); 
			  } else {
				  return ResponseEntity.notFound().build(); 
				  }
		  }
	 
    
   /* @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, 
    		@RequestBody Book bookDetails) {
    	           return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
         }
    */
}
