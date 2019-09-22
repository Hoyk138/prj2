package admin.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import admin.controller.ManageShopEvt;
import admin.server.ManageShopServer;

@SuppressWarnings("serial")
public class ManageShop extends JPanel {
	
//	private JPopupMenu jpm;
//	private JMenuItem[] jmiChatArr = new JMenuItem[20];
//	private JMenuItem[] jmiOrderArr = new JMenuItem[20];
//	private JMenuItem[] jmiCloseArr = new JMenuItem[20];
//	
//	private JLabel[] jlblPCStateArr = new JLabel[4];
//	
//	private JPanel[] jpPCStateArr;
//	
//	public ManageShop(MainView mv) {
//		setBorder(new TitledBorder("매장"));
//		
//		setLayout(new GridLayout(4, 5));
//		jpPCStateArr = new JPanel[20];
//		for (int pcNum = 1; pcNum <= 20; pcNum++) {
//			add(jpPCStateArr[pcNum-1] = new PCState(pcNum));
////			jpPCStateArr[pcNum-1].addMouseListener(this);
//		}//end for
//
//	}//ManageShop
	
	private MainView mv;
	
//	private JPanel jpCenter;
	
	private Map<Integer, PCState> pcStateMap = new HashMap<Integer, PCState>(27);
	private Map<Integer, PCStateEvt> pcStateEvtMap = new HashMap<Integer, PCStateEvt>(27);
	private JButton jbtnPCManage, jbtnLogManage;
	
	public ManageShop(MainView mv) {
		this.mv = mv;
		setBorder(new TitledBorder("매장"));
		
		setLayout(new BorderLayout());
		
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(5, 5));
		addJpPCStateArr(jpCenter);

		JPanel jpSouth = new JPanel();
		jbtnPCManage = new JButton("PC 관리");
		jbtnLogManage = new JButton("Log 관리");
		jpSouth.add(jbtnPCManage);
		jpSouth.add(jbtnLogManage);
		
		add("Center",jpCenter);
		add("South",jpSouth);
		
		//이벤트 등록
		ManageShopEvt mse = new ManageShopEvt(this);
		jbtnPCManage.addActionListener(mse);
		jbtnLogManage.addActionListener(mse);

		//매장 관리 서버를 오픈하고 사용자 소켓을 받기 위한 thread를 실행한다.
	    ManageShopServer mss = new ManageShopServer(this);
	    mss.start();
//		ManageShopServer mss = new ManageShopServer(this);
//		mss.start();
//		Thread thread = new Thread(this);
//		thread.start();
		
	}//ManageShop

	public void addJpPCStateArr(JPanel jpCenter) {
		for (int pcNum = 1; pcNum <= 25; pcNum++) {
			pcStateMap.put(pcNum, new PCState(this, pcNum));
//			pcStateEvtMap.put(pcNum, new PCState(pcNum).getPcse());
			jpCenter.add(pcStateMap.get(pcNum));
//			jpPCStateArr[pcNum-1] = new PCState(pcNum);
//			jpCenter.add(jpPCStateArr[pcNum-1]);
		}//end for
	}//setJpCenter

	public MainView getMv() {
		return mv;
	}

	public Map<Integer, PCState> getPcStateMap() {
		return pcStateMap;
	}

	public Map<Integer, PCStateEvt> getPcStateEvtMap() {
		return pcStateEvtMap;
	}

	public JButton getJbtnPCManage() {
		return jbtnPCManage;
	}

	public JButton getJbtnLogManage() {
		return jbtnLogManage;
	}
	
//	public JPanel jpPCState(int PCNum) {
//		JPanel jpPCState = new JPanel();
//		
//		//팝업 메뉴 생성
//		jpm = new JPopupMenu();
//		//팝업메뉴에 들어갈 메뉴 아이템 생성
////		jmiChat = new JMenuItem("채팅확인");
////		jmiOrder = new JMenuItem("주문확인");
////		jmiClose = new JMenuItem("결제 및 사용 종료");
//		jmiChatArr[PCNum-1] = new JMenuItem("채팅확인");
//		jmiOrderArr[PCNum-1] = new JMenuItem("주문확인");
//		jmiCloseArr[PCNum-1] = new JMenuItem("결제 및 사용 종료");
//		//팝업메뉴에 메뉴 아이템 등록
//		jpm.add(jmiChatArr[PCNum-1]);
//		jpm.add(jmiOrderArr[PCNum-1]);
//		jpm.add(jmiCloseArr[PCNum-1]);
//		//테이블에 팝업메뉴 배치
//		jpPCState.setComponentPopupMenu(jpm);
//		
//		//배치
//		jpPCState.setLayout(null);
//		for (int i = 0; i < jlblPCStateArr.length; i++) {
//			jlblPCStateArr[i] = new JLabel();
//			jlblPCStateArr[i].setText(String.valueOf(i+1)+"번 라벨");
//			jlblPCStateArr[i].setBounds(25, 25+25*i, 100, 25);
//			jpPCState.add(jlblPCStateArr[i]);
//		}//end for
//		
//		jmiChatArr[PCNum-1].addActionListener(this);
//		jmiOrderArr[PCNum-1].addActionListener(this);
//		jmiCloseArr[PCNum-1].addActionListener(this);
//		
//		jpPCState.setBorder(new TitledBorder(PCNum+"번"));
//		jpPCState.setBounds(0, 0, 150, 150);
//		jpPCState.setVisible(true);
//		
//		return jpPCState;
//	}//jpPCState
	

//	@Override
//	public void mouseEntered(java.awt.event.MouseEvent me) {
//		for (int i = 0; i < jpPCStateArr.length; i++) {
//			if (me.getSource() == jpPCStateArr[i]) {
//				jpPCStateArr[i].setBackground(new Color(0x0FE3A3));
//			}//end if
//		}
//	}
//
//	@Override
//	public void mouseExited(java.awt.event.MouseEvent me) {
//		for (int i = 0; i < jpPCStateArr.length; i++) {
//			if (me.getSource() == jpPCStateArr[i]) {
//				jpPCStateArr[i].setBackground(new Color(0xEEEEEE));	
//			}//end if
//		}
//	}
	
//	@Override
//	public void actionPerformed(ActionEvent ae) {
//		for (int i = 0; i < 20; i++) {
//			if (ae.getSource() == jmiChatArr[i]) {
//				new AdminChat();
//			}
//			if (ae.getSource() == jmiOrderArr[i]) {
//				System.out.println("PC-"+(i+1)+"번 주문 확인");
//			}
//			if (ae.getSource() == jmiCloseArr[i]) {
//				System.out.println("PC-"+(i+1)+"번 결제 및 사용 종료");
//			}
//		}
//	}

//	public JPanel[] getJpPCStateArr() {
//		return jpPCStateArr;
//	}
	
}
