package dil.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dil.ecommerce.entity.PurchaseDetails;
import dil.ecommerce.orchastrator.PurchaseOrchastrator;
import dil.ecommerce.repository.PurchaseRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/purchase")
public class PurchaseController {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	PurchaseOrchastrator purchaseOrchastrator;
	
	@PostMapping("/buy")
    public List<PurchaseDetails> purchaseItem(@RequestBody List<PurchaseDetails> purchaseItems) throws Exception{
		purchaseOrchastrator.validateProductsAvaliablity(purchaseItems);
		
		List<PurchaseDetails> orderDetails = purchaseRepository.saveAll(purchaseItems);
		if(CollectionUtils.isEmpty(orderDetails)) {
			throw new Exception("Something went wrong");
		}
		
		purchaseOrchastrator.removePurchasedItems(orderDetails);
    	return orderDetails;
    }

}
