package zhanlang.DAO.Imple;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import zhanlang.view.Client;

public class DataExchange {

	private ObjectInputStream ois;

	private static ObjectOutputStream oos;
	private static OutputStream os;
	
	public static  Object object;
	/**当前设计中，doWork 方法，接受两个参数，分别为，操作指令与对象，不合理，未来不是所有对象都需要传递两个参数。*/
	public boolean doWork(String command,Object o)throws Exception{
		oos  = new ObjectOutputStream(Client.os);
		
		oos.writeObject(command);
		System.out.println(command+"指令传递完毕");
		
		oos.writeObject(o);
		System.out.println(o+"对象传递完毕");
		
		ois= new ObjectInputStream(Client.is);
		object =ois.readObject();
		System.out.println(object);
		System.out.println("读取服务器反馈");

		if(object!= null){
			return true;
		}
		return false;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}
