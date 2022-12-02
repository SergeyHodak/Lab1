import java.util.ArrayList;
import java.util.List;

public class ModelResult {
    private List<Subject> subjects = new ArrayList<>(); // Глобальне поле, список предметів
    private List<Group> groups = new ArrayList<>(); // Глобальне поле, список груп
    private List<Student> students = new ArrayList<>(); // Глобальне поле, список студентів
    private List<Exam> exams = new ArrayList<>(); // Глобальне поле, список іспитів

    // Додавання нового предмета до глобального списку
    public void createSubject(String name) {
        Subject subject = new Subject(); // Новий екземпляр класу "предмат"
        subject.setName(name); // Присвоєння екземпляру "назви придмета"

        // Якщо предмет вже зареєстрований в глобальному списку
        if (findSubject(subject) != null) {
            System.out.println("\"" + name + "\" is already registered!");
            return;
        }
        subjects.add(subject); // Додати екземпляр до глобального списку предметів
    }

    // Додавання нової групи до глобального списку
    public void createGroup(String name) {
        Group group = new Group(); // Новий екземпляр класу "група"
        group.setName(name); // Присвоєння екземпляру "назви групи"

        // Якщо група вже зареєстрована в глобальному списку
        if (findGroup(group) != null) {
            System.out.println("\"" + name + "\" is already registered!");
            return;
        }
        groups.add(group); // Додати екземпляр до глобального списку груп
    }

    // Додавання нового студента до глобального списку
    public void createStudent(String lastName, String firstName, String middleName) {
        Student student = new Student(); // Новий екземпляр класу "студент"
        student.setLastName(lastName); // Присвоїти екземпляру "прізвище"
        student.setFirstName(firstName); // Присвоїти екземпляру "ім'я"
        student.setMiddleName(middleName); // Присвоїти екземпляру "по батькові"

        // Чи може вже цей студент зареєстрований?
        if (findStudent(student) !=  null) {
            System.out.println("Student \"" + lastName + " " + firstName + " " + middleName +  "\" is already registered!");
            return;
        }
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

        // Якщо такий студент є в глобальному списку
        if (findStudent(student) != null) {
            groups.get(idGroup).addStudent(student); // Додати студента до групи
            return; // Вийти з методу
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
    public List<Group> findGroupsBySubject(Subject subject) {
        for (Subject unit : subjects) {
            if (unit.getName().equals(subject.getName())) {
                return unit.getGroups();
            }
        }
        return new ArrayList<>();
    }

    // Додавання оцінки конкретному студенту з конкретного іспиту
    public void createRating(Student student, Subject subject, int rating) {
        // Отримати студента з глобального списку
        Student isStudent = findStudent(student);
        // Якщо такого студента нема в глобальному списку
        if (isStudent == null) {
            System.out.println("Student \"" + student.getLastName() + " " + student.getFirstName() + " " +
                    student.getMiddleName() + "\" is not found in the global registry");
            return;
        }

        // Отримати предмет з глобального списку
        Subject isSubject = findSubject(subject);
        // Якщо такого предмета нема в глобальному списку
        if (isSubject == null) {
            System.out.println("Subject \"" + subject.getName() + "\" is not found in the global registry");
            return;
        }

        // Створюємо екземпляр іспиту та заповнюємо його
        Exam exam = new Exam();
        exam.setSubject(isSubject);
        exam.setStudent(isStudent);
        exam.setRating(rating);

        // Перевіряємо чи може вже є такий запис
        for (Exam unit : exams) {
            if (unit.getSubject() == exam.getSubject() & unit.getStudent() == exam.getStudent()) {
                System.out.println("Such a key already exists. It has the value : " + unit.getRating());
                return;
            }
        }

        // Реєструймо цей екземпляр в глобальному списку
        exams.add(exam);
    }

    // Приватний метод, перевірка чи є предмет в глобальному списку
    private Subject findSubject(Subject subject) {
        for (Subject unit : subjects) {
            if (unit.getName().equals(subject.getName())) {
                return unit;
            }
        }
        return null;
    }

    // Приватний метод, перевірка чи є група в глобальному списку
    private Group findGroup(Group group) {
        for (Group unit : groups) {
            if (unit.getName().equals(group.getName())) {
                return unit;
            }
        }
        return null;
    }

    // Виведення всіх оцінок студентів певної групи
    public List<Exam> findRatingsByGroup(Group group) {
        List<Exam> result = new ArrayList<>();
        // Якщо група не зареєстрована в глобальному списку
        if (findGroup(group) == null) {
            System.out.println("Group \"" + group.getName() + "\" is not registered in the global list!");
            return result;
        }

        // Біг по списку оцінок з іспитів
        for (Exam exam : exams) {
            // Біг по списку груп цього предмета, за яким був іспит у студента
            for (Group unit : exam.getSubject().getGroups()) {
                // Якщо тут є группа з якої ведеться складання списку оцінок
                if (unit.getName().equals(group.getName())) {
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
    public void removeGroup(Group group) {
        int index = -1;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(group.getName())) {
                index = i;
                break;
            }
        }
        groups.remove(index);
    }

    // Зміна оцінки з предмета та ПІБ конкретного студента. (Зрозумів це як: Зміна оцінки з предмета по ПІБ конкретного студента)
    public void setRatingWithSubjectByStudent(Subject subject, Student student, int rating) {
        for (Exam exam : exams) {
            if (exam.getSubject().getName().equals(subject.getName()) &
                exam.getStudent().equals(student)
            ) {
                exam.setRating(rating);
            }
        }
    }
}
