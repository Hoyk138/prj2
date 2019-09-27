package admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

import admin.DAO.AdminDAO;
import admin.controller.PCStateEvt;
import user.helper.PriceThread;
import user.helper.TimeThread;


@SuppressWarnings("serial")
public class PCState extends JPanel implements Runnable {
	
	private MainView mv;
	private ManageShop ms;
	private int pcNum;
	
	private JPopupMenu jpm;
	private JMenuItem jmiChat;
	private JMenuItem jmiOrder;
	private JMenuItem jmiClose;
	
	private JLabel[] jlblPCStateArr = new JLabel[4];
	public static final int USER_ID = 0; 
	public static final int START_TIME = 1; 
	public static final int USE_TIME = 2; 
	public static final int USE_FEE = 3; 
	
	private Color backgrounColor;
	
	private PCStateEvt pcse;
	
	private Socket socket;
	private String userIP; 
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private Thread thread;
	
	private TimeThread tt;
	private PriceThread pt;
	
	public PCState(ManageShop ms, int pcNum) {
		this.mv = ms.getMv();
		this.ms = ms;
		this.pcNum = pcNum;
		
		backgrounColor = new Color(0xEEEEEE);
		
		//DB�� �����Ͽ� �ش� ��ȣ�� ��ϵ� PC�� �ִ��� Ȯ��
		if (registerFlag()) {
			setLayout(new GridLayout(4, 1));
			jlblPCStateArr[USER_ID] = new JLabel("����� ID: ");
			jlblPCStateArr[START_TIME] = new JLabel("���� �ð�: ");
			jlblPCStateArr[USE_TIME] = new JLabel("��� �ð�: ");
			jlblPCStateArr[USE_FEE] = new JLabel("��� ���: ");
			for (int i = 0; i < jlblPCStateArr.	length; i++) {
				add(jlblPCStateArr[i]);
			} // end for

//			// �˾� �޴� ���
//			jpm = new JPopupMenu();
//
//			jmiChat = new JMenuItem("ä��Ȯ��");
//			jmiOrder = new JMenuItem("�ֹ�Ȯ��");
//			jmiClose = new JMenuItem("���� �� ��� ����");
//			jpm.add(jmiChat);
//			jpm.add(jmiOrder);
//			jpm.add(jmiClose);
//
//			setComponentPopupMenu(jpm);
//
//			// �̺�Ʈ ���
////			PCStateEvt pcse = new PCStateEvt(this);
//			pcse = new PCStateEvt(this);
//			this.addMouseListener(pcse);
//			jmiChat.addActionListener(pcse);
//			jmiOrder.addActionListener(pcse);
//			jmiClose.addActionListener(pcse);
			
			// ���� ����
//			new PCStateServer(this);

		} else {
			setLayout(new BorderLayout());
			JLabel jlbl = new JLabel("��ϵ� PC�� �����ϴ�.");
			jlbl.setHorizontalAlignment(JLabel.CENTER);
			add(jlbl);
		}//end else	
				
		setBorder(new TitledBorder(pcNum+"��"));
		setVisible(true);
		
		setBackground(Color.white);
		
	}//PCState
	
	private boolean registerFlag() {
		boolean registerFlag = false;

		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			registerFlag = aDAO.selectPCip(pcNum);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end catch

		return registerFlag;
   }//registerFlag
	
	public void setSocket(Socket socket) throws IOException {
		 this.socket = socket;
		 userIP = socket.getInetAddress().getHostAddress();

		// ��Ʈ���� �����ϰ�
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		
		// ������� ID�� ���� �ð��� �޾� ��.
		String userID = dis.readUTF();
		String startTime = dis.readUTF();

		// �󺧿� �ݿ��Ѵ�.
		System.out.println(userID + "/" + startTime);
		jlblPCStateArr[USER_ID].setText("����� ID: "+userID);
		jlblPCStateArr[START_TIME].setText("���� �ð�: "+startTime);
		
		//���ð�, ���ݾ� thread
		if(tt==null) {
			tt=new TimeThread(jlblPCStateArr[USE_TIME]);
			tt.start();
		}//end if
		if(pt==null) {
			pt=new PriceThread(jlblPCStateArr[USE_FEE]);
			pt.start();
		}//end if
		
		backgrounColor = Color.LIGHT_GRAY;
		setBackground(backgrounColor);
		
		// �˾� �޴��� ����Ѵ�.
		jpm = new JPopupMenu();

		jmiChat = new JMenuItem("ä��Ȯ��");
		jmiOrder = new JMenuItem("�ֹ�Ȯ��");
		jmiClose = new JMenuItem("���� �� ��� ����");
		jpm.add(jmiChat);
		jpm.add(jmiOrder);
		jpm.add(jmiClose);

		setComponentPopupMenu(jpm);

		// �̺�Ʈ ���
//		PCStateEvt pcse = new PCStateEvt(this);
		pcse = new PCStateEvt(this);
		this.addMouseListener(pcse);
		jmiChat.addActionListener(pcse);
		jmiOrder.addActionListener(pcse);
		jmiClose.addActionListener(pcse);
		
		//thread ����
		thread = new Thread(this);
		thread.start();

	}// setSocket
	
	@Override
	public void run() {
		try {
			while (true) {
					String flag = dis.readUTF();
	
					switch (flag) {
					case "/ä��":
						backgrounColor = Color.RED;
						setBackground(backgrounColor);
						break;
					case "/ä������":
						backgrounColor = Color.LIGHT_GRAY;
						setBackground(backgrounColor);
						break;
					case "/�ֹ�":
						backgrounColor = Color.GREEN;
						setBackground(backgrounColor);
						break;
					}// switch case
			}//end while
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("!");
			//�޼����� �аų� ���� �� ���� ����(�����ڰ� ������ ������ ���)
			//���� ������ ������ �̿��� ��� �����ڿ��� ���� ���� �޼����� ���
			try {
				//������ â�� �����ڰ� ������ ����
//				jcs.getDlmConnect()
//				   .addElement(cnt+"���� ������ ["+nick+"]�Բ��� ������ ���� �Ͽ����ϴ�.");
//				JScrollPane jsp = jcs.getJspConnectView();
//				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
//				broadcast(cnt+"���� ������ ["+nick+"]�Բ��� ������ �����Ͽ����ϴ�.");
				JOptionPane.showMessageDialog(ms, pcNum+"�� PC�� ����� ���� �Ǿ����ϴ�.");
				
//				jcs.getJoinList().remove(nick);
//				broadcast(nick);
				
				//��Ʈ���� ���� ����
				if (dis!=null) { dis.close(); }//end if
				if (dos!=null) { dos.close(); }//end if
				if (socket!=null) { socket.close(); }//end if
//				//����Ʈ���� ����:
//				//this -> �޼����� �аų� ���� �� ���� ���ܸ� �߻���Ų ��ü
//				jcs.getConnectList().remove(this);
//				
//				//�г����� ����
//				jcs.getConnectNickList().remove(nick);
//				
//				//������ �г����� ��� �����ڿ��� ����
//				sendNick();
				
				jlblPCStateArr[USER_ID].setText("����� ID: ");
				jlblPCStateArr[START_TIME].setText("���� �ð�: ");
				jlblPCStateArr[USE_TIME].setText("��� �ð�: ");
				jlblPCStateArr[USE_FEE].setText("��� ���: ");
				
				setBackgrounColor(new Color(0xEEEEEE));
				
			} catch (IOException ioe1) {
				ioe1.printStackTrace();
			}//end catch
			ioe.printStackTrace();
		}//catch
	}//run

	public MainView getMv() {
		return mv;
	}

	public ManageShop getMs() {
		return ms;
	}

	public int getPcNum() {
		return pcNum;
	}
	
	public String getUserIP() {
		return userIP;
	}
	
	public JLabel[] getJlblPCStateArr() {
		return jlblPCStateArr;
	}

	public JMenuItem getJmiChat() {
		return jmiChat;
	}

	public JMenuItem getJmiOrder() {
		return jmiOrder;
	}

	public JMenuItem getJmiClose() {
		return jmiClose;
	}

	public Color getBackgrounColor() {
		return backgrounColor;
	}

	public void setBackgrounColor(Color backgrounColor) {
		this.backgrounColor = backgrounColor;
	}

	public PCStateEvt getPcse() {
		return pcse;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}
	
}//end class
