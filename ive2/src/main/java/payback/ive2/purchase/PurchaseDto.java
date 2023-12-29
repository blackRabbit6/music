package payback.ive2.purchase;

public class PurchaseDto {
    private String productName;
    private int quantity;

    public PurchaseDto() {
    }

    public PurchaseDto(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
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
