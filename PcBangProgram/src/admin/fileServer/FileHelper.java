package admin.fileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileHelper extends Thread {

//	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileInputStream fis;
	private List<String> listSendFile;//�����ڿ��� ���� ����
	private File temp1;

	public FileHelper(Socket client) throws IOException{
//		this.client = client;
		
		//4. �����͸� �ְ� ���� ��Ʈ�� ����
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());
		
		String temp = "";
		File[] serverFile = null;
		
		//6. Ŭ���̾�Ʈ�� �������� ������ ��� �ޱ�
//		String temp = "ad.png,bob.png,default.gif,m1_f1.png,maekjju.jpg,rs_bob.png,rs_m1_f1.png,rs_maekjju.jpg,rs_����� �̰ԾƴϾ�!!2.png,rs_�����ϰ�ʹ�.jpg,rs_��դ�.jpg,����� �̰ԾƴϾ�!!2.png,�����ϰ�ʹ�.jpg,��դ�.jpg";
		temp = dis.readUTF();//���� ��� �ޱ� 
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img

		temp1 = new File("C:/dev/pcbang/admin/img/item");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();
		for (int i = 0; i < serverFile.length; i++) {
			if (!temp.contains(serverFile[i].getName()) 
					&& !serverFile[i].getName().startsWith("rs_")) {
				listSendFile.add(serverFile[i].getName());
			} // end if
		} // end for
//		System.out.println("���� ����: "+listSendFile);
		
		//7. ������ ������ ���� ������(Ŭ�󸮾�Ʈ�� �� Ƚ���� �ݺ����� �д´�.)
		dos.writeInt(listSendFile.size());
		dos.flush();
		
	}//FileHelper
	
	public void run() {
		byte[] readData = new byte[120];//���Ͽ��� �о���� ������ �ӽ÷� ����
		long sendCnt = 0;//������ readData�� ����(������ �� �� ���� �� �ְ� ���� ����)
		int readSize = 0;//�о���� �迭�� ���� ����(ũ��)
		
		try {
			// 8. ������ ���� ���� ��� (FileStream�� �ʿ��ϴ�)
			for (int i = 0; i < listSendFile.size(); i++) {
				sendCnt = 0;
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));

				while ((readSize = fis.read(readData)) != -1) {// 512���� �� �߿� �� ���� ä�����°�
					sendCnt++;// ������ Ƚ ��(Ŭ���̾�Ʈ���� ���� ������ �� ������)
				} // end while
					// �����͸� ������ �������� ���� �ٽ� �ٿ��� �Ѵ�.
				fis.close();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
				dis.readUTF();// Ŭ���̾�Ʈ�� ���� ���� ��� ����

				// 8. ������ Ƚ���� Ŭ���̾�Ʈ���� ������.
				System.out.println("������ Ƚ��:"+sendCnt);
				dos.writeLong(sendCnt);
				dos.flush();
				// 11. ������ ���ϸ��� ������.
				System.out.println("������ ���ϸ�:"+listSendFile.get(i));
				dos.writeUTF(listSendFile.get(i));
				dos.flush();

//			//13. ���Ͽ��� �о�鿩 Ŭ���̾�Ʈ�� ������.
				while (sendCnt > 0) {
					readSize = fis.read(readData);
					dos.write(readData, 0, readSize);
					sendCnt--;
				} // end while
				dos.flush();

			} // end for
		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
	}//run
		
}//class