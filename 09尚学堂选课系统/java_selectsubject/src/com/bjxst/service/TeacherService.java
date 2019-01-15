package com.bjxst.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.bjsxt.dao.TeacherDao;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.utils.Utils;
/*
 * 调用Dao层的方法实现
 *   
 */
public class TeacherService {
	
	TeacherDao t=new TeacherDao();
	/**
	 * 
	 * @param id  输入老师的id
	 * @return  得到关于老师的个人信息
	 */
	public String searchTeaId(String  id){
		StringBuffer b=new StringBuffer();
		try {
			 List<Teacher> teaList = t.getTeaList();
			 for(Teacher teacher:teaList){
				 if(id.equals(teacher.getId())){
					b.append("ID:"+teacher.getId()+"  ");
					b.append("姓名:"+teacher.getName()+"  ");
					b.append("年纪:"+teacher.getAge()+"  ");
					b.append("性别:"+teacher.getSex()+"  ");
					b.append("课程:"+teacher.getCourse()+"  \n");
				 }
			 }
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b.toString();
	}
	
	
	
	/**
	 *  
	 * @param id   输入老师的id
	 * @param password  老师的密码
	 * @return  返回在 xml文件中是否存在这个id和密码
	 */
	public boolean teaPW(String id,String password){
		boolean flag = false;
		try {
		flag=t.Validate(id, password);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	} 
	
	
	 /**
	  * 
	  * @param id  课程的id
	  * @param name  课程的名字
	  * @param teacher 课程的老师
	  * @param teaID  课程老师的id
	  * @return  返回是否添加成功
	  */
	public boolean addCourse(String id,String name,String teacher,String teaID){
		try {
		  //在这里就要判断，如果有了这个老师的课程，那就不要添加
		  if(pattern(id,name,teacher)){
			return false;
		  };
			//在课程表中，添加课程
			Document doc = t.praseCourseXML();
			Element root = doc.getRootElement();
			Element course = root.addElement("course");
			course.addAttribute("ID", id);
			Element Ename = course.addElement("name");
			Ename.setText(name);
			Element Eteacher= course.addElement("teacher");
			Eteacher.setText(teacher);
			Eteacher.addAttribute("ID",teaID);
			//将内存中的数据写入到xml文件中
			Utils.ToXml(new File("courseXML.xml"), doc);
			//将课程添加到xml之后，在userXML中的表中老师的课程也要添加
			t.addCourseOfTeacher(teaID, name);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 用于实现对添加课程的id 姓名 老师是否已经存在
	 * @param id  要添加的课程的id
	 * @param name 要添加的课程的名字
	 * @param teacher 要添加的课程的老师
	 * @return 返回添加的课程是否成功
	 */
	private boolean pattern(String id, String name, String teacher) {
		boolean flag=false;;
		try {
			List<Course> parseCxml = t.parseCxml();
			for(Course c:parseCxml){
				if(c.getId().equals(id)||(
				   c.getName().equals(name)&&
				   c.getTeacher().equals(teacher))){
					flag=true;
					break;
			   }
		  }
		} catch (DocumentException e) {
		
		}
		return flag;	
	}


	/**
	 *  返回与老师id相同的所有的学生的信息
	 * @param teaID  老师的id
	 * @return 返回一个装着所有学生的信息的list
	 */
	public List<String> searchAllStuInfo(String teaID){
		List<String> ts=new ArrayList<>();
		try {
			//所有的学生信息
			List<Student> students = t.getStudents();
			//获取该老师的所有的学生id
			List<String> stuList = t.praseCourseViewXml(teaID);
			if(stuList.size()==0){return ts;}
			for(String s:stuList){
				StringBuilder b=new StringBuilder();
				for(Student stu:students){
					if(s.equals(stu.getID())){
					  b.append("ID:"+stu.getID()+"  ");
					  b.append("姓名:"+stu.getName()+"  ");
					  b.append("年纪:"+stu.getAge()+"  ");
					  b.append("性别:"+stu.getSex()+"  ");
					  ts.add(b.toString());
					}
				}
			}
		} catch (DocumentException e) {
			
		}
		
		return ts;
	}
	

}
