package dil.ecommerce.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dil.ecommerce.entity.PurchaseDetails;
import dil.ecommerce.repository.PurchaseRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/purchase")
public class PurchaseController {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	
	@GetMapping("/all")
    public List<PurchaseDetails> getAllPurchases(){
    	return purchaseRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public Optional<PurchaseDetails> getProductById(@PathVariable("id") int id){
    	return purchaseRepository.findById(id);
	}
	
	@GetMapping("/today")
    public List<PurchaseDetails> getPurchaseToday(){
    	List<Date> startEnd = getStartAndEndOfDay(new Date());
    	return purchaseRepository.findByDate(startEnd.get(0), startEnd.get(1));
	}
	
	@PostMapping("/add")
    public PurchaseDetails addPurchase(@RequestBody PurchaseDetails purchase){
    	return purchaseRepository.save(purchase);
    }
	
	private List<Date> getStartAndEndOfDay(Date currentDate) {
		List<Date> startEnd = new ArrayList<>();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.setTime(currentDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		startEnd.add(cal.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		startEnd.add(cal.getTime());
		return startEnd;
	}

}
