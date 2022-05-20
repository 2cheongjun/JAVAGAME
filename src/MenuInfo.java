public class MenuInfo {


    static class MenuAll { //부모클래스
        String name;// 메뉴명
        int price; // 가격
        int gram;  //무게

        public MenuAll(String name, int price, int gram) { // 메뉴이름, 가격, 그램
            super();
            this.name = name;
            this.price = price;
            this.gram = gram;
        }

//        public String toString() {
//            return String.format("(메뉴명:%s)(가격:%s)(gram:%s)", this.name, this.price, this.gram);
//        }

        static class ChobabMenu extends MenuAll { // 자식클래스 초밥메뉴 + 고소함/지방량 항목 추가됨
            int goSo; //고소함지수
            int fat;  // 지방량 지수

            public ChobabMenu(String name, int price, int gram, int goSo, int fat) { // 초밥상태
                super(name, price, gram);
                this.goSo = goSo;
                this.fat = fat;
            }

            public String toString() {
                return String.format("(메뉴명:%s)(가격:%s)(gram:%s)(고소함:%s)(지방량:%s)", this.name, this.price, this.gram, goSo, fat);
            }
        }


        static class drink extends MenuAll {  // 자식클래스 음료수 + 탄산/알콜 항목 추가됨
            int sparkling; //탄산함량
            int alcohol;   //알콜함량

            public drink(String name, int price, int gram, int sparkling, int alcohol) { //음료상태
                super(name, price, gram);
                this.sparkling = sparkling;
                this.alcohol = alcohol;
            }

            public String toString() {
                return String.format("(메뉴명:%s)(가격:%s)(gram:%s)(탄산:%s)(알콜:%s)", this.name, this.price, this.gram, sparkling, alcohol);
            }
        }

        static class side extends MenuAll {  // 자식클래스 사이드메뉴
            int fullness; //포만감

            public side(String name, int price, int gram, int fullness) { //사이드메뉴 상태
                super(name, price, gram);
                this.fullness = fullness;
            }

            public String toString() {
                return String.format("(메뉴명:%s)(가격:%s)(gram:%s)(포만감:%s)", this.name, this.price, this.gram, fullness);
            }
        }
    }
}



