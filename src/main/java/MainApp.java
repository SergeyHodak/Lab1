import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас, для обробки даних
public class MainApp {
    // Метод - запускалка(стандартна)
    public static void main(String[] args) {
        /* ------- примір перший -------
        * //Ініціалізація сканера
        * Scanner scanner = new Scanner(System.in);
        * System.out.println("Enter any string...");
        * String incomingString = scanner.nextLine(); // Затребувати ввести данні, присвоїти змінній типу "Рядок"
        * System.out.println("Your string is: " + incomingString); // Вивести введені дані в консоль
        */

        // ------- примір другий -------
        // Створення списку для зберігання введених даних
        List<Data> dataList = new ArrayList<>();
        // Ініціалізація сканера
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string data...");
        String incomingString = scanner.next(); // Введіть в консолі текст, щоб присвоїти в цю змінну
        System.out.println("Enter number data...");
        Integer incomingNumber = scanner.nextInt(); // Введіть в консолі ціле число, для цієї змінної

        // CREATE
        // Створення змінної з типом Data
        Data data = new Data();
        // Присвоїти в ці змінну, полю типа рядок, введений текс з консолі раніше
        data.setAnyIntData(incomingNumber);
        // Присвоїти в ці змінну, полю типа цілеЧисло, введене число з консолі раніше
        data.setAnyStringData(incomingString);
        dataList.add(data); // Додати цю заповнену змінну до масиву

        System.out.println("Enter search data...");
        String searchString = scanner.next(); // Ввести в консолі рядок, ьа зберегти його до цієї змінної

        //READ
        // Пробігтись по списку з данними
        for(Data searchData : dataList) {
            // Якщо позиція із спику, має текстову змінну, яка рівна останньому тексту введеному в консольці
            if(searchData.getAnyStringData().equals(searchString)) {
                System.out.println("Result of search is: stringData: "
                        + searchData.getAnyStringData()
                        + " intData: "
                        + searchData.getAnyIntData()); // Друк в консольку
                break; // Преривання циклу фор
            }
        }

        //UPDATE
        System.out.println("Enter data to update:");
        System.out.println("Old String:");
        String oldStringData = scanner.next(); // Ввести рядок, який вже є в списку (той який хочемо змінити)
        System.out.println("String:");
        String newStringData = scanner.next(); // Ввести рядок, яким буде попередній замінено
        System.out.println("Integer:");
        Integer newIntData = scanner.nextInt(); // Ввести нове ціле число

        // Пробігтись по списку з данними
        for(Data searchData : dataList) {
            // Якщо в позиції з списку, текстова змінна схожа з рядком який потрібно оновити
            if(searchData.getAnyStringData().equals(oldStringData)) {
                // Встановлюємо нові данні для цілого числа
                searchData.setAnyIntData(newIntData);
                // Встановлюємо новий текст, затираючи старий
                searchData.setAnyStringData(newStringData);
                break; // Преривання циклу фор (вихід з нього)
            }
        }

        //DELETE
        System.out.println("Enter data to delete:");
        System.out.println("search String:");
        // Ввести в консольці текстовий рядок, який необхідно видалити
        String stringDataToRemove = scanner.next();

        // Індекс(позиція) для видалення
        Integer indexToRemove = null;
        // Зробити циклічних кроків, які за кількістю відповідають розміру списку данних
        for(int i = 0; i < dataList.size(); i++) {
            // Якщо за номером кроку витягнути з списку данних такстову змінну, і вона буде рівна видаляємому тексту
            if(dataList.get(i).getAnyStringData().equals(stringDataToRemove)) {
                // Зареєструвати співпадання, як позиція (індекс)
                indexToRemove = i;
                break; // Завершити цикл фор
            }
        }
        // Видалити один елемнт типу данних, за індексом в списку данних
        dataList.remove(indexToRemove);
    }
}
