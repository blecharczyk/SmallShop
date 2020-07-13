package controller;

import entity.Category;
import entity.Invoice;
import entity.Product;
import entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class ShopController {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    public List<Product> getProducts(){
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product");

        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    public Invoice newOrder(Product product, int quantity){
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        if(quantity > product.getUnitsInStock()){
            throw new IllegalArgumentException("Nie ma wystarczajacej ilosci!!!");
        }
        session.saveOrUpdate(product);

        product.setUnitsInStock((product.getUnitsInStock()-quantity));
        Invoice inv = new Invoice();
        session.save(inv);
        inv.setProduct(product);
        inv.setQuantity(quantity);

        tx.commit();
        session.close();
        return inv;
    }

    public Product addProduct(String name, int quantity, Category category, Supplier supplier){
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Product prod = new Product(name, quantity);
        session.save(prod);
        prod.setCategory(category);
        prod.setSupplier(supplier);
        tx.commit();
        session.close();
        return prod;
    }

    public Category addCategory(String name){
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Category cat = new Category(name);
        session.save(cat);
        tx.commit();
        session.close();
        return cat;
    }

    public Supplier addSupplier(String companyName, String street, String city){
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Supplier sup = new Supplier(companyName, street, city);
        session.save(sup);
        tx.commit();
        session.close();
        return sup;
    }









}
