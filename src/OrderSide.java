import java.util.ArrayList;


public class OrderSide{ //사이드 메뉴

    ArrayList<Side> Menu;  // 초밥객체를 담기위한 ArrayList를 생성,이름을 Menu로 초기화

    public OrderSide() {

        Side side1 = new Side(); //고로케 이름 가격
        Side.name = "고로케";
        side1.price = 2000;


        Side side2 = new Side(); //가라아게 이름 가격
        Side.name = "가라아게";
        side2.price = 5000;

        Side side3 = new Side(); //우동 이름 가격
        Side.name = "우동";
        side3.price = 3000;


        Menu = new ArrayList<Side>();
        Menu.add(side1);
        Menu.add(side2);
        Menu.add(side3);



//      System.out.println(Menu.get(2).name); //메뉴의 세번째것을 가져와라.
//      System.out.println("Order");
//      System.out.println(Menu.size()); // 메뉴 갯수를 불러옴
//      System.out.printf(Menu.get(i).name);
    }

}