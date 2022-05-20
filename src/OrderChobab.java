import java.util.ArrayList;

public class OrderChobab { //초밥메뉴

    ArrayList<Chobab> Menu = null;  // 초밥객체를 담기위한 ArrayList를 생성,이름을 Menu로 초기화

    public OrderChobab() {

        Chobab ch0 = new Chobab(); // 광어초밥 이름/ 가격
        ch0.name = "광어";
        ch0.price = 2000;

        Chobab ch1 = new Chobab(); // 도로초밥 이름/ 가격
        ch1.name = "도로";
        ch1.price = 4000;

        Chobab ch2 = new Chobab();// 장어초밥 이름/가격
        ch2.name = "장어";
        ch2.price = 4000;

        Chobab ch3 = new Chobab();// 연어초밥 이름/가격
        ch3.name = "연어";
        ch3.price = 3000;

        Chobab ch4 = new Chobab();// 새우초밥 이름/가격
        ch4.name = "새우";
        ch4.price = 2000;

        Chobab ch5 = new Chobab();// 계란초밥 이름/가격
        ch5.name = "계란";
        ch5.price = 2000;


        Menu = new ArrayList<Chobab>(); // 메뉴에 추가
        Menu.add(ch0);
        Menu.add(ch1);
        Menu.add(ch2);
        Menu.add(ch3);
        Menu.add(ch4);
        Menu.add(ch5);


//      System.out.println(Menu.get(2).name); //메뉴의 세번째것을 가져와라.
//      System.out.println("Order");
//      System.out.println(Menu.size()); // 메뉴 갯수를 불러옴
//      System.out.printf(Menu.get(i).name);
    }

}