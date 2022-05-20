

public class ThreadSetTimer extends Thread {

    private int i = 0; // 1초마다 출력되는 타이머


    @Override
    public void run() {
        while (true) {
            try {
                showSec();
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void showSec() {
        System.out.println(++i+"초");
    }

}


//        ThreadSetTimer setTimer = new ThreadSetTimer();
//        setTimer.start();