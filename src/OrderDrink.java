import java.util.ArrayList;


public class OrderDrink { // 음료메뉴

    ArrayList<Drink> Menu = null;  // 음료객체를 담기위한 ArrayList를 생성,이름을 Menu로 초기화

    public OrderDrink() {

        Drink drink1 = new Drink(); //사이다 이름 가격
        drink1.name = "사이다";
        drink1.price = 2000;

        Drink drink2 = new Drink(); // 테라 이름 가격
        drink2.name = "테라";
        drink2.price = 5000;

        Drink drink3 = new Drink(); //진로 이름 가격
        drink3.name = "진로";
        drink3.price = 4000;

        Drink drink4 = new Drink(); //콜라 이름 가겨
        drink4.name = "콜라";
        drink4.price = 2000;

        Menu = new ArrayList<Drink>();

        Menu.add(drink1);
        Menu.add(drink2);
        Menu.add(drink3);
        Menu.add(drink4);

//      System.out.println(Menu.get(2).name); //메뉴의 세번째것을 가져와라.
//      System.out.println("Order");
//      System.out.println(Menu.size()); // 메뉴 갯수를 불러옴
//      System.out.printf(Menu.get(i).name);
    }

}