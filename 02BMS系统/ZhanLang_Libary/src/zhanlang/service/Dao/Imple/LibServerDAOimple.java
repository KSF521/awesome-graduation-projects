package zhanlang.service.Dao.Imple;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.DocumentException;

import zhanlang.entity.Book;
import zhanlang.entity.Person;
import zhanlang.service.Dao.LibServerDAO;
import zhanlang.service.operate.DataInfo;

public class LibServerDAOimple implements LibServerDAO{
	private static DataInfo di;
//	public static List<Person> perList1 = new ArrayList<Person>();
	public LibServerDAOimple() {
		di = new DataInfo();
	}
	static{
		di.init();
	}

	/**
	 * 注册(添加用户)
	 */
	@Override
	public boolean addUser(Person per) {
		boolean b;
		try {
			b = di.addData(per);
			di.init();
			return b;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 登录
	 */
	@Override
	public Person login(Person per) {
		boolean flag = false;
		System.out.println("1.这是怎么回事？=====" + di.perList);
		for(Person per1 : di.perList){
			System.out.println("2.这是怎么回事？" + per1);
			if((per.getUserName().equals(per1.getUserName())&&per.getPassword().equals(per1.getPassword()))){
				System.out.println("让你登录");
				return per1;
			}
		}
		System.out.println("不让你登录");
		return null;
	}
	
	/**
	 * 查看可借图书信息
	 */
	@Override
	public List<Book> showCanAll() {
		List<Book> canAllList = new ArrayList<Book>();
		System.out.println("实现类中的bookList:" + di.bookList);
		for (Book book : di.bookList) {
			if("null".equals(book.getBorrow())){//如果借出时间为空（就是没有借出去）
				canAllList.add(book);
			}
			
		}
		return canAllList;
	}

	/**
	 * 查看已借图书信息
	 */
	@Override
	public List<Book> showAlready(Person per) {
		List<Book> alreadyList = new ArrayList<Book>();
		for(Book book : di.bookList){
			System.out.println(book);
			if(book.getBorrow().equals(per.getUserName())){
				alreadyList.add(book);
			}
		}
		return alreadyList;
	}

	/**
	 * 根据图书编号完成借书
	 */
	@Override
	public boolean borrowById(Book book,Person per) {
		boolean flage = false;
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String outTime = sdf.format(data);
		Book book1 = borrowAssist(book, per);
		System.out.println("被借的是这本书" + book1);
		if(book1 != null){
			try {
				flage = di.altData(book1, "borrow",per.getUserName());
				if(!flage){
					return flage;
				}
				flage = di.altData(book1, "outTime", outTime);
				if(!flage){
					return flage;
				}
				di.init();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flage;
	}
	
	private Book borrowAssist(Book book,Person per){
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String outTime = sdf.format(data);
		for(Book book1 : di.bookList){
			if(book.getID().equals(book1.getID())||book1.getbName().contains(book.getID())){
				System.out.println("返回是否找到=================" + "找到了");
				return book;
			}
		}
		return null;
	}

	/**
	 * 根据图书编号还书
	 */
	@Override
	public boolean returnById(Book book) {
		Book b = returnAssist(book);
		if(b == null)
			return false;
		alterInfo(b, "borrow","null");
		di.init();
		return true;
	}
	
	private Book returnAssist(Book book){
		for(Book book1 : di.bookList){
			if(book1.getBorrow().equals(book.getBorrow())){
				if(book.getID().equals(book1.getID())||book1.getbName().contains(book.getID())){
					return book1;
				}
			}
			
		}
		return null;
	}
	
	
	/**
	 * 修改个人信息(修改密码)
	 */
	@Override
	public boolean alterInfo(Object obj,String property,String content) {
		boolean flage = false;
		try {
			flage = di.altData(obj, property, content);
			if(!flage){
				return flage;
			}
			di.init();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flage;
	}

	/**
	 * 根据图书编号查询图书信息
	 */
	@Override
	public Book selectById(Book book) {
		for(Book book1 : di.bookList){
			if(book.getID().equals(book1.getID())){
				return book1;
			}
		}
		return null;
	}
	
	/**
	 * 删除用户
	 */
	@Override
	public boolean deleteUser(Person per) {
		boolean flage = false;
		try {
			flage = di.delData(per);
			if(!flage){
				return flage;
			}
			di.init();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flage;
	}
	
	/**
	 * 添加图书
	 */
	@Override
	public boolean addBook(Book book) {
		boolean flage = false;
		try {
			flage = di.addData(book);
			di.init();
			if(!flage){
				return flage;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flage;
	}
	/**
	 * 删除图书
	 */
	@Override
	public boolean deleteBook(Book book) {
		boolean flage = false;
		try {
			flage = di.delData(book);
			if(!flage){
				return flage;
			}
			di.init();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flage;
	}
	
	/**
	 * 根据类型查询书籍
	 */
	@Override
	public List<Book> selectByType(Book book) {
		List<Book> bList = new ArrayList<Book>();
		for(Book book1 : di.bookList){
			if(book1.getType().equals(book.getType())){
				bList.add(book1);
			}
		}
		System.out.println("我传过来了什么书？=====" + bList);
		return bList;
	}
	
	/**
	 * 查询所有借书信息（管理员）
	 */
	@Override
	public List<List<Book>> borrowInfo() {
		Set<String> perBoSet = new HashSet<String>();//所有借过书的用户的集合
		List<List<Book>> bookBoList = new ArrayList<List<Book>>();//按照用户分类的被借出的书的集合
		for(Book book : di.bookList){
			perBoSet.add(book.getBorrow());
		}
		for(String userName : perBoSet){
			if(userName.equals("null")){
				continue;
			}
			List<Book> bookBList = new ArrayList<Book>();//同一个用户的所有借书的集合
			for(Book book : di.bookList){
				if(userName.equals(book.getBorrow())){
					bookBList.add(book);
				}
			}
			bookBoList.add(bookBList);
		}
		return bookBoList;
	}
	
}
