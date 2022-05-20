import java.util.Random;
import java.util.Scanner;


class TimeClock implements Runnable { // 브레이크 타임 타이머쓰레드
    long startTime = System.currentTimeMillis();
    long endTime = startTime + 500L; //1분 //60000L;

    static Random random = new Random();
    int number = random.nextInt(4);   // 브레이크타임 문구 랜덤으로 출력
    String[] sentence = {"을지로도루묵", "안녕하세요", "오늘날씨맑음", "독도는 우리땅", "윈터이스커밍"}; //랜덤으로 출력되는 문구

    @Override
    public void run() {

        System.out.println("◆ 하단 속담을 최대한 빠르게 타이핑 하세요. 빠르게 입력할수록 더 많은 체력을 얻습니다." + "\n");

        System.out.println("┏━━━━━━━━━━━━   《 문장을 정확히 입력 하세요 》  ━━━━━━━━━━━━━┓");

        System.out.print("▷" + sentence[number] + "\n"); // 문구 랜덤으로 출력
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < endTime) {     // endtime 보다 작을때까지 실행
            try {
                Thread.sleep(1000L);  // 10초
            } catch (InterruptedException e) {
            }

            Scanner timeSc = new Scanner(System.in);
            String timeText = timeSc.next();

            if (timeText.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { // 한글만 입력하게 조건주기
                System.out.println(timeText);

                long end = System.currentTimeMillis();
                System.out.println("소요시간:" + (end - start) / 1000.0 + "초 걸렸습니다.");// 프로그램이 끝나는 시점


                if ((end - start) / 1000.0 < 4) { //  // 4초 미만일때 주방장체력 60회복
                    System.out.println("체력이 60 회복 되었습니다. ");
                    Characters.Chef.HP = 60;
                    System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n"); // 주방장 체력 보상

                } else if ((end - start) / 1000.0 < 6) { // 6초 미만일때 주방장체력 50회복
                    System.out.println("체력이 50 회복 되었습니다.");
                    Characters.Chef.HP = 50;
                    System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                } else if ((end - start) / 1000.0 < 8) {  // 8초 미만일때 주방장체력 40회복
                    System.out.println("체력이 40 회복 되었습니다.");
                    Characters.Chef.HP = 40;
                    System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                } else {
                    System.out.println("체력이 30 회복 되었습니다."); //그외 30회복
                    Characters.Chef.HP = 30;
                    System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                }
            } else {
                System.out.println(" 한글로 다시 입력하세요 "); // 한글이외 입력시 출력

                Scanner timereSc = new Scanner(System.in);
                String timereText = timereSc.next();

                if (timereText.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { // 한글만 입력하게 조건주기
                    System.out.println(timeText);

                    long end = System.currentTimeMillis();
                    System.out.println("소요시간:" + (end - start) / 1000.0 + "초 걸렸습니다.");// 프로그램이 끝나는 시점


                    if ((end - start) / 1000.0 < 4) {  // 4초 미만일때 주방장체력 60회복
                        System.out.println("체력이 60 회복 되었습니다. ");
                        Characters.Chef.HP = 60;
                        System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n"); // 주방장 체력 보상

                    } else if ((end - start) / 1000.0 < 6) {  // 6초 미만일때 주방장체력 50회복
                        System.out.println("체력이 50 회복 되었습니다.");
                        Characters.Chef.HP = 50;
                        System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                    } else if ((end - start) / 1000.0 < 8) { // 8초 미만일때 주방장체력 40회복
                        System.out.println("체력이 40 회복 되었습니다.");
                        Characters.Chef.HP = 40;
                        System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                    } else {
                        System.out.println("체력이 30 회복 되었습니다.");  //그외 30회복
                        Characters.Chef.HP = 30;
                        System.out.printf("주방장 체력 : %s", Characters.Chef.HP+ "\n");
                    }
                }


            }
        }
    }
}



