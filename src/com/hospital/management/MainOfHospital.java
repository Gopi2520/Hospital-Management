package com.hospital.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MainOfHospital {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Scanner scn=new Scanner(System.in);
			System.out.println("Loading Drivder Completed!!!");
			String dburl="jdbc:mysql://localhost:3306/hospitals?user=root&password=Gopzz@2014";
			Connection con=DriverManager.getConnection(dburl);
			System.out.println("Welcome to Hospital Database Management!!!");
			while(true) {
				System.out.println("Which Data You Want to Access : ");
				System.out.println(" 1.HOSPITAL \n 2.DOCTORS \n 3.PATIENTS \n 4.EXIT");
				int choice=scn.nextInt();
				switch(choice) {
				case 1:HospitalDetails.hospitalmenu(con, scn);break;
				case 2:DoctorDetails.doctorMenu(con, scn); break;
				case 3:PatientDetails.PatientMenu(con,scn); break;
				case 4:System.out.println("succesfully Went out !!");return;
				default:System.out.println("Invalid choice !!...\n Please Try again!!");
				break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
