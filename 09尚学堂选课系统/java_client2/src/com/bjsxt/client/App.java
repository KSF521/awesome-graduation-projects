package com.bjsxt.client;

import java.io.IOException;
import java.net.Socket;

import com.bjsxt.swing.Admin;
import com.bjsxt.swing.Login;
import com.bjsxt.swing.Student;

public class App {
	public static void main(String[] args) {
		try {
			new Login().setVisible(true);
		} catch (IOException e) {
			System.out.println("当前客户端异常");
		}
	}

}
