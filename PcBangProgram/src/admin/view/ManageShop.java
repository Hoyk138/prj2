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
//		setBorder(new TitledBorder("����"));
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
		setBorder(new TitledBorder("����"));
		
		setLayout(new BorderLayout());
		
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(5, 5));
		addJpPCStateArr(jpCenter);

		JPanel jpSouth = new JPanel();
		jbtnPCManage = new JButton("PC ����");
		jbtnLogManage = new JButton("Log ����");
		jpSouth.add(jbtnPCManage);
		jpSouth.add(jbtnLogManage);
		
		add("Center",jpCenter);
		add("South",jpSouth);
		
		//�̺�Ʈ ���
		ManageShopEvt mse = new ManageShopEvt(this);
		jbtnPCManage.addActionListener(mse);
		jbtnLogManage.addActionListener(mse);

		//���� ���� ������ �����ϰ� ����� ������ �ޱ� ���� thread�� �����Ѵ�.
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
//		//�˾� �޴� ����
//		jpm = new JPopupMenu();
//		//�˾��޴��� �� �޴� ������ ����
////		jmiChat = new JMenuItem("ä��Ȯ��");
////		jmiOrder = new JMenuItem("�ֹ�Ȯ��");
////		jmiClose = new JMenuItem("���� �� ��� ����");
//		jmiChatArr[PCNum-1] = new JMenuItem("ä��Ȯ��");
//		jmiOrderArr[PCNum-1] = new JMenuItem("�ֹ�Ȯ��");
//		jmiCloseArr[PCNum-1] = new JMenuItem("���� �� ��� ����");
//		//�˾��޴��� �޴� ������ ���
//		jpm.add(jmiChatArr[PCNum-1]);
//		jpm.add(jmiOrderArr[PCNum-1]);
//		jpm.add(jmiCloseArr[PCNum-1]);
//		//���̺� �˾��޴� ��ġ
//		jpPCState.setComponentPopupMenu(jpm);
//		
//		//��ġ
//		jpPCState.setLayout(null);
//		for (int i = 0; i < jlblPCStateArr.length; i++) {
//			jlblPCStateArr[i] = new JLabel();
//			jlblPCStateArr[i].setText(String.valueOf(i+1)+"�� ��");
//			jlblPCStateArr[i].setBounds(25, 25+25*i, 100, 25);
//			jpPCState.add(jlblPCStateArr[i]);
//		}//end for
//		
//		jmiChatArr[PCNum-1].addActionListener(this);
//		jmiOrderArr[PCNum-1].addActionListener(this);
//		jmiCloseArr[PCNum-1].addActionListener(this);
//		
//		jpPCState.setBorder(new TitledBorder(PCNum+"��"));
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
//				System.out.println("PC-"+(i+1)+"�� �ֹ� Ȯ��");
//			}
//			if (ae.getSource() == jmiCloseArr[i]) {
//				System.out.println("PC-"+(i+1)+"�� ���� �� ��� ����");
//			}
//		}
//	}

//	public JPanel[] getJpPCStateArr() {
//		return jpPCStateArr;
//	}
	
}
