package dil.ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dil.ecommerce.entity.PurchaseDetails;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseDetails, Integer> {

	@Query("Select p from PurchaseDetails p where p.purchaseDate between ?1 and ?2")
	List<PurchaseDetails> findByDate(Date start, Date end);

}
