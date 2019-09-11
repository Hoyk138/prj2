package admin.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PCStateEvt extends MouseAdapter implements ActionListener {

	private PCState pcs;
	
	public PCStateEvt(PCState pcs) {
		this.pcs = pcs;
		
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
			pcs.setBackground(new Color(0xEEEEEE));	
		}//end if
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == pcs.getJmiChat()) {
				new AdminChat();
			}
			if (ae.getSource() == pcs.getJmiOrder()) {
				System.out.println("PC"+pcs.getPcNum()+"번 주문 확인");
			}
			if (ae.getSource() == pcs.getJmiClose()) {
				System.out.println("PC"+pcs.getPcNum()+"번 결제 및 사용 종료");
			}
	}
	
}
