package Week01;

import java.util.Timer;
import java.util.TimerTask;



public class TimerTaskExample {
	
    public static void main(String[] args) {
        	Stopwatch stopwatch = new Stopwatch();
    }
}
class Stopwatch {
    int s=0;
    int m=0;

 

    public Stopwatch() {
       Timer t = new Timer();
       
       t.scheduleAtFixedRate(new display(), 0, 1000);   
       t.scheduleAtFixedRate(new seconds(), 999, 999);
       t.scheduleAtFixedRate(new minutes(), 60*1000, 60*1000);
    }
 

class seconds extends TimerTask{

	
    

    @Override
    public void run() {
       
        	s++;
            if(s==60){
                s=0;
            }
        
    }
      

}

class minutes extends TimerTask{

	

    @Override
    public void run() {
    	m++;
    }    
}

class display extends TimerTask{

	 

    @Override
    public void run() {
    	System.out.println(m+":"+s);
    }    
	}
}
