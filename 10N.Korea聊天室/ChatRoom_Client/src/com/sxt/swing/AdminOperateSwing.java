package com.sxt.swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.sxt.request.Communication;
import com.sxt.vo.TransMsg;
import com.sxt.vo.Users;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
/**
 * 管理员操作界面
 * @author gabriel
 *
 */
public class AdminOperateSwing extends JFrame implements ActionListener, DocumentListener{
	private JPanel contentPane;
	private JTextField deleteField;	//删除用户ID的输入框
	private JPanel delPanel;
	private CardLayout cardLayout;
	private JTextField modifyIDFiled;	//修改用户ID的输入框
	private JTextField modifyPasswordField;	//修改用户密码的输入框
	private JTextField textField_1;		//查找用户id的输入框
	private JButton selectUserButton;
	private JButton allUserButton;
	private JButton delButton;
	private JButton modifyButton;
	private JButton adminButton;
	private JButton selectButton;
	private JPanel operatePanel;
	private JTextField usernameFiled;	//修改用户昵称的输入框
	
	private JTextPane allUserTextPane;	//显式所有用户的内容面板
	private JButton delUserButton;		//确认删除按钮
	private JButton btnNewButton;		//修改按钮
	private JCheckBox adminCheckBox;	//修改用户是否能够拥有管理员权限
	private JTextPane textPane;			//查找用户的信息显式区
	private JLabel lblNewLabel_1;



	/**
	 * Create the frame.
	 */
	public AdminOperateSwing() {
		setTitle("N.Korea聊天室");
		setForeground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//查看全部用户
		 allUserButton = new JButton();
		 allUserButton.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/syyh.jpg")));
		 allUserButton.addActionListener(this);
		allUserButton.setBounds(2, 134, 153, 23);
		contentPane.add(allUserButton);
		//删除用户按钮
		 delButton = new JButton();
		 delButton.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/scyh.jpg")));
		 delButton.addActionListener(this);
		delButton.setBounds(2, 206, 153, 23);
		contentPane.add(delButton);
		//修改用户按钮
		 modifyButton = new JButton();
		 modifyButton.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/xgyh.jpg")));
		 modifyButton.addActionListener(this);
		modifyButton.setBounds(2, 182, 153, 23);
		contentPane.add(modifyButton);
		//查找用户
		 selectButton = new JButton();
		 selectButton.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/czyh.jpg")));
		selectButton.addActionListener(this);
		selectButton.setBounds(2, 158, 153, 23);
		contentPane.add(selectButton);
		//管理员操作	
		 adminButton = new JButton();
		 adminButton.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/xxyh.jpg")));
		// TODO 管理员此处缺监听事件
		 adminButton.setBounds(2, 253, 153, 23);
		contentPane.add(adminButton);
		//设置背景
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon(AdminOperateSwing.class.getResource("/com/sxt/swing/images/imgp/gljm.jpg")));
		lblNewLabel_1.setBounds(0, 0, 762, 541);
		contentPane.add(lblNewLabel_1);
				
		//新的容器，用于显示点击按钮时的显示界面
		 operatePanel = new JPanel();
			operatePanel.setBounds(180, 102,580,417);
			contentPane.add(operatePanel);
			//卡牌布局
			 cardLayout=new CardLayout(0, 0);
			operatePanel.setLayout(cardLayout);
		
		
		//查看全部用户模块
		allUserTextPane=new JTextPane();
		allUserTextPane.setContentType("text/pain");
		allUserTextPane.setBackground(new Color(176, 224, 230));
		allUserTextPane.setForeground(new Color(65, 105, 225));
		allUserTextPane.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		//查看全部用户显示
		JScrollPane scrollPane = new JScrollPane(allUserTextPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		operatePanel.add(scrollPane, "1");
		
			cardLayout.last(operatePanel);
			 
			 //查找用户		
			 JPanel selectPanel = new JPanel();
			 selectPanel.setBackground(new Color(176, 224, 230));
			 selectPanel.setLayout(null);
			 operatePanel.add(selectPanel, "4"); 
				 textField_1 = new JTextField();
				 textField_1.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 textField_1.setBounds(190, 90, 153, 30);
				 selectPanel.add(textField_1);
				 textField_1.setColumns(10);
				 
				 		JLabel label_3 = new JLabel("账   号");
				 		label_3.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 		label_3.setBounds(100, 90, 118, 31);
				 		selectPanel.add(label_3);
				 		
				 		selectUserButton = new JButton("查找");
				 		selectUserButton.setForeground(new Color(255, 255, 255));
				 		selectUserButton.setBackground(new Color(102, 153, 204));
				 		selectUserButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
				 		selectUserButton.setBounds(375, 90, 100, 40);
				 		selectUserButton.addActionListener(this);
				 		selectPanel.add(selectUserButton);
				 		
				 		textPane = new JTextPane();
				 		textPane.setForeground(new Color(0, 0, 0));
				 		textPane.setFont(new Font("微软雅黑 Light", Font.BOLD, 26));
				 		textPane.setBackground(new Color(173, 216, 230));
				 		textPane.setBounds(0, 200, 580, 217);
						cardLayout.last(operatePanel);
				 		selectPanel.add(textPane);
				 //修改模块
				 JPanel modifyPanel = new JPanel();
				 operatePanel.add(modifyPanel, "3");
				 modifyPanel.setBackground(new Color(176, 224, 230));
				 modifyPanel.setLayout(null);
				 
				 JLabel lblNewLabel = new JLabel("账    号");
				 lblNewLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 lblNewLabel.setBounds(150, 115, 93, 36);
				 modifyPanel.add(lblNewLabel);
				 
				 modifyIDFiled = new JTextField();
				 modifyIDFiled.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
				 modifyIDFiled.setBounds(245, 125, 153, 30);
				 Document doc = modifyIDFiled.getDocument();
				 doc.addDocumentListener(this);
				 modifyPanel.add(modifyIDFiled);
				 modifyIDFiled.setColumns(10);
				 
				 JLabel label_6 = new JLabel("昵    称");
				 label_6.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 label_6.setBounds(150, 155, 93, 36);
				 modifyPanel.add(label_6);
				 
				 usernameFiled = new JTextField();
				 usernameFiled.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 usernameFiled.setColumns(10);
				 usernameFiled.setBounds(245, 165, 153, 30);
				 modifyPanel.add(usernameFiled);
				 
				 JLabel label = new JLabel("密    码");
				 label.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 label.setBounds(150, 192, 93, 36);
				 modifyPanel.add(label);
				 
				 modifyPasswordField = new JTextField();
				 modifyPasswordField.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 modifyPasswordField.setBounds(245, 201, 153, 30);
				 modifyPanel.add(modifyPasswordField);
				 
				 //是否设为管理员
				 adminCheckBox = new JCheckBox("是否设为管理员");
				 adminCheckBox.setBackground(new Color(173, 216, 230));
				 adminCheckBox.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				 adminCheckBox.setBounds(200, 240, 200, 30);
				 modifyPanel.add(adminCheckBox);
				 
				 btnNewButton = new JButton("修    改");
				 btnNewButton.setForeground(new Color(255, 255, 255));
				 btnNewButton.setBackground(new Color(40,96,144));
				 btnNewButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
				 btnNewButton.setBounds(280, 290, 100, 40);
				 btnNewButton.addActionListener(this);
				 modifyPanel.add(btnNewButton);
				 
				 	
				 	
				 //删除模块
				  delPanel = new JPanel();
				  delPanel.setLayout(null);
				  delPanel.setBounds(180, 102, 580, 417);
				  delPanel.setLayout(null);
				  delPanel.setBackground(new Color(176, 224, 230));
				  operatePanel.add(delPanel, "2");
				  
				  JLabel lblid = new JLabel("输入要删除的账号");
				  lblid.setBackground(new Color(0, 191, 255));
				  lblid.setFont(new Font("微软雅黑 Light", Font.BOLD, 22));
				  lblid.setBounds(65, 150, 200, 23);
				  delPanel.add(lblid);
				  
				  deleteField = new JTextField();
				  deleteField.setColumns(10);
				  deleteField.setFont(new Font("微软雅黑 Light", Font.BOLD, 22));
				  deleteField.setBounds(253, 150, 153, 30);
				  delPanel.add(deleteField);
				  
				  delUserButton = new JButton("删  除");
				  delUserButton.setForeground(new Color(255, 255, 255));
				  delUserButton.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
				  delUserButton.setBackground(new Color(40,96,144));
				  delUserButton.setBounds(280, 240, 100, 40);
				  delUserButton.addActionListener(this);
				  delPanel.add(delUserButton);
		 cardLayout.show(operatePanel, "1");
     	this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JPanel getDelPanel() {
		return delPanel;
	}

	public void setDelPanel(JPanel delPanel) {
		this.delPanel = delPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		//查看所有用户的监听事件
		if(e.getSource().equals(allUserButton)){
			allUserTextPane.setText("");
			cardLayout.show(operatePanel, "1");
			Communication communication = new Communication();
			communication.sender(new TransMsg("Admin-ShowAllUsers", null));
	    	communication.listener();
	    	List<TransMsg> list01 = communication.getList();
	    	for (TransMsg msg : list01) {
				if (msg.getFunction().equals("Admin-ShowAllUsers")) {
					List<Users> list = (List<Users>)msg.getMsg();
					Iterator<Users> item = list.iterator();
					Users user = null;
					
			        StringBuilder sb=new StringBuilder();
					Document doc = allUserTextPane.getDocument();
					while(item.hasNext()){
						user = item.next();
						sb.append("账    号："+user.getId()+"\r\n");
						sb.append("昵    称：" + user.getName() + "\r\n");
						sb.append("密    码：" + user.getPassword() + "\r\n");
						sb.append("身    份：" + (user.getAdminStatus()==1?"管理员":"普通用户")+ "\r\n");
						sb.append("--------------------------------------------------------------------------------------\r\n");
					}
					allUserTextPane.setText(sb.toString());
				}
			}
	    	if(list01.size() > 0){
	    		list01.remove(list01.size() - 1);
	    	}
		}
		
		//跳转到删除页面的事件
		if(e.getSource().equals(delButton)){
			cardLayout.show(operatePanel, "2");
		}
		
		//删除用户的事件
		if(e.getSource().equals(delUserButton)){
			String id = deleteField.getText();
			if(!"".equals(id)){
				String password = "";
				Users user = new Users(id, password);
				Communication communication = new Communication();
				communication.sender(new TransMsg("Admin-DeleteUsers", user));
		    	communication.listener();
		    	for (TransMsg msg : communication.getList()) {
					if (msg.getFunction().equals("Admin-DeleteUsers")) {
						Users users = (Users)msg.getMsg();
						if(users != null){
							String str = users.getName();
							if("true".equals(str)){
								JOptionPane.showMessageDialog(null, "删除成功！");
								
							}else{
								JOptionPane.showMessageDialog(null, "用户不存在！");
							}
						}else{
							JOptionPane.showMessageDialog(null, "用户不存在！");
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "请输入用户ID！");
		}
	}
		//跳转到修改用户的界面
		if(e.getSource().equals(modifyButton)){
			cardLayout.show(operatePanel, "3");
		}
		
		//修改用户的事件
		if(e.getSource().equals(btnNewButton)){
			String id = modifyIDFiled.getText();
			String name = usernameFiled.getText();
			String password = modifyPasswordField.getText();
			int admin = 0;
			boolean bool = adminCheckBox.isSelected();
			if(bool){
				admin = 1;
			}
			Users user = new Users(id, name, password, admin);
			Communication communication = new Communication();
			communication.sender(new TransMsg("Admin-ModifyUser", user));
		    communication.listener();
		    List<TransMsg> list = communication.getList();
		    Iterator<TransMsg> item = list.iterator();
		    while(item.hasNext()){
		    	TransMsg tm = item.next();
		    	if(tm.getFunction().equals("Admin-ModifyUser")){
		    		Users users = (Users)tm.getMsg();
		    		if("true".equals(users.getName())){
						JOptionPane.showMessageDialog(null, "修改成功！");
					}else{
						JOptionPane.showMessageDialog(null, "修改失败！");
					}
		    		item.remove();
		    	}
		    }
		}
		
		//跳转到查找用户的界面
		if(e.getSource().equals(selectButton)){
			cardLayout.show(operatePanel, "4");
		}
		
		//查找用户的事件
		if(e.getSource().equals(selectUserButton)){
			String id = textField_1.getText();
			String password = "";
			Users user = new Users(id, password);
			Communication communication = new Communication();
			communication.sender(new TransMsg("Admin-SearchUser", user));
		    communication.listener();
		    List<TransMsg> list = communication.getList();
	    	for (TransMsg msg : list) {
				if (msg.getFunction().equals("Admin-SearchUser")) {
					Users users = (Users)msg.getMsg();
					if(users != null){
						String name = users.getName();
						password = users.getPassword();
						int admin = users.getAdminStatus();
						
						StringBuilder sb = new StringBuilder();
						sb.append("账   号：" + id + "\r\n");
						sb.append("昵   称：" + name + "\r\n");
						sb.append("密   码：" + password + "\r\n");
						sb.append("身   份：" + (admin==1?"管理员":"普通用户")+ "\r\n");
						textPane.setText(sb.toString());
					}else{
						textPane.setText(" ");
						JOptionPane.showMessageDialog(null, "查无此人！");
					}
				}
			}
	    	if(list.size() > 0){
	    		list.remove(list.size() - 1);
	    	}
		}
	}

	/**
	 * 插入更新事件
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		Document doc = e.getDocument();  
		String s = "";
        try {
			s = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
        Users user = new Users(s, null);
        Communication communication = new Communication();
		communication.sender(new TransMsg("Admin-FindUser", user));
    	communication.listener();
    	for (TransMsg msg : communication.getList()) {
			if (msg.getFunction().equals("Admin-FindUser")) {
				Users users = (Users)msg.getMsg();
				if(users != null){
					String name = users.getName();
					String password = users.getPassword();
					usernameFiled.setText(name);
					modifyPasswordField.setText(password);
				}
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
}
