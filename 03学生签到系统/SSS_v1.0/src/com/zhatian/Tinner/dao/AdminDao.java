package com.zhatian.Tinner.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.DocumentException;

import com.zhatian.Tinner.entity.Sign;
import com.zhatian.Tinner.entity.SignDate;
import com.zhatian.Tinner.entity.Student;
import com.zhatian.Tinner.util.testXml;

public class AdminDao {
	/**
	 * 添加用户 传入参数:用户名,密码,姓名,性别,年龄,班级 返回一个布尔值,提示admin是否添加成功
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static boolean addStu(String student_Username, String student_Password, String name, String student_Sex,
			String student_Age, String student_Classroom) throws DocumentException, IOException {
		boolean flag = false;
		if (StudentDao.stuRegist(student_Username, student_Password, name, student_Sex, student_Age,
				student_Classroom)) {
			return true;
		}
		return flag;
	}

	/**
	 * 查找学生信息(通过id) 传入参数:一个学生的ID 返回一个学生对象
	 * 
	 * @throws DocumentException
	 */
	public static Student getStudentById(int id) throws DocumentException {
		List<Student> list = testXml.selXMLDoc();
		Student s = null;
		for (Student ss : list) {
			if (id == ss.getStudent_Id()) {
				s = ss;
			}
		}
		return s;
	}

	/**
	 * 查找学生信息(通过用户名) 传入参数:一个学生的用户名(登录名) 返回一个学生对象
	 * 
	 * @throws DocumentException
	 */
	public static Student getStudentByUsername(String student_Username) throws DocumentException {
		List<Student> list = testXml.selXMLDoc();
		Student s = null;
		for (Student ss : list) {
			if (student_Username.equals(ss.getStudent_Username())) {
				s = ss;
			}
		}
		return s;
	}

	/**
	 * 修改学生信息 传入参数:学生id,新密码.性别,年龄,班级 返回一个布尔值,提示admin是否修改成功
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static boolean stuMotify(int student_Id, String student_Password, String student_Name, String student_Sex,
			String student_Age, String student_Classroom) throws DocumentException, IOException {
		boolean flag = false;
		List<Student> students = testXml.selXMLDoc();
		for (Student s : students) {

			if (student_Id == s.getStudent_Id()) {
				s.setStudent_Password(student_Password);
				s.setStudent_Name(student_Name);
				s.setStudent_Sex(student_Sex);
				s.setStudent_Age(student_Age);
				s.setStudent_Classroom(student_Classroom);
				if (testXml.motifyXML(s)){
					flag = true;
				}

			}
		}
		return flag;
	}

	/**
	 * 删除学生信息 传入参数:学生id 返回一个布尔值,提示admin是否删除成功
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static boolean deleteStu(int student_Id) throws DocumentException, IOException {
		boolean boo = false;
		List<Student> stu = testXml.selXMLDoc();
		for (Student s : stu) {
			if (student_Id == s.getStudent_Id()) {
				if (testXml.deleteXML(s.getStudent_Id())) {
					boo = true;
				}
			}
		}
		return boo;
	}

	/**
	 * 查看所有人签到记录 传入参数:日期和班级 返回:当天此班级的所有人的签到信息
	 * 
	 * @throws DocumentException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	public static List<SignDate> getSignListHistory(String date, String classroom)
			throws DocumentException, NumberFormatException, ParseException {
		List<Sign> signs = testXml.selXMLDoc_sign();
		List<Sign> list = signs;
		List<Sign> list2 = new LinkedList<>();
		List<Student> students = testXml.selXMLDoc();// 存储不同班级所有学生的信息
		List<Student> stu = new LinkedList<>();
		;// 存储一个班级的学生信息
		for (Student s : students) {
			if (classroom.equals(s.getStudent_Classroom())) {
				stu.add(s);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
		for (Sign sign : list) {
			for (Student student : stu) {
				if (sign.getSign_studentId() == student.getStudent_Id()
						&& sdf.format(sign.getSign_Date()).equals(date)) {
					list2.add(sign);
				}
			}
		}
		List<SignDate> list3 = new LinkedList<>();
		for (Sign sign : list2) {
			String name = null;
			for (Student student : stu) {
				if (sign.getSign_studentId() == student.getStudent_Id()) {
					name = student.getStudent_Name();
				}

			}
			Date inTime = sign.getSign_InTime();
			Date outTime = sign.getSign_OutTime();
			String strInTime;
			String strOutTime;
			if(inTime != null){
				strInTime = sdf2.format(inTime);
			}else{
				strInTime = "未签到";
			}
			if(outTime != null){
				strOutTime = sdf2.format(outTime);
			}else{
				strOutTime = "未签退";
			}
			
			SignDate sd = new SignDate(name, strInTime,strOutTime );
			list3.add(sd);
		}

		return list3;
	}

	/**
	 * 获取历史签到信息
	 * 传入学生Id,
	 * 返回一个用户的历史签到信息
	 * @throws ParseException 
	 * @throws DocumentException 
	 * @throws NumberFormatException 
	 */
	public static List<Sign> getSignListByStudentId(Student student)
			throws NumberFormatException, DocumentException, ParseException {

		return StudentDao.getDistory(student.getStudent_Id());
	}

}
