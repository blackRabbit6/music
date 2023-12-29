package payback.ive2.basket;

public class BasketDto {
    private String productname;
    private int quantity;
    private String userId;  // 사용자 ID
    private String userName;  // 사용자 이름

    public BasketDto() {
    }

    public BasketDto(String productname, int quantity, String userId, String userName) {
        this.productname = productname;
        this.quantity = quantity;
        this.userId = userId;
        this.userName = userName;
    }

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
}
