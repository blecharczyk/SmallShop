package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    private String productName;
    private int unitsInStock;
    @ManyToOne
    @JoinColumn(name = "Supplier_Company_Name")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<Invoice> invoices;

    public Product(String productName, int unitsInStock) {
        this();
        this.productName = productName;
        this.unitsInStock = unitsInStock;
    }

    public Product() {
        invoices = new HashSet<>();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        supplier.getProducts().add(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }

    public void addInvoice(Invoice invoice) {
       invoices.add(invoice);
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

}
