package dil.ecommerce.orchastrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dil.ecommerce.entity.Product;
import dil.ecommerce.entity.PurchaseDetails;
import dil.ecommerce.repository.ProductRepository;

@Component
public class PurchaseOrchastrator {
	
	@Autowired
	ProductRepository productRepository;
	
	public void removePurchasedItems(List<PurchaseDetails> purchasedItems) {
		
		for (PurchaseDetails purchaseDetails : purchasedItems) {
			Product product = purchaseDetails.getProduct();
			int quantity = purchaseDetails.getQuantity();
			productRepository.removePruchasedProducts(product.getProductId(), quantity);
			
		}
	}

	public void validateProductsAvaliablity(List<PurchaseDetails> purchasedItems) throws Exception {
		for (PurchaseDetails purchaseDetails : purchasedItems) {
			Product product = purchaseDetails.getProduct();
			int quantity = purchaseDetails.getQuantity();
			int avaliabeQuantity = productRepository.getQuantityByProductId(product.getProductId());
			if(quantity > avaliabeQuantity) {
				throw new Exception("Product "+ product.getProductName()+" Not Avaliable");
			}
			
		}
		
	}

}
