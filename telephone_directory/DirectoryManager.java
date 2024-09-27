import java.util.HashMap;

public class DirectoryManager {
    private HashMap<String, String> phoneDirectory = new HashMap<>();

    public void addRecord(String name, String phone) {
        phoneDirectory.put(name, phone);
    }

    public void modifyRecord(String name, String phone) {
        phoneDirectory.put(name, phone);
    }

    public void deleteRecord(String name) {
        phoneDirectory.remove(name);
    }

    public String searchRecord(String name) {
        return phoneDirectory.get(name);
    }

    public HashMap<String, String> getAllRecords() {
        return phoneDirectory;
    }

    public boolean recordExists(String name) {
        return phoneDirectory.containsKey(name);
    }
}
