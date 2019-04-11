package com.ssi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ssi.dao.ProductDAO;
import com.ssi.entities.Product;

@Controller
@SessionAttributes(names= {"userid","product"})
public class MyController {

@Autowired
ProductDAO productDAO;

//mapping user parameter with userid attribute from session
@RequestMapping("fromsession")
public void showFromSession(@SessionAttribute("userid") String user, @CookieValue("JSESSIONID") String cookie) {
	System.out.println("User Id From Session : "+user+",  SessionId Cookie : "+cookie);
}
@RequestMapping("lastsearch")
public String showLastSearch() {
	return "lastsearch";
}
@RequestMapping("verify")
public ModelAndView verifyUser(@RequestParam("t1") String userid) {
	ModelAndView mv=new ModelAndView("loginsuccess");
	mv.addObject("userid", userid);
	return mv;
}
	
@RequestMapping("login")
public String showLogin() {
	return "login";
}
@RequestMapping("/newproduct")
public String showProductEntryForm() {
	return "productentry";
}

@RequestMapping("SaveProduct")
public ModelAndView saveProduct(@ModelAttribute("product") Product product) {
	productDAO.addProduct(product);
	ModelAndView mv=new ModelAndView("success");
	return mv;
}

@RequestMapping("search")
public String showSearchForm() {
	return "searchproduct";
}

@RequestMapping("SearchProduct")
public ModelAndView searchProduct(@RequestParam("pcode") int id) {
	Product product=productDAO.searchProduct(id);
	ModelAndView mv=new ModelAndView("productdetails");
	mv.addObject("product",product);//storing to session instead of request
	return mv;
}

@RequestMapping("viewall")
public ModelAndView showAllProducts() {
	List<Product> products=productDAO.getAllProducts();
	ModelAndView mv=new ModelAndView("showallproducts");
	mv.addObject("products",products);
	return mv;
}
@RequestMapping("update")
public ModelAndView showUpdateForm(@RequestParam("pcode") int pcode) {
	Product product=productDAO.searchProduct(pcode);
	ModelAndView mv=new ModelAndView("updateform");
	mv.addObject("product", product);
	return mv;
}

@RequestMapping("UpdateProduct")
public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
	productDAO.updateProduct(product);
	ModelAndView mv=new ModelAndView("redirect:viewall");
	return mv;
}
@RequestMapping("delete")
public ModelAndView removeProduct(@RequestParam("pcode") int pcode) {
	productDAO.removeProduct(pcode);
	ModelAndView mv=new ModelAndView("redirect:viewall");
	return mv;
}









}
