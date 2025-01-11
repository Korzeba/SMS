public class Student
{
    private String name;
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    private Double grade;
    private String studentID ;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getGrade() {
        return grade;
    }

    public String getStudentID() {
        return studentID;
    }


}
