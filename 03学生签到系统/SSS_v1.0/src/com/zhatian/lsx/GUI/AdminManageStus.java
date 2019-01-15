package com.zhatian.lsx.GUI;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminManageStus extends JFrame implements ActionListener{

	//注意命名规范
	private JButton addNewStu;
	private JButton modifyStu;
	private JButton return1;
	/***
	 * 
	 * 管理严管理学生的逻辑界面。
	 * 管理学生相关的功能会全部在这里以按钮的形式显示出来，供管理员选择
	 */
	public AdminManageStus(){
		//设置窗体的基本属性，大小和初始位置。以及窗口左上角的图标
		this.setLayout(null);
		this.setTitle("炸天学生签到系统_管理学生信息");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置背景
		ImageIcon imageBg = new ImageIcon("resource\\admin_resouces\\loginBg.jpg");
		JLabel bgLable = new JLabel(imageBg);
		//背景布局为空
		bgLable.setLayout(null);
		//获取当前窗体对象
		Container c = this.getContentPane();
		bgLable.setBounds(0, 0, imageBg.getIconWidth(), imageBg.getIconHeight());
		c.add(bgLable);
		
		//设置左上角图片文件
		ImageIcon liftIicon = new ImageIcon("resource\\admin_resouces\\ico1.jpg"); 
		//将左上角图标换做自定义图标
		this.setIconImage(liftIicon.getImage());
		
		JLabel Uplabel = new JLabel("管理学生信息");
		Uplabel.setFont(new Font("楷书",Font.BOLD,25));
		Uplabel.setBounds(175,115,160,70);
		bgLable.add(Uplabel);
		//---------------------------创建三个按钮----------------------------------
		addNewStu = new JButton("添加新的学生");
		addNewStu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		addNewStu.setBounds(190,183,130,45);
		addNewStu.addActionListener(this);
		
		modifyStu = new JButton("修改学生信息");
		modifyStu.setFont(new Font("微软雅黑", Font.BOLD, 14));
		modifyStu.setBounds(190,253,130,45);
		modifyStu.addActionListener(this);
		
		return1 = new JButton("返回上一级");
		return1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		return1.setBounds(190,323,130,45);
		return1.addActionListener(this);
				
		
		
		//---------------------创建一个盘子----------------------------
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
	
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(addNewStu);
		panel.add(modifyStu);
		panel.add(return1);
		
		bgLable.add(panel);
		this.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addNewStu){
			new AdminAddNewStu();
			this.dispose();
		}else if(e.getSource() == modifyStu){
			new AdminModifyStu();
			this.dispose();
			
		}else if(e.getSource() == return1){
			new AdminLogicOpera();
			this.dispose();
		}
		
	}
	public static void main(String[] args) {
		new AdminManageStus();
	}

}
