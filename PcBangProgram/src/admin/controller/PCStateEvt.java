package admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import admin.view.AdminChat;
import admin.view.PCState;

public class PCStateEvt extends MouseAdapter implements ActionListener {

	private PCState pcs;
	private int pcNum;
	
//	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	
	public PCStateEvt(PCState pcs) {
		this.pcs = pcs;
		pcNum = pcs.getPcNum();
		
	}//PCStateEvt
	
	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == pcs) {
			pcs.setBackground(new Color(0x0FE3A3));
		}//end if
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == pcs) {
			pcs.setBackground(pcs.getBackgrounColor());	
		}//end if
	}

	private AdminChat ac = null;
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pcs.getJmiChat()) {
//			new AdminChat(this);
			//�������� ä�� ������ �Ǵ�.
			if (pcs.getBackgrounColor()==Color.RED) {
				if (ac == null) {
					ac = new AdminChat(pcs,this);
					System.out.println("ac: "+ac);
				}//end if
//				AdminChat.getInstance(pcs);
//				pcs.setBackgrounColor(Color.LIGHT_GRAY);
//				pcs.setBackground(pcs.getBackgrounColor());
			} else {
				JOptionPane.showMessageDialog(pcs, "��û ���� ä���� �����ϴ�.");
			}//else if
		}//end if
			if (ae.getSource() == pcs.getJmiOrder()) {
				System.out.println("PC"+pcs.getPcNum()+"�� �ֹ� Ȯ��");
			}
			if (ae.getSource() == pcs.getJmiClose()) {
				System.out.println("PC"+pcs.getPcNum()+"�� ���� �� ��� ����");
				try {
					pcs.getDos().writeUTF("/����");
					System.out.println("���� �� ��� ���� �޼��� ���� �Ϸ�");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}//try catch
			}//end if
	}//actionPerformed
	
	public int getPcNum() {
		return pcNum;
	}

//	public DataInputStream getDis() {
//		return dis;
//	}
//
//	public DataOutputStream getDos() {
//		return dos;
//	}

	public AdminChat getAc() {
		return ac;
	}

	public void setAc(AdminChat ac) {
		this.ac = ac;
	}
	
}//class
