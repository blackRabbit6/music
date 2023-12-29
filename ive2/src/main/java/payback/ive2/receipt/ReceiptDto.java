package payback.ive2.receipt;

import java.util.Date;

public class ReceiptDto {
    private String name;
    private String productName;
    private int quantity;
    private Date buyday;
    private String purchaseId;

    public ReceiptDto() {
    }

    public ReceiptDto(String name, String productName, int quantity, Date buyday, String purchaseId) {
        this.name = name;
        this.productName = productName;
        this.quantity = quantity;
        this.buyday = buyday;
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

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}
