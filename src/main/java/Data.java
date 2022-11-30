package mvc.model;

// Клас, для зберігання данних
public class Data {
    // Реєстрація змінних для данних в полі класу
    private String anyStringData;
    private Integer anyIntData;

    // Метод, отримати дянні з змінної під назвою anyStringData
    public String getAnyStringData() {
        return anyStringData;
    }

    // Метод, задання значення для змінної під іменем anyStringData
    public void setAnyStringData(String anyData) {
        this.anyStringData = anyData;
    }

    // Метод, отримати дянні з змінної під назвою anyIntData
    public Integer getAnyIntData() {
        return anyIntData;
    }

    // Метод, задання значення для змінної під іменем anyIntData
    public void setAnyIntData(Integer anyIntData) {
        this.anyIntData = anyIntData;
    }
}
