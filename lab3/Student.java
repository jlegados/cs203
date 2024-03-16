public class Student {
    private static String name;
    private static int age;
    private static double gpa;

    public Student(String name, int age, double gpa) {
        Student.name = name;
        Student.age = age;
        Student.gpa = gpa;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public static String getStudentInfo(Student student) {
        return name + " " + age + " " + gpa;
    }

    public static String getLetterGrade(Student student) {
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