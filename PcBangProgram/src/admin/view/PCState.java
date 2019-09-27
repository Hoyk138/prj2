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
		
		//DB에 접근하여 해당 번호에 등록된 PC가 있는지 확인
		if (registerFlag()) {
			setLayout(new GridLayout(4, 1));
			jlblPCStateArr[USER_ID] = new JLabel("사용자 ID: ");
			jlblPCStateArr[START_TIME] = new JLabel("시작 시간: ");
			jlblPCStateArr[USE_TIME] = new JLabel("사용 시간: ");
			jlblPCStateArr[USE_FEE] = new JLabel("사용 요금: ");
			for (int i = 0; i < jlblPCStateArr.	length; i++) {
				add(jlblPCStateArr[i]);
			} // end for

//			// 팝업 메뉴 등록
//			jpm = new JPopupMenu();
//
//			jmiChat = new JMenuItem("채팅확인");
//			jmiOrder = new JMenuItem("주문확인");
//			jmiClose = new JMenuItem("결제 및 사용 종료");
//			jpm.add(jmiChat);
//			jpm.add(jmiOrder);
//			jpm.add(jmiClose);
//
//			setComponentPopupMenu(jpm);
//
//			// 이벤트 등록
////			PCStateEvt pcse = new PCStateEvt(this);
//			pcse = new PCStateEvt(this);
//			this.addMouseListener(pcse);
//			jmiChat.addActionListener(pcse);
//			jmiOrder.addActionListener(pcse);
//			jmiClose.addActionListener(pcse);
			
			// 서버 오픈
//			new PCStateServer(this);

		} else {
			setLayout(new BorderLayout());
			JLabel jlbl = new JLabel("등록된 PC가 없습니다.");
			jlbl.setHorizontalAlignment(JLabel.CENTER);
			add(jlbl);
		}//end else	
				
		setBorder(new TitledBorder(pcNum+"번"));
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

		// 스트림을 연결하고
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		
		// 사용자의 ID와 시작 시간을 받아 와.
		String userID = dis.readUTF();
		String startTime = dis.readUTF();

		// 라벨에 반영한다.
		System.out.println(userID + "/" + startTime);
		jlblPCStateArr[USER_ID].setText("사용자 ID: "+userID);
		jlblPCStateArr[START_TIME].setText("시작 시간: "+startTime);
		
		//사용시간, 사용금액 thread
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
		
		// 팝업 메뉴를 등록한다.
		jpm = new JPopupMenu();

		jmiChat = new JMenuItem("채팅확인");
		jmiOrder = new JMenuItem("주문확인");
		jmiClose = new JMenuItem("결제 및 사용 종료");
		jpm.add(jmiChat);
		jpm.add(jmiOrder);
		jpm.add(jmiClose);

		setComponentPopupMenu(jpm);

		// 이벤트 등록
//		PCStateEvt pcse = new PCStateEvt(this);
		pcse = new PCStateEvt(this);
		this.addMouseListener(pcse);
		jmiChat.addActionListener(pcse);
		jmiOrder.addActionListener(pcse);
		jmiClose.addActionListener(pcse);
		
		//thread 실행
		thread = new Thread(this);
		thread.start();

	}// setSocket
	
	@Override
	public void run() {
		try {
			while (true) {
					String flag = dis.readUTF();
	
					switch (flag) {
					case "/채팅":
						backgrounColor = Color.RED;
						setBackground(backgrounColor);
						break;
					case "/채팅종료":
						backgrounColor = Color.LIGHT_GRAY;
						setBackground(backgrounColor);
						break;
					case "/주문":
						backgrounColor = Color.GREEN;
						setBackground(backgrounColor);
						break;
					}// switch case
			}//end while
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("!");
			//메세지를 읽거나 보낼 수 없는 상태(접속자가 접속을 종료한 경우)
			//접속 종료한 접속자 이외의 모든 접속자에게 접속 종료 메세지를 출력
			try {
				//관리자 창에 접속자가 나감을 통지
//				jcs.getDlmConnect()
//				   .addElement(cnt+"번재 접속자 ["+nick+"]님께서 접속을 종료 하였습니다.");
//				JScrollPane jsp = jcs.getJspConnectView();
//				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
//				broadcast(cnt+"번재 접속자 ["+nick+"]님께서 접속을 종료하였습니다.");
				JOptionPane.showMessageDialog(ms, pcNum+"번 PC의 사용이 종료 되었습니다.");
				
//				jcs.getJoinList().remove(nick);
//				broadcast(nick);
				
				//스트림의 연결 끊기
				if (dis!=null) { dis.close(); }//end if
				if (dos!=null) { dos.close(); }//end if
				if (socket!=null) { socket.close(); }//end if
//				//리스트에서 삭제:
//				//this -> 메세지를 읽거나 보낼 수 없어 예외를 발생시킨 객체
//				jcs.getConnectList().remove(this);
//				
//				//닉네임을 삭제
//				jcs.getConnectNickList().remove(nick);
//				
//				//삭제된 닉네임을 모든 접속자에게 제공
//				sendNick();
				
				jlblPCStateArr[USER_ID].setText("사용자 ID: ");
				jlblPCStateArr[START_TIME].setText("시작 시간: ");
				jlblPCStateArr[USE_TIME].setText("사용 시간: ");
				jlblPCStateArr[USE_FEE].setText("사용 요금: ");
				
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
