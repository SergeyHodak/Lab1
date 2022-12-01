import java.util.ArrayList;
import java.util.List;

public class ModelResult {
    private List<Subject> subjects = new ArrayList<>(); // Глобальне поле, список предметів
    private List<Group> groups = new ArrayList<>(); // Глобальне поле, список груп
    private List<Student> students = new ArrayList<>(); // Глобальне поле, список студентів

    // Додавання нового предмета до глобального списку
    public void createSubject(String name) {
        Subject subject = new Subject(); // Новий екземпляр класу "предмат"
        subject.setName(name); // Присвоєння екземпляру "назви придмета"
        subjects.add(subject); // Додати екземпляр до глобального списку предметів
    }

    // Додавання нової групи до глобального списку
    public void createGroup(String name) {
        Group group = new Group(); // Новий екземпляр класу "група"
        group.setName(name); // Присвоєння екземпляру "назви групи"
        groups.add(group); // Додати екземпляр до глобального списку груп
    }

    // Додавання нового студента до глобального списку
    public void createStudent(String lastName, String firstName, String middleName) {
        Student student = new Student(); // Новий екземпляр класу "студент"
        student.setLastName(lastName); // Присвоїти екземпляру "прізвище"
        student.setFirstName(firstName); // Присвоїти екземпляру "ім'я"
        student.setMiddleName(middleName); // Присвоїти екземпляру "по батькові"
        students.add(student); // Додати екземпляр до глобального списку студентів
    }

    // Додавання ПІБ студента до групи
    public void addStudentInGroup(String groupName, Student student) {
        int idGroup = -1; // Ідентифікатор групи
        // Біг по глобальному списку груп
        for (int i = 0; i < groups.size(); i++) {
            // Якщо группу знайдено в списку
            if (groups.get(i).getName().equals(groupName)) {
                idGroup = i; // Зареєструвати її ідентифікатор
                break; // Завершити цикл
            }
        }
        // Якщо группу не знайдено
        if (idGroup == -1) {
            System.out.println("Group \"" + groupName + "\" not found");
            return; // Вийти з методу
        }

        // Біг по глобальному списку студентів
        for (Student unit : students) {
            // Якщо такий студент є в глобальному списку
            if (unit.getLastName().equals(student.getLastName()) &
                    unit.getFirstName().equals(student.getFirstName()) &
                    unit.getMiddleName().equals(student.getMiddleName())
            ) {
                groups.get(idGroup).addStudent(unit); // Додати студента до групи
                return; // Вийти з методу
            }
        }
        // Студент не зареєстрований в глобальному списку студентів
        System.out.println("Student \"" + student.getLastName() + " " + student.getFirstName() + " " +
                student.getMiddleName() + "\" is not found in the global registry");
    }

    // Додавання групи до предмета за назвою
    public void addGroupInSubject(String subjectName, String groupName) {
        int idSubject = -1;
        // Біг по глобальному списку предметів
        for (int i = 0; i < subjects.size(); i++) {
            // Якщо предмет знайдено в списку
            if (subjects.get(i).getName().equals(subjectName)) {
                idSubject = i; // Зареєструвати його ідентифікатор
                break; // Завершити цикл
            }
        }
        // Якщо предмет не знайдено
        if (idSubject == -1) {
            System.out.println("Subject \"" + subjectName + "\" not found");
            return; // Вийти з методу
        }

        // Біг по глобальному списку груп
        for (Group group : groups) {
            // Якщо така група є в глобальному списку
            if (group.getName().equals(groupName)) {
                subjects.get(idSubject).addGroup(group); // Додати групу до предмета
                return; // Вийти з методу
            }
        }
        // Група не зареєстрований в глобальному списку груп
        System.out.println("Group \"" + groupName + "\" is not found in the global registry");
    }

    // Пошук та виведення предметів по ПІБ студента
    public List<String> findSubjectsByStudent(Student student) {
        List<String> result = new ArrayList<>(); // Список на віддічу з метода
        boolean existenceInList = false; // Наявність студента в глобальному списку
        // Біг по глобальному списку студентів
        for (Student unit : students) {
            // Якщо такий студент є
            if (unit.getLastName().equals(student.getLastName()) &
                unit.getFirstName().equals(student.getFirstName()) &
                unit.getMiddleName().equals(student.getMiddleName())
            ) {
                existenceInList = true; // Реєструймо що є співпадання
                break; // Завершити цикл
            }
        }
        // Якщо студента нема в глобальному списку
        if (!existenceInList) {
            return result; // Віддати пустий масив та завершити виконання методу
        }

        // Біг по глобальному списку предметів
        for (Subject subject : subjects) {
            boolean existence = false; // Наявність студента
            // Біг по списку груп в придметі
            for (Group group : subject.getGroups()) {
                // Біг по списку студентів в групі
                for (Student groupStudent : group.getStudents()) {
                    // Якщо є студент якого шукаємо
                    if (groupStudent.getLastName().equals(student.getLastName()) &
                        groupStudent.getFirstName().equals(student.getFirstName()) &
                        groupStudent.getMiddleName().equals(student.getMiddleName())
                    ) {
                        result.add(subject.getName()); // Додати предмет до списку результату
                        existence = true; // Тут студента знайдено
                        break; // Завершити цикл бігу по студентам в групі
                    }
                }
                // Якщо цей предмет був доданий до списку
                if (existence) {break;} // Завершити цикл бігу по группам предмета
            }
        }
        return result; // Віддати результат
    }
}
