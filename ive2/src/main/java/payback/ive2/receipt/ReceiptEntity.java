package payback.ive2.receipt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ReceiptEntity {
    @Id
    private int purchaseId;
    private String name;
    private String productName;
    private int quantity;
    private Date buyday;

    public ReceiptEntity() {
    }

    public ReceiptEntity(int purchaseId, String name, String productName, int quantity, Date buyday) {
        this.purchaseId = purchaseId;
        this.name = name;
        this.productName = productName;
        this.quantity = quantity;
        this.buyday = buyday;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBuyday() {
        return buyday;
    }

    public void setBuyday(Date buyday) {
        this.buyday = buyday;
    }
}
