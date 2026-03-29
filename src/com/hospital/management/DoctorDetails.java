package com.hospital.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DoctorDetails {
	public static void doctorMenu(Connection con,Scanner scn) {
		while(true) {
			System.out.println("what do you Want to do ??");
			System.out.println("1.Add Hospital \n 2.View Hospital \n3.Update Hospital \n4.Delete Hospital \5.DoctorWithSpecifications \n6.Exit");
			int choice=scn.nextInt();
			switch(choice) {
			case 1:addDoctor(con,scn);
				break;
			case 2:viewDoctor(con,scn);
				break;
			case 3:updateDoctor(con,scn);
				break;
			case 4:deleteDoctor(con,scn);
				break;
			case 5:viewDoctorswithSpecialization(con);
				break;
			case 6:System.out.println("Exited Hospital Data !!!");
			return;
			default:System.out.println("Invalid Choice !!...\n Please Try again.......");
			break;
			}
			}
	}

	public static void addDoctor(Connection con,Scanner scn) {
		try{
			System.out.println("Enter the Doctor ID : ");
			int id=scn.nextInt();
			scn.nextLine();
			System.out.println("Enter the Doctor Name : ");
			String name=scn.nextLine();
			scn.nextLine();
			System.out.println("Enter the Doctor Specializtion : ");
			String type=scn.next();
			System.out.println("Enter the Hospital ID : ");
			int hid=scn.nextInt();
			String query="INSERT INTO DOCTOR (DOCTOR_ID,DOCTOR_NAME,DOCTOR_SPECIALIZATION,HOS_ID) VALUES(?,?,?,?)";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.setString(2,name);
			psmt.setString(3,type);
			psmt.setInt(4,hid);
			int count=psmt.executeUpdate();
			if(count>0) {
				System.out.println("Successfully Uploaded !!!");
			}else {
				System.out.println("Something Went Wrong Please Try again !!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void viewDoctor(Connection con,Scanner scn) {
		try{
			System.out.println("Enter the Doctor ID : ");
			int id=scn.nextInt();
			String query="SELECT * FROM DOCTOR WHERE DOCTOR_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			ResultSet rs=psmt.executeQuery();
			System.out.println("=========================================================================");
			while(rs.next()) {
				System.out.println("Doctor ID : "+ rs.getInt("DOCTOR_ID") +"Doctor Name : "+ rs.getString("DOCTOR_NAME") +"Doctor Specialization : "+ rs.getString("DOCTOR_SPECIALIZATION") +"HOPITAL ID : "+ rs.getInt("HOS_ID") );
				System.out.println("=========================================================================");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void updateDoctor(Connection con,Scanner scn) {
		try{
			System.out.println("Enter the DOCTOR ID : ");
			int id=scn.nextInt();
			scn.nextLine();
			System.out.println("Enter the DOCTOR's New Name : ");
			String name=scn.nextLine();
			scn.next();
			System.out.println("Enter the DOCTOR's Specialization: ");
			String spec=scn.nextLine();
			System.out.println("Enter the Hospital ID : ");
			int hid=scn.nextInt();
			String query="UPDATE DOCTOR SET DOCTOR_NAME=?,DOCTOR_SPECIALIZATION=?,HOS_ID=? WHERE DOCTOR_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setString(1,name);
			psmt.setString(2,spec);
			psmt.setInt(3,hid);
			psmt.setInt(4, id);
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
	public static void deleteDoctor(Connection con,Scanner scn) {
		try{
			System.out.println("Delete DOCTOR	-->");
			System.out.println("Enter the DOCTOR ID : ");
			int id=scn.nextInt();
			String query="DELETE FROM DOCTOR WHERE DOCTOR_ID=?";
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
	public static void viewDoctorswithSpecialization(Connection con) {
		try{
			String query="SELECT * FROM DOCTOR ORDER BY DOCTOR_SPECIALIZATION DESC";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("=========================================================================");
			while(rs.next()) {
				System.out.println("Doctor ID : "+rs.getInt("DOCTOR_ID")+"Doctor Name : "+rs.getString("DOCTOR_NAME")+"Doctor Specialization : "+rs.getString("DOCTOR_SPECIALIZATION")+"HOPITAL ID : "+rs.getInt("HOSPITAL_ID"));
				System.out.println("=========================================================================");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
