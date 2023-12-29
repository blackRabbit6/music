package payback.ive2.cancel;

public class CancelDto {
    private String productName;
    private Integer quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    // 다른 필드의 getter와 setter 메서드 추가
}