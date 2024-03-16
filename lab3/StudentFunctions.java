public class StudentFunctions {
    public static String getStudentInfo(Student student) {
        return "Name: " + student.getName() + ", Age:" + student.getAge()
                + ", GPA:" + student.getGpa();
    }

    public static String getLetterGrade(Student student) {
        double gpa = student.getGpa();
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.0) {
            return "B";
        } else if (gpa >= 2.0) {
            return "C";
        } else if (gpa >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }
}
