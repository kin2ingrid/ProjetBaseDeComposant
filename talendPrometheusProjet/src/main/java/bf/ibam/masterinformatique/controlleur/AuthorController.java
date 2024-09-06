package bf.ibam.masterinformatique.controlleur;
import java.util.List;
import java.util.Optional;

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
import bf.ibam.masterinformatique.service.AuthorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("apiAuteur")

public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/listAuteurs")
    public  List<Author> getListAuthor() {
    	return authorService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
           return ResponseEntity.notFound().build();
    }
    }    
    
    @PostMapping("/enregistrer")
    public ResponseEntity<List<Author>> createAuthor(@RequestBody List<Author> author) {
        List<Author> creatAuthor = authorService.saveAll(author);
        return ResponseEntity.ok(creatAuthor);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, 
    		@RequestBody Author authorDetails) {
    	           return ResponseEntity.ok(authorService.updateAuthor(id, authorDetails));
         }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    	authorService.deleteById(id);
        return ResponseEntity.noContent().build();
   }  
    
}


