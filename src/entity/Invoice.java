package entity;

import javax.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceNumber;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "Product")
    private Product product;

    public Invoice(int quantity) {
        this.quantity = quantity;
    }

    public Invoice() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getInvoices().add(this);
    }

}
