import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name; // Назва предмету
    private List<Group> groups = new ArrayList<>(); // Список груп

    // Метод отримання значення з поля name
    public String getName() {
        return name;
    }

    // Метод задаючий нове значення для поля name
    public void setName(String name) {
        this.name = name;
    }

    // Метод отримання списку груп в предметі
    public List<Group> getGroups() {
        return groups;
    }

    // Метод реєстрації группи в предметі
    public void addGroup(Group group) {
        groups.add(group);
    }
}
