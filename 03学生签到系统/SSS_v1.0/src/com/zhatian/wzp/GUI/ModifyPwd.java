package com.zhatian.wzp.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.zhatian.Tinner.entity.Student;
import com.zhatian.kraken.control.StudentAction;



/**
 * 修改密码窗体
 * @author Wang zhanpeng
 */

public class ModifyPwd extends JFrame {
	//提升组件的作用域
	private ImageIcon background;
	private ImageIcon lg;
	private Container container;
	private JPanel jp;
	private JPanel information;
	private JLabel head;
	private JLabel logo;
	private JLabel back;
	private JLabel passwordLabel;
	private JLabel nameLabel;
	private JLabel sexLabel;
	private JLabel ageLabel;
	private JLabel classLabel;
	private JLabel bottom1;
	private JLabel bottom2;
	private JPasswordField passwordText;
	private JTextField nameText;
	private JRadioButton boy;
	private JRadioButton girl;
	private ButtonGroup bg;
	private JTextField ageText;
	private JTextField classText;
	private JButton okbtn;
	private JButton resert;
	private JButton returnMain;
	private Font font;
	static ModifyPwd mp;
	
	//判断用户修改成功还是失败的标志
	private boolean flag;
	
	/*
	 * 初始化窗体类
	 */
	public static void main(String[] args) {
		mp = new ModifyPwd();
	}
	
	/*
	 * 窗体组成及布局
	 */
	public ModifyPwd() {
		this.setTitle("炸天_签到系统");
		
		//保存背景和logo图片
		background = new ImageIcon("resource\\student_resources\\modify.jpg");
		lg = new ImageIcon("resource\\student_resources\\logo.jpg");
		
		//初始化一个存放组件的容器
		container = this.getContentPane();
		
		//将背景和logo存放到JLabel中，并初始化JLabel的位置和大小(大小为所存图片的大小)
		back = new JLabel(background);
		logo = new JLabel(lg);
		back.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		logo.setBounds(0, 0, lg.getIconWidth(), lg.getIconHeight());
		
		//初始化一个JPanel容器，设置其布局为空，初始化其大小和位置
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		
		//参数设置为true，设置背景色不透明
		jp.setOpaque(true);
		
		//设置窗体不可变及初始化窗体大小和位置
		this.setResizable(false);
		this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setLocationRelativeTo(null);
		
		//将提示信息添加到JPanel中
		information = new JPanel();
		
		//初始化各组件
		head = new JLabel("修改信息");
		passwordLabel = new JLabel("密 码: ");
		nameLabel = new JLabel("姓 名: ");
		sexLabel = new JLabel("性 别: ");
		ageLabel = new JLabel("年 龄: ");
		classLabel = new JLabel("班 级: ");
		bottom1 = new JLabel("炸天出品");
		bottom2 = new JLabel("必属精品");
		passwordText = new JPasswordField();
		
		//将还未修改前的密码显示在输入框中
		passwordText.setText(LoginWindow.stu.getStudent_Password());
		
		nameText = new JTextField();
		//将还未修改前的姓名显示在输入框中
		nameText.setText(LoginWindow.stu.getStudent_Name());
		
		//将还未修改前的性别显示在输入框中
		if("男".equals((LoginWindow.stu.getStudent_Sex()))) {
			boy = new JRadioButton("男", true);
			girl = new JRadioButton("女", false);
		} else if("女".equals((LoginWindow.stu.getStudent_Sex()))) {
			girl = new JRadioButton("女", true);
			boy = new JRadioButton("男", false);
		}
		bg = new ButtonGroup();
		bg.add(boy);
		bg.add(girl);
		
		ageText = new JTextField();
		//将还未修改前的年龄显示在输入框中
		ageText.setText(LoginWindow.stu.getStudent_Age());
		
		classText = new JTextField();
		//将还未修改前的班级显示在输入框中
		classText.setText(LoginWindow.stu.getStudent_Classroom());
	
		okbtn = new JButton("确定");
		//给确定按钮添加监听器
		okbtn.addActionListener(new myActionListener());
		resert = new JButton("重置");
		//给重置按钮添加监听器
		resert.addActionListener(new myActionListener());
		
		returnMain = new JButton("返回主页");
		//给返回主页按钮添加监听器
		returnMain.addActionListener(new myActionListener());
		
		//姓名、性别、年龄和班级设置字体类型、颜色和大小
		font = new Font("微软雅黑", 0, 24);
		passwordLabel.setForeground(Color.black);
		nameLabel.setForeground(Color.black);
		sexLabel.setForeground(Color.black);
		ageLabel.setForeground(Color.black);
		classLabel.setForeground(Color.black);
		passwordLabel.setFont(font);
		nameLabel.setFont(font);
		sexLabel.setFont(font);
		ageLabel.setFont(font);
		classLabel.setFont(font);
		
		//给单选框内字体设置字体类型、颜色和大小
		font = new Font("楷体", 0, 22);
		boy.setFont(font);
		boy.setForeground(Color.BLUE);
		boy.setBackground(new Color(217, 169, 121));
		girl.setFont(font);
		girl.setBackground(new Color(217, 169, 121));
		girl.setForeground(Color.RED);
		
		//给用户输入框内字体设置字体类型和大小
		font = new Font("楷体", 0, 20);
		nameText.setFont(font);
		ageText.setFont(font);
		classText.setFont(font);
		
		//给确定和终止按钮内添加字体类型和大小
		font = new Font("微软雅黑", 0, 18);
		okbtn.setFont(font);
		resert.setFont(font);
		
		//给返回主页按钮内添加字体类型和大小
		font = new Font("微软雅黑", 0, 20);
		returnMain.setForeground(Color.black);
		returnMain.setFont(font);
		
		//给头部字体设置类型、颜色和大小
		font = new Font("微软雅黑", 0, 36);	
		head.setForeground(Color.white);
		head.setFont(font);
		
		//给尾部字体设置类型、颜色和大小
		font = new Font("微软雅黑", 0, 26);
		bottom1.setForeground(Color.white);
		bottom1.setFont(font);
		bottom2.setForeground(Color.white);
		bottom2.setFont(font);
		
		
		//给各个组件设置大小和布局位置(自定义坐标布局)
		head.setBounds(325, 30, 200, 80);
		logo.setBounds(220, 10, 100, 100);
		passwordLabel.setBounds(240, 130, 100, 50);
		nameLabel.setBounds(240, 170, 100, 50);
		sexLabel.setBounds(240, 210, 100, 50);
		ageLabel.setBounds(240, 250, 100, 50);
		classLabel.setBounds(240, 290, 100, 50);
		passwordText.setBounds(300, 140, 200, 30);
		nameText.setBounds(300, 180, 200, 30);
		boy.setBounds(300, 220, 60, 36);
	    girl.setBounds(420, 220, 60, 36);
		ageText.setBounds(300, 260, 200, 30);
		classText.setBounds(300, 300, 200, 30);
		returnMain.setBounds(650, 20, 120, 90);
	    okbtn.setBounds(270, 366, 100, 56);
	    resert.setBounds(410, 366, 100, 56);
	    bottom1.setBounds(620, 400, 120, 60);
	    bottom2.setBounds(620, 430, 120, 60);
	    
	    //将各组件添加到JPanel中
	    jp.add(passwordLabel);
	    jp.add(nameLabel);
	    jp.add(sexLabel);
	    jp.add(ageLabel);
	    jp.add(classLabel);
	    jp.add(passwordText);
	    jp.add(nameText);
	    jp.add(boy);
	    jp.add(girl);
	    jp.add(ageText);
	    jp.add(classText);
	    jp.add(okbtn);
	    jp.add(resert);
	    jp.add(returnMain);
	    jp.add(bottom1);
	    jp.add(bottom2);
	    jp.add(head);
	    jp.add(logo);
	    jp.add(back);
	    
	    //将JPanel添加到容器中
	    container.add(jp);
	    container.add(information);
	    
	    //将容器设置为可见
		this.setVisible(true);
	}
	
	/*
	 * 构造一个内部类实现ActionListener接口来处理监听到的事件
	 */
	private class myActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//监听到确定按钮事件，执行各种判断
			if(okbtn == e.getSource()) {
				
				//获得用户输入的密码、姓名、性别、年龄和班级
				String pwd = new String(passwordText.getPassword());
				String name = nameText.getText();
				String sex = "";
				if(boy.isSelected()) {
					sex = boy.getText();
				} else if(girl.isSelected()) {
					sex = girl.getText();
				}
				String age = ageText.getText();
				String _class = classText.getText();
				
				////将修改过的密码、姓名、性别、年龄和班级存入实体类Student中
				LoginWindow.stu.setStudent_Password(pwd);
				LoginWindow.stu.setStudent_Name(name);
				LoginWindow.stu.setStudent_Sex(sex);
				LoginWindow.stu.setStudent_Age(age);
				LoginWindow.stu.setStudent_Classroom(_class);
				
				//判断是否修改成功的标志
				flag = StudentAction.stuModification(LoginWindow.stu);
				
				//防止用户还未修改完成服务器断开等突发事件，将用户信息重置为未修改前的状态
				LoginWindow.stu = StudentAction.stuFindStudentById(LoginWindow.stu.getStudent_Id());
			
				if(flag) {
					JOptionPane.showMessageDialog(information, "修改信息成功！", "提示信息",JOptionPane.INFORMATION_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(information, "修改信息失败！", "提示信息",JOptionPane.WARNING_MESSAGE); 
				}
			}
			
			//监听到重置事件，将用户输入的数据恢复至未修改前
			if(resert == e.getSource()) {
				//将还未修改前的密码显示在输入框中
				passwordText.setText(LoginWindow.stu.getStudent_Password());
				
				//将还未修改前的姓名显示在输入框中
				nameText.setText(LoginWindow.stu.getStudent_Name());
				
				//将还未修改前的性别显示在输入框中
				if("男".equals((LoginWindow.stu.getStudent_Sex()))) {
					boy.setSelected(true);
				} else if("女".equals((LoginWindow.stu.getStudent_Sex()))) {
					girl.setSelected(true);
				}

				//将还未修改前的年龄显示在输入框中
				ageText.setText(LoginWindow.stu.getStudent_Age());
				
				//将还未修改前的班级显示在输入框中
				classText.setText(LoginWindow.stu.getStudent_Classroom());
			}
			
			//监听到返回主页事件，将该窗口关闭
			if(returnMain == e.getSource()) {
				mp.dispose();
				MainWindow.mw = new MainWindow();
			}
		}	
	}
}
