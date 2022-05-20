
public class ThreadClean extends Thread{ // 청소 쓰레드

    @Override
    public void run() {

        for(int i=0;i<4;i++){ // 알바생 청소

            try{ Thread.sleep(400);System.out.println("알바생 :༼ つ ◕_◕ ༽つ 바닥 쓸고 "+"\n");
            }catch(Exception e){ }

            try{ Thread.sleep(500);System.out.println("알바생 :༼ つ ◕_◕ ༽つ 걸레질 하고 "+"\n");
            }catch(Exception e){ }

            try{ Thread.sleep(200);System.out.println("알바생 :༼ つ ◕_◕ ༽つ 테이블 닦고 "+"\n");
            }catch(Exception e){ }
        }
    }
}

class CleanAlba extends Thread{

    @Override
    public void run() {

        for(int i=0;i<4;i++){ // 주방장 청소

            try{ Thread.sleep(300);System.out.println("주방장 :٩(ˊᗜˋ*)و 먼지 털고 "+"\n");
            }catch(Exception e){ }

            try{ Thread.sleep(100);System.out.println("주방장 :٩(ˊᗜˋ*)و 설거지 하고 "+"\n");
            }catch(Exception e){ }

        }

    }
}






