package payback.ive2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class LoginDao {

    @Autowired
    private EntityManager entityManager;

    public UserTypeAndName getUserTypeAndName(String id, String pw) {
        String query = "SELECT 'USER' as type, name FROM USERS WHERE id = ? AND pw = ? " +
                "UNION ALL SELECT 'MANAGER' as type, name FROM MANAGER WHERE id = ? AND pw = ?";

        Query sqlQuery = entityManager.createNativeQuery(query);
        sqlQuery.setParameter(1, id);
        sqlQuery.setParameter(2, pw);
        sqlQuery.setParameter(3, id);
        sqlQuery.setParameter(4, pw);

        try {
            Object[] result = (Object[]) sqlQuery.getSingleResult();
            if (result != null) {
                UserTypeAndName userTypeAndName = new UserTypeAndName();
                userTypeAndName.type = (String) result[0];
                userTypeAndName.name = (String) result[1];
                return userTypeAndName;
            } else {
                return null; // 실패할 경우 null 반환
            }
        } catch (NoResultException e) {
            return null; // 실패할 경우 null 반환
        }
    }

    public static class UserTypeAndName {
        public String type;
        public String name;
    }
}