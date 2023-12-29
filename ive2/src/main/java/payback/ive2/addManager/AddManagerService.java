package payback.ive2.addManager;

import org.springframework.stereotype.Service;

@Service
public class AddManagerService {

  private final AddManagerDao addManagerDao;

  public AddManagerService(AddManagerDao addManagerDao){
    this.addManagerDao = addManagerDao;
  }
  public String addManager(AddManagerDto addManagerDto){
    return addManagerDao.addManager(addManagerDto);
  }
}
