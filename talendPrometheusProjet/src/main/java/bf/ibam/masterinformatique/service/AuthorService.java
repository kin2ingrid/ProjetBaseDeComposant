package bf.ibam.masterinformatique.service;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bf.ibam.masterinformatique.exception.ResourceNotFoundException;
import bf.ibam.masterinformatique.model.Author;
import bf.ibam.masterinformatique.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    
    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> {
            //System.out.println("Author: " + author.getAuthor());
            System.out.println("AuthorLabel: " + author.getNom());
        });
        return authors;

    }
    public Optional<Author> findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(a -> {
//            System.out.println("Author: " + a.getAuthor());
//            System.out.println("AuthorLabel: " + a.getAuthorLabel());
        });
        return author;
     }

    public List<Author> saveAll(List<Author> author) {
        return authorRepository.saveAll(author);
    }
  
    
    public Author save(Author author) {
        return authorRepository.save(author);
    }
    
    /*
     public Author save(Author author) {
        // Associer chaque livre à l'auteur avant de sauvegarder
        for (Book book : author.getBooks()) {
            book.setAuthor(author);
        }

        // Sauvegarder l'auteur avec les livres associés
        return authorRepository.save(author);
    }
     */

    public Author updateAuthor(Long id, Author authorDetails) {
        return authorRepository.findById(id)
            .map(author -> {
                author.setCode(authorDetails.getCode());
                author.setNom(authorDetails.getNom());
                author.setPrenom(authorDetails.getPrenom());
                author.setNationalite(authorDetails.getNationalite());
                return authorRepository.save(author);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }



}
