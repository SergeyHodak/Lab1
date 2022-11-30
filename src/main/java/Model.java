import java.util.ArrayList;
import java.util.List;

// Клас моделі - реалізація основної логіки роботи системи
public class Model {
    private List<Data> dataList = new ArrayList<>(); // глобальне поле збереження даних

    // Метод створення нового об'єкта
    public void create(Integer incomingNumber, String incomingString) {
        Data data = new Data();
        data.setAnyIntData(incomingNumber);
        data.setAnyStringData(incomingString);
        dataList.add(data);
    }

    // Метод пошуку об'єкта
    public Data find(String searchString) {
        for(Data searchData : dataList) {
            if(searchData.getAnyStringData().equals(searchString)) {
                return searchData;
            }
        }
        return null;
    }

    // Метод оновлення об'єкта
    public Data update(String oldStringData, String newStringData, Integer newIntData) {
        Data data = find(oldStringData); // Знаходимо старі дані в списку данних
        if(data != null){ // Якщо знайшли
            data.setAnyIntData(newIntData); // Міняємо дані на введені
            data.setAnyStringData(newStringData);
            return data;
        }
        return null;
    }

    // Метод видалення об'єкта
    public int delete(String stringDataToRemove) {
        // Задамо змінну, яка зберіга індекс за яким видалати данні
        int indexToRemove = -1; // (заздалегіть неіснуючий індекс)
        for(int i = 0; i < dataList.size(); i++) { // Перебираємо дані у списку
            if(dataList.get(i).getAnyStringData().equals(stringDataToRemove)) { // Якщо знайшли відповідні
                indexToRemove = i; // Запам'ятовуємо індекс для видалення
                break; // Виходимо з циклу
            }
        }
        if(indexToRemove != -1) { // Якщо знайшли відповідні дані для видалення
            dataList.remove(indexToRemove); // Виконуємо видалення
        }
        return indexToRemove;
    }
}
