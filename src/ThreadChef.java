
public class ThreadChef implements Runnable { // 주방장 쓰레드


    @Override
    public void run() {

        Characters.Workers.satisfaction = Characters.Workers.satisfaction + 10; // 손님 만족도 10+ 상승

        System.out.println("───────────────────────────────────────────────────────");
        System.out.printf("\t"+"\t"+"\t  손님의 만족도가 "+ "%s 상승했습니다."+"\n",
                                              Characters.Workers.satisfaction);
        System.out.println("───────────────────────────────────────────────────────");
    }
}


//    class Thread03{ // 쓰레드를 생성하는 클래스
//        public static void main(String[] args){
//
//            chefThread chefAct = new chefThread(); // 작업을 정의한 클래스의 객체를 생성
//            Thread chefThread = new Thread(chefAct);
//
//
//            chefThread.start();  // 스레드를 실행하는 메소드start() 실행
//        }
//    }





