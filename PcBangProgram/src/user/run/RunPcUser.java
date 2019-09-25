package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import kr.co.sist.util.img.ImageResize;
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
	
	public void sendImage() throws UnknownHostException, IOException {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		
		try {
			//2. ���ϻ��� (������ ��� ������ ����) 
			client = new Socket("localhost", 5000);
//			client = new Socket("211.63.89.130", 5000);
//			client = new Socket("211.63.89.132", 5000);
//			client = new Socket("211.63.89.133", 5000);
//			client = new Socket("211.63.89.134", 5000);
			//4. �����͸� �ְ� ���� ��Ʈ�� ����
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());


			// C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img
			// ��� ������ ����� CSV�� �����
			File file = new File("C:/Users/owner/git/prj2/PcBangProgram/src/user/image");
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

			// 5. ������ ���ϸ���Ʈ CSV Data ������
			dos.writeUTF(csvImgFile.toString());//���ڿ��� ��Ʈ�� ���
			dos.flush();//��Ʈ���� ������ ������(����)���� ����
			
			//9. �������� ���� ������ ������ ���� ���� �޾� �� Ƚ����ŭ �ݺ���Ų��. 
			int fileCnt = dis.readInt();

			int readCnt = 0;
			String recieveFileName = "";
			byte[] readData= new byte[512];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				
//				
				//Ŭ������ bomb
				//�÷��׸� �ְ� �������μ� ������ Ŭ���̾�Ʈ�� �굹�� �ʰ� ���ݴϴ�. 
				dos.writeUTF("Y");//���ŷ �޼���
				
				//code block: �������� ������ ���� ������ ���
				//10. �о���� ������ Ƚ�� �ޱ�
				readCnt = dis.readInt();
//				System.out.println("Ŭ���̾�Ʈ: " + readCnt);
				//12. �޾Ƽ� ������ ���ϸ��� �ޱ� 
				recieveFileName = dis.readUTF();
//				System.out.println("Ŭ���̾�Ʈ: " + recieveFileName);
				//14. ���ϻ���(���� �̸��� �ִٸ� ���� ����ڴ�.)
				fos = new FileOutputStream("C:/Users/owner/git/prj2/PcBangProgram/src/user/image/"+recieveFileName);
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0, readSize);
					readCnt--;
				}//end while
				fos.flush();
				fos.close();
				//14.thumbnail ���� ����
				ImageResize.resizeImage("C:/Users/owner/git/prj2/PcBangProgram/src/user/image/"+recieveFileName, 100, 80);
				

			}//end for
			
		} finally {
			if(dis != null) { dis.close(); }//end if
			if(dos != null) { dos.close(); }//end if
			if(client != null) { client.close(); }//end if
		}//end finally
		
	}//sendImage
	

	public static void main(String[] args) {
		RunPcUser rpu=new RunPcUser();
		try {
			rpu.sendImage();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		
		
	}//main
	
	
	
}//class

