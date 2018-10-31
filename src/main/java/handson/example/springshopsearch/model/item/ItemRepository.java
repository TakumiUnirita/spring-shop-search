package handson.example.springshopsearch.model.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findById(long keyword);
	List<Item> findByNameContainsOrderByIdAsc(String keyword);
	List<Item> findByDescriptionContainsOrderByIdAsc(String keyword);
	List<Item> findByNameOrDescriptionContainsOrderByIdAsc(String keyword,String keyword2);
}
