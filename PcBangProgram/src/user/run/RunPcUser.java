package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import user.view.UserLogin;

public class RunPcUser {
	
	public RunPcUser() {
		new UserLogin();
	}//RunPcUser
	
	public String getIp() {
		InetAddress local; 
		String ip=null;
		
		try { 
			local = InetAddress.getLocalHost(); 
			ip = local.getHostAddress(); 
		} catch (UnknownHostException e1) {
			e1.printStackTrace(); 
		}//end catch
		return ip;
	}//getIp
	
//	public void sendImage() throws UnknownHostException, IOException {
//		//1.�������� ����
//		Socket client=null;
//		DataOutputStream dos=null;
//		DataInputStream dis=null;
//		FileOutputStream fos=null;
//		
//		try {
//			//2.���� ���� : �� ��ǻ�� ip�� ������ ����
//			client=new Socket(getIp(),5000); /////////////
//			//�����͸� �ְ���� ��Ʈ�� ����
//			dos=new DataOutputStream(client.getOutputStream()); //������
//			dis=new DataInputStream(client.getInputStream()); //���̱�
//			
//			//[ ��� ] ��� ������ ����� CSV�����
//			File file=new File("C:/dev/workspace/Team22/src/user/images"); //���� ����
//			File[] temp=file.listFiles(); //������ list�� �迭�� ���
//			
//			StringBuilder csvFile=new StringBuilder();
//			for(int i=0;i<temp.length;i++) {
//				if(i!=0) {
//					csvFile.append(",");
//				}//end if
//				csvFile.append(temp[i].getName());
//			}//end for
//			
//			//������ ���ϸ���Ʈ CSV data ������ ////? 
//			dos.writeUTF(csvFile.toString()); //���ڿ��� ��Ʈ�� ���
//			dos.flush(); //��Ʈ���� ������ ������(����)�� ����
//			
//			//�������� ���� ������ ������ �������� �޾� �� Ƚ����ŭ �ݺ���Ŵ
//			int fileCnt=dis.readInt();
//			int readCnt=0;
//			String fileName="";
//			
//			byte[] readData=new byte[512]; //
//			int readSize=0;
//			
//			for(int i=0; i<fileCnt;i++) {
//	//			dos.wirteUTF(); //������ ������ ��?
//				//�о���� ������ ȸ�� �ޱ�
//				readCnt=dis.readInt();
//				//�޾Ƽ� ������ ���ϸ� �ޱ�
//				fileName=dis.readUTF();
//				//���ϻ���
//				fos=new FileOutputStream("C:/dev/workspace/Team22/src/user/images/"+fileName);
//				
//				while(readCnt>0) {
//					readSize=dis.read(readData);
//					fos.write(readData,0,readSize); ////////?
//					readCnt--;
//				}//end while
//				fos.flush();
//				//14. thumbnail ���� ����
//	//			ImageResize.resizeImage("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName, 100, 80);
//			}//end for
//		}finally {
//			if(dis!=null) {dis.close();}//end if
//			if(dos!=null) {dos.close();}//end if
//			if(client!=null) {dos.close();}//end if
//		}//end finally
//		
//	}//sendImage
	

	public static void main(String[] args) {
		
		RunPcUser rpu=new RunPcUser();
//		try {
//			pcu.sendImage();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}//end catch
		
		
//		new UserLogin();
	}//main
	
	
	
}//class
