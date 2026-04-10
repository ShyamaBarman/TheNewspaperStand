import java.sql.*;
import java.util.Scanner;
public class Main {
 private static Scanner sc = new Scanner(System.in);
 public static void main(String[] args) {
 while (true) {
 System.out.println("\n===== Newspaper Subscription System =====");
 System.out.println("1. Register Subscriber");
 System.out.println("2. Record Payment");
 System.out.println("3. Mark Delivery");
 System.out.println("4. View Unpaid Subscribers");
 System.out.println("5. Exit");
 System.out.print("Enter choice: ");
 
 try {
 int choice = sc.nextInt();
 sc.nextLine(); // Clear buffer
 switch (choice) {
 case 1: registerSubscriber(); break;
 case 2: recordPayment(); break;
 case 3: markDelivery(); break;
 case 4: viewUnpaid(); break;
 case 5: System.out.println("Exiting System..."); return;
 default: System.out.println("Invalid choice. Try again.");
    }
 } catch (Exception e) {
 System.out.println("Invalid input! Please enter a number.");
 sc.nextLine(); // Clear bad input
      }
   }
 }
 private static void registerSubscriber() {
 String sql = "INSERT INTO subscribers (name, address, paper_name, monthly_rate) VALUES 
(?, ?, ?, ?)";
 try (Connection conn = DBConnection.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
 System.out.print("Enter Name: "); pstmt.setString(1, sc.nextLine());
 System.out.print("Enter Address: "); pstmt.setString(2, sc.nextLine());
 System.out.print("Enter Newspaper: "); pstmt.setString(3, sc.nextLine());
 System.out.print("Enter Monthly Rate: "); pstmt.setDouble(4, sc.nextDouble());
 sc.nextLine(); // IMPORTANT: Clear buffer
 
 pstmt.executeUpdate();
 System.out.println("Success: Subscriber added.");
 } catch (SQLException e) {
 System.out.println("Error: " + e.getMessage());
    }
 }
 private static void recordPayment() {
 String sql = "INSERT INTO payments (sub_id, pay_month) VALUES (?, ?)";
 try (Connection conn = DBConnection.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
 System.out.print("Enter Subscriber ID: "); pstmt.setInt(1, sc.nextInt());
 sc.nextLine(); 
 System.out.print("Enter Month (YYYY-MM): "); pstmt.setString(2, sc.nextLine());
 
 pstmt.executeUpdate();
 System.out.println("Success: Payment recorded.");
 } catch (SQLException e) {
 System.out.println("Error: " + e.getMessage());
    }
 }
 private static void markDelivery() {
 String sql = "INSERT INTO deliveries (sub_id, del_date) VALUES (?, ?)";
 try (Connection conn = DBConnection.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
 System.out.print("Enter Subscriber ID: "); pstmt.setInt(1, sc.nextInt());
 sc.nextLine();
 System.out.print("Enter Delivery Date (YYYY-MM-DD): "); pstmt.setString(2, 
sc.nextLine());
 
 pstmt.executeUpdate();
 System.out.println("Success: Delivery marked.");
 } catch (SQLException e) {
 System.out.println("Error: " + e.getMessage());
    }
 }
 private static void viewUnpaid() {
 String sql = "SELECT name, paper_name, monthly_rate FROM subscribers " +
 "WHERE sub_id NOT IN (SELECT sub_id FROM payments WHERE pay_month = ?)";
 try (Connection conn = DBConnection.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
 System.out.print("Month: ");
 String month = sc.nextLine();
 pstmt.setString(1, month);
 
 ResultSet rs = pstmt.executeQuery();
 System.out.println("Unpaid Subscribers:");
 while (rs.next()) {
 System.out.printf("- %-15s | %-20s | Rs %.2f\n", 
 rs.getString("name"), rs.getString("paper_name"), rs.getDouble("monthly_rate"));
    }
 } catch (SQLException e) {
 System.out.println("Error: " + e.getMessage());
   }
 }
