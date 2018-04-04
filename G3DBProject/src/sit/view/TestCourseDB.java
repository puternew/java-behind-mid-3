
package sit.view;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import sit.controller.CourseController;
import sit.model.Course;
public class TestCourseDB {
    public static void main(String[] args) {
        try{
        CourseController courseCtrl = new CourseController("sit","sit");
//        courseCtrl.dropCourseTable();
//        courseCtrl.createCourseTable();
        

        Course c1 =new Course("int105","Computer programming II");
//        courseCtrl.insertCourse(c1);
//        int insertedRed = courseCtrl.insertCourse(c1);

//        courseCtrl.truncateCourse();
//        courseCtrl.insertFromFile("courseList.txt");
        
        ArrayList<Course> cList = courseCtrl.selectCourse();    
            for (int i = 0; i < cList.size(); i++) {
                System.out.println(cList.get(i));
                String cId = (cList.get(i)).getCourseId();
                if (cId.equalsIgnoreCase("GEN111")) {
                    System.out.println("Strong man");
                }
            }
        courseCtrl.closeCourseConnection();
        
        }
        
//        catch(FileNotFoundException fnfe){
//        System.out.println(fnfe);
//        }
        catch(ClassNotFoundException cnf){
            System.out.println(cnf);
        } 
        catch(SQLException sqle){
            System.out.println(sqle);
        }
            
    }
}
