package com.ssi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entities.Product;

@Component
public class ProductDAO {
	@Autowired
	SessionFactory sessionFactory;
	public Product addProduct(Product product) {
		Session session=sessionFactory.openSession();
		session.save(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product removeProduct(int pcode) {
		Session session=sessionFactory.openSession();
		Product product=searchProduct(pcode);
		session.delete(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product updateProduct(Product product) {
		Session session=sessionFactory.openSession();
		session.update(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product searchProduct(int id) {
		Session session=sessionFactory.openSession();
		Product product= session.get(Product.class,id);
		session.close();
		return product;
	}
	public List<Product> getAllProducts(){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		List<Product> productList=query.list();
		session.close();
		return productList;
	}
	
}
