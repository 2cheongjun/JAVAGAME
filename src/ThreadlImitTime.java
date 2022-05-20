class limitTime extends Thread {
    private int number = 0;
    private boolean stop;

    limitTime() {
        this.stop = true;
    }
    @Override
    public void run() {
        while (stop) {
            try {
                number++;
                System.out.print("▷");
                sleep(30);

                if (number == 32) {
                    System.out.println("제작완료!");
                    stop = false; // false가 되면 종료
                    break;
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}



//    public void threadStop(boolean stop){
//        this.stop = stop;
//    }


//
// limitTime = new limitTime(); // 제한시간 10초 생성
//         limitTime.start();// 제한시간 10초 시작
