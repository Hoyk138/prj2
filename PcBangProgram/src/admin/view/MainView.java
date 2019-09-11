package admin.view;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admin.controller.MainViewEvt;

@SuppressWarnings("serial")
public class MainView extends JFrame{
	
	private JButton jbtnmanageShop;
	private JButton jbtnmanagePC;
	private JButton jbtnmanageMember;
	private JButton jbtnmanageItem;
	private JButton jbtnmanageOrder;
	private JButton jbtnmanagePayment;
	
	private CardLayout card;
	private JPanel jpCenter;
	
	public MainView() {
		super("PC�� ���� ���α׷�");
		
		//��ư ����
		jbtnmanageShop = new JButton("����");
		jbtnmanagePC = new JButton("PC");
		jbtnmanageMember = new JButton("ȸ��");
		jbtnmanageItem = new JButton("��ǰ");
		jbtnmanageOrder = new JButton("�ֹ�");
		jbtnmanagePayment = new JButton("����");
		
		//North�� ���� JPanel
		JPanel jpNorth = new JPanel();
		jpNorth.add(jbtnmanageShop);
		jpNorth.add(jbtnmanagePC);
		jpNorth.add(jbtnmanageMember);
		jpNorth.add(jbtnmanageItem);
		jpNorth.add(jbtnmanageOrder);
		jpNorth.add(jbtnmanagePayment);
	
		//Center�� ���� JPanel
		card = new CardLayout();
		jpCenter = new JPanel(card);
		
		jpCenter.add("shop", new ManageShop(this));
		jpCenter.add("pc", new ManagePC(this));
		jpCenter.add("member", new ManageMember(this));
		jpCenter.add("item", new ProductView());
		jpCenter.add("order", new OrderView());
		jpCenter.add("payment", new CalcView());
		
		
		//��ġ
		add("North",jpNorth);
		add("Center",jpCenter);
		
		//�̺�Ʈ ���
		MainViewEvt mve = new MainViewEvt(this);
		jbtnmanageShop.addActionListener(mve);
		jbtnmanagePC.addActionListener(mve);
		jbtnmanageMember.addActionListener(mve);
		jbtnmanageItem.addActionListener(mve);
		jbtnmanageOrder.addActionListener(mve);
		jbtnmanagePayment.addActionListener(mve);
		
		setBounds(100, 100, 900, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//MainView

	public JButton getJbtnmanageShop() {
		return jbtnmanageShop;
	}

	public JButton getJbtnmanagePC() {
		return jbtnmanagePC;
	}

	public JButton getJbtnmanageMember() {
		return jbtnmanageMember;
	}

	public JButton getJbtnmanageItem() {
		return jbtnmanageItem;
	}

	public JButton getJbtnmanageOrder() {
		return jbtnmanageOrder;
	}

	public JButton getJbtnmanagePayment() {
		return jbtnmanagePayment;
	}

	public CardLayout getCard() {
		return card;
	}

	public JPanel getJpCenter() {
		return jpCenter;
	}
	
	

}//class
