// Клас контролера - зв'язок моделі та інтерфейсу системи
public class Controller {
    // Єдиний метод в класі
    public void initController(){

        View view = new View(); // Створення виду
        Model model = new Model();// Створення моделі

        while(true) { // Безкінечний цикл вайл
            view.printMenuData(); // ....виводити меню.
            Integer command = view.readCommand();// ...зчитувати команду...

            if(command.equals(5)) { // поки користувач не введе (вихід, команда 5) (або не відключать світло :) )
                break; // Вихід з безкінечного циклу
            } else if(command.equals(1)){ // якщо користувач ввів команду додавання
                view.printIncomingString("Enter data to add:"); //виводимо переважний рядок
                model.create(view.readIntData(), view.readStringData()); //зчитуємо та створюємо новий запис
                view.printIncomingString("Data was added");
            } else if(command.equals(2)){ // якщо користувач ввів команду пошуку
                view.printIncomingString("Enter data to find:");
                Data data = model.find(view.readStringData()); //Шукаємо дані
                if(data != null) { //якщо знайшли
                    view.printData(data); //виводимо їх
                } else {
                    view.printIncomingString("Nothing to show...");//інакше говоримо, що нічого не знайшли
                }
            } else if(command.equals(3)) { //якщо користувач ввів команду оновлення
                view.printIncomingString("Enter data to update:");
                view.printIncomingString("Enter old string: ");
                Data newData = model.update(view.readStringData(), //зчитуємо дані для пошуку та шукаємо
                        view.readStringData(),
                        view.readIntData());
                view.printIncomingString("result of updating:");
                if(newData != null) { //якщо знайшли
                    view.printData(newData); //виводимо що знайшли
                } else {
                    view.printIncomingString("Nothing to update");
                }
            } else if (command.equals(4)) { //якщо користувач ввів команду видалення
                view.printIncomingString("Enter data to delete:");
                int index = model.delete(view.readStringData()); //зчитуємо та видаляємо дані
                if(index != -1) {
                    view.printIncomingString("Data in index" + index + " was deleted..."); //якщо видалили - виводимо віддалений індекс
                } else {
                    view.printIncomingString("Nothing to delete");
                }
            } else { // повідомляємо що немає такої команди
                view.printIncomingString("Wrong command");
            }
        }
    }
}
