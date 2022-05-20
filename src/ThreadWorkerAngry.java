

public class ThreadWorkerAngry implements Runnable{ // 주문실패시에 손님 쓰레드


    @Override
    public void run(){

        try{ Thread.sleep(1000);}catch(Exception e){}
        Characters.Chef.HP -= 10; //주방장 현재체력 감소시킴
        Characters.Chef.scoreStar = Characters.Chef.scoreStar - 10; //손님이 주문실패시 주방장 평판 -10 내림

        System.out.println(String.format("주방장 체력 : %s" +"\n"+ "주방장 평판 : %s ", // 주문성공시 주방장의 상태변경
                Characters.Chef.HP, Characters.Chef.scoreStar)); //주방장체력 , 주방장 평판
    }
}


/*메인 메소드안에 넣을 객체생성 및 쓰레드 객체생성

   ThreadWorkersFail mistake = new ThreadWorkersFail();
        Thread mistakeOrder = new Thread(mistake);
        mistakeOrder.start();

    */
