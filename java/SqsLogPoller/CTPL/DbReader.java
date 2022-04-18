package SqsLogPoller.CTPL;

import java.io.IOException;

public class DbReader extends Thread{
	
	
	SingletonPoller sp =null;
	public void run()
	
	{
		while(true)                             //infinite loop that has a 5 second sleep window (adjustable)
		{
			try {
				sp = SingletonPoller.getInstance();      //getting singleton instance
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if(sp.ReportDetails.get(sp.iterate)!=null)         // if data is available then display data
			{
			System.out.println(sp.ReportDetails.get(sp.iterate));
			sp.iterate++;
			}
			else											// else the thread waits for 5 seconds (adjustable) for the DB to be filled with data
			{
				try {
					MainRunner.dr.sleep(5000);
				} catch (InterruptedException e) {
		
					e.printStackTrace();
				}
				sp.iterate++;
			}
			
			
		}

}
}
