package payback.ive2.cancel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BASKET")
public class CancelEntity {
    @Id
    private Long id; // 주요 키 (Primary Key) 필드, 필요에 따라 수정
    private String productName; // 제품명 필드
    private Integer quantity; // 수량 필드
    private String userId; // 사용자 식별자 필드

    // Getter 및 Setter 메서드 (생략 가능)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    // 다른 필드에 대한 Getter 및 Setter 메서드도 필요할 수 있습니다.
}
