package payback.ive2.addManager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER")
public class AddManagerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long entityId;

  private String managerId;
  private String managerPw;
  private String managerName;

  public Long getEntityId() {
    return entityId;
  }

  public void setEntityId(Long entityId) {
    this.entityId = entityId;
  }

  public String getManagerId() {
    return managerId;
  }

  public void setManagerId(String managerId) {
    this.managerId = managerId;
  }

  public String getManagerPw() {
    return managerPw;
  }

  public void setManagerPw(String managerPw) {
    this.managerPw = managerPw;
  }

  public String getManagerName() {
    return managerName;
  }

  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }
}
