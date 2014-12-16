package objects;

public class Student implements Comparable<Student> {
    private int studentId;
    private String studentGroup;
    private String studentName;

    public Student(int studentId, String studentGroup, String studentName) {
        this.studentId = studentId;
        this.studentGroup = studentGroup;
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public int compareTo(Student o) {
        int id1 = this.getStudentId();
        int id2 = o.getStudentId();
        return id1 - id2;
    }
}
