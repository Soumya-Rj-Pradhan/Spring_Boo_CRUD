package org.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProdService {
	@Autowired
	private ProdRepo repo;
	
	public void saveProd(Product p) {
		repo.save(p);
	}
	
	public List<Product> listAll(){
		return repo.findAll();
	}
	
	public Product getProduct(Long id) {
		return repo.findById(id).get();
	}
	
	public void deleteProduct(Long id) {
		repo.deleteById(id);
	}
}
