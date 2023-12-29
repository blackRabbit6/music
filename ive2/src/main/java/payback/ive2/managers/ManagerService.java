package payback.ive2.managers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private Map<String, Manager> managers = new HashMap<>();

    public Manager getManagerById(String managerId){
        return managers.get(managerId);
    }
}
