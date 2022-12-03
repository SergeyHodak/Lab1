import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас представлення інтерфейсу користувача
public class View {
    private final Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    // Метод виведення меню
    public void printMenu() {
        printIncomingString("Please select command:"); // Будьласка виберіть команду
        printIncomingString("1 - add new subject"); // Додати новий предмет
        printIncomingString("2 - add new group"); // Додати нову групу
        printIncomingString("3 - add new student"); // Додати нового студента
        printIncomingString("4 - add student in group"); // Додати студента в групу
        printIncomingString("5 - add group in subject"); // Додати групу в предмет
        printIncomingString("6 - find subjects by student"); // Знайти предмети по студенту
        printIncomingString("7 - find groups by student"); // Знайти групи по студенту
        printIncomingString("8 - find groups by subject"); // Знайти групи по предмету
        printIncomingString("9 - add rating by key (student and subject)"); // Добати оцінку по ключу (студент та предмет)
        printIncomingString("10 - get all ratings by group"); // Отримати всі оцінки за групою
        printIncomingString("11 - delete group"); // Видалити групу
        printIncomingString("12 - update rating"); // Зіміна оцінки
        printIncomingString("13 - exit"); // Завершення сеансу
    }

    // Метод читання команди користувача
    public int readCommand() {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception ex) {
                printIncomingString("Enter a valid integer");
            }
        }
        return result;
    }

    // Метод виведення будь-якого вхідного рядка
    public void printIncomingString(String incomingString){
        System.out.println(incomingString);
    }

    // Метод читання вхідного рядка для даних
    public String readStringData(String field) {
        printIncomingString("Enter text for the field: " + field); // Введіть текст для поля: назва поля
        String result;
        while (true) {
            try {
                result = scanner.nextLine();
                if (result.length() != 0) {
                    break;
                } else {
                    printIncomingString("Must not be null");
                }
            } catch (Exception ex) {
                printIncomingString("Enter the text");
            }
        }
        return result;
    }

    // Метод читання вхідного числа для даних
    public int readIntData(String field) {
        printIncomingString("Enter number for the field: " + field); // Введіть число для поля: назва поля
        int result;
        while (true) {
            try {
                result = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception ex) {
                printIncomingString("Enter a valid integer");
            }
        }
        return result;
    }

    // Метод виведення предметів по студенту
    public void printSubjectNames(List<String> subjectNames) {
        if (subjectNames.size() == 0) {
            printIncomingString("Subjects not found!");
        } else {
            for (String subjectName : subjectNames) {
                printIncomingString(subjectName);
            }
        }
    }

    // Метод виведення груп по (студенту або предмету)
    public void printGroupNames(List<Group> groups) {
        printIncomingString("Result:");
        if (groups.size() == 0) {
            printIncomingString("Groups not found!");
        } else {
            for (Group group : groups) {
                printIncomingString(group.getName());
            }
        }
    }

    // Метод виведення всіх оцінок студентів певної групи
    public void printAllRatings(List<Exam> exams) {
        printIncomingString("Result:");
        if (exams.size() == 0) {
            printIncomingString("No ratings were found for the group!");
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
                printIncomingString(subject + ":");
                for (Exam exam : exams) {
                    if (exam.getSubject().getName().equals(subject)) {
                        Student student = exam.getStudent();
                        printIncomingString("   " +
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
