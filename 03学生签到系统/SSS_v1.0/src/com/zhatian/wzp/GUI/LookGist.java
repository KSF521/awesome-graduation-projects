package com.zhatian.wzp.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zhatian.Tinner.entity.Sign;
import com.zhatian.kraken.control.StudentAction;
import com.zhatian.wzp.util.Regex;


/**
 * 查看历史梗概窗体
 * @author Wang zhanpeng
 *
 */
public class LookGist extends JFrame {
	// 提升组件的作用域
	private ImageIcon background;
	private ImageIcon lg;
	private JLabel head;
	private JLabel prompt;
	private JTextField searchText;
	private JLabel bottom1;
	private JLabel bottom2;
	private JLabel back;
	private JLabel logo;
	private Container container;
	private JPanel jp;
	private JPanel information;
	private JScrollPane scroll;
	private JTextArea gistArea;
	private JButton returnMain;
	private JButton search;
	private Font font;
	static LookGist sg;
	private Sign sn;
	
	/*
	 * 初始化窗体类
	 */
	public static void main(String[] args) {
		sg = new LookGist();
	}
	
	/*
	 * 窗体组成及布局
	 */
	public LookGist() {
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
		
		//初始化各组件
		prompt = new JLabel("请输入您要查看梗概的日期：");
		searchText = new JTextField();
		gistArea = new JTextArea();
		gistArea.setLineWrap(true);
		gistArea.setWrapStyleWord(true);
		scroll = new JScrollPane(gistArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		head = new JLabel("查看梗概");
		returnMain = new JButton("返回主页");
		search = new JButton("搜索");
		bottom1 = new JLabel("炸天出品");
		bottom2 = new JLabel("必属精品");
		information = new JPanel();
		//给搜索按钮添加监听器
		search.addActionListener(new myActionListener());
		returnMain.setForeground(Color.black);
		returnMain.setFont(font);
		//给返回按钮添加监听器
		returnMain.addActionListener(new myActionListener());
		
		//给提示字体设置类型、颜色和大小
		font = new Font("微软雅黑", 0, 20);
		prompt.setForeground(Color.black);
		prompt.setFont(font);
		
		//给搜索框内和显示框字体设置类型和大小
		font = new Font("楷体", 0, 20);
		searchText.setFont(font);
		gistArea.setForeground(Color.black);
		gistArea.setFont(font);
		
		//给搜索按钮字体设置类型、颜色和大小
		font = new Font("微软雅黑", 0, 18);
		search.setForeground(Color.black);
		search.setFont(font);
		
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
		prompt.setBounds(12, 130, 260, 40);
		searchText.setBounds(270, 130, 200, 35);
		returnMain.setBounds(650, 20, 100, 80);
		search.setBounds(480, 118, 80, 60);
		scroll.setBounds(270, 180, 290, 290);
		gistArea.setBounds(0, 0, 290, 290);
		bottom1.setBounds(620, 400, 120, 60);
		bottom2.setBounds(620, 430, 120, 60);
		
		//将各组件添加到JPanel中
		jp.add(head);
		jp.add(prompt);
		jp.add(searchText);
		jp.add(returnMain);
		jp.add(search);
		jp.add(scroll);
		jp.add(bottom1);
		jp.add(bottom2);
		jp.add(logo);
		jp.add(back);
		
		//将JPanel添加到容器中
		container.add(jp);
		
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
				sg.dispose();
				MainWindow.mw = new MainWindow();
			}	
			
			//监听到搜索事件，调用相应方法进行搜索
			if(search == e.getSource()) {
				String date = searchText.getText();
				if("".equals(date)) {
					JOptionPane.showMessageDialog(information, "输入日期为空！", "提示信息",JOptionPane. WARNING_MESSAGE);
				} else {
					if(Regex.dateRegex(date) == false) {
						JOptionPane.showMessageDialog(information, "日期格式错误！", "提示信息",JOptionPane. WARNING_MESSAGE);
					} else {
						sn =  StudentAction.stuShowLog(LoginWindow.stu, date);	
						if(StudentAction.stuShowLog(LoginWindow.stu, date) == null) {
							JOptionPane.showMessageDialog(information, "查询不到该天的梗概！", "提示信息",JOptionPane. WARNING_MESSAGE);
						} else {
							String sign_DateLog = sn.getSign_DateLog();
							gistArea.setText(sign_DateLog);
						}
						
					}
				}
			}	
		}	
	}
}
