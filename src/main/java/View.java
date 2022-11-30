import java.util.Scanner;

// Клас представлення інтерфейсу користувача
public class View {
    private final static Scanner scanner = new Scanner (System.in);

    // Метод виведення меню
    public void printMenuData(){
        System.out.println("Please select command:");
        System.out.println("1 - add new data");
        System.out.println("2 - find data");
        System.out.println("3 - update data");
        System.out.println("4 - delete data");
        System.out.println("5 - exit");
    }

    // Метод читання команди користувача
    public Integer readCommand(){
        return scanner.nextInt();
    }

    // Метод виведення будь-якого вхідного рядка
    public void printIncomingString(String incomingString){
        System.out.println(incomingString);
    }

    // Метод читання вхідного рядка для даних
    public String readStringData(){
        System.out.println("Enter string data...");
        String incomingString = scanner.next(); // Ввести дані типу "Рядок"
        return incomingString;
    }

    // Метод читання вхідного числа для даних
    public Integer readIntData()	{
        System.out.println("Enter number data...");
        Integer incomingNumber = scanner.nextInt(); // Ввести дані типу "Ціле число"
        return incomingNumber;
    }

    // Метод виведення даних
    public void printData(Data data) {
        System.out.println("Data is {stringData: "
                + data.getAnyStringData()
                + " intData: "
                + data.getAnyIntData() + "}");
    }
}
