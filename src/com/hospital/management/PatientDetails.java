package com.hospital.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PatientDetails {
		public static void PatientMenu(Connection con,Scanner scn) {
			while(true) {
				System.out.println("what do you Want to do ??");
				System.out.println("1.Add Patient \n 2.View Patient \n3.Update Patient \n4.Delete Patient \n5.View Status of a Patient \n6.Exit");
				int choice=scn.nextInt();
				switch(choice) {
				case 1:addPatient(con,scn);
					break;
				case 2:viewPatient(con,scn);
					break;
				case 3:updatePatient(con,scn);
					break;
				case 4:deletePatient(con,scn);
					break;
				case 5:statusOfPatient(con,scn);
					break;
				case 6:System.out.println("Exited Patient Data !!!");
				return;
				default:System.out.println("Invalid Choice !!...\n Please Try again.......");
				break;
				
				}
			}
		}
	public static void addPatient(Connection con,Scanner scn) {
		try {
			System.out.println("Enter the PATIENT ID : ");
			int id=scn.nextInt();
			scn.nextLine();
			System.out.println("Enter the PATIENT Name : ");
			String name=scn.nextLine();
			System.out.println("Enter the PATIENT Disease : ");
			String dis=scn.next();
			System.out.println("Enter the Doctor ID: ");
			int did=scn.nextInt();
			String query="INSERT INTO PATIENT (PATIENT_ID,PATIENT_NAME,PATIENT_DISEASE,DOCTOR_ID,SOP) VALUES(?,?,?,?,?)";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.setString(2,name);
			psmt.setString(3,dis);
			psmt.setInt(4, did);
			psmt.setString(5, "ADMITTED");
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
	public static void viewPatient(Connection con,Scanner scn) {
		try {
			System.out.println("Enter the Patient ID : ");
			int id=scn.nextInt();
			String query="SELECT * FROM PATIENT WHERE PATIENT_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			ResultSet rs=psmt.executeQuery();
			System.out.println("=========================================================================");
			while(rs.next()) {
				System.out.println("PATIENT ID : "+rs.getInt("PATIENT_ID")+"PATIENT Name : "+rs.getString("PATIENT_NAME")+"PATIENT Disease : "+rs.getString("PATIENT_DISEASE")+"PATIENT Status : "+rs.getString("SOP")+"DOCTOR ID : "+rs.getInt("DOCTOR_ID"));
				System.out.println("=========================================================================");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void updatePatient(Connection con,Scanner scn) {
		try {
			System.out.println("Enter the PATIENT ID : ");
			int id=scn.nextInt();
			scn.next();
			System.out.println("Enter the PATIENT's New Name : ");
			String name=scn.nextLine();
			scn.next();
			System.out.println("Enter the PATIENT's Disease : ");
			String dis=scn.nextLine();
			System.out.println("Enter the DOCTOR ID : ");
			int did=scn.nextInt();
			String query="UPDATE PATIENT SET PATIENT_NAME=?,PATIENT_DISEASE=?,DOCTOR_ID=?,SOP=? WHERE PATIENT_ID=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setString(1,name);
			psmt.setString(2,dis);
			psmt.setInt(3, did);
			psmt.setString(4,"ADMITTED");
			psmt.setInt(5, id);
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
	public static void deletePatient(Connection con,Scanner scn) {
		try {
		System.out.println("Enter the PATIENT ID : ");
		int id=scn.nextInt();
		boolean loop=true;
		String query="";
		String action="";
		while(loop) {
			System.out.println("Delete OR Discharge PATIENT-->");
			System.out.println("1.Delete \n 2.Discharge");
			int option=scn.nextInt();
			switch(option) {
			case 1:query="DELETE FROM PATIENT WHERE PATIENT_ID=?";
			loop= false;
			action="Delete"; 
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1, id);
			int count=psmt.executeUpdate();
			if(count>0) {
				System.out.println("Success!!! Action= "+action);
			}else {
				System.out.println("Try Again !!!");
			}
			break;
			case 2:query="UPDATE PATIENT SET SOP=? WHERE PATIENT_ID=?";
			action="Discharged";
			loop=false;
			PreparedStatement psmt2=con.prepareStatement(query);
			psmt2.setString(1, action);
			psmt2.setInt(2, id);
			int count2=psmt2.executeUpdate();
			if(count2>0) {
				System.out.println("Success!!! Action= "+action);
			}else {
				System.out.println("Try Again !!!");
			}
			break;
			default:System.out.println("Invalid Choice !! \n Try Again!!");
			break;
			}	
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void statusOfPatient(Connection con,Scanner scn) {
		try {
			String query="SELECT PATIENT_NAME,SOP FROM PATIENT ORDER BY SOP DESC";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("=========================================================================");
			while(rs.next()) {
				System.out.println("PATIENT NAME : "+rs.getString("PATIENT_NAME")+"STATUS OF A PATIENT : "+rs.getString("SOP"));
				System.out.println("=========================================================================");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
