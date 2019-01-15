package com.sxt.serice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.*;
import javax.xml.ws.handler.MessageContext;

import com.sxt.request.Communication;
import com.sxt.request.ConnectionServer;
import com.sxt.request.StartApp;
import com.sxt.swing.MainRoomSwing;
import com.sxt.swing.OneChatPanel;
import com.sxt.swing.SigleChatSwing;
import com.sxt.util.FriTreeNode;
import com.sxt.util.PicInfo;
import com.sxt.vo.TransMsg;
import com.sxt.vo.Users;
import com.sxt.windows.CoolToolTip;
import com.sxt.windows.ChatPic;
import com.sxt.util.FontAndText;;

/**
 * 用于聊天室主界面的相关 操作
 * 
 * @author pengl
 *
 */
public class MainRoomOperate implements ActionListener, TreeSelectionListener, Runnable, MouseListener, WindowListener,
		KeyListener, ItemListener {
	private MainRoomSwing mainRoomSwing; // 主聊天窗口页面
	private List<TransMsg> list = null; // 聊天信息列表
	private List<JPanel> historyList; // 历史消息
	private ObjectInputStream historyIps; // 历史消息文件输入流
	private ObjectOutputStream historyOps; // 历史消息文件输出流
	private List<JPanel> nowChatList; // 主窗口内聊天消息列表
	List<Users> onlineList = new ArrayList<Users>(); // 存在在线好友列表
	private static HashMap<String, SigleChatSwing> sigleChat = new HashMap<>();
	// 当前开辟的聊天窗口map

	// 用于解决Jtree点击一次出现两次事件的boolean参数
	private boolean flag = true;

	// 用于修改传输的聊天字体大小颜色等消息的下拉框菜单
	private JComboBox fontStyle = null;
	private JComboBox fontSize = null;
	private JComboBox fontColor = null;

	/**
	 * 获取当前所有的聊天窗口和用户id信息
	 * 
	 * @return hashmap
	 */
	public static HashMap<String, SigleChatSwing> getSigleChat() {
		return sigleChat;
	}

	public static void setSigleChat(HashMap<String, SigleChatSwing> sigleChat) {
		sigleChat = sigleChat;
	}

	/**
	 * 构造函数 1.初始化聊天界面 2.给聊天界面添加各种监听事件
	 */
	public MainRoomOperate() {
		historyList = new ArrayList<>();
		nowChatList = new ArrayList<>();
		try {
			File file = new File("res/history/chat.data");
			if (!file.exists())
				file.createNewFile();
			historyIps = new ObjectInputStream(new FileInputStream(file));
			historyOps = new ObjectOutputStream(new FileOutputStream(file));
		} catch (Exception e) {
		}
		new Thread(new UserList()).start();
		mainRoomSwing = new MainRoomSwing();
		mainRoomSwing.getNewsLabel().setBounds(500, 26, 200, 50);
		new Thread(new Runnable() {
			// 显示时间
			@Override
			public void run() {
				while (true) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					mainRoomSwing.getNewsLabel().setText(dateFormat.format(new java.util.Date()));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		mainRoomSwing.getNewsLabel().setText("14:56:20");
		mainRoomSwing.getFriendsTree().addTreeSelectionListener(this);
		mainRoomSwing.getFontButton().addActionListener(this);
		mainRoomSwing.getSendButton().addActionListener(this);
		mainRoomSwing.getEmojiButton().addMouseListener(this);
		mainRoomSwing.getHistoryLabel().addMouseListener(this);
		mainRoomSwing.getSendMessage().addMouseListener(this);
		mainRoomSwing.getChatMessage().addMouseListener(this);
		mainRoomSwing.getSendMessage().addKeyListener(this);
		Thread thread = new Thread(this);
		thread.start();

	}

	/**
	 * 用于按钮（字体，表情，发送，清楚）点击的监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * 改变字体按钮
		 */
		if (e.getSource().equals(mainRoomSwing.getFontButton())) {
			if (flag) {
				setFontWindow();

				fontStyle.addItemListener(this);
				fontSize.addItemListener(this);
				fontColor.addItemListener(this);
				flag = false;
			} else {
				mainRoomSwing.getMessagePane().setBounds(0, 120, 700, 434);
				flag = true;
			}
		}
		if (e.getSource().equals(mainRoomSwing.getEmojiButton())) {

		}

		/**
		 * 提交按钮事件
		 */
		if (e.getSource().equals(mainRoomSwing.getSendButton())) {

			Communication c = new Communication();
			c.sender(new TransMsg("Chat", StartApp.getList().get(0), getFontAttrib().toString()));
			mainRoomSwing.getSendMessage().setText(null);
		}

	}

	/**
	 * jTree属性改变事件 TreeSelectionEvent 用于点击好友节点时初始化私聊窗口（存在窗口，不存在窗口）
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// 返回最后选择的节点
		final FriTreeNode node = (FriTreeNode) mainRoomSwing.getFriendsTree().getLastSelectedPathComponent();
		
		if (node.isLeaf()) {
			if (!sigleChat.containsKey(node.getuName())) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						Users users = new Users();
						users.setName(node.getuName());
						users.setId(node.getID());
						SigleChatOperate sigleChatOperate = new SigleChatOperate(users);
						SigleChatSwing sigleChatSwing = sigleChatOperate.getSigleChatSwing();
						sigleChat.put(node.getID(), sigleChatSwing);
					}
				});
				thread.start();
			}
		} else {
			if (sigleChat.get(node.getuName()) != null) {
				sigleChat.get(node.getuName()).setVisible(true);
			}
		}

	}

	/**
	 * 聊天消息接收线程
	 */
	@Override
	public void run() {
		Communication communication = new Communication();
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			communication.listener();
			List<TransMsg> list = (List<TransMsg>) communication.getList();
			java.util.Iterator<TransMsg> iterator = list.iterator();
			while (iterator.hasNext()) {
				TransMsg transMsg = (TransMsg) iterator.next();
				if (transMsg.getFunction().equals("Chat")) {
					Users users = (Users) transMsg.getMsg();
					String message = transMsg.getContent();
					int index = message.lastIndexOf("*");
					FontAndText attr;
					if (index > 0 && index < message.length() - 1) {/* 存在表情信息 */
						attr = getRecivedFont(message.substring(0, index));
						receivedPicInfo(message.substring(index + 1, message.length()));
					} else {
						message = message.substring(0, message.length() - 1);
						attr = getRecivedFont(message);
					}
					long time = System.currentTimeMillis();
					Date date = new Date(time);
					Users users2 = StartApp.getList().get(0);
					mainRoomSwing.getChatMessage().setVisible(false);
					JPanel contentPanel = new OneChatPanel(users, attr.getMsg(), receivdPicInfo, attr).getPanel();
					historyList.add(contentPanel);
					mainRoomSwing.getChatMessage().add(contentPanel);
					mainRoomSwing.getChatMessage().setVisible(true);
					JScrollPane scrollPane = mainRoomSwing.getMessagePane();
					JScrollBar bar = scrollPane.getVerticalScrollBar();
					bar.setValue(bar.getMaximum());

					iterator.remove();
				}

				// 私聊窗口消息处理机制
				if (transMsg.getFunction().equals("SigleChat")) {

					ArrayList<Users> users = (ArrayList<Users>) transMsg.getMsg();
					Users self = LoginOperate.getSelf();
					if (users.get(0).equals(self)) {// 我是发送者

						if (sigleChat.containsKey(users.get(1).getId())) {// 判断当前窗口是否存在
							SigleChatSwing sigleChatSwing = sigleChat.get(users.get(1).getId());
							sigleChatSwing.setVisible(true);
							String message = transMsg.getContent();
							long time = System.currentTimeMillis();
							Date date = new Date(time);
							int index = message.lastIndexOf("*");
							FontAndText attr;
							if (index > 0 && index < message.length() - 1) {/* 存在表情信息 */
								attr = sigleChatSwing.getRecivedFont(message.substring(0, index));
								sigleChatSwing.receivedPicInfo(message.substring(index + 1, message.length()));
							} else {
								message = message.substring(0, message.length() - 1);
								attr = sigleChatSwing.getRecivedFont(message);
							}
							sigleChatSwing.getTextPane().setVisible(false);
							sigleChatSwing.getTextPane().add(
									new OneChatPanel(users.get(0), message, sigleChatSwing.getReceivdPicInfo(), attr)
											.getPanel());
							sigleChatSwing.getTextPane().setVisible(true);
							JScrollPane scrollPane = sigleChatSwing.getMessagePane();
							JScrollBar bar = scrollPane.getVerticalScrollBar();
							bar.setValue(bar.getMaximum());
							iterator.remove();
						} else {
							SigleChatOperate sigleChatOperate = new SigleChatOperate(users.get(1));
							SigleChatSwing sigleChatSwing = sigleChatOperate.getSigleChatSwing();
							String message = transMsg.getContent();
							long time = System.currentTimeMillis();
							Date date = new Date(time);
							int index = message.lastIndexOf("*");
							FontAndText attr;
							if (index > 0 && index < message.length() - 1) {/* 存在表情信息 */
								attr = sigleChatSwing.getRecivedFont(message.substring(0, index));
								sigleChatSwing.receivedPicInfo(message.substring(index + 1, message.length()));
							} else {
								message = message.substring(0, message.length() - 1);
								attr = sigleChatSwing.getRecivedFont(message);
							}
							sigleChatSwing.getTextPane().setVisible(false);
							sigleChatSwing.getTextPane().add(
									new OneChatPanel(users.get(0), message, sigleChatSwing.getReceivdPicInfo(), attr)
											.getPanel());
							sigleChatSwing.getTextPane().setVisible(true);
							JScrollPane scrollPane = sigleChatSwing.getMessagePane();
							JScrollBar bar = scrollPane.getVerticalScrollBar();
							bar.setValue(bar.getMaximum());
							sigleChat.put(users.get(1).getId(), sigleChatSwing);
							iterator.remove();
						}
						continue;
					}
					if (users.get(1).equals(self)) {// 我是接受者
						if (sigleChat.containsKey(users.get(0).getId())) {
							// 是否存在与该用户的对话聊天
							SigleChatSwing sigleChatSwing = sigleChat.get(users.get(0).getId());
							sigleChatSwing.setVisible(true);
							String message = transMsg.getContent();
							long time = System.currentTimeMillis();
							Date date = new Date(time);
							int index = message.lastIndexOf("*");
							FontAndText attr;
							if (index > 0 && index < message.length() - 1) {/* 存在表情信息 */
								attr = sigleChatSwing.getRecivedFont(message.substring(0, index));
								receivedPicInfo(message.substring(index + 1, message.length()));
							} else {
								message = message.substring(0, message.length() - 1);
								attr = sigleChatSwing.getRecivedFont(message);
							}
							sigleChatSwing.getTextPane().setVisible(false);
							sigleChatSwing.getTextPane().add(
									new OneChatPanel(users.get(0), message, sigleChatSwing.getReceivdPicInfo(), attr)
											.getPanel());
							sigleChatSwing.getTextPane().setVisible(true);
							JScrollPane scrollPane = sigleChatSwing.getMessagePane();
							JScrollBar bar = scrollPane.getVerticalScrollBar();
							bar.setValue(bar.getMaximum());
							iterator.remove();
						} else {
							SigleChatOperate sigleChatOperate = new SigleChatOperate(users.get(0));
							SigleChatSwing sigleChatSwing = sigleChatOperate.getSigleChatSwing();
							String message = transMsg.getContent();
							long time = System.currentTimeMillis();
							Date date = new Date(time);
							int index = message.lastIndexOf("*");
							FontAndText attr;
							if (index > 0 && index < message.length() - 1) {/* 存在表情信息 */
								attr = sigleChatSwing.getRecivedFont(message.substring(0, index));
								sigleChatSwing.receivedPicInfo(message.substring(index + 1, message.length()));
							} else {
								message = message.substring(0, message.length() - 1);
								attr = sigleChatSwing.getRecivedFont(message);
							}
							sigleChatOperate.getSigleChatSwing().getTextPane().setVisible(false);
							sigleChatOperate.getSigleChatSwing().getTextPane().add(
									new OneChatPanel(users.get(0), message, sigleChatSwing.getReceivdPicInfo(), attr)
											.getPanel());
							sigleChatOperate.getSigleChatSwing().getTextPane().setVisible(true);
							JScrollPane scrollPane = sigleChatSwing.getMessagePane();
							JScrollBar bar = scrollPane.getVerticalScrollBar();
							bar.setValue(bar.getMaximum());
							sigleChat.put(users.get(0).getId(), sigleChatSwing);
							iterator.remove();
						}
					}
				}
			}

		}

	}
	/**
	 * 更新用户列表
	 */

	class UserList implements Runnable {
		int temp = 0;
		int num = 0;
		List<Users> onlineList = new ArrayList<Users>();

		@Override
		public void run() {
			Communication communication = new Communication();
			communication.sender(new TransMsg("Admin-ShowAllUsers", null));

			communication.listener();

			List<TransMsg> list = (List<TransMsg>) communication.getList();
			java.util.Iterator<TransMsg> iterator = list.iterator();
			while (iterator.hasNext()) {
				TransMsg msg = (TransMsg) iterator.next();
				if (msg.getFunction().equals("Admin-ShowAllUsers")) {
					List<Users> allUser = (List<Users>) msg.getMsg();
					for (Users users : allUser) {
						updateOutFriendNode(users);
					}
				}
				iterator.remove();
			}

			if (num >= 1) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (true) {
				if (flag) {
					communication.sender(new TransMsg("UsersList", null));
					communication.listener();
					List<TransMsg> list1 = (List<TransMsg>) communication.getList();
					java.util.Iterator<TransMsg> iterator1 = list1.iterator();
					while (iterator1.hasNext()) {
						TransMsg msg = (TransMsg) iterator1.next();
						if (msg.getFunction().equals("UsersList")) {
							List<Users> list2 = (List<Users>) msg.getMsg();
							if (onlineList == null) {
								onlineList.addAll(list2);
								for (Users user : onlineList) {
									updateOnFriendNode(user);
								}
								iterator1.remove();
								continue;
							}
							if (onlineList != null) {
								for (Users users : list2) {
									if (!(onlineList.contains(users))) {
										onlineList.add(users);
										updateOnFriendNode(users);
									}
								}
							}
						}

					}
					flag = false;
					if (temp >= 1) {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}

		}
	}

	/**
	 * 更新列表 刷新列表UI
	 * 
	 * @param str
	 */
	public void updateOnFriendNode(Users users) {

		FriTreeNode node = new FriTreeNode(users.getName(), "ID:" + users.getId(),
				new ImageIcon("res/headimg/" + users.getImg()), users.getId());
		mainRoomSwing.getNode1().addchild(node);

		// mainRoomSwing.getFriendsTree().updateUI();

	}

	/**
	 * 更新列表 刷新列表UI
	 * 
	 * @param str
	 */
	public void updateOutFriendNode(Users users) {

		FriTreeNode node = new FriTreeNode(users.getName(), "ID:" + users.getId(),
				new ImageIcon("res/headimg/" + users.getImg()), users.getId());
		mainRoomSwing.getNode2().addchild(node);

		// mainRoomSwing.getFriendsTree().updateUI();
	}

	/**
	 * 在主聊天页面上画出font设置框
	 */
	public void setFontWindow() {
		mainRoomSwing.getMessagePane().setBounds(0, 120, 700, 384);
		fontStyle = new JComboBox();
		fontStyle.setBackground(new Color(40, 96, 144));
		fontStyle.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		fontStyle.setModel(
				new DefaultComboBoxModel(new String[] { "字体样式", "微软雅黑 Light", "宋体", "楷体", "仿宋", "华文隶书", "黑体" }));
		fontStyle.setBounds(20, 507, 131, 43);
		mainRoomSwing.getContentPane().add(fontStyle);

		fontSize = new JComboBox();
		fontSize.setBackground(new Color(40, 96, 144));
		fontSize.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		fontSize.setModel(
				new DefaultComboBoxModel(new String[] { "字体大小", "14", "16", "18", "20", "22", "24", "26", "28" }));
		fontSize.setBounds(180, 507, 131, 43);
		mainRoomSwing.getContentPane().add(fontSize);

		fontColor = new JComboBox();
		fontColor.setBackground(new Color(40, 96, 144));
		fontColor.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		fontColor.setModel(new DefaultComboBoxModel(new String[] { "字体颜色", "黑色", "红色", "黄色", "绿色", "蓝色" }));
		fontColor.setBounds(340, 507, 131, 43);
		mainRoomSwing.getContentPane().add(fontColor);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		// 历史聊天记录
		if (arg0.getSource().equals(mainRoomSwing.getHistoryLabel())) {
			mainRoomSwing.getChatMessage().setVisible(false);
			mainRoomSwing.getChatMessage().add(new OneChatPanel(null, null, null, null).getHistoryPanel());
			mainRoomSwing.getChatMessage().setVisible(true);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mainRoomSwing.getPicsJWindow().setVisible(false);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getButton() != 1) {// 不是左键
			return;
		}
		JComponent source = null;
		try {
			source = (JComponent) arg0.getSource();
		} catch (ClassCastException e) {
			return;
		}
		if (arg0.getX() >= 0 && arg0.getX() <= source.getWidth() && arg0.getY() >= 0
				&& arg0.getY() <= source.getHeight()) {
			if (source.equals(mainRoomSwing.getEmojiButton())) {
				mainRoomSwing.getPicsJWindow().setVisible(true);
				mainRoomSwing.getPicsJWindow().setLocation(400, 500);
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		try {
			ConnectionServer.getSocket().close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	private List<PicInfo> receivdPicInfo = new LinkedList<PicInfo>();
	private List<PicInfo> myPicInfo = new LinkedList<PicInfo>();

	/**
	 * 重组发送的表情信息
	 * 
	 * @return 重组后的信息串 格式为 位置|代号+位置|代号+……
	 */
	private String buildPicInfo() {
		StringBuilder sb = new StringBuilder("");
		StyledDocument docMsg = mainRoomSwing.getSendMessage().getStyledDocument();
		// 遍历jtextpane找出所有的图片信息封装成指定格式
		for (int i = 0; i < mainRoomSwing.getSendMessage().getText().length(); i++) {
			// if(mainRoomSwing.getSendMessage().getCh){
			if (docMsg.getCharacterElement(i).getName().equals("icon")) {
				// ChatPic = (ChatPic)
				Icon icon = StyleConstants.getIcon(
						mainRoomSwing.getSendMessage().getStyledDocument().getCharacterElement(i).getAttributes());
				ImageIcon icon2 = (ImageIcon) icon;
				ChatPic cupic = (ChatPic) icon;
				PicInfo picInfo = new PicInfo(i, cupic.getIm() + "");
				myPicInfo.add(picInfo);
				sb.append(i + "&" + cupic.getIm() + "+");
			}
		}
		return sb.toString();

	}

	/* 错误信息气泡提示 */
	private CoolToolTip error_tip;

	/**
	 * 发送消息
	 */
	private FontAndText myFont = null;

	public String sendMsg() {
		String message = mainRoomSwing.getSendMessage().getText();
		if (message.length() == 0) {
			error_tip.setText("请输入聊天信息！");
			error_tip.setVisible(true);
			return "";
		}
		if (message.length() > 100) {
			error_tip.setText("消息最多一百个字符！你要发送的为" + message.length() + "个字符！");
			error_tip.setVisible(true);
			return "";
		}

		myFont = getFontAttrib();

		return myFont.toString();
	}

	FontAndText att = new FontAndText();

	/**
	 * 获取所需要的文字设置
	 * 
	 * @return FontAttrib
	 */
	private FontAndText getFontAttrib() {

		att.setText(mainRoomSwing.getSendMessage().getText() + "*" + buildPicInfo());// 文本和表情信息
		return att;
	}

	/*
	 * 重组收到的表情信息串
	 */
	public void receivedPicInfo(String picInfos) {

		String[] infos = picInfos.split("[+]");
		for (int i = 0; i < infos.length; i++) {

			String[] tem = infos[i].split("[&]");
			if (tem.length == 2) {
				PicInfo pic = new PicInfo(Integer.parseInt(tem[0]), tem[1]);
				receivdPicInfo.add(pic);
			}
		}
	}

	/**
	 * 将收到的消息转化为自定义的字体类对象
	 * 
	 * @param message
	 *            收到的聊天信息
	 * @return 字体类对象
	 */
	public FontAndText getRecivedFont(String message) {
		String[] msgs = message.split("[|]");
		String fontName = "";
		int fontSize = 0;
		String[] color;
		String text = message;
		Color fontC = new Color(222, 222, 222);
		if (msgs.length >= 4) {/* 这里简单处理，表示存在字体信息 */
			fontName = msgs[0];
			fontSize = Integer.parseInt(msgs[1]);
			color = msgs[2].split("[-]");
			if (color.length == 3) {
				int r = Integer.parseInt(color[0]);
				int g = Integer.parseInt(color[1]);
				int b = Integer.parseInt(color[2]);
				fontC = new Color(r, g, b);
			}
			text = "";
			for (int i = 3; i < msgs.length; i++) {
				text = text + msgs[i];
			}
		}
		FontAndText attr = new FontAndText();

		attr.setName(fontName);
		attr.setSize(fontSize);
		attr.setColor(fontC);

		attr.setText(text);

		return attr;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (mainRoomSwing.getSendMessage().getText() != null) {
				Communication c = new Communication();
				c.sender(new TransMsg("Chat", StartApp.getList().get(0), getFontAttrib().toString()));
				mainRoomSwing.getSendMessage().setText(null);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * 字体设置下拉框元素状态改变事件
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			/**
			 * 设置字体样式监听器
			 */
			if (fontStyle.getSelectedItem() != null) {
				if (fontStyle.getSelectedItem() != "字体样式") {
					att.setName((String) fontStyle.getSelectedItem());
				}
			}

			/**
			 * 设置字体大小监听器
			 */

			if (fontSize.getSelectedItem() != null) {
				if (fontSize.getSelectedItem() != "字体大小") {
					String temp = (String) fontSize.getSelectedItem();
					att.setSize(Integer.parseInt(temp));
				}
			}

			/**
			 * 设置字体颜色监听器
			 */

			if (fontColor.getSelectedItem() != null) {
				if (fontColor.getSelectedItem() != "字体颜色") {
					String temp_color = (String) fontColor.getSelectedItem();
					if (temp_color.equals("黑色")) {
						att.setColor(new Color(0, 0, 0));
					} else if (temp_color.equals("红色")) {
						att.setColor(new Color(255, 0, 0));
					} else if (temp_color.equals("蓝色")) {
						att.setColor(new Color(0, 0, 255));
					} else if (temp_color.equals("黄色")) {
						att.setColor(new Color(255, 255, 0));
					} else if (temp_color.equals("绿色")) {
						att.setColor(new Color(0, 255, 0));
					}
				}
			}

		}

	}

}
