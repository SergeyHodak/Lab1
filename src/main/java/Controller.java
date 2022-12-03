import java.util.List;
import java.util.Scanner;

// Клас контролера - зв'язок моделі та інтерфейсу системи
public class Controller {
    public void init() {
        Scanner scanner = new Scanner(System.in); // Сканер
        View view = new View(scanner); // Візуалка
        Model model = new Model(); // Модель
        boolean perform = true;
        while(perform) {
            view.printMenu(); // Вивести меню
            int command = view.readCommand(); // Отримати команду
            switch (command) {
                case 13 -> { // Вихід
                    perform = false; // Реєстрація зупинення циклу
                }
                case 1 -> { // Додати новий предмет
                    String subjectName = view.readStringData("subjectName"); // Отримати від користівача рядок
                    String result = model.createSubject(subjectName); // Виконати реєстрацію предмета
                    view.printIncomingString(result); // Оголосити результат виконання команди
                }
                case 2 -> { // Додати нову групу
                    String groupName = view.readStringData("groupName");// Отримати від користівача рядок
                    String result = model.createGroup(groupName);// Виконати реєстрацію групи
                    view.printIncomingString(result); // Оголосити результат виконання команди
                }
                case 3 -> { // Додати нового студента
                    Student student = getStudent(view); // Студент
                    String result = model.createStudent(student); // Реєстрація
                    view.printIncomingString(result); // Результат
                }
                case 4 -> { // Додати студента в групу
                    Student student = getStudent(view); // Студент
                    String groupName = view.readStringData("groupName"); // Група
                    String result = model.addStudentInGroup(groupName, student); // Реєстрація
                    view.printIncomingString(result); // Результат
                }
                case 5 -> { // Додати групу в предмет
                    String subjectName = view.readStringData("subjectName"); // Предмет
                    String groupName = view.readStringData("groupName"); // Група
                    String result = model.addGroupInSubject(subjectName, groupName); // Реєстрація
                    view.printIncomingString(result); // Результат
                }
                case 6 -> { // Знайти предмети по студенту
                    Student student = getStudent(view); // Студент
                    List<String> result = model.findSubjectsByStudent(student); // Пошук
                    view.printSubjectNames(result); // Друкувати результат
                }
                case 7 -> { // Знайти групи по студенту
                    Student student = getStudent(view); // Студент
                    List<Group> result = model.findGroupsByStudent(student); // Пошук
                    view.printGroupNames(result); // Друкувати результат
                }
                case 8 -> { // Знайти групи по предмету
                    String subjectName = view.readStringData("subjectName"); // Предмет
                    List<Group> result = model.findGroupsBySubject(subjectName); // Пошук
                    view.printGroupNames(result); // Друкувати результат
                }
                case 9 -> { // Добати оцінку по ключу (студент та предмет)
                    Student student = getStudent(view); // Студент
                    String subjectName = view.readStringData("subjectName"); // Предмет
                    int rating = view.readIntData("rating"); // Оцінка
                    String result = model.createRating(student, subjectName, rating); // Реєстрація
                    view.printIncomingString(result); // Результат
                }
                case 10 -> { // Отримати всі оцінки за групою
                    String groupName = view.readStringData("groupName"); // Група
                    List<Exam> result = model.findRatingsByGroup(groupName); // Пошук
                    view.printAllRatings(result); // Друкувати результат
                }
                case 11 -> { // Видалити групу
                    String groupName = view.readStringData("groupName"); // Група
                    String result = model.removeGroup(groupName);// Видалення
                    view.printIncomingString(result); // Результат
                }
                case 12 -> { // Зіміна оцінки
                    String subjectName = view.readStringData("subjectName"); // Предмет
                    Student student = getStudent(view); // Студент
                    int rating = view.readIntData("rating"); // Оцінка
                    String result = model.setRatingWithSubjectByStudent(subjectName, student, rating);// Оновлення
                    view.printIncomingString(result); // Результат
                }
                default -> view.printIncomingString("This team is not registered");
            }
        }
        scanner.close();
    }

    private Student getStudent(View view) {
        Student student = new Student(); // Екземпляр
        student.setLastName(view.readStringData("lastName")); // Прізвище
        student.setFirstName(view.readStringData("firstName")); // Ім'я
        student.setMiddleName(view.readStringData("middleName")); // По батькові
        return student;
    }
}
