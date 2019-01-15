package zhanlang.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import zhanlang.DAO.UserDAO;
import zhanlang.DAO.Imple.UserDAOImple;
import zhanlang.entity.Book;

public class U_BorrowBook2 extends JFrame {
	
	private UserDAO ud = new UserDAOImple();
	public List<Book> bookListByType = null;

	private JPanel contentPane;
	private JTextField inputID;
	private JLabel b1;
	private JLabel b2;
	private JLabel b3;
	private JLabel b4;
	private JLabel b5;
	private JLabel b6;
	private JLabel b7;
	private JLabel b8;
	static String bookID;
	private JButton renwen;
	String flag="1";
//	private JMenuItem menu1 = new JMenuItem("");
//	private JMenuItem menu2 = new JMenuItem("");
//	private JMenuItem menu3 = new JMenuItem("");
//	private JMenuItem menu4 = new JMenuItem("");
//	private JMenuItem menu5 = new JMenuItem("");
//	private JMenuItem menu6 = new JMenuItem("");
//	private JMenuItem menu7 = new JMenuItem("");
//	private JMenuItem menu8 = new JMenuItem("");
	/**
	 * Create the frame.
	 */
	public U_BorrowBook2() {
		setTitle("浏览页面");
		/***/
		setBackground(new Color(189, 183, 107));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/**创建用户页面窗体，大小900*600  */
		setBounds(100, 100,900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 298, 576, 2);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(140, 482, 576, 2);
		contentPane.add(scrollPane_1);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/输入框.jpg")));
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_8.setBounds(237, 87, 108, 23);
		contentPane.add(label_8);
		
		inputID = new JTextField();
		inputID.setBounds(343, 89, 175, 21);
		contentPane.add(inputID);
		inputID.setColumns(10);
		
		JButton button = new JButton("搜索");
		button.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/搜索.jpg")));
		button.setBackground(SystemColor.menu);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookID = inputID.getText();
				System.out.println("=============:" + bookID);
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				Client.uBorrowbookFrame.dispose();
			}
		});
		button.setBounds(529, 87, 93, 23);
		contentPane.add(button);
		
		b1 = new JLabel("1");
		b1.setFont(new Font("黑体", Font.BOLD, 14));
		b1.setBounds(136, 147, 16, 15);
		contentPane.add(b1);
		
		b2 = new JLabel("2");
		b2.setFont(new Font("黑体", Font.BOLD, 14));
		b2.setBounds(280, 147, 16, 15);
		contentPane.add(b2);
		
		b5 = new JLabel("5");
		b5.setFont(new Font("黑体", Font.BOLD, 14));
		b5.setBounds(136, 332, 16, 15);
		contentPane.add(b5);
		
		b6 = new JLabel("6");
		b6.setFont(new Font("黑体", Font.BOLD, 14));
		b6.setBounds(280, 332, 16, 15);
		contentPane.add(b6);
		
		b3 = new JLabel("3");
		b3.setFont(new Font("黑体", Font.BOLD, 14));
		b3.setBounds(434, 147, 16, 15);
		contentPane.add(b3);
		
		b4 = new JLabel("4");
		b4.setFont(new Font("黑体", Font.BOLD, 14));
		b4.setBounds(586, 147, 16, 15);
		contentPane.add(b4);
		
		b7 = new JLabel("7");
		b7.setFont(new Font("黑体", Font.BOLD, 14));
		b7.setBounds(434, 332, 16, 15);
		contentPane.add(b7);
		
		b8 = new JLabel("8");
		b8.setFont(new Font("黑体", Font.BOLD, 14));
		b8.setBounds(586, 332, 16, 15);
		contentPane.add(b8);
		
		JMenuItem menu1 = new JMenuItem("");
		menu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				
				
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/1.png")));
				Client.uBorrowbookFrame2.dispose();
				
			}
		});
//		menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/1.png")));
		menu1.setBounds(150, 147, 95, 120);
		contentPane.add(menu1);
		
		JMenuItem menu2 = new JMenuItem("");
		menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/2.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/2.png")));
		menu2.setBounds(295, 147, 95, 120);
		contentPane.add(menu2);
		
		JMenuItem menu3 = new JMenuItem("");
		menu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/3.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/3.png")));
		menu3.setBounds(454, 147, 95, 120);
		contentPane.add(menu3);
		
		JMenuItem menu4 = new JMenuItem("");
		menu4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/4.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/4.png")));
		menu4.setBounds(603, 147, 95, 120);
		contentPane.add(menu4);
		
		JMenuItem menu5 = new JMenuItem("");
		menu5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/5.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/5.png")));
		menu5.setBounds(150, 332, 95, 120);
		contentPane.add(menu5);
		
		JMenuItem menu6 = new JMenuItem("");
		menu6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/6.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/6.png")));
		menu6.setBounds(295, 332, 95, 120);
		contentPane.add(menu6);
		
		JMenuItem menu7 = new JMenuItem("");
		menu7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				if(flag.equals("2")){
					System.out.println("1");
					U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/11.png")));
					
				}else{
					System.out.println("2");
					U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/7.png")));
				}
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/7.png")));
		menu7.setBounds(454, 332, 95, 120);
		contentPane.add(menu7);
		
		JMenuItem menu8 = new JMenuItem("");
		menu8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.uBookMessageFrame = new U_BookMessage();
				Client.uBookMessageFrame.setVisible(true);	
				Client.uBookMessageFrame.setLocationRelativeTo(null);
				U_BookMessage.booklable.setIcon(new ImageIcon(UserMenu.class.getResource("/img/8.png")));
				Client.uBorrowbookFrame2.dispose();
			}
		});
		menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/img/8.png")));
		menu8.setBounds(596, 332, 95, 120);
		contentPane.add(menu8);
		
		JButton ertong = new JButton("");
		ertong.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/儿童读物.jpg")));
		ertong.setBackground(SystemColor.menu);
		ertong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","儿童文学"));
				System.out.println("1.这个是空吗？=============" + U_BorrowBook2.class.getResource(bookListByType.get(0).getImage()));
				System.out.println("2.这个是空吗？===============" + bookListByType.get(0));
				System.out.println("3.这个是空吗？=============" + bookListByType.get(0).getImage());
				System.out.println("4.这个是空吗？=============" + bookListByType);
				
				System.out.println("5.这个是空吗？=============" + U_BorrowBook2.class.getResource("D:\\workspace\\百战程序员\\ZhanLang_Libary\\src\\img\\1.png"));
				System.out.println("6.这个是空吗？=============" + U_BorrowBook2.class.getResource("/img/"+bookListByType.get(0).getImage()));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
//				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
//				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
//				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));	
				b6.setText("");
				b7.setText("");
				b8.setText("");
			}
		});
		ertong.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		ertong.setBounds(108, 26, 83, 23);
		contentPane.add(ertong);
		
		renwen = new JButton("");
		renwen.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/人文社科.jpg")));
		renwen.setBackground(SystemColor.menu);
		renwen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="2";
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","人文社科"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));
			}
		});
		renwen.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		renwen.setBounds(207, 26, 75, 23);
		contentPane.add(renwen);
		
		JButton qingchun = new JButton("");
		qingchun.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/青春励志.jpg")));
		qingchun.setBackground(SystemColor.menu);
		qingchun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="3";
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","青春励志"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));	
			}
		});
		qingchun.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		qingchun.setBounds(300, 26, 75, 23);
		contentPane.add(qingchun);
		
		JButton wangluo = new JButton("");
		wangluo.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/网络文学.jpg")));
		wangluo.setBackground(SystemColor.menu);
		wangluo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="4";
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","网络文学"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));	
			}
		});
		wangluo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		wangluo.setBounds(486, 26, 75, 23);
		contentPane.add(wangluo);
		
		JButton gudian = new JButton("");
		gudian.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/古典文学.jpg")));
		gudian.setBackground(SystemColor.menu);
		gudian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="5";
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","古典文学"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));	
			}
		});
		gudian.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		gudian.setBounds(571, 26, 75, 23);
		contentPane.add(gudian);
		
		JButton kongbu = new JButton("");
		kongbu.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/恐怖悬疑.jpg")));
		kongbu.setBackground(SystemColor.menu);
		kongbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag="6";
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","恐怖悬疑"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));
				menu2.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(1).getImage())));
				menu3.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(2).getImage())));
				menu4.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(3).getImage())));
				menu5.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(4).getImage())));
				menu6.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(5).getImage())));
				menu7.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(6).getImage())));
				menu8.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(7).getImage())));
			}
		});
		kongbu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		kongbu.setBounds(657, 26, 83, 23);
		contentPane.add(kongbu);
		
		JLabel book1 = new JLabel("智能时代");
		book1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book1.setBounds(160, 273, 54, 15);
		contentPane.add(book1);
		
		JLabel book2 = new JLabel("智能时代");
		book2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book2.setBounds(305, 277, 54, 15);
		contentPane.add(book2);
		
		JLabel book3 = new JLabel("智能时代");
		book3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book3.setBounds(454, 277, 54, 15);
		contentPane.add(book3);
		
		JLabel book4 = new JLabel("智能时代");
		book4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book4.setBounds(603, 277, 54, 15);
		contentPane.add(book4);
		
		JLabel book5 = new JLabel("智能时代");
		book5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book5.setBounds(160, 456, 54, 15);
		contentPane.add(book5);
		
		JLabel book6 = new JLabel("智能时代");
		book6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book6.setBounds(305, 456, 54, 15);
		contentPane.add(book6);
		
		JLabel book7 = new JLabel("智能时代");
		book7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book7.setBounds(464, 456, 54, 15);
		contentPane.add(book7);
		
		JLabel book8 = new JLabel("智能时代");
		book8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		book8.setBounds(603, 462, 54, 15);
		contentPane.add(book8);
		
		JButton zhenguan = new JButton("");
		zhenguan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookListByType = ud.selectByType("selectByType", new Book("1","张三","镇馆之宝"));
				menu1.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/image1/"+bookListByType.get(0).getImage())));   
			}
		});
		zhenguan.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/镇馆之宝.jpg")));
		zhenguan.setBounds(385, 26, 83, 23);
		contentPane.add(zhenguan);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(U_BorrowBook2.class.getResource("/picture/text/浏览图书背景.jpg")));
		label.setBounds(0, 0, 884, 562);
		contentPane.add(label);
		
		
	}
	
}
