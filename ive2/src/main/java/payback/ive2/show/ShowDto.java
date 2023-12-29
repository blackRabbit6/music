package payback.ive2.show;

public class ShowDto {
    private String productname;
    private int quantity;
    private String userId;

    public ShowDto() {
    }

    public ShowDto(String productname, int quantity, String userId) {
        this.productname = productname;
        this.quantity = quantity;
        this.userId = userId;
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
}
