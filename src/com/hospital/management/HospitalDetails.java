package com.hospital.management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalDetails {
	public static void hospitalmenu(Connection con,Scanner scn) {
		while(true) {
			System.out.println("what do you Want to do ??");
			System.out.println("1.Add Hospital \n 2.View Hospital \n3.Update Hospital \n4.Delete Hospital \n5.Exit");
			int choice=scn.nextInt();
			switch(choice) {
			case 1:addHospital(con,scn);
				break;
			case 2:viewHospital(con,scn);
				break;
			case 3:updateHospital(con,scn);
				break;
			case 4:deleteHospital(con,scn);
				break;
			case 5:System.out.println("Exited Hospital Data !!!");
			return;
			default:System.out.println("Invalid Choice !!...\n Please Try again.......");
			break;
			}
		}
	}
	public static void addHospital(Connection con,Scanner scn) {
		try{
			System.out.println("Enter the Hospital ID : ");
			int id=scn.nextInt();
			scn.nextLine();
			System.out.println("Enter the Hospital Name : ");
			String name=scn.nextLine();
			scn.nextLine();
			System.out.println("Enter the Hospital Location(City) : ");
			String loc=scn.next();
			String query="INSERT INTO HOSPITAL (HOS_ID,HOS_NAME,HOS_LOCATION) VALUES(?,?,?)";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.setString(2,name);
			psmt.setString(3,loc);
			int count=psmt.executeUpdate();
			if(count>0) {
				System.out.println("Successfully Uploaded !!!");
			}else {
				System.out.println("Something Went Wrong Please Try again !!!");
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
}
	public static void viewHospital(Connection con,Scanner scn) {
		try {
			System.out.println("Enter the Hospital ID : ");
			int id=scn.nextInt();
			String query="SELECT * FROM HOSPITAL WHERE HOS_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			ResultSet rs=psmt.executeQuery();
			System.out.println("=========================================================================");
			while(rs.next()) {
				System.out.println("Hospital ID : "+rs.getInt("HOS_ID")+"Hospital Name : "+rs.getString("HOS_NAME")+"Hospital Location : "+rs.getString("HOS_LOCATION"));
			}
			System.out.println("=========================================================================");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void updateHospital(Connection con,Scanner scn) {
		try {
			System.out.println("Enter the Hospital ID : ");
			int id=scn.nextInt();
			scn.next();
			System.out.println("Enter the Hospital's New Name : ");
			String name=scn.nextLine();
			scn.next();
			System.out.println("Enter the Hospital's Location : ");
			String loc=scn.nextLine();
			String query="UPDATE HOSPITAL SET HOS_NAME=?,HOS_LOCATION=? WHERE HOS_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setString(1,name);
			psmt.setString(2,loc);
			psmt.setInt(3,id);
			int count=psmt.executeUpdate();
			if(count>0) {
				System.out.println("Succesfully Updated !!!");
			}
			else {
				System.out.println("Something went wrong Please try Again !!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void deleteHospital(Connection con,Scanner scn) {
		try {
			System.out.println("Delete Hospital-->");
			System.out.println("Enter the Hospital ID : ");
			int id=scn.nextInt();
			String query="DELETE FROM HOSPITAL WHERE HOS_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			int count=psmt.executeUpdate();
			if(count>0) {
				System.out.println("Successfully Deleted !!!");
			}else {
				System.out.println("Try Again !!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
