package Back_End;

public class TimeElapsed {
    
    private long starting_time;
    private long ending_time;
    private String prefix_type;

    public TimeElapsed(){
    }

    public void start(){
        starting_time = System.nanoTime();
    }

    public void stop(){
        ending_time = System.nanoTime();
    }

    public double getTime(String option){
        if(option == "sec"){
            prefix_type = "sec";
            return ((double)ending_time-(double)starting_time)/1000000000;
        }
        else if(option == "mili"){
            prefix_type = "mili";
            return ((double)ending_time-(double)starting_time)/1000000;
        }
        else if(option == "micro"){
            prefix_type = "micro";
            return ((double)ending_time-(double)starting_time)/1000;
        }
        else if(option == "nano"){
            prefix_type = "nano";
            return ending_time-starting_time;
        }
        else{
            throw new RuntimeException("Error! Please specify sec, mili, micro, or nano.");
        }
    }

    public void pp(String option, String message){
        System.out.printf("It took %f %s-seconds to run %s", getTime(option), prefix_type, message);
    }
}
