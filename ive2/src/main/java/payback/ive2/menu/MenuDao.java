package payback.ive2.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuDao {

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<MenuDto> getMenuItems() {
        String query = "SELECT productname, quantity FROM menu";

        Query sqlQuery = entityManager.createNativeQuery(query);

        List<Object[]> results = sqlQuery.getResultList();

        // 결과를 MenuDto로 변환
        List<MenuDto> menuItems = new ArrayList<>();
        for (Object[] result : results) {
            String productName = (String) result[0];
            // Object 타입을 int로 변환
            int quantity = Integer.parseInt(result[1].toString());
            MenuDto menuItem = new MenuDto(productName, quantity);
            menuItems.add(menuItem);
        }

        return menuItems;
    }

    public int getMenuQuantity(String productName) {
        String query = "SELECT quantity FROM menu WHERE productname = :productName";
        Query sqlQuery = entityManager.createNativeQuery(query);
        sqlQuery.setParameter("productName", productName);

        Object result = sqlQuery.getSingleResult();

        return Integer.parseInt(result.toString());
    }

    @Transactional
    public void updateMenuQuantity(String productName, int newQuantity) {
        String updateQuery = "UPDATE menu SET quantity = :newQuantity WHERE productname = :productName";
        Query sqlQuery = entityManager.createNativeQuery(updateQuery);
        sqlQuery.setParameter("newQuantity", newQuantity);
        sqlQuery.setParameter("productName", productName);

        sqlQuery.executeUpdate();
    }
}