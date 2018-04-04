package dil.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dil.ecommerce.entity.Product;
import dil.ecommerce.entity.PurchaseDetails;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByProductName(String name);

	@Query("Select p from Product p where p.productName like ?1%")
	List<Product> findByProductNameLike(String name);

	List<Product> findByCategory(String catogory);

	@Query("UPDATE P SET P.quantity = P.quantity-?2 FROM PRODUCT P WHERE P.productId = ?1")
	void removePruchasedProducts(int productId, int quantity);

	@Query("SELECT P.quantity FROM PRODUCT P WHERE P.productId = ?1")
	int getQuantityByProductId(int productId);

}
