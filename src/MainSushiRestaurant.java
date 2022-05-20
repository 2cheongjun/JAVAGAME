
import java.util.Random;
import java.util.Scanner;


public class MainSushiRestaurant { // 등장 캐릭터, 초밥, 음료수, 사이드 메뉴

    static Characters.Chef chef = new Characters.Chef("고영수", "주방장", 30, 40, 100, 0);
    static Characters.Student student = new Characters.Student("단골대학생", "대학생", 16, 10000, 0, "아저씨 안녕하세요! 단골 입니다!", 50);
    static Characters.Workers workers = new Characters.Workers("피곤한직장인", "직장인", 30, 10000, 0, "오늘 하루 어떻게 지나간 걸까..\n피곤하다..배고파", 100);
    static Characters.Foreigner foreigner = new Characters.Foreigner("외국인", "외국인관광객", 26, 10000, 0, "Hello! 안녕하세요우.\n원해 초밥마니 음료수 주세요");
    static String[] guests;
    static MenuInfo.MenuAll.ChobabMenu flatfish = new MenuInfo.MenuAll.ChobabMenu("광어", 2000, 20, 6, 3);
    static MenuInfo.MenuAll.ChobabMenu tuna = new MenuInfo.MenuAll.ChobabMenu("도로", 4000, 30, 5, 6);
    static MenuInfo.MenuAll.ChobabMenu longfish = new MenuInfo.MenuAll.ChobabMenu("장어", 4000, 40, 3, 3);
    static MenuInfo.MenuAll.ChobabMenu salmon = new MenuInfo.MenuAll.ChobabMenu("연어", 3000, 20, 5, 8);
    static MenuInfo.MenuAll.ChobabMenu shrimp = new MenuInfo.MenuAll.ChobabMenu("새우", 2000, 20, 2, 3);
    static MenuInfo.MenuAll.ChobabMenu egg = new MenuInfo.MenuAll.ChobabMenu("계란", 2000, 10, 5, 1);
    static MenuInfo.MenuAll.drink sparklingWater = new MenuInfo.MenuAll.drink("사이다", 2000, 500, 6, 3);
    static MenuInfo.MenuAll.drink alcoholBeer = new MenuInfo.MenuAll.drink("테라", 4000, 500, 10, 16);
    static MenuInfo.MenuAll.drink alcoholSoju = new MenuInfo.MenuAll.drink("진로", 5000, 500, 0, 26);
    static MenuInfo.MenuAll.drink coke = new MenuInfo.MenuAll.drink("콜라", 2000, 500, 100, 0);
    static MenuInfo.MenuAll.side gorokea = new MenuInfo.MenuAll.side("고로케", 2000, 10, 10);
    static MenuInfo.MenuAll.side chicken = new MenuInfo.MenuAll.side("가라아게", 5000, 10, 10);
    static MenuInfo.MenuAll.side udong = new MenuInfo.MenuAll.side("우동", 3000, 500, 8);


    static Random random = new Random(); // 공통으로 사용하는 랜덤 객체 (초밥,음료,사이드,손님의 랜덤에 사용)
    static int orderNumber = 3; // 주문수 설정 0이되면 상점, 브레이크타임으로 이동
    static int maxFail = 0; // 전체 주문실패수
    static int failCount = 0; // maxFail에 1씩 더한 값을 orderFail과 / 정산하기 주문실패에서 사용
    public static limitTime limitTime; // 초밥 제작중 쓰레드



    public static void main(String[] args) { // 게임시작

        guests = new String[]{foreigner.getName(), student.getName(), workers.getName(), foreigner.getName()}; // 랜덤으로 노출되는 손님 캐릭터


        Thread ThreadMainSound = new ThreadMainSound(); // 메인음악 시작쓰레드
        ThreadMainSound.start();

        ThreadOpening intro = new ThreadOpening(); // 게임 오프닝 쓰레드
        intro.start();
        try { intro.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }


        System.out.print("\n" + "▶초밥집 운영하기 게임을 시작 하시겠습니까?" + "\n"); // 게임진입 첫 문구
        System.out.print("\n" + "1.게임시작 \t 2.종료하기" + "\n");

        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int selectNum = scanner.nextInt();


        if (selectNum == 1) { //1번 선택시 가게이름 입력

                    System.out.println("\n" + "▶초밥집 이름을 입력해 주세요");
                    Scanner shops = new Scanner(System.in);
                    String shopName = shops.next();
                    System.out.printf("\n"+"[%s]초밥집 영업을 시작합니다." + "\n", shopName); // 입력받은 가게이름의 값을 출력
                    System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨   초밥집 오픈   ▨▨▨▨▨▨▨▨▨▨▨▨▨▨"+ " \n");

                } else if (selectNum == 2) { // 게임종료
                    System.out.print("영업을 종료합니다." + "\n");
                    selectMenu();
                } else {
                    System.out.print("가게이름을 입력해주세요." + "\n");// 1,2외에 다른것을 선택시 가게이름 입력을 다시 입력요청
                    System.out.println("▶초밥집 이름을 입력해 주세요");
                    Scanner shops = new Scanner(System.in);
                    String shopName = shops.next();
                    System.out.printf("[%s]초밥집 영업을 시작합니다." + "\n%n", shopName);// 다시 입력받은 가게이름을 출력

                }
            selectMenu(); // 메뉴선택하기로 이동
        }



    public static void clean() {  // 메뉴 .청소하기 선택시 실행
        System.out.println("청결한 매장관리는 필수. 청소를 시작합니다. \n");

        System.out.println("알바생이 청소를 도와주러왔습니다.");
        System.out.print("▨▨▨▨▨▨▨▨▨▨▨▨▨▨   매장청소중   ▨▨▨▨▨▨▨▨▨▨▨▨▨▨ \n");

        ThreadClean clean = new ThreadClean();  // 주방장 청소를 실행하는 쓰레드
        clean.start();
        CleanAlba alba = new CleanAlba();  // 알바 청소를 실행하는 쓰레드
        alba.start();

        try { clean.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }
        try { alba.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

        System.out.print("▨▨▨▨▨▨▨▨▨▨▨▨▨▨   매장청소끝   ▨▨▨▨▨▨▨▨▨▨▨▨▨▨ \n");
        System.out.println("매장이 깨끗해 졌어요!");
        System.out.println("손님맞이를 시작해봅시다." + "\n");

        RandomOrder(); // 랜덤주문으로 영업시작
         }

    public static void RandomOrder() { // 주문하기 점심영업을 시작
        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨   어서오세요   ▨▨▨▨▨▨▨▨▨▨▨▨▨▨");

        int idx = random.nextInt(3);   // 손님 등장인물(직장인, 대학생, 외국인)을 랜덤으로 출력
        System.out.print("▷" + guests[idx] + "님이 입장했습니다." + "\n"); // 랜덤으로 손님 입장


        if (guests[idx].equals("피곤한직장인")) {
            System.out.printf("%s", workers.tired + "\n");//직장인일 때 멘트출력
        } else if (guests[idx].equals("단골대학생")) {
            System.out.printf("%s", student.service + "\n"); //대학생일 때 멘트출력
        } else {
            System.out.printf("%s", foreigner.english + "\n");//외국인일 때 멘트출력
        }

        Chobab chobab = new OrderChobab().Menu.get(new Random().nextInt(5)); // 초밥 랜덤으로 출력
        Drink drink = new OrderDrink().Menu.get(new Random().nextInt(3)); // 음료 랜덤으로 출력



        System.out.println("┏━━━━━━━━━━━ 《 괄호안의 단어를 정확히 타이핑하세요 》 ━━━━━━━━━━┓");
        System.out.println("                                                        ");
        System.out.printf("  \t \t \t \t  《 %s 》 " + "+" + " 《 %s 》                 " + "\n", chobab.name, drink.name);// 랜덤으로 출력되는 초밥과 음료
        System.out.println("                                                       ");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");


//        if (cookSuccess.matches(".*[가-힣]+.*")) { // 한글만입력가능
        Scanner scannerOrder = new Scanner(System.in);
        String cookSuccess = scannerOrder.next();

        if (chobab.getName().equals(cookSuccess)) {

                limitTime = new limitTime();
                limitTime.start();// 제작중 로딩쓰레드
                try { limitTime.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

                System.out.println("───────────────────────────────────────────────────────");
              System.out.println("초밥" + chobab.price + "원" + " / 음료" + drink.price + "원"); // 저녁영업부터 사이드메뉴추가
                orderSuccess(chobab.price, drink.price, guests[idx]);
                // 주문성공시 orderSuccessDinner로 이동

        }else if(!chobab.getName().equals(cookSuccess)){
                limitTime = new limitTime();
                limitTime.start();// 제작중 로딩쓰레드
                try { limitTime.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }
                orderFail(guests[idx]);// 주문실패로 이동
             }


        }


    public static void breakTime() { // 브레이크 타임,  재료가 떨어지거나 주방장의 체력이 떨어졌을때 선택해 실행할수 있음

        System.out.println("        〇");
        System.out.println("　　ｏ");
        System.out.println("　　°");
        System.out.println("　┳┳ ∩∩");
        System.out.println("　┃┃(･∀･)　☆　　★");
        System.out.println("┏┻┻┷━Ｏ━━━━━━ ┏┷┓┏┷┓");
        System.out.println("┃ 브레이크 타임 ┨★┠┨☆┃");
        System.out.println("┗©━━©┛━━━━━━━ ┗©┛┗©┛");
        System.out.println("◆ 미니게임을 통해 재료를 얻고, 주방의 체력도 회복하세요!");

        TimeClock clock = new TimeClock(); // 미니게임 쓰레드 (빠르게 입력할수록 더많은 체력을 얻음)
        Thread time = new Thread(clock);
        time.start();
        try { time.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

        orderNumber = 3; // 미니게임이 종료되면 주문수를 다시 세팅함
        System.out.println("\n" + "1.계속손님받기  \t 2.상점가기" + "\n"); // 메뉴선택하기


        Scanner menuSc = new Scanner(System.in);
        while (!menuSc.hasNextInt()) { // 값이 숫자인지 판별
            menuSc.next();//값이 숫자가 아니면 버린다
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int showMenu = menuSc.nextInt();


        if (showMenu == 1) {
            System.out.println("\n" + "저녁영업을 시작합니다." + "\n");
            randomOrderDinner(); // 1번 선택시 랜덤오더 저녁영업으로 이동
        } else {
            Shop(); //그외 선택시 상점으로 이동
        }
    }

    public static void randomOrderDinner() { // 브레이크 타임을 한번 거친후에는 주문이 저녁영업으로 넘어감. 저녁영업은 사이드메뉴가 추가됨

        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨   어서오세요   ▨▨▨▨▨▨▨▨▨▨▨▨▨▨");


        int idx = random.nextInt(2);   // 등장인물 랜덤으로 출력
        System.out.print("▷" + guests[idx] + "님이 입장했습니다." + "\n"); // 손님(직장인, 대학생, 외국인)중 랜덤 입장

        switch (guests[idx]) {
            case "피곤한직장인" -> System.out.printf("%s", workers.tired + "\n"); // 피곤한 직장인이 입장했을때 출력
            case "단골대학생" -> System.out.printf("%s", student.service + "\n"); // 단골학생이 입장했을때 출력
            case "외국인" -> System.out.printf("%s", foreigner.english + "\n"); // 외국인이 입장해을때 출력
        }

        Chobab chobab = new OrderChobab().Menu.get(new Random().nextInt(5)); // 초밥 랜덤으로 출력
        Drink drink = new OrderDrink().Menu.get(new Random().nextInt(3)); // 음료 랜덤으로 출력
        Side side = new OrderSide().Menu.get(new Random().nextInt(2)); // 사이드메뉴 랜덤으로 출력


        System.out.println("┏━━━━━━━━━━━ 《 괄호안의 단어를 정확히 타이핑하세요 》 ━━━━━━━━━━┓");
        System.out.println("                                                       ");
        System.out.printf("  \t \t \t \t《 %s 》 " + "《 %s 》 " + "《 %s 》 " + "\n", chobab.name, drink.name, Side.name);// 랜덤으로 출력되는 초밥, 음료 ,사이드 메뉴
        System.out.println("                                                       ");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");



        Scanner scannerOrder = new Scanner(System.in);
        String cookSuccess = scannerOrder.next();

//        if (cookSuccess.matches(".*[가-힣]+.*")) { // 한글만입력가능
            if (chobab.getName().equals(cookSuccess)) {
                limitTime = new limitTime();
                limitTime.start();// 제작중 로딩쓰레드
                try { limitTime.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

                System.out.println("───────────────────────────────────────────────────────");
                System.out.println("초밥" + chobab.price + "원" + " / 음료" + drink.price + "원" + " /사이드" + side.price + "원"); // 저녁영업부터 사이드메뉴추가
                orderSuccessDinner(chobab.price, drink.price, side.price, guests[idx]); // 주문성공시 orderSuccessDinner로 이동

            }else if(!chobab.getName().equals(cookSuccess)){
                limitTime = new limitTime();
                limitTime.start();// 제작중 로딩쓰레드
                try { limitTime.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }
                orderFail(guests[idx]);// 주문실패로 이동
                 }
              }



    public static <WorkerThread> void orderSuccess(int chobab, int drink, String guests) { //점심 주문성공시 실행됨 (저녁주문시는 orderSuccessDinner로 이동)
        {

            orderNumber = orderNumber - 1; // 주문시 가능주문수 -1씩 감소

            // 주문성공시 효과음 쓰레드 사운드
            successSound orderOk = new successSound();
            orderOk.start();

            System.out.println("⁺ 　　    ˚");
            System.out.println(".  * 　　      .         　             .     　⁺ 　 .");
            System.out.println("           ✦     주문성공! 잘먹겠습니다!     ✦     .     　⁺");
            System.out.println("⁺ 　　                                               ˚");
            System.out.println(".       * 　　　         *   .                　⁺ 　 .");


            ThreadChef chefAct = new ThreadChef(); // 손님의 만족도 상승, 주방장코인 상승하는 쓰레드
            Thread chefThread = new Thread(chefAct);
            chefThread.start();
            try {
                chefThread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            WorkerThread success = (WorkerThread) new ThreadWorkersHappy(); // 주방장 체력 감소, 주방장 평판 상승시키는 쓰레드
            Thread worker = new Thread((Runnable) success);
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }


            if (Characters.Chef.HP <= 0 || orderNumber == 5) { // 주방장체력이 0이 되거나 주문수가 5가 되면 breakTime 으로이동
                breakTime();
            }

            if (orderNumber <= 0) { // 주문수가 0이거나 0보다 작다면 재료부족으로 주문받기 불가
                System.out.println("재료부족으로 주문을 받을 수 없습니다." + "\n");
                System.out.print("1.상점가기"+"\t"+"2.정산하기"+"\t"+"3.브레이크타임"+"\t" +"4.메뉴로 이동" + "\n");

                Scanner lackSc = new Scanner(System.in);
                while (!lackSc.hasNextInt()) {
                    lackSc.next();
                    System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                }
                int lack = lackSc.nextInt();

                if (lack == 1) { //1번 선택시 상점으로 이동하기
                    Shop();
                } else if (lack == 2) { //2. 선택시 정산하기 출력
                        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
                        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓   《 오늘의 정산 》   〓〓〓〓〓〓〓〓〓〓〓〓〓");

                        System.out.printf("오늘의 매출:%s", Characters.Chef.coin + "\n"); // 주방장의 총 매출
                        System.out.println("───────────────────────────────────────────────────────");
                        System.out.printf("손님 만족도:%s", Characters.Npc.satisfaction + "\n"); // 총 손님의 만족도
                        System.out.printf("주방장 평판점수:%s", Characters.Chef.scoreStar + "\n"); // 총 주방장의 평판
                        System.out.printf("주문성공 :%s", Characters.Npc.satisfaction / 10 + "\n"); // 총 주문성공수
                        System.out.printf("주문실패 %s회" + "\n", failCount); // 총 주문실패수
                        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");

                        if (Characters.Chef.scoreStar < 50) {
                            System.out.println("BAD! 아쉽네요, 상점에서 아이템을 업그레이드해 고객만족도를 더 올려보세요!" + "\n" + "\n");
                        } else if (Characters.Chef.scoreStar < 100) {
                            System.out.println("GOOD! 적당히 잘했어요! 목표를 달성했네요!" + "\n" + "\n");
                        } else if (Characters.Chef.scoreStar < 200) {
                            System.out.println("PERFECT! 잘했어요! 목표를 달성했습니다. 매출기록을 세워 달인 마크를 획득하세요!" + "\n" + "\n");
                        } else {
                            System.out.println("BAD! 아쉽네요, 다시 도전해보세요." + "\n" + "\n");
                        }

                        System.out.println("1.메뉴로 돌아가기 ");
                        Scanner regameSc = new Scanner(System.in);
                        while (!regameSc.hasNextInt()) {
                            regameSc.next();
                            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                        }
                        int regame = regameSc.nextInt();//화면에서 입력받은 값
                        if (regame == 1) {
                            selectMenu(); // 1.게임시작으로 돌아가기
                        }
                    }
                else if(lack == 3){
                    breakTime();
                }else{
                    selectMenu();
                }
            }


            switch (guests) { // 랜덤으로 출력되는 손님

                case "피곤한직장인" -> {
                    workers.stress = workers.stress - 10;
                    System.out.print("▷▷▷ 직장인의 스트레스지수 -10감소 되었습니다." + "\n"); //주문성공시 직장인 스트레스 -10감소
                }
                case "외국인" -> System.out.print("딜리셔스!" + "\n"); // 주문성공시 외국인 출력문구
                case "단골대학생" -> {
                    student.custom = student.custom + 10;
                    System.out.print("▷▷▷ 학생의 단골지수가 +10 되었습니다." + "\n"); // 주문성공시 학생의 단골지수 +10씩증가
                    System.out.println("▷▷▷ 단골 서비스로 고로케를 드려요^^");
                }
            }


            Characters.Chef.coin = Characters.Chef.coin + (chobab + drink); // 초밥+음료수가격 더한값을 주방장 매출에 더해 출력


            System.out.println("───────────────────────────────────────────────────────");
            System.out.printf("손님에게 %s원을 받았습니다." + "\t" + "\t" + "\t" + "\t" + "현재매출: %s원%n", // 주문성공시 주방장의 상태변경
                    (chobab + drink), Characters.Chef.coin); // 초밥+음료 주문금액 / 현재 매출 출력
            System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");

        }


        if ((0 >= Characters.Chef.HP)) {  // 주방장 체력이 바닥나면 자동으로 상점으로 이동
            System.out.println("▶ 체력이 바닥났네요.  브레이크 타임입니다." + "\n" + "\n");

            breakTime(); // 브레이크 타임으로 이동(체력 ,주문수 얻을수 있음)
        }


        System.out.println("가능한 주문수 : " + orderNumber + "\n" + "\n" + "\n" + "\n"); // 가능한 주문수 출력
        if (orderNumber > 0) {
            RandomOrder(); // 주문수가 0보다 클때 주문이 진행 ,작거나 같으면 상점 or 브레이크 타임으로 이동
        } else {
            System.out.println("오늘의 재료가 모두 소진되었습니다." + "\n"); // 전체 주문가능수가 0일때  재료소진되어 1.브레이크타임 2.상점가기를 통해 재료를 얻어야함.

            System.out.print("1.브레이크 타임갖기" + "\n" + "2.상점에 장보러가기" + "\n");
            Scanner todaySc = new Scanner(System.in);
            while (!todaySc.hasNextInt()) {
                todaySc.next();
                System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
            }
            int nextSelect = todaySc.nextInt();
                int selectbreaktime =1;
            if (nextSelect == selectbreaktime) {
                breakTime(); //재료가 소진되었을때 브레이크 타임갖거나
            } else {
                Shop();  // 상점으로 이동해 재료를 얻음
            }
        }
    }

    public static <WorkerThread> void orderSuccessDinner(int chobab, int drink, int side, String guests) { //저녁 주문성공시( 초밥, 음료, 사이드,게스트 정보를 가져옴)
        {                                                                                                 // 점심 주문성공은 orderSuccess

            // 주문성공시 효과음 쓰레드 사운드
            successSound orderOk = new successSound();
            orderOk.start();

            System.out.println("⁺ 　　    ˚");
            System.out.println(".  * 　　      .         　 ✦     .     　⁺ 　 .");
            System.out.println("⁺ 　　                                        ˚");
            System.out.println("             . 주문성공! 잘먹겠습니다!     ✦     .     　⁺");
            System.out.println("⁺ 　　                                               ˚");
            System.out.println(".       * 　　　         *   .                　⁺ 　 .");
            System.out.println(". 　 　         . 　                   　  . 　 　  ");


            orderNumber = orderNumber - 1; // 주문할때 마다 -1씩 주문수 차감 ( 점심영업후 > 브레이크 타임에서 저녁영업 주문수 재설정 )

            if ((0 >= Characters.Chef.HP)) {  // 주방장 체력이 바닥나면 자동으로 상점으로 이동
                System.out.println("▶ 체력이 바닥났네요.  브레이크 타임입니다." + "\n" + "\n");

                breakTime(); // 브레이크 타임으로 이동(체력 ,주문수 얻을수 있음)
            }

            if (orderNumber <= 0) {
                System.out.println("재료부족으로 주문을 받을 수 없습니다." + "\n");
                System.out.print("1.상점가기" + "\t" + "2.브레이크타임으로 재료얻기" + "\t" + "3.정산하기" + "\n");
                Scanner lackSc = new Scanner(System.in);
                while (!lackSc.hasNextInt()) {
                    lackSc.next();
                    System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                }
                int lack = lackSc.nextInt();

                if (lack == 1) {
                    Shop(); // 재료부족시 상점으로 이동
                } else if (lack == 2) {
                    breakTime(); // 브레이크타임으로 이동(브레이크 타임에서도 체력과 재료를 얻을수 있음)
                } else {
                    System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓   《 오늘의 정산 》   〓〓〓〓〓〓〓〓〓〓〓〓〓");

                    System.out.printf("오늘의 매출:%s", Characters.Chef.coin + "\n"); // 주방장의 총 매출
                    System.out.println("───────────────────────────────────────────────────────");
                    System.out.printf("손님 만족도:%s", Characters.Npc.satisfaction + "\n"); // 총 손님의 만족도
                    System.out.printf("주방장 평판점수:%s", Characters.Chef.scoreStar + "\n"); // 총 주방장의 평판
                    System.out.printf("주문성공 :%s", Characters.Npc.satisfaction / 10 + "\n"); // 총 주문성공수
                    System.out.printf("주문실패 %s회" + "\n", failCount); // 총 주문실패수
                    System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");

                    if (Characters.Chef.scoreStar < 50) {
                        System.out.println("BAD! 아쉽네요, 상점에서 아이템을 업그레이드해 고객만족도를 더 올려보세요!" + "\n" + "\n");
                    } else if (Characters.Chef.scoreStar < 100) {
                        System.out.println("GOOD! 적당히 잘했어요! 목표를 달성했네요!" + "\n" + "\n");
                    } else if (Characters.Chef.scoreStar < 200) {
                        System.out.println("PERFECT! 잘했어요! 목표를 달성했습니다. 매출기록을 세워 달인 마크를 획득하세요!" + "\n" + "\n");
                    } else {
                        System.out.println("BAD! 아쉽네요, 다시 도전해보세요." + "\n" + "\n");
                    }


                    System.out.println(" 1.게임다시하기 \t 2.메뉴로 돌아가기");
                    Scanner regameSc = new Scanner(System.in);
                    while (!regameSc.hasNextInt()) {
                        regameSc.next();
                        System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                    }
                    int regame = regameSc.nextInt();
                    if (regame == 1) {
                        selectMenu(); // 2.메뉴선택으로 이동
                    } else {
                        selectMenu();//그 외 메뉴선택으로 이동
                    }
                }
            }
        }


        ThreadChef chefAct = new ThreadChef(); // 손님의 만족도 상승, 손님 코인 감소 시키는 쓰레드
        Thread chefThread = new Thread(chefAct);
        chefThread.start();
        try {
            chefThread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        WorkerThread success = (WorkerThread) new ThreadWorkersHappy(); // 주방장 체력 감소, 주방장 평판 상승시키는 쓰레드
        Thread worker = new Thread((Runnable) success);
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }


        switch (guests) {
            case "피곤한직장인" -> System.out.printf("▷▷▷ 직장인의 스트레스지수 %s", workers.stress - 10 + " 감소" + "\n"); // 주문성공시 직장인 스트레스지수 -10감소
            case "외국인" -> System.out.print("딜리셔스! 한국 너무 조아요!" + "\n"); //주문성공시 외국인 출력문구
            case "단골대학생" -> {
                System.out.printf("▷▷▷ 학생의 단골지수 %s", student.custom + 10 + "증가" + "\n");//주문성공시 단골학생 단골지수+10 증가
                System.out.println("▷▷▷ 단골 서비스로 고로케를 드려요^^");
            }
        }


        Characters.Chef.coin = Characters.Chef.coin + (chobab + drink + side); // 초밥+음료수+사이드메뉴 가격을 주방장매출에 더함

        System.out.println("───────────────────────────────────────────────────────");
        System.out.printf("손님에게 %s원을 받았습니다." + "\t" + "\t" + "\t" + "\t" + "현재매출: %s원%n", // 주문성공시 주방장의 매출상태 변경
                (chobab + drink + side), Characters.Chef.coin); // 초밥,음료,사이드 주문합계금액 ,  주방장 현재 매출
        System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");


        System.out.println("가능한 주문수 : " + orderNumber + "\n" + "\n" + "\n" + "\n"); //가능한 주문의 수
        if (orderNumber > 0) {
            randomOrderDinner(); //저녁 주문오더로 이동

        } else {
            if ((0 >= Characters.Chef.HP) || orderNumber == 0) {  // 주방장체력이 바닥나거나 주문수끝나면 영업종료 주문불가시
                System.out.println("▶ 주방장의 체력이 소진돼 영업을 종료합니다.");
                System.out.print("1.오늘의 정산" + "\t" + "2.메뉴로 돌아가기" + "\n");
                Scanner todaySc = new Scanner(System.in);
                while (!todaySc.hasNextInt()) {
                    todaySc.next();
                    System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                }
                int finalSum = todaySc.nextInt();

                if (finalSum == 1) { // 오늘의 정산선택

                    System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓   《 오늘의 정산 》   〓〓〓〓〓〓〓〓〓〓〓〓〓");

                    System.out.printf("오늘의 매출:%s", Characters.Chef.coin + "\n"); // 총 하루매출
                    System.out.println("───────────────────────────────────────────────────────");
                    System.out.printf("손님 만족도:%s", Characters.Npc.satisfaction + "\n"); // 총 고객 만족도
                    System.out.printf("주방장 평판점수:%s", Characters.Chef.scoreStar + "\n"); // 총 주방장 평판
                    System.out.printf("주문성공 :%s", "10" + "\n"); // 총 주문성공수
                    System.out.printf("주문실패 %s회", failCount + "\n"); // 총 주문실패수
                    System.out.println("▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨▨");
                    System.out.println("잘했어요! 목표를 달성했습니다.매출기록을 세워 달인 마크를 획득하세요!" + "\n" + "\n");

                    System.out.println(" 1.게임다시하기 \t 2.메뉴로 돌아가기");
                    Scanner regameSc = new Scanner(System.in);
                    while (!regameSc.hasNextInt()) {
                        regameSc.next();
                        System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
                    }
                    int regame = regameSc.nextInt();

                    if (regame == 1) {
                        selectMenu(); // 2.메뉴선택으로 이동
                    }
                } else {
                    selectMenu();//그 외 메뉴선택으로 이동
                }
            }
        }
    }


    public static void orderFail(String guests) { // 초밥을 제대로 제공 못하면 ( 오타가 나면 ) 실패

        orderNumber = orderNumber - 1; // 주문할때마다 가능주문수 -1씩 감소

        ThreadfailSound failSound = new ThreadfailSound(); //실패시 경고음 쓰레드
        failSound.start();

//        try { okSound.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }


        failCount = failCount + 1; // 주문실패시 마다 1씩 더해서 출력
        if (failCount == maxFail + 1) {
            System.out.printf("주문실패%s회" + "\n", failCount);
        } else if (failCount == maxFail + 2) {
            System.out.printf("주문실패%s회" + "\n", failCount);
        } else if (failCount == maxFail + 3) {
            System.out.printf("주문실패%s회" + "\n", failCount);
            gameOver();
        }


        System.out.println("　 ／∵∴∴∵∴∵∴＼ ");
        System.out.println("　/∵∴∵∴∵∴∴∵∴∴∵＼     ？?????????????????????   ");
        System.out.println(" /: ／／　　   　 ＼ ");
        System.out.println(" ｜/　　(･)　(･)   ｜");
        System.out.println(" (6　　　　 つ　   ｜ ");
        System.out.println(" ｜　　  ＿＿＿　  ｜ ");
        System.out.println(" ｜  ＼　　＼＿/  ／ ");
        System.out.println(" ｜    ＼＿＿＿＿／");
        System.out.print("주문실패!" + "손님이 컴플레인을 걸었습니다.서비스를 제공해서 화를 풀어주세요." + "\n");

        System.out.println("───────────────────────────────────────────────────────");

        ThreadWorkerAngry mistake = new ThreadWorkerAngry(); // 손님 만족도가 떨어지는 쓰레드
        Thread mistakeOrder = new Thread(mistake);
        mistakeOrder.start();
        try { mistakeOrder.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

            if ("피곤한직장인".equals(guests)) {
                System.out.print("▷▷▷ 직장인의 스트레스지수가 +10 되었습니다." + "\n"); // 주문실패시 직장인스트레스지수 +10증가
            } else if ("외국인".equals(guests)) {
                System.out.print("이거 아니에요. 메뉴가 잘못나왔어요." + "\n");//주문실패시 외국인 출력문구
            } else if ("단골대학생".equals(guests)) {
                System.out.print("▷▷▷ 학생의 단골지수가 -10 돠었습니다." + "\n"); //주문실패시 단골대학생 단골지수 -10감소
            }

            Side side = new OrderSide().Menu.get(new Random().nextInt(2));// 서비스로 랜덤사이드 메뉴를 제공
            System.out.printf("%s", Side.name + "을(를)ლ(´ڡ`ლ) 제공합니다." + "\n" + "\n" + "\n");


        RandomOrder();

        if (Characters.Chef.HP < 0) { // 주방장체력이 0이 되면 breakTime 으로이동
            breakTime(); //
            }
        }



    public static void selectMenu() { // 초밥집 이름 작성 후 > 선택할수 있는 5가지 선택메뉴

        System.out.print("▶ 가게문을 열었습니다.무엇을 할까요?" + "\n"); //처음 가게문을 열었을때 메뉴선택 5가지
        System.out.print("1.손님받기" + "\n" + "2.메뉴관리" + "\n" + "3.상점가기" + "\n" + "4.청소하기" + "\n" + "5.등장인물정보" + "\n");
        Scanner what = new Scanner(System.in);
        while (!what.hasNextInt()) {
            what.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int whatTodo = what.nextInt();

        String Sale = switch (whatTodo) { // 1-5번호 선택시에 실행되는 문구
            case 1 -> "손님받기를 선택하셨습니다." + "\n";
            case 2 -> "메뉴관리를 선택하셨습니다" + "\n";
            case 3 -> "상점가기를 선택하셨습니다" + "\n";
            case 4 -> "청소하기를 선택하셨습니다" + "\n";
            case 5 -> "주방장 상태보기를 선택하셨습니다" + "\n";
            default -> "무엇을 할지 선택해주세요" + "\n";
        }; // 번호 선택시 출력되는 케이스 1-5
        System.out.println(Sale);
        if (whatTodo == 1) {
            RandomOrder(); // 점심 주문하기로 이동
        } else if (whatTodo == 2) {
            showMenu(); // 메뉴판 보기로 이동
        } else if (whatTodo == 3) {
            Shop(); // 상점가기로 이동
        } else if (whatTodo == 4) {
            clean(); // 청소하기 메뉴로 이동
        } else {
            charactersInfo(); // 주방장 정보 보기로 이동
        }
    }

    public static void showMenu() {// 메뉴2. 메뉴관리 선택시 현재 판매중인 초밥, 음료수, 사이드 메뉴 정보를 보여줌
        {
            System.out.println("•─────────•°•❀••❀• 메뉴판 정보를 확인하세요. •❀••❀•°•────────•" + "\n");

            System.out.println("1.메뉴판 보기 \t 2.뒤로가기" + "\n");
            Scanner menuSc = new Scanner(System.in);
            while (!menuSc.hasNextInt()) {
                menuSc.next();
                System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
            }
            int showMenu = menuSc.nextInt();

            if (showMenu == 1) { // 초밥 메뉴 정보

                System.out.println("┏━━━━━━━━━━━━━━━━     《  초밥정보  》    ━━━━━━━━━━━━━━━━━┓" + "\n");

                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 초밥메뉴
                        flatfish.name, flatfish.price, flatfish.gram, flatfish.goSo, flatfish.fat); // 광어초밥 정보
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 도로초밥 정보
                        tuna.name, tuna.price, tuna.gram, tuna.goSo, tuna.fat);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 장어초밥 정보
                        longfish.name, longfish.price, longfish.gram, longfish.goSo, longfish.fat);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 연어초밥 정보
                        salmon.name, salmon.price, salmon.gram, salmon.goSo, salmon.fat);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 새우초밥 정보
                        shrimp.name, shrimp.price, shrimp.gram, shrimp.goSo, shrimp.fat);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "고소함:%s/10" + "\t" + "지방량:%s/10" + "\n", // 계란초밥 정보
                        egg.name, egg.price, egg.gram, egg.goSo, egg.fat);
                System.out.println("───────────────────────────────────────────────────────" + "\n" + "\n" + "\n");


                System.out.println("┏━━━━━━━━━━━━━━━━     《  음료정보  》    ━━━━━━━━━━━━━━━━━┓" + "\n"); // 음료 메뉴 정보

                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "탄산:%s/10" + "\t" + "알콜:%s" + "\n", // 사이다 정보
                        sparklingWater.name, sparklingWater.price, sparklingWater.gram, sparklingWater.sparkling, sparklingWater.alcohol);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "탄산:%s/10" + "\t" + "알콜:%s" + "\n", // 테라맥주 정보
                        alcoholBeer.name, alcoholBeer.price, alcoholBeer.gram, alcoholBeer.sparkling, alcoholBeer.alcohol);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "탄산:%s/10" + "\t" + "알콜:%s" + "\n", // 진로소주 정보
                        alcoholSoju.name, alcoholSoju.price, alcoholSoju.gram, alcoholSoju.sparkling, alcoholSoju.alcohol);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "탄산:%s/10" + "\t" + "알콜:%s" + "\n", // 콜라 정보
                        coke.name, coke.price, coke.gram, coke.sparkling, coke.alcohol);
                System.out.println("───────────────────────────────────────────────────────" + "\n" + "\n" + "\n");


                System.out.println("┏━━━━━━━━━━━━━━━━    《  사이드 정보  》    ━━━━━━━━━━━━━━━━┓" + "\n"); // 사이드 메뉴 정보


                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "포만감:%s/10" + "\n", // 고로케 정보
                        gorokea.name, gorokea.price, gorokea.gram, gorokea.fullness);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "포만감:%s/10" + "\n", // 치킨가라아게 정보
                        chicken.name, chicken.price, chicken.gram, chicken.fullness);
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%s ▶" + "\t" + "%s원" + "\t" + "%sg" + "\t" + "\t" + "포만감:%s/10" + "\n", // 우동
                        udong.name, udong.price, udong.gram, udong.fullness);
                System.out.println("───────────────────────────────────────────────────────" + "\n");


                selectMenu(); // 뒤로 돌아가, 5가지 메뉴중 다시 선택하기
            }
            if (showMenu == 2) {
                selectMenu(); // 뒤로 돌아가, 5가지 메뉴중 다시 선택하기

            }
        }
    }

    public static void Upgrade() { // 3.상점가기 > 업그레이드에서는 체력, 평판, 손님만족도 구입 가능
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.printf("주방장의 가진돈:%s 체력:%s 평판:%s   " + "\n", Characters.Chef.coin, Characters.Chef.HP, Characters.Chef.scoreStar);// 주방장의 상태보기
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.print("업그레이드 선택 \n");
        System.out.println("────────────────────────────────────────────────────────────");
        System.out.print("1.일반도마 > 고급도마( 주문가능수+ 5 ) 3000원 \n");
        System.out.print("2.무딘칼 > 장미칼 사시미( 돈X2배 ) 5000원 \n");
        System.out.print("3.일반앞치마 > 화려한 앞치마( 만족도 X 2 ) 2000원 \n");
        System.out.print("4.어제 사온활어 > 갓잡은 활어( 만족도 X 3) 3000원 \n");
        System.out.print("5.안산다. \n");


        Scanner upgradeSc = new Scanner(System.in);
        while (!upgradeSc.hasNextInt()) {
            upgradeSc.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int selectMenu = upgradeSc.nextInt();


        switch (selectMenu) { // 메뉴선택 1-5
            case 1 -> {
                orderNumber = orderNumber + 5;  // 주문가능수 +5 증가
                Characters.Chef.coin = Characters.Chef.coin - 3000; // 1번선택시 주방장돈 -3000원 차감
                System.out.println("1.일반도마 > 고급도마를 골랐습니다.");
                System.out.println("주문 가능수가 5회 추가 되었습니다. \n");
            }
            case 2 -> {
                Characters.Chef.coin = Characters.Chef.coin * 2; // 2번 선택시 주방장돈 x2
                Characters.Chef.coin = Characters.Chef.coin - 5000; // 주방장돈 -5000원 차감
                System.out.println("2.무딘칼 > 장미칼 사시미( 돈X2배 )를 골랐습니다.");
                System.out.println("주방장 현재 매출이 X 2두배가 됩니다.\n");
            }
            case 3 -> {
                Characters.Npc.satisfaction = Characters.Npc.satisfaction * 2; // 3번선택시 고객만족도 x2
                Characters.Chef.coin = Characters.Chef.coin - 2000; // 주방장돈 -2000원
                System.out.println("3.일반앞치마 > 화려한 앞치마( 만족도 X 2 )를 골랐습니다.");
                System.out.println(" 만족도 X 2 가 되었습니다.\n");
            }
            case 4 -> {
                Characters.Npc.satisfaction = Characters.Npc.satisfaction * 3;//4번 선택시 고객만족도 x3
                Characters.Chef.coin = Characters.Chef.coin - 3000; //주방장돈 -3000원
                System.out.print("4.어제 사온활어 > 갓잡은 활어( 만족도 X 3)를 골랐습니다.\n");
                System.out.println("누적된 평판이 X 3 가 되었습니다.\n");
            }
            case 5 -> {
                System.out.print("5.안산다를 골랐습니다. \n");
                System.out.println("안삽니다.");
            }
            default -> System.out.println("다시 번호를 선택해주세요");
        }
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.printf("주방장의 가진돈:%s 체력:%s 평판:%s   " + "\n", Characters.Chef.coin, Characters.Chef.HP, Characters.Chef.scoreStar); // 주방장 상태표시
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.println("2를 누르면 아이템 구매하기" + "\n");
        Scanner itemMenuSc = new Scanner(System.in);
        while (!itemMenuSc.hasNextInt()) {
            itemMenuSc.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int itemMenu = itemMenuSc.nextInt();
        if (itemMenu == 2) {
            Buyitem(); // 상점 > 아이템구입으로 이동
        }
    }

    public static void Buyitem() { // 메뉴3.상점가기  > 아이템 구입하기( 체력, 주문수 구입 가능)


        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.printf("주방장의 가진돈:%s 체력:%s 평판:%s", Characters.Chef.coin, Characters.Chef.HP, Characters.Chef.scoreStar + "\n"); // 주방장 현재 상태
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");

        System.out.print("아이템 구매" + "\n");
        System.out.println("────────────────────────────────────────────────────────────");
        System.out.print("1.홍삼 ( 체력 +100 ) 7000원 \n");
        System.out.print("2.낚지 ( 체력 +70 ) 5000원  \n");
        System.out.print("3.밀크시슬 ( 체력 +50 ) 3000원 \n");
        System.out.print("4.레드불 ( 체력 +30 ) 2000원  \n");
        System.out.print("5.재료구입 ( 주문가능수 +5 ) 5000원  \n");
        System.out.print("6.안산다.  \n");

        Scanner itemUpgradeSc = new Scanner(System.in);
        while (!itemUpgradeSc.hasNextInt()) {
            itemUpgradeSc.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int selectItemMenu = itemUpgradeSc.nextInt();

        switch (selectItemMenu) {
            case 1 -> {
                Characters.Chef.HP = Characters.Chef.HP + 100;  // 주방장체력 +100상승
                Characters.Chef.coin = Characters.Chef.coin - 7000;// 주방장돈 -7000원
                System.out.print("1.홍삼 ( 체력 +100 )를 골랐습니다. \n");
                System.out.println("주방장 체력이 100+ 되었습니다.");
            }
            case 2 -> {
                Characters.Chef.HP = Characters.Chef.HP + 70;  // 주방장체력 +70상승
                Characters.Chef.coin = Characters.Chef.coin - 5000;// 주방장돈 -5000원
                System.out.print("2.낚지 ( 체력 +70 )를 골랐습니다.  \n");
                System.out.println("주방장 체력이 70+ 되었습니다.");
            }
            case 3 -> {
                Characters.Chef.HP = Characters.Chef.HP + 50;  // 주방장체력 +50상승
                Characters.Chef.coin = Characters.Chef.coin - 3000; // 주방장돈 -5000원
                System.out.print("3.밀크시슬 ( 체력 +50 )를 골랐습니다. \n");
                System.out.println("주방장 체력이 50+ 되었습니다.");
            }
            case 4 -> {
                Characters.Chef.HP = Characters.Chef.HP + 30;  // 주방장체력 +30상승
                Characters.Chef.coin = Characters.Chef.coin - 2000; // 주방장돈 -2000원
                System.out.print("4.레드불 ( 체력 +30 )를 골랐습니다. \n");
                System.out.println("주방장 체력이 30+ 되었습니다.");
            }
            case 5 -> {
                orderNumber = orderNumber + 5;  // 전체 주문가능수 +5 증가
                Characters.Chef.coin = Characters.Chef.coin - 3000; //주방장돈 -3000원
                System.out.print("5.재료구입 ( 주문가능수 +5 )를 골랐습니다. \n");
                System.out.println("주문가능 수 5+ 되었습니다.");
            }
            default -> System.out.print("6.안산다. 를 골랐습니다. \n");
        }
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");
        System.out.printf("주방장의 가진돈:%s 체력:%s 평판:%s   " + "\n" + "\n", Characters.Chef.coin, Characters.Chef.HP, Characters.Chef.scoreStar); //주방장상태
        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓" + "\n");

        System.out.println("1.주문계속받기\t 2.업그레이드\t 3.메뉴로 돌아가기" + "\n");
        Scanner backSc = new Scanner(System.in);
        while (!backSc.hasNextInt()) {
            backSc.next();
            System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
        }
        int backMenu = backSc.nextInt();

        switch (backMenu) {
            case 1 -> randomOrderDinner();  // 저녁영업 랜덤 주문으로 이동
            case 2 -> Upgrade();            // 메뉴3.상점 > 업그레이드로 이동
            default -> selectMenu();        // 메뉴선택으로 이동
        }
    }


    public static void Shop() { // 메뉴3.상점가기 에서는 업그레이드와 아이템구매가 가능
        System.out.println("\n" + "\n" + "•─────────•°•❀••❀• 상점에서 아이템을 구매하세요 •❀••❀•°•────────•");
        if (Characters.Chef.coin < 2000) { // 주방장돈이 2000원이하이면 상점에서 구입불가
            System.out.println("돈이 없습니다...돈을 벌어서 다시 오세요. 메뉴로 다시 돌아갑니다" + "\n");
            selectMenu();
        } else {
            System.out.println("조리도구를 업그레이드하고, 아이템을 구매해 체력을 강화할 수 있습니다");
            System.out.println("────────────────────────────────────────────────────────────");

            System.out.println("1.업그레이드하기\t 2.아이템 구매하기" + "\n");
            Scanner shopMenuSc = new Scanner(System.in);
            while (!shopMenuSc.hasNextInt()) {
                shopMenuSc.next();
                System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
            }
            int shopMenu = shopMenuSc.nextInt();

            if (shopMenu == 1) {
                Upgrade(); // 메뉴3.상점> 업그레이드하기로 이동
            } else {
                Buyitem(); // 메뉴3 상점> 아이템구입으로 이동
            }
        }
    }


    public static void charactersInfo() { // 메뉴5. 캐릭터 정보보기 메뉴에서는 주방장 정보 확인
        {
            System.out.println("•─────────•°•❀••❀•  등장인물 정보를 선택하세요  •❀••❀•°•────────•" + "\n");

            System.out.print("1.주방장정보 \n");
            Scanner charInfo = new Scanner(System.in);
            while (!charInfo.hasNextInt()) {
                charInfo.next();
                System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
            }
            int characterShow = charInfo.nextInt();//화면에서 입력받은 값

            if (characterShow == 1) { // 주방장정보 선택시 출력

                System.out.printf("이름 ▶ %s" + "\n" + "직업 ▶ %s" + "\n" + "나이 ▶ %s" + "\n" + "체력 ▶ %s" + "\n" + "평판 ▶ %s" + "\n" + "매출 ▶ %s" + "\n",
                        chef.name, chef.job, chef.age, Characters.Chef.HP, Characters.Chef.scoreStar, Characters.Chef.coin); //주방장 현재 상태

                System.out.println("•─────────•°•❀••❀•    주방장 경력 정보입니다.  •❀••❀•°•────────•" + "\n");
                System.out.println(" 빅마마 요리학원 1년 수료 ");
                System.out.println(" 백종원 요리비책 유투브 구독자 ");
                System.out.println(" 은행골 요리사 3년 ");
                System.out.println(" 일식 조리사자격증 보유"+"\n"+"\n");
            }
            System.out.println("1.메뉴로 돌아가기" +"\n");
            Scanner shopMenuSc = new Scanner(System.in);
            while (!shopMenuSc.hasNextInt()) {
                shopMenuSc.next();
                System.err.print("숫자만 입력하세요"); // 숫자이외 입력시 출력
            }
            int shopMenu = shopMenuSc.nextInt();

            if (shopMenu == 1) {
                selectMenu(); // 메뉴로 돌아기기
            }
        }

    }

    public static void gameOver() { // 게임오버 (주문을 3번실패시 게임종료하기로 이동)

        ThreadGameover threadOver =new ThreadGameover();
        threadOver.start();
        try { threadOver.join(); } catch (InterruptedException ie) { ie.printStackTrace(); }

        System.out.println("주문실패 3번으로 게임을 종료합니다."+ "\n"+ "\n");

        selectMenu();
        }
    }

