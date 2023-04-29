package BoomGame.main;

public class Time implements Runnable{
    //public static int TIME_PLAY=100;

    @Override
    public void run() {
        while(true){
            try {
                BoomBang.time_p();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
