public class Student {
    private String name;       // Imię studenta
    private int age;           // Wiek studenta
    private double grade;      // Ocena studenta
    private String studentID;  // Unikalny identyfikator studenta

    /*
    Sprawdzenie czy wszstkie potrzebne dane nie są pustymi ciągami znaków oraz czy spełniają podane w setterach warunki.
    Potem mamy podstawowe gettery oraz settery z warunkami logicznymi dla danych.
    Gdy wprowadzone dane nie zgaadzają sie z warunkami to jest rzucany IllegalArgumentException.
     */

    public Student(String studentID, String name, int age, double grade)
    {
        setStudentID(studentID);
        setName(name);
        setAge(age);
        setGrade(grade);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot have negative value.");
        }
        this.age = age;
    }

    public double getGrade()
    {
        return grade;
    }

    public void setGrade(double grade)
    {
        if (grade < 0.0 || grade > 100.0)
        {
            throw new IllegalArgumentException("Enter a grade between 0.0 and 100.0");
        }
        this.grade = grade;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public void setStudentID(String studentID)
    {
        if (studentID == null || studentID.trim().isEmpty())
        {
            throw new IllegalArgumentException("StudentID Name cannot be empty.");
        }
        this.studentID = studentID;
    }

}
