import controller.ShopController;
import entity.Category;
import entity.Invoice;
import entity.Product;
import entity.Supplier;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {


    public static void main(final String[] args) throws Exception {
        ShopController sc = new ShopController();

        Category food = sc.addCategory("food");
        Supplier supplier1 = sc.addSupplier("Sadownik", "Krakow", "Kijowska");
        Supplier supplier2 = sc.addSupplier("Mleczarz", "Warszawa", "Mickiewicza");

        Product milk = sc.addProduct("milk", 500, food, supplier2);
        Product apple = sc.addProduct("apple", 100, food, supplier1);

        sc.newOrder(milk, 10);

    }
}