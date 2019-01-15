package com.sxt.swing;

import javax.swing.ImageIcon;
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

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 登陆页面窗口绘制
 * @author penglijun
 *
 */
public class LoginSwing extends JFrame implements MouseListener, DocumentListener{
	/**
	 * 用户登录界面
	 */

	private JPanel contentPane;//内容面板
	private JTextField idField;//用户名输入框
	private TextField passwordField;//用户密码框
	private JButton regButton;//注册按钮
	private JButton loginButton;//登录按钮
	private JLabel lblNewLabel_1;
	private ImageIcon icon;	//头像显式框

/**
 * 登录界面初始化
 */
	public LoginSwing() {
		setTitle("N.Korea聊天室");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginSwing.class.getResource("/com/sxt/swing/images/imgp/weixiao.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 387);
	
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		idField = new JTextField("用户名/账号");
		idField.setColumns(10);
		idField.setForeground(Color.LIGHT_GRAY);
		idField.setBounds(161, 181, 237, 32);
		idField.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		Document doc = idField.getDocument();
		doc.addDocumentListener(this);
		idField.addMouseListener((MouseListener) this);
		contentPane.add(idField);
		
		passwordField = new TextField("密码");
		passwordField.setColumns(10);
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setBounds(161, 241, 237, 32);
		passwordField.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		passwordField.addMouseListener(this);
		contentPane.add(passwordField);
		
		loginButton = new JButton();
		loginButton.setBounds(298, 301, 100, 40);
		loginButton.setIcon(new ImageIcon(LoginSwing.class.getResource("/com/sxt/swing/images/imgp/ddll.jpg")));
		contentPane.add(loginButton);
		
		regButton = new JButton();
		regButton.setBounds(162, 301, 100, 40);
		regButton.setIcon(new ImageIcon(LoginSwing.class.getResource("/com/sxt/swing/images/imgp/zcdl.PNG")));
		contentPane.add(regButton);
		//最上端图片
		JLabel background = new JLabel();
		Image image1 = new ImageIcon("/ChatRoom_Client/src/com/sxt/swing/images/imgp/emoji.png").getImage();
		background.setBounds(0, 0, 423, 159);
		background.setIcon(new ImageIcon(LoginSwing.class.getResource("/com/sxt/swing/images/imgp/bbjjj.gif")));
		contentPane.add(background);
		
		
		lblNewLabel_1 = new JLabel();
		icon =new ImageIcon("res/headimg/11.jpg");
		lblNewLabel_1.setIcon(icon);
		lblNewLabel_1.setBounds(33, 181, 106, 139);
		contentPane.add(lblNewLabel_1);
		
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JButton getLoginButton() {
		return loginButton;
	}


	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}


	public JTextField getIdField() {
		return idField;
	}


	public void setIdField(JTextField idField) {
		this.idField = idField;
	}


	public TextField getPasswordField() {
		return passwordField;
	}


	public void setPasswordField(TextField passwordField) {
		this.passwordField = passwordField;
	}


	public JButton getRegButton() {
		return regButton;
	}


	public void setRegButton(JButton regButton) {
		this.regButton = regButton;
	}
	 
    //输入框的鼠标监听事件
	@Override
	public void mouseClicked(MouseEvent e) {
		 if(e.getSource()==idField)
	      {
	    	  idField.setText("");
	    	  idField.setForeground(Color.BLACK);
	      }
	      else if(e.getSource()==passwordField)
	      {
	    	  passwordField.setText("");
	    	  passwordField.setForeground(Color.BLACK);
	    	  passwordField.setEchoChar('●');
	      }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

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
					String src = users.getImg();
					icon = new ImageIcon("res/headimg/" + src);
					icon.setImage(icon.getImage().getScaledInstance(106, 119, Image.SCALE_DEFAULT));
					lblNewLabel_1.setIcon(icon);
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
