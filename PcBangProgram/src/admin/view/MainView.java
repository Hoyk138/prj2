package admin.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import admin.controller.MainViewEvt;

@SuppressWarnings("serial")
public class MainView extends JFrame{
	
	private JButton jbtnmanageShop;
//	private JButton jbtnmanagePC;
	private JButton jbtnmanageMember;
	private JButton jbtnmanageItem;
	private JButton jbtnmanageOrder;
	private JButton jbtnmanagePayment;
	
	private CardLayout card;
	private JPanel jpCenter;
	
    private	OrderView ov;
	
	public MainView(String name) {
		super("PC�� ���� ���α׷�["+name+"]");
		
		//��ư ����
		jbtnmanageShop = new JButton("����");
//		jbtnmanagePC = new JButton("PC");
		jbtnmanageMember = new JButton("ȸ��");
		jbtnmanageItem = new JButton("��ǰ");
		jbtnmanageOrder = new JButton("�ֹ�");
		jbtnmanagePayment = new JButton("����");
		
		//North�� ���� JPanel
		JPanel jpNorth = new JPanel();
		jpNorth.add(jbtnmanageShop);
//		jpNorth.add(jbtnmanagePC);
		jpNorth.add(jbtnmanageMember);
		jpNorth.add(jbtnmanageItem);
		jpNorth.add(jbtnmanageOrder);
		jpNorth.add(jbtnmanagePayment);
	
		//Center�� ���� JPanel
		card = new CardLayout();
		jpCenter = new JPanel(card);
		
		jpCenter.add("shop", new ManageShop(this));
//		jpCenter.add("pc", new ManagePC(this));
		jpCenter.add("member", new ManageMember(this));
		jpCenter.add("item", new ProductView(this));
		jpCenter.add("order", ov = new OrderView());
		jpCenter.add("payment", new CalcView());
		
		//��ġ
		add("North",jpNorth);
		add("Center",jpCenter);
		
		//�̺�Ʈ ���
		MainViewEvt mve = new MainViewEvt(this);
		jbtnmanageShop.addActionListener(mve);
//		jbtnmanagePC.addActionListener(mve);
		jbtnmanageMember.addActionListener(mve);
		jbtnmanageItem.addActionListener(mve);
		jbtnmanageOrder.addActionListener(mve);
		jbtnmanagePayment.addActionListener(mve);
		
		setBounds(100, 100, 900, 800);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////////////////////////////////////////////
		jbtnmanageShop.setBackground(new Color(0xF5D08A));
		jbtnmanageMember.setBackground(new Color(0xF5D08A));
		jbtnmanageItem.setBackground(new Color(0xF5D08A));
		jbtnmanageOrder.setBackground(new Color(0xF5D08A));
		jbtnmanagePayment.setBackground(new Color(0xF5D08A));
		jpNorth.setBackground(new Color(0x3C5A91));
		////////////////////////////////////////////
		
	}//MainView

	public JButton getJbtnmanageShop() {
		return jbtnmanageShop;
	}

//	public JButton getJbtnmanagePC() {
//		return jbtnmanagePC;
//	}

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

	public OrderView getOv() {
		return ov;
	}

}//class
