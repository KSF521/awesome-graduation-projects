package com.zhatian.wzp.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zhatian.kraken.control.StudentAction;
import com.zhatian.wzp.GUI.LoginWindow.JLabelTimerTask;

/**
 * 主界面窗体
 * @author Wang zhanpeng
 */

public class MainWindow extends JFrame {
	// 提升组件的作用域
	private ImageIcon background;
	private Container container;
	private JPanel jp;
	private JPanel information;
	private JLabel head;
	private JLabel bottom1;
	private JLabel bottom2;
	private JLabel logo;
	private JLabel back;
	private ImageIcon lg;
	private JButton signIn;
	private JButton signOut;
	private JButton quit;
	private JButton addGist;
	private JButton lookGist;
	private JButton modifyPwd;	
	private Font font;
	private JPanel timePanel;
    private JLabel timeLabel;
	private JLabel displayArea;
	private String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	private String time;
	private int ONE_SECOND = 1000;
	static MainWindow mw;
	
	//判断用户签到和签退是否成功的标志
	private boolean flag1;
	private boolean flag2;
	
	/*
	 * 初始化窗体类
	 */
	public static void main(String[] args) {
		mw = new MainWindow();
	}
	
	/*
	 * 窗体组成及布局
	 */
	public  MainWindow() {
		this.setTitle("炸天_签到系统");
		
		//保存背景和logo图片
		background = new ImageIcon("resource\\student_resources\\main.jpg");
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
		timePanel = new JPanel();
		timeLabel = new JLabel("时间: ");
		displayArea = new JLabel();  
	    configTimeArea(); 
		timePanel.add(timeLabel);
		timePanel.add(displayArea);
		head = new JLabel("主界面");
		
		signIn = new JButton("签到");
		//给签到按钮添加监听器
		signIn.addActionListener(new myActionListener());
		
		signOut = new JButton("签退");
		//给签退按钮添加监听器
		signOut.addActionListener(new myActionListener());
		
		quit = new JButton("退出");
		//给退出按钮添加监听器
		quit.addActionListener(new myActionListener());
		
		addGist = new JButton("增加梗概");
		//给增加梗概按钮添加监听器
		addGist.addActionListener(new myActionListener());
		lookGist = new JButton("查看梗概");
		//给查看梗概按钮添加监听器
		lookGist.addActionListener(new myActionListener());
		modifyPwd = new JButton("修改密码");
		//给修改密码按钮添加监听器
		modifyPwd.addActionListener(new myActionListener());
		
		bottom1 = new JLabel("炸天出品");
		bottom2 = new JLabel("必属精品");
		
		//给签到按钮内设置字体类型、颜色和大小
		font = new Font("微软雅黑", 0, 40);
		signIn.setForeground(Color.orange);
		signIn.setFont(font);
		
		//给签退按钮内设置字体类型、颜色和大小
		signOut.setForeground(Color.green);
		signOut.setFont(font);
		
		//给退出按钮内设置字体类型、颜色和大小
		quit.setForeground(Color.red);
		quit.setFont(font);
		
		//给增加梗概按钮内设置字体类型和大小
		font = new Font("微软雅黑", 0, 18);
		addGist.setFont(font);
		
		//给查看梗概按钮内设置字体类型和大小
		lookGist.setFont(font);
		
		//给修改密码按钮内设置字体类型和大小
		modifyPwd.setFont(font);
		
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
		
		//给动态时间字体设置字体类型、颜色和大小
		font = new Font("微软雅黑", 0, 24);
		timePanel.setBackground(new Color(217, 169, 121));
		timeLabel.setForeground(Color.black);
		displayArea.setForeground(Color.black);
		timeLabel.setFont(font);
		displayArea.setFont(font);
		
		//给各个组件设置大小和布局位置(自定义坐标布局)
		timePanel.setBounds(560, 20, 200, 50);
		head.setBounds(325, 30, 200, 80);
		logo.setBounds(220, 10, 100, 100);
		signIn.setBounds(150, 150, 150, 150);
		signOut.setBounds(330, 150, 150, 150);
		quit.setBounds(510, 150, 150, 150);
		addGist.setBounds(200, 350, 120, 70);
		lookGist.setBounds(340, 350, 120, 70);
		modifyPwd.setBounds(480, 350, 120, 70);
		bottom1.setBounds(620, 400, 120, 60);
	    bottom2.setBounds(620, 430, 120, 60);
	    
	    //将各组件添加到JPanel中
	    jp.add(timePanel);
		jp.add(head);
		jp.add(logo);
		jp.add(bottom1);
		jp.add(bottom2);
		jp.add(signIn);
		jp.add(signOut);
		jp.add(quit);
		jp.add(addGist);
		jp.add(lookGist);
		jp.add(modifyPwd);
		jp.add(back);
		
	    //将JPanel添加到容器中
		container.add(jp);
		container.add(information);
		
		 //将容器设置为可见
		this.setVisible(true);
	}
	
	/**
	 * 此方法创建一个计时器任务来更新每秒的时间。
	 */
	 private void configTimeArea() {
		 Timer tmr = new Timer();
		 tmr.scheduleAtFixedRate(new JLabelTimerTask(),new Date(), ONE_SECOND);
	 }
	  
	 /**
	 * 计时器任务来更新时间显示区域 
	 *
	 */
	 protected class JLabelTimerTask extends TimerTask {
		 SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		 @Override
		 public void run() {
		  time = dateFormatter.format(Calendar.getInstance().getTime());
		  displayArea.setText(time);
		 }
	 }
	
	/*
	 * 构造一个内部类实现ActionListener接口来处理监听到的事件
	 */
	private class myActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//监听到签到按钮事件，执行各种判断
			if(signIn == e.getSource()) {
				flag1 = StudentAction.stuSignIn(LoginWindow.stu);
				if(flag1) {
					JOptionPane.showMessageDialog(information, "签到成功！", "提示信息",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(information, "签到失败！", "提示信息",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			//监听到签退按钮事件，执行各种判断
			if(signOut == e.getSource()) {
				flag2 = StudentAction.stuSignOut(LoginWindow.stu);
				if(flag2) {
					JOptionPane.showMessageDialog(information, "签退成功！", "提示信息",JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(information, "签退失败！", "提示信息",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			//监听到退出按钮事件，执行程序退出
			if(quit == e.getSource()) {
				mw.dispose();
			}
			
			//监听到增加梗概按钮事件，打开增加梗概窗口
			if(addGist == e.getSource()) {
				AddGist.ag = new AddGist();
				mw.dispose();
			}
			
			//监听到查看梗概按钮事件，打开查看梗概窗口
			if(lookGist == e.getSource()) {
				LookGist.sg = new LookGist();
				mw.dispose();
			}	
			
			//监听到修改按钮事件，打开修改窗口
			if(modifyPwd == e.getSource()) {
				ModifyPwd.mp = new ModifyPwd();
				mw.dispose();
			}	
		}	
	}
}
