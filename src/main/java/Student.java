// Клас, для зберігання персональних данних студента
public class Student {
    private String lastName; // Прізвище
    private String firstName; // Ім'я
    private String middleName; // По батькові

    // Метод отримання значення з поля lastName
    public String getLastName() {
        return lastName;
    }

    // Метод задання нового значення для поля lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Метод отримання значення з поля firstName
    public String getFirstName() {
        return firstName;
    }

    // Метод задання нового значення для поля firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Метод отримання значення з поля middleName
    public String getMiddleName() {
        return middleName;
    }

    // Метод задання нового значення для поля middleName
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Для порівнювань
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!getLastName().equals(student.getLastName())) return false;
        if (!getFirstName().equals(student.getFirstName())) return false;
        return getMiddleName().equals(student.getMiddleName());
    }
}
