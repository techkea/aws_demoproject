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
            Connection conn = DriverManager.getConnection("jdbc:mysql://54.90.58.194:3306/my_company", "****", "****");
            // Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/mycompany3", "mycompany3", "****");
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
}