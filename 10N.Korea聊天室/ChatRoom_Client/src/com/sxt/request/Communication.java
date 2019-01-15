package com.sxt.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sxt.vo.TransMsg;

/**
 * 用于和服务器交互，发送和接收来自服务器的信息
 * @author pengl
 *
 */
public class Communication {
	private  static List<TransMsg> list=new ArrayList<>();
	private static InputStream inputStream=null;
	private static OutputStream outputStream=null;
	
	/**
	 * 构造方法
	 */
	public Communication() {
		try {
			inputStream=ConnectionServer.getSocket().getInputStream();
			outputStream=ConnectionServer.getSocket().getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将服务器传来的信息放在list中
	 * 获取list
	 * @return
	 */
	public static List<TransMsg> getList() {
		return list;
	}

	/**
	 * 设置list
	 * @param list
	 */
	public static void setList(List<TransMsg> list) {
		Communication.list = list;
	}

	/**
	 * 负责接收服务器传入的信息
	 * 将服务器传来的消息放在list中
	 */
	public  void listener(){
			try {
				ObjectInputStream ois=new ObjectInputStream(inputStream);
				TransMsg msg=(TransMsg) ois.readObject();
				list.add(msg);
			} catch (IOException e) {
//				e.printStackTrace();
			} catch (Exception e) {
//				e.printStackTrace();
			}
	}
	
	/**
	 * 消息传递方法，将客户端的消息传递到服务器
	 * @param msg  需要传递的消息  以TransMsg格式封装
	 */
	public void sender(TransMsg msg){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(msg);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
