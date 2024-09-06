/**
 * 
 */
package bf.ibam.masterinformatique.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entite auteur
 */
@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "nationalite")
  private String nationalite;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   // @JsonBackReference
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();
   

   @Override
   public String toString() {
       return "Author{id=" + id + ", code='" + code + "', nom='" + nom + "', prenom='" + prenom + "', nationalite='" + nationalite + "'}";
   }
   
 
}
