package zhanlang.DAO.Imple;

import java.util.List;

import zhanlang.DAO.ManagerDAO;
import zhanlang.entity.Book;
import zhanlang.entity.Person;

public class ManagerDAOImple implements ManagerDAO{

	@Override
	/**
	 * 管理员登录功能
	 * 提供两个参数，分别是   操作指令   与   登录者身份。
	 */
	public boolean login(String command,Person loginPerson) {
		
			DataExchange de = new DataExchange();
			try {
				return de.doWork(command,loginPerson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}
	
	@Override
	public Object addUser(String command, Person person) {
		DataExchange de = new DataExchange();
		try {
			de.doWork(command,person);
			if(((boolean)DataExchange.object)==false){
				return false;
			}
			return true; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 管理员添加图书功能
	 * 提供两个参数，分别是   操作指令   与   想要添加 的图书的信息。
	 */
	@Override
	public Object addBook(String command, Book book) {
		DataExchange de = new DataExchange();
		try {
			return de.doWork(command,book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object deleteUser(String command, Person person) {
		DataExchange de = new DataExchange();
		try {
			return de.doWork(command,person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object alterPassword(String command, Person person) {
		DataExchange de = new DataExchange();
		try {
			return de.doWork(command,person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public Object deleteBook(String command, Book book) {
		DataExchange de = new DataExchange();
		try {
			return de.doWork(command,book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object showAlready(String command, Book book) {
		DataExchange de = new DataExchange();
		try {
			return de.doWork(command,book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<List<Book>> borrowInfo(String command, Book book) {
		// TODO Auto-generated method stub
		List<Book> bookBList = null;
		List<List<Book>> bookList = null;
		DataExchange de = new DataExchange();
			try {
				de.doWork(command, book);
				Object obj = de.getObject();
				if(obj instanceof List){
					System.out.println("=======类型转换正常=======" + obj);
					bookList = (List<List<Book>>) obj;
				}else{
					System.out.println("=======类型转换不正常=======" + obj);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return bookList;
	}



}
