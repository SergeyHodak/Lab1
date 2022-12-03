import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Subject> subjects = new ArrayList<>(); // Глобальне поле, список предметів
    private final List<Group> groups = new ArrayList<>(); // Глобальне поле, список груп
    private final List<Student> students = new ArrayList<>(); // Глобальне поле, список студентів
    private final List<Exam> exams = new ArrayList<>(); // Глобальне поле, список іспитів

    // Додавання нового предмета до глобального списку
    public String createSubject(String name) {
        Subject subject = new Subject(); // Новий екземпляр класу "предмат"
        subject.setName(name); // Присвоєння екземпляру "назви придмета"

        // Якщо предмет вже зареєстрований в глобальному списку
        if (findSubject(name) != null) {
            return "\"" + name + "\" is already registered!";
        }
        subjects.add(subject); // Додати екземпляр до глобального списку предметів
        return "Successfully completed";
    }

    // Додавання нової групи до глобального списку
    public String createGroup(String name) {
        Group group = new Group(); // Новий екземпляр класу "група"
        group.setName(name); // Присвоєння екземпляру "назви групи"

        // Якщо група вже зареєстрована в глобальному списку
        if (findGroup(name) != null) {
            return "\"" + name + "\" is already registered!";
        }
        groups.add(group); // Додати екземпляр до глобального списку груп
        return "Successfully completed";
    }

    // Додавання нового студента до глобального списку
    public String createStudent(Student student) {
        // Чи може вже цей студент зареєстрований?
        if (findStudent(student) !=  null) {
            return "Student \"" +
                    student.getLastName() + " " +
                    student.getFirstName() + " " +
                    student.getMiddleName() +
                    "\" is already registered!";
        }
        students.add(student); // Додати екземпляр до глобального списку студентів
        return "Successfully completed";
    }

    // Додавання ПІБ студента до групи
    public String addStudentInGroup(String groupName, Student student) {
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
            return "Group \"" + groupName + "\" not found";
        }

        // Якщо такий студент є в глобальному списку
        if (findStudent(student) != null) {
            groups.get(idGroup).addStudent(student); // Додати студента до групи
            return "Successfully completed"; // Вийти з методу
        }
        // Студент не зареєстрований в глобальному списку студентів
        return "Student \"" + student.getLastName() + " " + student.getFirstName() + " " +
                student.getMiddleName() + "\" is not found in the global registry";
    }

    // Додавання групи до предмета за назвою
    public String addGroupInSubject(String subjectName, String groupName) {
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
            return "Subject \"" + subjectName + "\" not found";
        }

        // Біг по глобальному списку груп
        for (Group group : groups) {
            // Якщо така група є в глобальному списку
            if (group.getName().equals(groupName)) {
                subjects.get(idSubject).addGroup(group); // Додати групу до предмета
                return "Successfully completed"; // Вийти з методу
            }
        }
        // Група не зареєстрований в глобальному списку груп
        return "Group \"" + groupName + "\" is not found in the global registry";
    }

    // Пошук та виведення предметів по ПІБ студента
    public List<String> findSubjectsByStudent(Student student) {
        List<String> result = new ArrayList<>(); // Список на віддічу з метода
        // Якщо студента нема в глобальному списку
        if (findStudent(student) == null) {
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

    // Пошук та виведення групи з ПІБ студента (Зрозумів це як: Пошук груп за ПІБ студента)
    public List<Group> findGroupsByStudent(Student student) {
        List<Group> result = new ArrayList<>(); // Список на віддічу з метода
        // Якщо студента нема в глобальному списку
        if (findStudent(student) == null) {
            return result; // Віддати пустий масив та завершити виконання методу
        }

        // Біг по глобальному списку груп
        for (Group group : groups) {
            // Біг по студентам групи
            for (Student groupStudent : group.getStudents()) {
                // Якщо в цій групі є вказаний студент
                if (groupStudent.getLastName().equals(student.getLastName()) &
                    groupStudent.getFirstName().equals(student.getFirstName()) &
                    groupStudent.getMiddleName().equals(student.getMiddleName())
                ) {
                    result.add(group); // Додати до результуючого списку цю групу
                    break; // Вийти з циклу бігання по списку студентів цієї групи
                }
            }
        }
        return result;
    }

    // Приватний метод, перевірка чи є студент в глобальному списку
    private Student findStudent(Student student) {
        // Біг по глобальному списку студентів
        for (Student unit : students) {
            // Якщо такий студент є
            if (unit.getLastName().equals(student.getLastName()) &
                    unit.getFirstName().equals(student.getFirstName()) &
                    unit.getMiddleName().equals(student.getMiddleName())
            ) {
                return unit;
            }
        }
        return null;
    }

    // Пошук груп за назвою предмета
    public List<Group> findGroupsBySubject(String subjectName) {
        for (Subject unit : subjects) {
            if (unit.getName().equals(subjectName)) {
                return unit.getGroups();
            }
        }
        return new ArrayList<>();
    }

    // Додавання оцінки конкретному студенту з конкретного іспиту
    public String createRating(Student student, String subjectName, int rating) {
        // Отримати студента з глобального списку
        Student isStudent = findStudent(student);
        // Якщо такого студента нема в глобальному списку
        if (isStudent == null) {
            return "Student \"" + student.getLastName() + " " + student.getFirstName() + " " +
                    student.getMiddleName() + "\" is not found in the global registry";
        }

        // Отримати предмет з глобального списку
        Subject isSubject = findSubject(subjectName);
        // Якщо такого предмета нема в глобальному списку
        if (isSubject == null) {
            return "Subject \"" + subjectName + "\" is not found in the global registry";
        }

        // Створюємо екземпляр іспиту та заповнюємо його
        Exam exam = new Exam();
        exam.setSubject(isSubject);
        exam.setStudent(isStudent);
        exam.setRating(rating);

        // Перевіряємо чи може вже є такий запис
        for (Exam unit : exams) {
            if (unit.getSubject() == exam.getSubject() & unit.getStudent() == exam.getStudent()) {
                return "Such a key already exists. It has the value : " + unit.getRating();
            }
        }

        // Реєструймо цей екземпляр в глобальному списку
        exams.add(exam);
        return "Successfully completed";
    }

    // Приватний метод, перевірка чи є предмет в глобальному списку
    private Subject findSubject(String subjectName) {
        for (Subject unit : subjects) {
            if (unit.getName().equals(subjectName)) {
                return unit;
            }
        }
        return null;
    }

    // Приватний метод, перевірка чи є група в глобальному списку
    private Group findGroup(String groupName) {
        for (Group unit : groups) {
            if (unit.getName().equals(groupName)) {
                return unit;
            }
        }
        return null;
    }

    // Виведення всіх оцінок студентів певної групи
    public List<Exam> findRatingsByGroup(String groupName) {
        List<Exam> result = new ArrayList<>();
        Group group = findGroup(groupName); // Підтягування групи з глобального списку
        // Якщо група не зареєстрована в глобальному списку
        if (group == null) {
            return result;
        }

        // Біг по списку оцінок з іспитів
        for (Exam exam : exams) {
            // Біг по списку груп цього предмета, за яким був іспит у студента
            for (Group unit : exam.getSubject().getGroups()) {
                // Якщо тут є группа з якої ведеться складання списку оцінок
                if (unit.getName().equals(groupName)) {
                    // Якщо є такий студент
                    if(findStudentByGroup(exam.getStudent(), group)) {
                        result.add(exam);
                    }
                    break; // Завершити біг по списку груп цього предмета
                }
            }
        }
        return result;
    }

    // Приватний метод, чи є студент в вказаній групі
    private boolean findStudentByGroup(Student student, Group group) {
        for (Group groupInList : groups) {
            if (groupInList.getName().equals(group.getName())) {
                return groupInList.getStudents().contains(student);
            }
        }
        return false;
    }

    // Видалення групи та всіх студентів у ній
    public String removeGroup(String groupName) {
        int index = -1;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(groupName)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            groups.remove(index);
            return "Successfully completed";
        } else {
            return "Group \"" + groupName + "\" is not registered in the global list!";
        }
    }

    // Зміна оцінки з предмета та ПІБ конкретного студента. (Зрозумів це як: Зміна оцінки з предмета по ПІБ конкретного студента)
    public String setRatingWithSubjectByStudent(String subjectName, Student student, int rating) {
        // Чи є предмет в глобальному списку
        if (findSubject(subjectName) == null) {
            return "Subject \"" + subjectName + "\" is not registered in the global list!";
        }

        // Чи є такий студент в глобальному списку
        if (findStudent(student) == null) {
            return "Student \"" +
                    student.getLastName() + " " +
                    student.getFirstName() + " " +
                    student.getMiddleName() +
                    "\" is not registered in the global list!";
        }

        for (Exam exam : exams) {
            if (exam.getSubject().getName().equals(subjectName) &
                exam.getStudent().equals(student)
            ) {
                exam.setRating(rating);
            }
        }
        return "Successfully completed";
    }
}
