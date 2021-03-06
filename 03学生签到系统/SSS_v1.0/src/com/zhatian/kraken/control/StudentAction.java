package com.zhatian.kraken.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import com.zhatian.Tinner.entity.Sign;
import com.zhatian.Tinner.entity.Student;
import com.zhatian.kraken.util.KrakenRecord;
import com.zhatian.kraken.util.KrakenUtil;

/**
 * 学生端服务器连接接口
 * 学生客户端可调用此类中的静态方法连接服务器
 * 
 * @author ask-kraken
 *
 */
public class StudentAction {

	private static Socket socket;
	private static ObjectInputStream input;
	private static ObjectOutputStream output;

	private static void init() {
		//System.out.println("init");
		try {
			socket = new Socket(KrakenUtil.getHostPath(), 9000);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("init-end");
	}

	private static void destroy() throws Exception {
		input.close();
		output.close();
		socket.close();
	}

	/**
	 * 学生注册-0
	 * 
	 * @return true表示成功 false表示失败
	 */
	public static boolean stuRegister(String username, String password, String name, String sex, String age,
			String classroom) {
		KrakenRecord.log("StudentAction:stuRegister");
		boolean result = false;
		try {
			init();
			output.writeInt(0);
			output.flush();
			output.writeUTF(username);
			output.writeUTF(password);
			output.writeUTF(name);
			output.writeUTF(sex);
			output.writeUTF(age);
			output.writeUTF(classroom);

			output.flush();

			result = input.readBoolean();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 学生登录-1 返回学生实体
	 * 
	 * @return 学生实体
	 */
	public static Student studentLogin(String username, String password) {
		KrakenRecord.log("StudentAction:studentLogin");
		Student student = null;
		try {// 获取连接
			init();

			// 传输数据
			output.writeInt(1);
			output.flush();
			output.writeUTF(username);
			output.writeUTF(password);
			output.flush();

			// 返回
			student = (Student) input.readObject();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * 修改学生信息-2
	 * 
	 * @param student
	 * @return true表示成功 false表示失败
	 */
	public static boolean stuModification(Student student) {
		KrakenRecord.log("StudentAction:stuModification");
		boolean result = false;
		try {
			init();
			output.writeInt(2);
			output.flush();
			output.writeObject(student);
			output.flush();

			result = input.readBoolean();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生签到-3
	 * 
	 * @param student
	 * @return true表示成功 false表示失败
	 */
	public static boolean stuSignIn(Student student) {
		KrakenRecord.log("StudentAction:stuSignIn");
		boolean result = false;
		try {
			init();
			output.writeInt(3);
			output.flush();
			output.writeObject(student);
			output.writeObject(new Date());
			output.flush();

			result = input.readBoolean();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生签退-4
	 * 
	 * @param student
	 * @return true表示成功 false表示失败
	 */
	public static boolean stuSignOut(Student student) {
		KrakenRecord.log("StudentAction:stuSignOut");
		boolean result = false;
		try {
			init();
			output.writeInt(4);
			output.flush();
			output.writeObject(student);
			output.writeObject(new Date());
			output.flush();

			result = input.readBoolean();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生写日志-5
	 * 
	 * @param student
	 * @param logContext
	 * @return true表示成功 false表示失败
	 */
	public static boolean stuLog(Student student, String logContext) {
		KrakenRecord.log("StudentAction:stuLog");
		boolean result = false;
		try {
			init();
			output.writeInt(5);
			output.flush();
			output.writeObject(student);
			output.writeUTF(logContext);
			output.flush();
			
			result = input.readBoolean();
			
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生查找指定日期日志-6
	 * 
	 * @param student
	 * @param strTime
	 * @return
	 */
	public static Sign stuShowLog(Student student, String strTime) {
		KrakenRecord.log("StudentAction:stuShowLog");
		Sign result = null;
		try {
			init();
			output.writeInt(6);
			output.flush();
			output.writeObject(student);
			output.writeUTF(strTime);
			output.flush();
			
			result = (Sign) input.readObject();
			
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过Id查找学生-7
	 * 
	 * @param Id
	 * @return
	 */
	public static Student stuFindStudentById(int Id) {
		KrakenRecord.log("StudentAction:stuFindStudentById");
		Student student = null;
		try {// 获取连接
			init();

			// 传输数据
			output.writeInt(7);
			output.flush();
			
			output.writeInt(Id);
			output.flush();

			// 返回
			student = (Student) input.readObject();

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

}
