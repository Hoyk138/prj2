package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import kr.co.sist.util.img.ImageResize;
import user.view.UserLogin;

public class RunPcUser {
	
	public RunPcUser(String adminIP) {
		new UserLogin(adminIP);
	}//RunPcUser
	
//	public String getIp() {
//		InetAddress local; 
//		String ip=null;
//		
//		try { 
//			local = InetAddress.getLocalHost(); 
//			ip = local.getHostAddress(); 
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace(); 
//		}//end catch
//		return ip;
//	}//getIp
	
	public void sendImage(String adminIP) throws UnknownHostException, IOException {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		 
		try {
			//2. ���ϻ��� (������ ��� ������ ����) 
//			client = new Socket("localhost", 5000);
//			client = new Socket("211.63.89.130", 5000);
//			client = new Socket("211.63.89.132", 5000);
//			client = new Socket("211.63.89.133", 5000);
//			client = new Socket("211.63.89.134", 5000);
//			client = new Socket("211.63.89.142", 5000);
			System.out.println(adminIP);
			client = new Socket(adminIP, 5000);
			//4. �����͸� �ְ� ���� ��Ʈ�� ����
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());


			// c:/dev/pcbang/user/img/item
			// ��� ������ ����� CSV�� �����
			File file = new File("c:/dev/pcbang/user/img/item");
			
			if(!file.exists()) {
				file.mkdir();
			}//end if
			
			File[] fileArr = file.listFiles();
			StringBuilder csvImgFile = new StringBuilder();
			for (int i = 0; i < fileArr.length; i++) {
				if (!fileArr[i].getName().startsWith("rs_")) {
					if (i != 0) {
						csvImgFile.append(",");
					} // end if
					csvImgFile.append(fileArr[i].getName());
//			System.out.println(fileArr[i].getName());
				} // end if
			} // end for
//		System.out.println(csvImgFile.toString());

//		String[] temp = file.list();
//		StringBuilder csvImgFile2 = new StringBuilder();
//		for (String string : temp) {
////			System.out.println(string);
//			csvImgFile2.append(string).append(",");
//		}//end for
//		System.out.println(csvImgFile2.toString());
			
			System.out.println("������ ���� ���� ����Ʈ: "+csvImgFile.toString());

			// 5. ������ ���ϸ���Ʈ CSV Data ������
			dos.writeUTF(csvImgFile.toString());//���ڿ��� ��Ʈ�� ���
			dos.flush();//��Ʈ���� ������ ������(����)���� ����
			
			//9. �������� ���� ������ ������ ���� ���� �޾� �� Ƚ����ŭ �ݺ���Ų��. 
			int fileCnt = dis.readInt();
			System.out.println("�������� �˷� �� '�޾ƾ� �� ���� ����': "+fileCnt);

			long readCnt = 0;
			String recieveFileName = "";
			byte[] readData= new byte[120];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				System.out.println("----------"); 
				readCnt = 0;
				System.out.println("Ŭ���̾�Ʈ(������ Ƚ�� �ʱ�ȭ): " + readCnt);
				//Ŭ������ bomb
				//�÷��׸� �ְ� �������μ� ������ Ŭ���̾�Ʈ�� �굹�� �ʰ� ���ݴϴ�. 
				dos.writeUTF("Y");//���ŷ �޼���
				dos.flush();
				
				//code block: �������� ������ ���� ������ ���
				//10. �о���� ������ Ƚ�� �ޱ�
				readCnt = dis.readLong();
				System.out.println("Ŭ���̾�Ʈ(������ Ƚ��): " + readCnt);
				//12. �޾Ƽ� ������ ���ϸ��� �ޱ� 
				recieveFileName = dis.readUTF();
				System.out.println("Ŭ���̾�Ʈ(���ϸ�): " + recieveFileName);
				//14. ���ϻ���(���� �̸��� �ִٸ� ���� ����ڴ�.)
				fos = new FileOutputStream("c:/dev/pcbang/user/img/item/"+recieveFileName);
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0, readSize);
					readCnt--;
				}//end while
				fos.flush();
				fos.close();
				//14.thumbnail ���� ����
				System.out.println("thumbnail ���� ����:"+recieveFileName); 
				try {
					ImageResize.resizeImage("c:/dev/pcbang/user/img/item/"+recieveFileName, 160, 130);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					System.out.println(recieveFileName);
				}
				System.out.println("----------"); 
				
			}//end for
			
		} finally {
			if(dis != null) { dis.close(); }//end if
			if(dos != null) { dos.close(); }//end if
			if(client != null) { client.close(); }//end if
		}//end finally
		
	}//sendImage
	

	public static void main(String[] args) {
		boolean flag = false;
		String adminIP = "";
//		String[] options = {"��","�ƴϿ�"};
		RunPcUser rpu = null;
		do {
			//adminIP = JOptionPane.showInputDialog("������ PC�� IP�� �Է� ���ּ���\n��)127.0.01");
			adminIP = JOptionPane.showInputDialog("������ PC�� IP�� �Է� ���ּ���\n��)211.63.89.130");
			if (adminIP==null) {
				adminIP="";
			}//end if
			//switch (JOptionPane.showOptionDialog(null, adminIP+"�� ���� �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
			switch (JOptionPane.showConfirmDialog(null, "�Է��Ͻ� IP ["+adminIP+"]�� ���� �Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				rpu = new RunPcUser(adminIP);
				flag = true;
				try {
					rpu.sendImage(adminIP);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
				break;
			case JOptionPane.CANCEL_OPTION:
				flag = true;
				break;
			case JOptionPane.CLOSED_OPTION:
				flag = true;
				break;
			}//switch case
			
		} while (!flag);
		
	}//main
	
}//class

