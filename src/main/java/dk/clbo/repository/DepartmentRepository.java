package dk.clbo.repository;

import dk.clbo.model.Department;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.Class.*;

public class DepartmentRepository {
    public ArrayList<Department> getAllDepartments(){
        ArrayList<Department> allDepartments = new ArrayList<Department>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }


        try {
            //Connection conn = DriverManager.getConnection("jdbc:mysql://54.90.58.194:3306/my_company", "remote", "1234");
             Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/mycompany3", "mycompany3", "Oy6ab79p??8i");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departments");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Department tmp = new Department(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                allDepartments.add(tmp);
            }
        }
        catch(SQLException e){
            allDepartments.add(new Department(10, "Somtheng went wrong", e.getMessage() ));
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
        return allDepartments;
    }
    public void create(Department dept){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            //Connection conn = DriverManager.getConnection("jdbc:mysql://54.90.58.194:3306/my_company", "remote", "1234");
            Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/mycompany3", "mycompany3", "Oy6ab79p??8i");
            System.out.println(dept.getDeptName() + ", " + dept.getLocation());
            String SQL = "INSERT INTO departments (deptname, location) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());

            ps.executeUpdate();


        }
        catch(SQLException e){
           // allDepartments.add(new Department(10, "Somtheng went wrong", e.getMessage() ));
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }
}