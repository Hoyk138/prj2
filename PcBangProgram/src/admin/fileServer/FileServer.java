package admin.fileServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * �����ڰ� �������� ���ϸ��� ��� �޾Ƽ� �����ڿ� �������� �ʴ� ������ ã�� ������ ��.
 * @author owner
 */
public class FileServer extends Thread{
	private List<FileHelper> list = new ArrayList<FileHelper>();
	
//	public void test2() {
//	public synchronized void run() {
	public void run() {
		//1. ���� ���� ����(��ǻ���� ��Ʈ�� ������.)
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(5000);
//			List<Socket> clientList = new ArrayList<Socket>();
//			int cnt = 0;
			Socket client = null;
			
			FileHelper fh = null;
			//�ݺ��� �ȿ��� ������ �������� �ʴ´�.
			//���� �����ڿ��� �����ϱ� ���� List�� ���.
			while( true ) {//Ŭ���̾�Ʈ�� �� ���� ���� �𸥴�.
				//3. �����ڰ� ������
				client = server.accept();
//				clientList.add(server.accept());
//				for (int j = 0; j < clientList.size(); j++) {
				fh = new FileHelper(client);
				list.add(fh);
				fh.start();
			
//				clientList.get(j).close();
//				}//end for
//				clientList.remove(client);

			}//end while
		} catch(IOException ioe) {
			try {
			    if(server != null) { server.close(); }//end if
			} catch(IOException ioe2) {
				ioe2.printStackTrace();
			}//end catch
		}//end catch
	}//run
	
//	public void test() {
//		String temp = "ad.png,bob.png,default.gif,m1_f1.png,maekjju.jpg,rs_bob.png,rs_m1_f1.png,rs_maekjju.jpg,rs_����� �̰ԾƴϾ�!!2.png,rs_�����ϰ�ʹ�.jpg,rs_��դ�.jpg,����� �̰ԾƴϾ�!!2.png,�����ϰ�ʹ�.jpg,��դ�.jpg";
//		//C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
//		
//		//temp�� �����ϴ� ���ϰ� admin�� �����ϴ� ������ ���Ͽ� ���� ���ϸ��� ã�� ���
//		
//		File file = new File("C:/dev/workspace/jdbc_prj/src/kr/co/sist/admin/img");
//		File[] fileArr = file.listFiles();
//		String[] fileNameArr = file.list();
//		StringBuilder tempSB = new StringBuilder();
//		for (int i = 0; i < fileArr.length; i++) {
//			if (i != 0) {
//				tempSB.append(",");
//			}//end if
//			tempSB.append(fileArr[i].getName());
////			System.out.println(fileArr[i].getName());
//		}//end for 
//		String adminImgStr = tempSB.toString();
//		
//		System.out.println(temp);
//		System.out.println(adminImgStr);
//		
//		List<String> list = new ArrayList<String>();
//		String[] userImgArr = temp.split(",");
////		for (int j = 0; j < fileNameArr.length; j++) {
//////			if (adminImgStr.contains(userImgArr[i])) {
//////				adminImgStr = adminImgStr.replace(userImgArr[i], "");
//////			}//end if
//////			System.out.println(userImgArr[i]);
////			for (int i = 0; i < userImgArr.length; i++) {
//////				System.out.println(fileNameArr[j]);
////				if (fileNameArr[j].equals(userImgArr[i])) {
//////					System.out.println(userImgArr[i]);
//////					System.out.println(fileNameArr[j]);
////					fileNameArr[j] = "";
////					list.add(fileNameArr[j]);
//////					System.out.println(fileNameArr[j]);
////				}//end if
////			}//end for
////		}//end for
//		
//		boolean flag = false;
//		for (int i = 0; i < fileNameArr.length; i++) {
//			for (int j = 0; j < userImgArr.length; j++) {
//				if (fileNameArr[i].equals(userImgArr[j])) {
//					flag = true;
//					break;
//				}//end if
//			}//end for
//			if (!flag) {
//				list.add(fileNameArr[i]);
//			}//end if
//			flag = false;
//		}//end for
//		
////		System.out.println(adminImgStr);
//
//		for (int i = 0; i < fileNameArr.length; i++) {
//			if (!fileNameArr[i].isEmpty()) {
//				System.out.println(fileNameArr[i]);
//			}//end if
//		}//end for
//		
//		System.out.println(list);
//		
//	}//test
	
//	public static void main(String[] args) {
////		new FileServer().test();
//		new FileServer().test2();
//		
//	}//main
	
}//class
