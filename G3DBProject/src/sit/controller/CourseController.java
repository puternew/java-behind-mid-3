/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import sit.db.ConnectionManager;
import sit.model.Course;


public class CourseController {
    private Connection con;

    public CourseController(String username,String psw) 
            throws ClassNotFoundException, SQLException {
        con=ConnectionManager.createConnection
        ("jdbc:derby://localhost:1527/course", username, psw);
    }
    public void executeSQLFromUser(String sql) throws SQLException{
        Statement stmt = con.createStatement();
        boolean hasResultset = stmt.execute(sql);
        if (hasResultset) {
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {            
            String cId = rs.getString("courseId");
            String cName = rs.getString("courseName");
            Course cTmp = new Course(cId, cName);
            System.out.println(cTmp);
       
        }
       }
        else  {
        int updatedRecs = stmt.getUpdateCount();
            System.out.println(updatedRecs+"Updated Recs");
        }
    }
    
    public ArrayList<Course> selectCourse()throws SQLException{
        ArrayList<Course> courseList = new ArrayList<Course>();
        
        Statement stmt = con.createStatement();
        String sql = "Select * from course";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {            
            String cId = rs.getString("courseId");
            String cName = rs.getString("courseName");
            Course cTmp = new Course(cId, cName);
            System.out.println(cTmp);
            
            courseList.add(cTmp);
        }
        return courseList;
    }
            
    public void createCourseTable() throws SQLException{
        String sql="create table course"
                + "(courseId varchar(6),"
                + "courseName varchar(50),"
                + "primary key(courseId))";
        System.out.println(sql);
        
        Statement stmt=con.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("create table course successfully");
        
    }
    public void dropCourseTable() throws SQLException{
        String sql="drop table course";
        System.out.println(sql);
        
        Statement stmt=con.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("drop table course successfully");
        
    }
    public int insertCourse(Course subject) throws SQLException{
        String sql="insert into course(courseId, courseName) values ("+
                "'"+subject.getCourseId()+"', '"+subject.getCourseName()+"')";
        System.out.println(sql);
        
        Statement stmt=con.createStatement();
        int insertedRec=stmt.executeUpdate(sql);
        System.out.println(insertedRec+" inserted successfully");
        return insertedRec;
    }
    
    public void closeCourseConnection() 
            throws SQLException{
        ConnectionManager.closeConnection(con);
    }
    
    
    public int insertFromFile(String fileName) throws FileNotFoundException,SQLException{
        Scanner scFile = new Scanner(new File(fileName));
        scFile.useDelimiter(",");
        String sql = "insert into course(courseId, courseName) values (?,?)";
        int insertedRecs = 0;
        
        PreparedStatement ppstm = con.prepareStatement(sql);
        while (scFile.hasNext()) {            
//            System.out.println(scFile.next());
            String cId = scFile.next().trim();
            String cName = scFile.next().trim();       
            ppstm.setString(1, cId);
            ppstm.setString(2, cName);
            
            insertedRecs += ppstm.executeUpdate();
       }
        return insertedRecs;
    }
    
    public int truncateCourse() throws SQLException{
        Statement statement = con.createStatement();
        return statement.executeUpdate("TRUNCATE TABLE COURSE");
    }
    
    
}
