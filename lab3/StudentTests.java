import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTests {
    @Test
    public void GetStudentInfoTest(){
        Student student = new Student("Sehun", 17, 3.0);
        String expected = "Name: Sehun, Age:17, GPA:3.0";
        Assertions.assertEquals(expected, StudentFunctions.getStudentInfo(student));
    }
    @Test
    public void GetStudentInfoTest2(){
        Student student = new Student("Baekhyun", 18, 4.0);
        String expected = "Name: Baekhyun, Age:18, GPA:4.0";
        Assertions.assertEquals(expected, StudentFunctions.getStudentInfo(student));
    }
    @Test
    public void GetStudentInfoTest3(){
        Student student = new Student("Jongin", 17, 2.0);
        String expected = "Jongin 17 2.0";
        Assertions.assertEquals(expected, Student.getStudentInfo(student));
    }
    @Test
    public void GetStudentInfoTest4(){
        Student student = new Student("Kyungsoo", 18, 1.0);
        String expected = "Kyungsoo 18 1.0";
        Assertions.assertEquals(expected, Student.getStudentInfo(student));
    }
    @Test
    public void GetLetterGradeTest() {
        Student student = new Student("Minseok", 18, 4.0 );
                Assertions.assertEquals("A", StudentFunctions.getLetterGrade(student));
    }
    @Test
    public void GetLetterGradeTest2(){
        Student student = new Student("Chanyeol",17, 3.0);
        Assertions.assertEquals("B", StudentFunctions.getLetterGrade(student));
    }
    @Test
    public void GetLetterGradeTest3(){
        Student student = new Student("Yixing",18, 2.0 );
        Assertions.assertEquals("C", Student.getLetterGrade(student));
    }
    @Test
    public void GetLetterGradeTest4() {
        Student student = new Student("Junmyeon", 17, 1.0);
        Assertions.assertEquals("D", Student.getLetterGrade(student));
    }

}