package org.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdController {
	@Autowired
	private ProdService service;
	
	@RequestMapping("/")
	public String home(Model m) {
		List<Product> listAll = service.listAll();
		m.addAttribute("allProducts", listAll);
		return "index";
	}
	
	@RequestMapping("/new")
	public String createProduct(Model m) {
		m.addAttribute("product_new", new Product());
		return "create";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String storeProduct(@ModelAttribute("product_new")Product p) {
		service.saveProd(p);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView updateProduct(@PathVariable(name="id")Long id) {
		ModelAndView mav = new ModelAndView("update");
		Product p = service.getProduct(id);
		mav.addObject("product_edit", p);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id")Long id) {
		service.deleteProduct(id);
		return "redirect:/";
	}
	
}