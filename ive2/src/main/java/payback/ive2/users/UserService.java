package payback.ive2.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private Map<String, User> users = new HashMap<>();
  
  public User getUserById(String userId){
    return users.get(userId);
  }
}
