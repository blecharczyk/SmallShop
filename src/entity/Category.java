package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;
    private String name;
    @OneToMany(mappedBy="category")
    private List<Product> products;

    public Category() {
        products = new ArrayList<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        product.setCategory(this);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }
}
