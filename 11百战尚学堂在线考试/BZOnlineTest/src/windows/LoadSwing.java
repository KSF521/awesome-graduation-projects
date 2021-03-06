﻿package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;

import windowOperation.LoadWinOpe;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoadSwing extends JFrame implements ActionListener,MouseListener{

	private JTextField userName;// 用户账号输入框
	private JPasswordField password;// 用户密码输入框
	private JRadioButton teacher;// 管理员选择单选按钮
	private JRadioButton student;// 学生选择单选按钮
	private JButton load;// 登录按钮
	private Socket socket;
	private PrintWriter pWriter;
	//主方法用于启动页面
	public static void main(String[] args) {
		LoadSwing frame = new LoadSwing();
		frame.setVisible(true);
	}

	// ====================登入界面======================
	public LoadSwing() {
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		//当让服务器建立时，建立一个Socket。
		try {
		socket = new Socket("localhost", 8899);
		pWriter = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//放到界面的开始
		setTitle("BZ在线考试系统");
		setSize(500, 380);   
        setLocation(200, 50); 
        setResizable(false);
        setIconImage(new ImageIcon("image/tubiao.png").getImage());
        String path = "image\\loadSwing.png";  
        ImageIcon background = new ImageIcon(path);  
        JLabel label = new JLabel(background);  
        label.setBounds(0, 0, 500, 380);  
        JPanel imagePanel = (JPanel) this.getContentPane();
        getContentPane().setLayout(null);
        
        JLabel nameLabel = new JLabel("账户");
        nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		nameLabel.setBounds(128, 115, 32, 15);
		getContentPane().add(nameLabel);
		
		userName = new JTextField();
		userName.setForeground(Color.WHITE);
		userName.setBackground(Color.GRAY);
		userName.setFont(new Font("宋体", Font.PLAIN, 16));
		userName.setBounds(190, 112, 162, 21);
		getContentPane().add(userName);
		userName.setColumns(15);
		
		JLabel passwordLabel = new JLabel("密码");
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		passwordLabel.setBounds(128, 167, 32, 15);
		getContentPane().add(passwordLabel);
		
		password = new JPasswordField();
		password.setForeground(Color.WHITE);
		password.setBackground(Color.GRAY);
		password.setFont(new Font("宋体", Font.PLAIN, 16));
		password.setEchoChar('*');
		password.setBounds(190, 164, 162, 21);
		getContentPane().add(password);
		
		teacher = new JRadioButton("管理员");
		teacher.setSelected(true);
		teacher.setForeground(Color.BLACK);
		teacher.setFont(new Font("宋体", Font.PLAIN, 16));
		teacher.setBounds(128, 208, 73, 23);
		teacher.setContentAreaFilled(false);
		getContentPane().add(teacher);
		
		student = new JRadioButton("学 生");
		student.setForeground(Color.BLACK);
		student.setFont(new Font("宋体", Font.PLAIN, 16));
		student.setBounds(279, 208, 73, 23);
		student.setContentAreaFilled(false);
		getContentPane().add(student);
		// 将单选按钮添加按钮组a
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(student);
		bGroup.add(teacher);
		
		load = new JButton("登      录");
		load.setForeground(Color.WHITE);
		load.setBackground(Color.DARK_GRAY);
		load.setFocusPainted(false);
		load.addActionListener(this);
		load.addMouseListener(this);

		load.setFont(new Font("宋体", Font.PLAIN, 16));
		load.setBounds(150, 255, 182, 33);
		getContentPane().add(load);
		student.setContentAreaFilled(false);
		teacher.setContentAreaFilled(false);
		
        imagePanel.setOpaque(false);  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
	}
	// 点击跳转到其他其他界面
	public void actionPerformed(ActionEvent e) {
	LoadWinOpe lOpe = new LoadWinOpe();
	
		if (student.isSelected()) {
			if (e.getSource() == load) {
				pWriter.print("3"+"\r");
				pWriter.flush();
				pWriter.print("0"+"\r");
				pWriter.flush();
				//判断用户名及密码是否正确
				boolean flag = lOpe.transferUser(userName.getText(), password.getPassword(),socket);
				if (flag) {
					JOptionPane.showMessageDialog(null, "登录成功");
					StudentSwing swing = new StudentSwing(socket);
					swing.setVisible(true);
					this.setVisible(false);
				}
				if (!flag) {
					JOptionPane.showMessageDialog(null, "用户名密码错误或者该用户已登录", "欢迎你", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		if (teacher.isSelected()) {
			if (e.getSource() == load) {
				pWriter.print("3"+"\r");
				pWriter.flush();
				pWriter.print("1"+"\r");
				pWriter.flush();
				boolean flag = lOpe.transferTeacher(userName.getText(), password.getPassword(),socket);
				if (flag) {
					TeacherSwing swing = new TeacherSwing(socket);
					JOptionPane.showMessageDialog(null, "欢迎来到教师管理界面", "欢迎老师", JOptionPane.INFORMATION_MESSAGE);
					swing.setVisible(true);
					this.setVisible(false);	
				}
				if (!flag) {
					JOptionPane.showMessageDialog(null, "用户名密码错误或者该用户已登录", "信息错误", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	//鼠标经过登入按钮改变颜色
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		load.setBackground(Color.BLACK);
		}
	@Override
	public void mouseExited(MouseEvent e) {
		load.setBackground(Color.DARK_GRAY);
	}
}
