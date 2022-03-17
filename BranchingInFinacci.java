
//NOTE: Before using this code, make sure you have already install javaMaven in your compiler and set up JHM in pom.xml file

//Make a Main class, copy and paste there this main class

package exe;

import java.io.IOException;
import org.openjdk.jmh.*;

public class Main {
	
		public static void main(String[] args) throws IOException {
			
			org.openjdk.jmh.Main.main(args);
                       // Thread th = new Thread();
                       // ExecutorService Service= Executors.newSingleThreadExecutor();
		
                      //  Service.execute(new Test());
		
			
		}
}




//create a class with name Test2, copy and paste this code there.

package exe;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

      
public class Test2 {
  
         //Benchmark in for loop (fibonacci series)
        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        @Measurement(iterations = 2)
        @OutputTimeUnit(TimeUnit.SECONDS)
        @Fork(1)
        @Warmup(iterations = 1)
        
        public void noRecursion(){
            int n1= 0,n2=1,n3,i,count = 25;
            
            //system.out.print(n1+" "+ n2); //print 0 and 1
            for(i =2;i<count;i++){
                
                n3 = n1 + n2;
                
                //system.out.print(" "+ n3);
                n1 = n2;
                n2 = n3;
                
            }
        }
     
          //Benchmark in recursion, feel free to unmark the comment and see the result.
         /* @Benchmark
          @BenchmarkMode(Mode.AverageTime)
          @Measurement(iterations = 2)
          @OutputTimeUnit(TimeUnit.SECONDS)
          @Fork(1)
          @Warmup(iterations = 1)*/
        
       // fibonacci series using recursion (fibonacci series)
        public void withRecursion(){
            int count = 45;
            fib(count);
        }
        
        public void fib(int count){
            int n1= 0,n2=1,n3 =0;
            
            if(count >0){
                n3 = n1 + n2;
                n1 = n2;
                n2 = n3;
       
                
                //system.out.print(" "+ n3);
                fib(count -1);
            }
            
            
        }
}
