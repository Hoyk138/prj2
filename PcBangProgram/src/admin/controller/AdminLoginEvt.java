package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.DAO.AdminDAO;
import admin.VO.AdminLoginVO;
import admin.view.AdminLogin;
import admin.view.MainView;




public class AdminLoginEvt implements ActionListener{
	
	private AdminLogin al;
	
	public AdminLoginEvt(AdminLogin al) {
		
	this.al=al;	
	}//AdminLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource()==al.getJtfId()) { //���̵� �Է¹�����
			if(!al.getJtfId().getText().equals("")) { // ���̵� ����ִ��� �Ǵ�
				al.getJpfPass().requestFocus();//���� �����Ѵٸ� Ŀ���� ������� �̵� 
			}//end if
		}//end if

		if(ae.getSource()==al.getJpfPass() || ae.getSource()==al.getJbtnLogin()) { //��й�ȣ
			
			//�Է��� ��й�ȣ �������� 
			String inputPass=new String (al.getJpfPass().getPassword()); //������ PlainText(��) 
			String inputld=al.getJtfId().getText(); // ���̵���
			String shaPass="";
			
			try {
				//PlainText -> CipheText�� ��ȯ
				shaPass=DataEncrypt.messageDigest("MD5", inputPass);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}//end catch

			//�Է¹��� ���̵�� ��й�ȣ�� VO�� ����
			AdminLoginVO alVO=new AdminLoginVO(inputld, shaPass);
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			AdminDAO aDAO=AdminDAO.getInstance(); 
			try {
				String name = aDAO.selectLogin(alVO);
					
				if( !name.isEmpty() ) { //�Էµ� ���̵�� ��й�ȣ�� ��ġ�ϴ� ������ �ִٸ�
					new MainView();
					al.dispose();
				}else {
					al.getJtfId().setText(""); //���̵� �ʱ�ȭ
					al.getJpfPass().setText(""); //��й�ȣ �ʱ�ȭ
					JOptionPane.showMessageDialog(al, "���̵� Ȥ�� ��й�ȣ�� Ȯ�����ּ���");
					al.getJtfId().requestFocus(); //Ŀ���� ���̵� ��ġ
				}//end if
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch			
		}//end if
		
	}//actionPerformed
	

		
}//class
