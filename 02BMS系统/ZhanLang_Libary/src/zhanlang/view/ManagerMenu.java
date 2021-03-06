package zhanlang.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.Messaging.SyncScopeHelper;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Window.Type;
import javax.swing.JMenuItem;

public class ManagerMenu extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ManagerMenu() {
		setFont(new Font("微软雅黑", Font.BOLD, 18));
		setTitle("BMS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("图书管理");
		menu.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("添加图书");
		menuItem.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mAddBookFrame = new M_AddBook();
				Client.mAddBookFrame.setVisible(true);	
				Client.mAddBookFrame.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem menuItem_6 = new JMenuItem("借阅信息");
		menuItem_6.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.ShowBorrowMessage = new M_ShowBorrowMessage();
				Client.ShowBorrowMessage.setVisible(true);	
				Client.ShowBorrowMessage.setLocationRelativeTo(null);
			}
		});
		menu.add(menuItem_6);
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("删除图书");
		menuItem_1.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mDeleteBookFrame= new M_DeleteBook();
				Client.mDeleteBookFrame.setVisible(true);	
				Client.mDeleteBookFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("用户管理");
		menu_1.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("添加用户");
		menuItem_2.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mAddUserFrame= new M_AddUser();
				Client.mAddUserFrame.setVisible(true);	
				Client.mAddUserFrame.setLocationRelativeTo(null);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("删除用户");
		menuItem_3.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mDeleteUserFrame= new M_DeleteUser();
				Client.mDeleteUserFrame.setVisible(true);	
				Client.mDeleteUserFrame.setLocationRelativeTo(null);
			}
		});
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("修改密码");
		menuItem_4.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.mModifyUserPwdFrame= new M_ModifyUserPwd();
				Client.mModifyUserPwdFrame.setVisible(true);
				Client.mModifyUserPwdFrame.setLocationRelativeTo(null);
			}
		});
		menu_1.add(menuItem_4);
		
		JMenu menu_2 = new JMenu("帮助");
		menu_2.setFont(new Font("楷体", Font.BOLD, 15));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_5 = new JMenuItem("关于我们");
		menuItem_5.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.about=new M_About();
				Client.about.setVisible(true);
				Client.about.setLocationRelativeTo(null);
			}
		});
		menu_2.add(menuItem_5);
		
		JMenu menu_3 = new JMenu("退出");
		menu_3.setFont(new Font("楷体", Font.BOLD, 15));
		menu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.loginMenuFrame = new LoginMenu();	
				Client.loginMenuFrame.setVisible(true);	
				Client.loginMenuFrame.setLocationRelativeTo(null);
				Client.managerMenuFrame.dispose();
			}
		});
		menuBar.add(menu_3);
		
		JMenuItem menuItem_7 = new JMenuItem("退出登录");
		menuItem_7.setFont(new Font("楷体", Font.BOLD, 14));
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.loginMenuFrame= new LoginMenu();
				Client.loginMenuFrame.setVisible(true);	
				Client.loginMenuFrame.setLocationRelativeTo(null);
				Client.managerMenuFrame.dispose();
			}
		});
		menu_3.add(menuItem_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(676, 10, 93, 77);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ManagerMenu.class.getResource("/picture/text/客户端背景.jpg")));
		label_1.setBounds(0, 0, 884, 541);
		contentPane.add(label_1);
	}
}
