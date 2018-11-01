package package2;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws IOException{
		String filepath = "C:\\Users\\Administrator\\Desktop\\test.txt";
		String outputfilepath = "C:\\Users\\Administrator\\Desktop\\Testing.xls";
		String ipaddress = "";
		int port = 0;
		Runnable delaytask = new Runnable(){
			@Override
			public void run(){
				try {
					FileFormatChange.fileformatchange(filepath, outputfilepath);
					Sendfile.sendfile(ipaddress, port, outputfilepath);
					System.out.println("done!" + Calendar.getInstance().getTime());
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Woops, something wrong!!");
				}		
			}
		};
		
		ScheduledExecutorService schedulPool = Executors.newScheduledThreadPool(1);
		schedulPool.scheduleAtFixedRate(delaytask, 0, 5, TimeUnit.MINUTES);
	}
}
