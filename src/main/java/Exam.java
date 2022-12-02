// Клас, для зберігання інформації з іспиту студента
public class Exam {
    private Subject subject; // Предмет (перший ключ)
    private Student student; // Студент (другий ключ)
    private int rating; // Оцінка (значення)

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
