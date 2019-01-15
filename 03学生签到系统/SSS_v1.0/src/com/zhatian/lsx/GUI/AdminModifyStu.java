package com.zhatian.lsx.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zhatian.Tinner.entity.Student;
import com.zhatian.kraken.control.AdminAction;

public class AdminModifyStu extends JFrame implements ActionListener {
	private Student student1;
	private JButton selectById;
	private JButton selectByUserName;
	private JButton modify;
	private JButton del;
	private JButton return1;
	private JPanel panel;
	//设置布局用变量
	private final int fontSize = 18;
	private final int fieldSizeY = 25;
	private final int fieldSizeX = 140;
	private final int labelX = 135;
	private final int fieldX = 200;
	
	
	private JTextField userNameField;
	private JTextField pwdField;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField ageField;
	private JTextField clazzField;
	private JTextField idField;
	/***
	 * 
	 * 管理员修改学生信息操作
	 * 管理员在该功能中，可以通过学生ID和学生用户名两种方式进行检索。
	 * 检索到对应学生之后，该学生的所有信息将会输出到屏幕文本框中
	 * 管理员可以直接对显示出来的该学生对象信息进行修改，或者删除
	 */
	public AdminModifyStu() {

		this.setLayout(null);
		this.setTitle("炸天学生签到系统_修改学生信息");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon imageBg = new ImageIcon("resource\\admin_resouces\\loginBg.jpg");
		JLabel bgLable = new JLabel(imageBg);
		//背景在这个框架中会被作为桌布，所以需要设置布局为空
		bgLable.setLayout(null);
		bgLable.setBounds(0, 0, imageBg.getIconWidth(), imageBg.getIconHeight());
		// 获取窗体对象
		Container c = this.getContentPane();
		c.setLayout(null);
		c.add(bgLable);
		// 设置左上角图片文件
		ImageIcon liftIicon = new ImageIcon("resource\\admin_resouces\\ico1.jpg");
		// 将左上角图标换做自定义图标
		this.setIconImage(liftIicon.getImage());
		
		// --------------------------创建label-----------------------------------
		JLabel Uplabel = new JLabel("修改学生信息");
		Uplabel.setFont(new Font("楷书", Font.BOLD, 28));
		Uplabel.setBounds(165, 0, 210, 60);
		bgLable.add(Uplabel);
		// 弹窗用panel
		panel = new JPanel();
		// ---------------------------创建六个 标签 六个文本框  和  两个查找按钮----------------------------
		JLabel idLabel = new JLabel("    ID：");
		idLabel.setFont(new Font("", Font.PLAIN, fontSize));
		idLabel.setBounds(labelX, 140, 65, 22);
		idField = new JTextField(20);
		idField.setBounds(fieldX, 140, fieldSizeX, fieldSizeY);
		selectById = new JButton("查找");
		selectById.setFont(new Font("", Font.PLAIN, 16));
		selectById.setBounds(355, 140, 70, 25);
		selectById.addActionListener(this);
		bgLable.add(selectById);
		bgLable.add(idLabel);
		bgLable.add(idField);

		
		JLabel msgLabel1 = new JLabel("米");
		msgLabel1.setForeground(Color.red);
		msgLabel1.setFont(new Font("", Font.PLAIN, 8));
		msgLabel1.setBounds(200,117,10,20);
		bgLable.add(msgLabel1);
		JLabel msgLabel = new JLabel("ID无法修改");
		
		msgLabel.setFont(new Font("", Font.PLAIN, 12));
		msgLabel.setForeground(Color.red);
		msgLabel.setBounds(210,115,120,25);
		bgLable.add(msgLabel);
		
		JLabel useNameLabel = new JLabel("用户名：");
		useNameLabel.setFont(new Font("", Font.BOLD, fontSize));
		useNameLabel.setBounds(115, 170, 100, 22);
		userNameField = new JTextField(20);
		userNameField.setBounds(fieldX,170, fieldSizeX, fieldSizeY);
		selectByUserName = new JButton("查找");
		selectByUserName.setFont(new Font("", Font.PLAIN, 16));
		selectByUserName.setBounds(355, 170, 70, 25);
		selectByUserName.addActionListener(this);
		bgLable.add(useNameLabel);
		bgLable.add(userNameField);
		bgLable.add(selectByUserName);

		JLabel pwdLabel = new JLabel("密码：");
		pwdLabel.setFont(new Font("", Font.BOLD, fontSize));
		pwdLabel.setBounds(labelX, 200, 65, 22);
		pwdField = new JTextField(20);
		pwdField.setBounds(fieldX, 200, fieldSizeX, fieldSizeY);
		bgLable.add(pwdLabel);
		bgLable.add(pwdField);

		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("", Font.BOLD, fontSize));
		nameLabel.setBounds(labelX, 230, 65, 22);
		nameField = new JTextField(20);
		nameField.setBounds(fieldX, 230, fieldSizeX, fieldSizeY);
		bgLable.add(nameLabel);
		bgLable.add(nameField);

		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setFont(new Font("", Font.BOLD, fontSize));
		sexLabel.setBounds(labelX, 260, 65, 22);
		sexField = new JTextField(20);
		sexField.setBounds(fieldX, 260, fieldSizeX, fieldSizeY);
		bgLable.add(sexLabel);
		bgLable.add(sexField);

		JLabel ageLabel = new JLabel("年龄：");
		ageLabel.setFont(new Font("", Font.BOLD, fontSize));
		ageLabel.setBounds(labelX, 290, 65, 22);
		ageField = new JTextField(20);
		ageField.setBounds(fieldX, 290, fieldSizeX, fieldSizeY);
		bgLable.add(ageLabel);
		bgLable.add(ageField);

		JLabel clazzLabel = new JLabel("班级：");
		clazzLabel.setFont(new Font("", Font.BOLD, fontSize));
		clazzLabel.setBounds(labelX, 320, 65, 22);
		clazzField = new JTextField(20);
		clazzField.setBounds(fieldX, 320, fieldSizeX, fieldSizeY);
		bgLable.add(clazzLabel);
		bgLable.add(clazzField);

		// ------------------------再创建三个最下面的按钮---------------------------

		modify = new JButton("确认修改");
		modify.setFont(new Font("", Font.BOLD, 10));
		modify.setBounds(110, 360, 78, 35);
		modify.addActionListener(this);

		del = new JButton("确认删除");
		del.setFont(new Font("", Font.BOLD, 10));
		del.setBounds(205, 360, 78, 35);
		del.addActionListener(this);

		return1 = new JButton("返回");
		return1.setFont(new Font("", Font.PLAIN, 12));
		return1.setBounds(299, 360, 78, 35);
		return1.addActionListener(this);

		bgLable.add(modify);
		bgLable.add(del);
		bgLable.add(return1);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Student student1 = null;
		/*
		 * 在这里定义一个学生变量，其实就是将select1和select2查询到的学生的对象保存起来。在后面的删除当中可以直接用
		 * 因为修改学生信息的时候，必须是重新创建一个对象，将修改过的信息收集起来传递过去。但是删除的操作可以直接用这个查询出来的对象。没有修改
		 */
		/*
		 * 最开始的我的想法是正确的，只不过我的变量声明的位置不对、我放在了actionperform这个方法的最开始。相当于
		 * 每次执行这个actionperform方法的时候都会执行一遍Student student1 = null;而最重要的是，我没有意识到的
		 * 问题是，每次点击一个按钮都是相当于一个独立的鼠标事件。都会从actionperform重新的执行。所以我把这个放在这里是错误的
		 * 应该把通过查找获得的学生对象放在这个类的私有域当中。这样才对。
		 */

		if (e.getSource() == selectById) {
			// 管理员想要通过id查找学生，所以我们从输入文本框得到用户输入的id
			String idString = idField.getText();
			// 类型转换
			int id = Integer.parseInt(idString);
			// 调用服务器端的adminFindStudentById方法。得到一个学生类型的对象
			// 然后把得到的对象赋值给最开始的那个私有域的student1
			student1 = AdminAction.adminFindStudentById(id);
			// 将查询到的信息显示在屏幕上。注意JTextField的setText方法的参数必须是string类型。所以要进行类型转换
			// 这个字符串类型转换方法真的是很6
			/*
			 * 因为在服务器端。这个AdminAction.adminFindStudentById(id)方法是一定会给我返回一个学生对象的
			 * 服务器端对这个学生对象的初始赋值为null。靳平的dao层也是这样的方式，所以这只需要进行一个是否为null的判断
			 * 
			 */
			if (student1 == null) {
				JOptionPane.showMessageDialog(panel, "系统中并无此ID用户！请检查！");
				idField.setText("");
			} else {
				idField.setText("" + student1.getStudent_Id());
				pwdField.setText(student1.getStudent_Password());
				userNameField.setText(student1.getStudent_Username());
				nameField.setText(student1.getStudent_Name());
				sexField.setText(student1.getStudent_Sex());
				ageField.setText(student1.getStudent_Age());
				clazzField.setText(student1.getStudent_Classroom());
			}

		} else if (e.getSource() == selectByUserName) {
			// 同上，管理员想要通过用户名查找。那么就获得用户的用户名
			String name = userNameField.getText();
			// 通过用户名查找该学生信息，返回该学生信息。
			student1 = AdminAction.adminFindStudentByUsername(name);
			
			if (student1 == null) {
				JOptionPane.showMessageDialog(panel, "系统中并无此用户名用户！请检查！");
				userNameField.setText("");
			} else {
				// 将返回的学生信息进行展示。和上面的语句一样
				idField.setText("" + student1.getStudent_Id());
				pwdField.setText(student1.getStudent_Password());
				userNameField.setText(student1.getStudent_Username());
				nameField.setText(student1.getStudent_Name());
				sexField.setText(student1.getStudent_Sex());
				ageField.setText(student1.getStudent_Age());
				clazzField.setText(student1.getStudent_Classroom());

			}
			/*
			 * 这个时候其实屏幕上已经显示了一个学生的信息了，我们想要将服务器的信息修改，就需要我们把修改过的信息传递给服务器。
			 * 然后服务器在接收到修改之后的学生信息之后，再次执行覆盖写入即可
			 */
		} else if (e.getSource() == modify) {
			
			// 当点击了这个按钮的时候，首先我们应该把文本框里的信息获取过来。
			student1.setStudent_Username(userNameField.getText());
			student1.setStudent_Password(pwdField.getText());
			student1.setStudent_Name(nameField.getText());
			student1.setStudent_Sex(sexField.getText());
			student1.setStudent_Age(ageField.getText());
			student1.setStudent_Classroom(clazzField.getText());
//			String id = idField.getText();
//			int id1 = Integer.parseInt(id);
//			String userName = userNameField.getText();
//			String pwd = pwdField.getText();
//			String name = nameField.getText();
//			String sex = sexField.getText();
//			String age = ageField.getText();
//			String clazz = clazzField.getText();
			// 获取信息完毕，创建一个新的对象。然后再传给服务器。让服务器将这个新的对重现写入XML或数据库
			//Student student = new Student(id1, userName, pwd, name, sex, age, clazz);
			// 调用服务器端的修改方法。
			boolean flag = AdminAction.adminChangeStudent(student1);
			if (flag) {
				JOptionPane.showMessageDialog(panel, "修改成功！！");
				clearField();
			} else {
				JOptionPane.showMessageDialog(panel, "修改失败，请重新操作！");
				// 保留住这个重置代码。因为还不知道什么情况会修改失败。所以如果继续修改的话，清空之后就相当于麻烦 了，还得一个一个重新写入
				clearField();
			}
		} else if (e.getSource() == del) {

			if (student1 == null) {
				JOptionPane.showMessageDialog(panel, "此用户信息为空！！");
				clearField();
			} else {
				boolean flag = AdminAction.adminDeleteStudent(student1);
				if (flag) {
					JOptionPane.showMessageDialog(panel, "删除成功！！");
					clearField();
				} else {
					JOptionPane.showMessageDialog(panel, "删除失败，请重新操作！");
					clearField();
				}
			}

		} else if (e.getSource() == return1) {
			new AdminManageStus();
			this.dispose();
		}
	}

	/**
	 * 重置文本域方法
	 * 当用户输入数据有误需要重新输入的时候，自动重置文本域的信息为空。方便用户再次输入
	 * 将这八条语句提出来。避免了每次都需要写八条语句的繁琐
	 */
	public void clearField(){
		idField.setText("");
		userNameField.setText("");
		pwdField.setText("");
		nameField.setText("");
		sexField.setText("");
		ageField.setText("");
		clazzField.setText("");
	}
	
	
	public static void main(String[] args) {
		new AdminModifyStu();
	}

}
