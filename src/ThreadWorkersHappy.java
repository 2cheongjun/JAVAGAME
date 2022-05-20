

public class ThreadWorkersHappy implements Runnable{ // 주문성공시에 손님 쓰레드


    @Override
    public void run(){

        try{ Thread.sleep(1000);}catch(Exception e){ }
        Characters.Chef.HP -= 10; //주방장 현재체력 감소시킴
        Characters.Chef.scoreStar = Characters.Chef.scoreStar + 10; //주방장 평판 +10 올림

        System.out.printf("주방장 체력 : %s" +"\n"+ "주방장 평판 : %s %n", // 주문성공시 주방장의 상태변경
                Characters.Chef.HP, Characters.Chef.scoreStar); //주방장체력, 주방장평판
    }
}


/*메인 메소드안에 넣을 객체생성 및 쓰레드 객체생성

    WorkerThread success = new WorkersThread();
    Thread worker = Thread(success);

    worker.start();

    */
