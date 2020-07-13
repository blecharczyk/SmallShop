package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Supplier {
    @Id
    private String companyName;
    private String street;
    private String city;
    @OneToMany(mappedBy = "supplier")
    private Set<Product> suppliedProducts;

    public Supplier(String companyName, String street, String city) {
        this();
        this.companyName = companyName;
        this.street = street;
        this.city = city;
    }

    public Supplier() {
        suppliedProducts = new HashSet<>();
    }

    public Set<Product> getProducts() {
        return suppliedProducts;
    }

    public void addProduct(Product product){
        product.setSupplier(this);
    }
}
