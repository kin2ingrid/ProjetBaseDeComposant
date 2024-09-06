package bf.ibam.masterinformatique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bf.ibam.masterinformatique.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
 

}
