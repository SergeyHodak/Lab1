import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name; // Назва групи
    private List<Student> students = new ArrayList<>(); // Список студентів группи

    // Метод отримання значення з поля name
    public String getName() {
        return name;
    }

    // Метод задання нового значення для поля name
    public void setName(String name) {
        this.name = name;
    }

    // Метод отримання списку студентів з поля students
    public List<Student> getStudents() {
        return students;
    }

    // Метод додавання нового значення до поля списку студентів
    public void addStudent(Student student) {
        students.add(student);
    }
}
