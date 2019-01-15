package com.zhatian.wzp.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.zhatian.kraken.control.StudentAction;



/**
 * 增加梗概窗体
 * @author Wang zhanpeng
 *
 */

public class AddGist extends JFrame {
	// 提升组件的作用域
	private ImageIcon background;
	private ImageIcon lg;
	private JLabel head;
	private JLabel bottom1;
	private JLabel bottom2;
	private JLabel back;
	private JLabel logo;
	private Container container;
	private JPanel jp;
	private JPanel information;
	private JScrollPane scroll;
	private JTextArea gistArea;
	private JButton okbtn;
	private JButton resert;
	private JButton returnMain;
	private Font font;
	static AddGist ag;
	
	//判断增加梗概是否成功标志
	private boolean flag;
	
	/*
	 * 初始化窗体类
	 */
	public static void main(String[] args) {
		ag = new AddGist();
	}
	
	/*
	 * 窗体组成及布局
	 */
	public AddGist() {
		this.setTitle("炸天_签到系统");
		
		//保存背景和logo图片
		background = new ImageIcon("resource\\student_resources\\addgist.jpg");
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
		gistArea = new JTextArea();
		gistArea.setLineWrap(true);
		gistArea.setWrapStyleWord(true);
		scroll = new JScrollPane(gistArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		okbtn = new JButton("确定");
		//给确定按钮添加监听器
		okbtn.addActionListener(new myActionListener());
		
		resert = new JButton("重置");
		//给重置按钮添加监听器
		resert.addActionListener(new myActionListener());
		
		returnMain = new JButton("返回主页");
		returnMain.setForeground(Color.black);
		returnMain.setFont(font);
		//给返回按钮添加监听器
		returnMain.addActionListener(new myActionListener());
		
		head = new JLabel("增加梗概");
		bottom1 = new JLabel("炸天出品");
		bottom2 = new JLabel("必属精品");
		
		//给用户输入框内字体设置类型和大小
		font = new Font("楷体", 0, 20);
		gistArea.setFont(font);
		
		//给确定和终止按钮内添加字体类型、大小
		font = new Font("微软雅黑", 0, 18);
		okbtn.setFont(font);
		resert.setFont(font);
		
		//给返回按钮字体设置类型和大小
		font = new Font("微软雅黑", 0, 16);
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
		head.setBounds(355, 30, 200, 80);
		logo.setBounds(250, 10, 100, 100);
		returnMain.setBounds(650, 20, 100, 80);
		scroll.setBounds(270, 130, 240, 240);
		gistArea.setBounds(0, 0, 240, 240);
		okbtn.setBounds(280, 395, 80, 48);
		resert.setBounds(420, 395, 80, 48);
	    bottom1.setBounds(620, 400, 120, 60);
		bottom2.setBounds(620, 430, 120, 60);
		
		//将各组件添加到JPanel中
		jp.add(head);
		jp.add(scroll);
		jp.add(returnMain);;
		jp.add(okbtn);
		jp.add(resert);
		jp.add(bottom1);
		jp.add(bottom2);
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
			//监听到返回主页事件，将该窗口关闭
			if(returnMain == e.getSource()) {
				ag.dispose();
				MainWindow.mw = new MainWindow();
			}
			
			//监听到确定按钮事件，判断增加梗概是否成功
			if(okbtn == e.getSource()) {
				String gists = gistArea.getText();
				if(!("".equals(gists))) {	
					flag = StudentAction.stuLog(LoginWindow.stu, gists);
					if(flag) {
						JOptionPane.showMessageDialog(information, "增加梗概成功！", "提示信息",JOptionPane.INFORMATION_MESSAGE); 
						ag.dispose();
						MainWindow.mw = new MainWindow();
					} else {
						JOptionPane.showMessageDialog(information, "增加梗概失败！", "提示信息",JOptionPane.WARNING_MESSAGE); 
					}
				} else {
					JOptionPane.showMessageDialog(information, "输入梗概为空！", "提示信息",JOptionPane.WARNING_MESSAGE); 
				}
			}
			
			//监听到重置事件，将用户输入的数据清空
			if(resert == e.getSource()) {
				if(!"".equals(gistArea.getText())){
					gistArea.setText("");
				}
			}
		}	
	}
}
