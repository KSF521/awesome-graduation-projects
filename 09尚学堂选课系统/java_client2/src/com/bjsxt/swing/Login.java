package com.bjsxt.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.bjsxt.client.Regex;
import com.bjsxt.service.Service;
import com.bjsxt.service.SwingUtils;

public class Login extends JFrame implements ActionListener,MouseListener {

	private Socket socket;// socket
	private PrintWriter pw;
	private BufferedReader br;

	final ImageIcon IconLoginMax=new ImageIcon("image/loginMax.png");
//	final Image loginMax =IconLoginMax.getImage();
	final ImageIcon IconLoginMax2=new ImageIcon("image/loginMax2.png");
//	final Image loginMax2 =IconLoginMax2.getImage();
	
	final Image title =new ImageIcon("image/sxt.png").getImage();
	final ImageIcon login = new ImageIcon("image/login2.jpg");
	final Image back =new ImageIcon("image/login1.jpg").getImage();
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JRadioButton student;
	private JRadioButton teacher;
	private JRadioButton administrator;
	private ButtonGroup group;
	private JButton button;
	private JLabel PW;
	private JLabel ID;
	private JLabel loginButtonMax;

	public Login() throws IOException {
		socket = new Socket("127.0.0.1", 8900);
		pw = new PrintWriter(this.socket.getOutputStream());
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	     double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	     int x=(int)(width-480)/2;
	     int y=(int)(height-415)/2;
	     setBounds(x,y,480,415);
//	     int y=(int)(height-700)/2;
//	     setBounds(x,y,470,700);
		setTitle("SXT学生选课系统");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.getRootPane().setDefaultButton(button);
		
		loginButtonMax=new JLabel();
		loginButtonMax.setIcon(IconLoginMax);
		loginButtonMax.setBounds(170, 310, 124,50);
		loginButtonMax.addMouseListener(this);
		contentPane.add(loginButtonMax);
		
		button=new JButton();
		contentPane.getRootPane().setDefaultButton(button);
		SwingUtils.enterPressesWhenFocused(button);
		contentPane.add(button);
		button.addActionListener(this);
		
		
		
		userName = new JTextField();
		userName.setBounds(90, 104, 188, 25);
		userName.setText("");
		contentPane.add(userName);
		userName.setColumns(10);
		
		ID = new JLabel("");
		ID.setFont(new Font("宋体", 1, 17));
		ID.setBounds(300, 104, 150, 30);
		contentPane.add(ID);
		

		password = new JPasswordField();
		password.setText("");
		password.setBounds(90, 185, 188, 25);
		contentPane.add(password);
		password.setColumns(10);

		PW = new JLabel("");
		PW.setFont(new Font("宋体", 1, 17));
		PW.setBounds(296, 187, 150, 30);
		contentPane.add(PW);
		
		student = new JRadioButton("学生登录",true);
		student.setBounds(43, 235, 110, 27);
		student.setFont(new Font("微软雅黑", 1, 19));
		student.setOpaque(false);
		contentPane.add(student);

		teacher = new JRadioButton("教师登录");
		teacher.setBounds(180, 235, 110, 27);
		teacher.setFont(new Font("微软雅黑", 1, 19));
		teacher.setOpaque(false);
		contentPane.add(teacher);

		administrator = new JRadioButton("管理员登录");
		administrator.setBounds(320, 235, 140, 27);
		administrator.setFont(new Font("微软雅黑", 1, 19));
		administrator.setOpaque(false);
		contentPane.add(administrator);
		
		group = new ButtonGroup();
		group.add(student);
		group.add(teacher);
		group.add(administrator);
		
		JLabel labelTitle1 = new JLabel("") 
		 {
	 public void paint(Graphics g) {
			g.drawImage(title, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		 };
		 labelTitle1.setBounds(65, -10, 350,150);
		 contentPane.add(labelTitle1);
		
		
		 JLabel p = new JLabel("") 
		 {
	 public void paint(Graphics g) {
			g.drawImage(back, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		 };
		 p.setBounds(0, 0, 470,370);
		 contentPane.add(p);

		new Thread(new LogRegex()).start();
	}
	// 向服务器发送登录数据，
	public void sendLog() {
		StringBuilder sb = new StringBuilder();
		sb.append("登录,");
		sb.append(userName.getText() + ",");
		sb.append(password.getPassword());
		System.out.println(sb.toString());
		Service.send(pw, sb.toString());
	}
	/*
	 * 登录界面的校正线程
	 * 每0.1秒判定一次
	 */
	private class LogRegex implements Runnable {

		@Override
		public void run() {
			while (true) {
				regex();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void regex() {
		Regex regex = new Regex();
		if (!regex.passwordRegex(password.getText())) {
			PW.setForeground(Color.RED);
			PW.setText("4-10位");
		} else {
			PW.setForeground(Color.GREEN);
			PW.setText("密码格式正确");
		}

		ID.setForeground(Color.GREEN);
		ID.setText("ID格式正确");

		if (student.isSelected())
			if (!regex.stuIDRegex(userName.getText())) {
				ID.setForeground(Color.RED);
				ID.setText("学生ID6位数字");
			}
		if (teacher.isSelected())
			if (!regex.teacherIDRegex(userName.getText())) {
				ID.setForeground(Color.RED);
				ID.setText("教师ID4位数字");
			}
		if (administrator.isSelected())
			if (!regex.adminIDRegex(userName.getText())) {
				ID.setForeground(Color.RED);
				ID.setText("管理员ID2位数字");
			}
	}

	
		/*
		 * 解析服务器传来的数据 依照标识码分别进行操作
		 */
		private void judgeLogin(String str) {
			if (str.startsWith("登录")) {
				if ("true".equals(dealInfo(str))){
					if (student.isSelected()) {
						new Student(socket).setVisible(true);
						dispose();
					}
					if (teacher.isSelected()) {
						 new Teacher(socket).setVisible(true);
						 dispose();
					}
					if (administrator.isSelected()) {
						 new Admin(socket).setVisible(true);
						 dispose();
					}
				}else
				JOptionPane.showMessageDialog(null, "登陆失败", "登录", JOptionPane.ERROR_MESSAGE);
					
				userName.setText("");
				password.setText("");
				
			}
		}
		/*
		 * 将获得的字符串进行处理 按“,”分开，丢掉标识码 返回有用的信息
		 */
		private String dealInfo(String str) {
			String[] sArr = str.split(",");
			return sArr[1];
		}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			if(Color.GREEN.equals(PW.getForeground())&&Color.GREEN.equals(ID.getForeground()))
			{
				sendLog();
				judgeLogin(Service.listen(br));
			}
			else
			JOptionPane.showMessageDialog(null, "ID或密码错误", "登录", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==loginButtonMax)
		{
			loginButtonMax.setIcon(IconLoginMax2);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==loginButtonMax)
		{
			loginButtonMax.setIcon(IconLoginMax);
			if(Color.GREEN.equals(PW.getForeground())&&Color.GREEN.equals(ID.getForeground()))
			{
				sendLog();
				judgeLogin(Service.listen(br));
			}
			else
			JOptionPane.showMessageDialog(null, "ID或密码错误", "登录", JOptionPane.ERROR_MESSAGE);
		}
	}

}
