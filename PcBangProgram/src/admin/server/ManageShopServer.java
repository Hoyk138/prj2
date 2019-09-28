package admin.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import admin.DAO.AdminDAO;
import admin.view.ManageShop;

public class ManageShopServer extends Thread {
	
	private ManageShop ms;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	private Thread thread;
	
//	private Map<Integer, PcStateReceiver> pcStateReciverMap = new HashMap<Integer, PcStateReceiver>(27);
	
	public ManageShopServer(ManageShop ms) {
		this.ms = ms;
		serverSocket = null;
		socket = null;

	}//ManageShop

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(9000);
			System.out.println("���� ������ ������ ���Ƚ��ϴ�.");
			
			String ip = null;
			AdminDAO aDAO = AdminDAO.getInstance();
			int pcNum = 0;
//			ClientReceiver clientReceiver = null;
			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress().getAddress());
				ip = socket.getInetAddress().getHostAddress();
				System.out.println(ip);
				//ip�� �ش��ϴ� PC��ȣ�� DB���� ��ȸ�� ����
				pcNum = aDAO.selectPCNum(ip);
//				pcNum = aDAO.selectPCNum("211.63.89.133");
				System.out.println(pcNum);
				//PC��ȣ�� �ش��ϴ� PCState�� map���� �ҷ��� ������ �ְ�
				ms.getPcStateMap().get(pcNum).setSocket(socket);
//				ms.getPcStateEvtMap().get(pcNum).setSocket(socket);
//				pcStateReciverMap.put(pcNum, new PcStateReceiver(socket));
				//thread�� �����Ѵ�.
//				thread = new Thread(ms.getPcStateMap().get(pcNum));
//				Thread thread = new Thread(ms.getPcStateEvtMap().get(pcNum));
//				thread.start();
//				pcStateReciverMap.get(pcNum).start();
//				clientReceiver = new ClientReceiver(socket);
//				map.put(ip, clientReceiver);
//				clientReceiver.start();
			}//end while
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException ioe1) {
			ioe1.printStackTrace();
			try {
				if (socket != null) { socket.close(); }//end if
				if (serverSocket != null) { serverSocket.close(); }//end if
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}//end catch
		}//end catch
	}//run

	public Thread getThread() {
		return thread;
	}
	
}//class
