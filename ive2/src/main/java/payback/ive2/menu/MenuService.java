package payback.ive2.menu;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
  private final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<MenuDto> getMenuItems() {
        return menuDao.getMenuItems();
    }
}
