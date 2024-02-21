import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Hospital_Management_System {
Scanner sc=new Scanner(System.in);
    public void createtable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twinkle", "root", "Twinkle@123");
            String sql = "CREATE TABLE IF NOT EXISTS Hospital (\n" +
                    "    id INT,\n" +
                    "    name VARCHAR(255),\n" +
                    "    address VARCHAR(255),\n" +
                    "    phone VARCHAR(255),\n" +
                    "    roomnumber INT\n" +
                    ");\n";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void addpatient() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twinkle", "root", "Twinkle@123");

            System.out.println("Enter Patient ID: ");
            int id = sc.nextInt();

            sc.nextLine();
            System.out.println("Enter Patient name: ");
            String name = sc.nextLine();

            System.out.println("Enter Patient Address: ");
            String address = sc.nextLine();

            System.out.println("Enter Patient phone number: ");
            String phone = sc.nextLine();

            System.out.println("Enter Patient Room number: ");
            int room = sc.nextInt();

            String sql = "INSERT INTO Hospital VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setInt(5, room);

            int status = ps.executeUpdate();

            if ( status > 0)
            {
                System.out.println("Your data has been successfully inserted.");
            }
            else
            {
                System.out.println("Your data has not been successfully inserted. Please try again.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printdetails()  {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twinkle", "root", "Twinkle@123");

            String sql = "select * from Hospital";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int roomnumber  = rs.getInt("roomnumber");

                System.out.println("-----------------------------");
                System.out.println("ID IS: " + id);
                System.out.println("NAME IS: " + name);
                System.out.println("ADDRESS IS: " + address);
                System.out.println("PHONE IS: " + phone);
                System.out.println("ROOM NUMBER IS: " + roomnumber);

                System.out.println();
                System.out.println("-----------------------------");
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void updatename() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twinkle", "root", "Twinkle@123");
            String sql = "UPDATE Hospital SET name=?, address=?, phone=?, roomnumber=? WHERE id=?";

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter patient new name: ");
            String name = sc.nextLine();

            System.out.println("Enter patient new address: ");
            String address = sc.nextLine();

            System.out.println("Enter patient new phone number: ");
            String phone = sc.nextLine();

            System.out.println("Enter patient new room number: ");
            int roomnumber = sc.nextInt();

            System.out.println("Enter your id which you want to update: ");
            int id = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, roomnumber);
            ps.setInt(5, id);

            int st = ps.executeUpdate();

            if (st > 0) {
                System.out.println("Your data has been successfully updated.");
            } else {
                System.out.println("Your data was not updated.");
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void deletename()
    {
          try{
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twinkle", "root", "Twinkle@123");

              String sql = "Delete from Hospital where ID=?";
              Scanner sc = new Scanner(System.in);
              System.out.println("Enter patient id which you want to delete");
              int id = sc.nextInt();

              PreparedStatement ps = con.prepareStatement(sql);
              ps.setInt(1, id);

              int status = ps.executeUpdate();
              if (status > 0)
              {
                  System.out.println("patient data has been deleted");
              }
              else
              {
                  System.out.println("patient data has not been deleted");
              }

          }
          catch (Exception e)
          {
              System.out.println(e);
          }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital_Management_System ob = new Hospital_Management_System();
        ob.createtable();
       int choice;
        do {

            System.out.println("-*-*-*-*-*-*-*-*Hospital Management Data-*-*-*-*-*-*-*-*");
            System.out.println("1.addpatient");
            System.out.println("2. printdetails");
            System.out.println("3.updatename");
            System.out.println("4.deletename");
            System.out.println("5.exit");

            System.out.println("Enter your choice");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ob.addpatient();
                    break;

                case 2:
                    ob.printdetails();
                    break;

                case 3:
                    ob.updatename();
                    break;

                case 4:
                    ob.deletename();
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
        while (choice!=5);
    }
}