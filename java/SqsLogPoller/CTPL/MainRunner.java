package SqsLogPoller.CTPL;

import java.io.IOException;



public class MainRunner extends Thread{
	
	static MainRunner rn;
	static DbReader dr;       //made static so that it can be called directly from "DbReader" class 
	SingletonPoller sp=null;  // singleton object variable
	public void run()
	{
		
		try {
			sp = SingletonPoller.getInstance();         //getting singleton instance
		} catch (IOException e) {

			e.printStackTrace();
		}

		sp.executor.start();  							// starting the executor that polls data from sqs queue
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		
		rn = new MainRunner();        // start thread that initiates the executor
		rn.start();
		
		dr = new DbReader();
		dr.start();					// start thread that reads data from the MapDB Htree
	}		
				
}

