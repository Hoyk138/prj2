package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.view.AdminLogin;
import admin.vo.AdminLoginVO;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdminLoginEvt implements ActionListener{
	
	private AdminLogin al;
	
	public AdminLoginEvt(AdminLogin al) {
		
	this.al=al;	
	}//AdminLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//��ó���� â�㶧 Ŀ���� ���̵� �־����� ���ھ�
		
		if(ae.getSource()==al.getJtfId()) { //���̵� �Է¹�����
			if(!al.getJtfId().getText().equals("")) { // ���̵� ����ִ��� �Ǵ�
				al.getJpfPass().requestFocus();//���� �����Ѵٸ� Ŀ���� ������� �̵� 
			}//end if
		}//end if

		if(ae.getSource()==al.getJpfPass() || ae.getSource()==al.getJbtnLogin()) { //��й�ȣ
			
			//�Է��� ��й�ȣ �������� : char[]�� �۾��� ���Ǽ��� ���� String�� �Ҵ�
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
					System.out.println(name);
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
