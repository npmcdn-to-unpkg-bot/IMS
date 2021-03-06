package com.ims.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ims.domain.Product;
import com.ims.domain.ProductCategories;

@Repository
@Transactional
public class ProductCategoriesDAOImpl implements ProductCategoriesDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createNewProductCategories(ProductCategories productCategories)
	{
		sessionFactory.getCurrentSession().save(productCategories);
	}
	
	@Override
	public void removeProductCategoriesByUpc(Long upc)
	{
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from ProductCategories where productUpc= :upc");
		
		query.setLong("upc", upc);
		
		query.executeUpdate();
	}
	
	@Override
	public void removeProductCategoriesById(Long id)
	{
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from ProductCategories where categoryId = :id");
		
		query.setLong("id", id);
		
		query.executeUpdate();
	}
	
	@Override
	public ProductCategories getProductCategoriesByUpc(Long upc)
	{
		ProductCategories productCategory = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ProductCategories where productUpc= :upc");
		
		query.setLong("upc", upc);
		
		productCategory = (ProductCategories) query.uniqueResult();
		
		return productCategory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategories> getProductCategoriesById(Long id)
	{		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ProductCategories pc where pc.productCategory.categoryId = :id");
		
		query.setLong("id", id);
		
		return (List<ProductCategories>) query.list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategories> getAllProductCategories()
	{
		return sessionFactory.getCurrentSession().createQuery("from ProductCategories").list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategoryId(Long categoryId)
	{
		return (List<Product>) sessionFactory.getCurrentSession().createQuery("select pc.product from ProductCategories pc where pc.productCategory.categoryId = :id").setLong("id", 1).list();
	}
}
