package payback.ive2.purchase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE")
public class PurchaseEntity {
    @Id
    private Long id;
    private String userId;
    private String userName;
    private String productName;
    private int quantity;
    // 다른 필드 추가 가능

    public PurchaseEntity() {
    }

    public PurchaseEntity(String userId, String userName, String productName, int quantity) {
        this.userId = userId;
        this.userName = userName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    // 다른 필드 및 메서드 추가 가능

}
