package Week02;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bakery {

	public static void main(String[] args) {
		LinkedBlockingQueue<bun> coolingRack = new LinkedBlockingQueue<>(18);
        LinkedBlockingQueue<bun> shelf = new LinkedBlockingQueue<>(10);
         
        ScheduledExecutorService bakery = Executors.newScheduledThreadPool(2);
        
        bakery.scheduleAtFixedRate(new Baker(coolingRack), 0, 5, TimeUnit.SECONDS);
        bakery.scheduleAtFixedRate(new Worker(coolingRack, shelf), 0, 1, TimeUnit.SECONDS);
        bakery.scheduleAtFixedRate(new Customer(shelf), 0, 1, TimeUnit.SECONDS);
        
        
        try {
        	
        	Thread.sleep(15*1000);
        	System.out.println("Shop is closed..Please Come next day\nThank you..");
        	bakery.shutdownNow();
        }
        catch(Exception e) {
        	
        }
        
	}

}

class bun {}



class Baker implements Runnable {

 

    LinkedBlockingQueue<bun> coolingRack;

 

    public Baker(LinkedBlockingQueue<bun> coolingRack) {
        this.coolingRack = coolingRack;
    }
    
    
        @Override
    public void run() {
        for (int i=0; i<12; i++){
            bun b = new bun();
            
            try{
                coolingRack.put(b);
                System.out.println("BAKERY:  added new bun to the cooling rack");
                Thread.sleep(1000);
            }
            catch (Exception e) {}
        }
    }   
    
}

 

class Worker implements Runnable {

 

    LinkedBlockingQueue<bun> coolingRack;
    LinkedBlockingQueue<bun> shelf;

 

    public Worker(LinkedBlockingQueue<bun> coolingRack, LinkedBlockingQueue<bun> shelf) {
        this.coolingRack = coolingRack;
        this.shelf = shelf;
    }
    
    
    @Override
    public void run() {
        for(int i=0; i<4; i++){
           try{
               bun b = coolingRack.take();
               System.out.println("WORKER: taking bun from the cooling rack");
               shelf.put(b);
               System.out.println("WORKER: placing bun on the shelf");
           }
           catch(Exception e) {}
        }
    }   
    
}

 

class Customer implements Runnable {

	LinkedBlockingQueue<bun> shelf;
	public Customer( LinkedBlockingQueue<bun> shelf) {
	        this.shelf = shelf;
	    }
    @Override
    public void run() {
    	
                try {
                	System.out.println("CUSTOMER: Customer Enter to take bun from shelf..");
                	
                	if(shelf.size() <=0) {
                		System.out.println("CUSTOMER: Currently No bun in shelf."
                				+ "Please Wait...");
                		
                	}
                	else {
                		bun b = shelf.take();
                    	System.out.println("CUSTOMER: Cutomer has taken bun and exit");
                	}
                		
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
           
    		

    }   
    
}
