public class Student {
    private String name;       // Imię studenta
    private int age;           // Wiek studenta
    private double grade;      // Ocena studenta
    private String studentID;  // Unikalny identyfikator studenta

    // Konstruktor do inicjalizacji atrybutów
    public Student(String studentID, String name, int age, double grade) {
        setStudentID(studentID);
        setName(name);
        setAge(age);
        setGrade(grade);
    }

    // Getter i Setter dla name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Imię nie może być puste.");
        }
        this.name = name;
    }

    // Getter i Setter dla age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Wiek musi być liczbą dodatnią.");
        }
        this.age = age;
    }

    // Getter i Setter dla grade
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Ocena musi być w zakresie od 0,0 do 100,0.");
        }
        this.grade = grade;
    }

    // Getter i Setter dla studentID
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if (studentID == null || studentID.trim().isEmpty()) {
            throw new IllegalArgumentException("ID studenta nie może być puste.");
        }
        this.studentID = studentID;
    }

    // Metoda displayInfo() do wydrukowania szczegółów studenta
    public void displayInfo() {
        System.out.println("Szczegóły Studenta:");
        System.out.println("ID: " + studentID);
        System.out.println("Imię: " + name);
        System.out.println("Wiek: " + age);
        System.out.println("Ocena: " + grade);
    }
}
