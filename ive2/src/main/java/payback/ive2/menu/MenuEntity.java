package payback.ive2.menu;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuEntity {
    @Id
    private String productname;
    private int quantity;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
