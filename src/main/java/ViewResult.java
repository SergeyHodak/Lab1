import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас представлення інтерфейсу користувача
public class ViewResult {
    private final static Scanner scanner = new Scanner (System.in);

    // Метод виведення меню
    public void printMenu() {
        System.out.println("Please select command:"); // Будьласка виберіть команду
        System.out.println("1 - add new subject"); // Додати новий предмет
        System.out.println("2 - add new group"); // Додати нову групу
        System.out.println("3 - add new student"); // Додати нового студента
        System.out.println("4 - add student in group"); // Додати студента в групу
        System.out.println("5 - add group in subject"); // Додати групу в предмет
        System.out.println("6 - find subjects by student"); // Знайти предмети по студенту
        System.out.println("7 - find groups by student"); // Знайти групи по студенту
        System.out.println("8 - find groups by subject"); // Знайти групи по предмету
        System.out.println("9 - add rating by key (student and subject)"); // Добати оцінку по ключу (студент та предмет)
        System.out.println("10 - get all ratings by group"); // Отримати всі оцінки за групою
        System.out.println("11 - delete group"); // Видалити групу
        System.out.println("12 - update rating"); // Зіміна оцінки
        System.out.println("13 - exit"); // Завершення сеансу
    }

    // Метод читання команди користувача
    public int readCommand() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception ex) {
                // Ви зробили помилку під час введення цілого числа, спробуйте ще раз!
                System.out.println("You made a mistake when entering an integer, please try again!");
            }
        }
    }

    // Метод виведення будь-якого вхідного рядка
    public void printIncomingString(String incomingString){
        System.out.println(incomingString);
    }

    // Метод читання вхідного рядка для даних
    public String readStringData(String field){
        while (true) {
            try {
                System.out.println("Enter text for the field: " + field); // Введіть текст для поля: назва поля
                return scanner.next();
            } catch (Exception ex) {
                // Ви зробили помилку, під час введення даних типу рядок! Спробуйте ще раз.
                System.out.println("You made a mistake while entering string type data! Try again.");
            }
        }
    }

    // Метод читання вхідного числа для даних
    public int readIntData(String field) {
        while (true) {
            try {
                System.out.println("Enter number for the field: " + field); // Введіть число для поля: назва поля
                return scanner.nextInt();
            } catch (Exception ex) {
                // Ви зробили помилку під час введення цілого числа, спробуйте ще раз!
                System.out.println("You made a mistake when entering an integer, please try again!");
            }
        }
    }

    // Метод виведення предметів по студенту
    public void printSubjectNames(List<String> subjectNames) {
        System.out.println("Result:");
        if (subjectNames.size() == 0) {
            System.out.println("Subjects not found!");
        } else {
            for (String subjectName : subjectNames) {
                System.out.println(subjectName);
            }
        }
    }

    // Метод виведення груп по (студенту або предмету)
    public void printGroupNames(List<Group> groups) {
        System.out.println("Result:");
        if (groups.size() == 0) {
            System.out.println("Groups not found!");
        } else {
            for (Group group : groups) {
                System.out.println(group.getName());
            }
        }
    }

    // Метод виведення всіх оцінок студентів певної групи
    public void printAllRatings(List<Exam> exams) {
        System.out.println("Result:");
        if (exams.size() == 0) {
            System.out.println("No ratings were found for the group!");
        } else {
            // Формуємо відсортування по предметам
            List<String> subjects = new ArrayList<>();
            for (Exam exam : exams) {
                if (!subjects.contains(exam.getSubject().getName())) {
                    subjects.add(exam.getSubject().getName());
                }
            }

            // Виводимо
            for (String subject : subjects) {
                System.out.println(subject + ":");
                for (Exam exam : exams) {
                    if (exam.getSubject().getName().equals(subject)) {
                        Student student = exam.getStudent();
                        System.out.println("   " +
                                student.getLastName() + " " +
                                student.getFirstName() + " " +
                                student.getMiddleName() + " - " +
                                exam.getRating());
                    }
                }
            }
        }
    }
}
