package com.example.empManageApp.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Menu {

	public static int readChoice(int i) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int choice;
			while(true)
			{
				System.out.println("Enter choice: ");
				choice = Integer.parseInt(br.readLine());
				if(choice<0 || choice>i) {
					throw new Exception();
				}
				else {
					return choice;
				}
			}
		}
		
	}
}
