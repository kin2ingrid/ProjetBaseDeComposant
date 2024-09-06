package bf.ibam.masterinformatique.service;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bf.ibam.masterinformatique.exception.ResourceNotFoundException;
import bf.ibam.masterinformatique.model.Author;
import bf.ibam.masterinformatique.model.Book;
import bf.ibam.masterinformatique.repository.AuthorRepository;
import bf.ibam.masterinformatique.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Data
@Service
public class BookService {
	
@Autowired
  private final BookRepository bookRepository;

@Autowired 
   private AuthorRepository authorRepository;
private static final Logger logger = LoggerFactory.getLogger(BookService.class);


    public List<Book> findAll() { 
        List<Book> booksFind = bookRepository.findAll();
        System.out.println("***detailBook***: " + booksFind);
       // logger.info("Fetched listBook: " + booksFind);

        return booksFind;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    @Transactional
    public Book save(Book book) { 
    	 // Enregistrer l'auteur s'il n'existe pas déjà
    	  if (book.getAuthor() != null && book.getAuthor().getId() != null) {
              Author author = authorRepository.findById(book.getAuthor().getId())
                              .orElseThrow(() -> new RuntimeException("Author not found"));
              book.setAuthor(author);
          }
        // Enregistrer le livre avec l'auteur
        //bookRepository.save(book);

        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> saveAll(List<Book> books) { 
        for (Book book : books) {
            // Vérifier et récupérer l'auteur
        	 logger.info("**********Saving book with details: {}", book);
            if (book.getAuthor() != null && book.getAuthor().getId() != null) {
                Author author = authorRepository.findById(book.getAuthor().getId())
                                  .orElseThrow(() -> new RuntimeException("Author not found"));
                book.setAuthor(author);
                logger.info("--------Book author: {}", book.getAuthor());

            }
            // Log avant de sauvegarder le livre
            logger.info("++++++++++Saving book: {}", book);    
            // Enregistrer chaque livre
            bookRepository.save(book);
        }
        return bookRepository.saveAll(books);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    
    
    public Optional<Book> getBookById(Long id) {
       Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional;
        } else {
            throw new RuntimeException("Book not found for this id " + id);
        }
    }

    
    public Book updateBook(Long id, Book bookDetails) {
       Optional<Book> bookOptional = bookRepository.findById(id);
       if (bookOptional.isPresent()) {
          Book book = bookOptional.get();
           book.setIsbn(bookDetails.getIsbn());
         book.setTitre(bookDetails.getTitre());
          book.setAuthor(bookDetails.getAuthor());
           return bookRepository.save(book);
        } else {
          throw new RuntimeException("Book not found for this id " + id);
        }
   }



}
