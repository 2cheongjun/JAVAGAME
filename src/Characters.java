public class Characters {


static class People {
    public int coin; // 부모(등장인물) 클래스
    String name;// 이름
    String job; // 직업
    int age;    // 나이


    public People(String name, String job, int age, int coin) {
        this.name = name;
        this.job = job;
        this.age = age;
        this.coin =coin;
    }

    public String toString() {
        return String.format("%s(job:%s)(age:%s)(age:%s)", this.name, this.job, this.age, this.coin);
    }
    public String getName() { // 이름가져오기
        return name;
    }
}


            static class Chef extends People {
                 static int HP;         // 주방장 체력
                 static int scoreStar; // 주방장 평판
                 static int coin;

                //
                public Chef(String name, String job, int age, int HP, int scoreStar ,int coin) { //주방장 정보
                    super(name, job, age,coin);
                    Chef.HP = HP; //주방장 체력
                    Chef.scoreStar = scoreStar; //주방장 평점
                }
//                public static void greetings(){
//                    System.out.println("☞☞☞☞☞☞☞☞☞☞☞☞☞☞주방장: 맛있는초밥을 제공합니다."); //주방장 인사말
//                }


           }
            static class Npc extends People {
                public static int satisfaction; //손님 만족도


                public Npc(String name, String job, int age,int coin, int satisfaction) {
                    super(name, job, age, coin);
                    Npc.satisfaction =satisfaction;
                }

            }

                    static class Student extends Npc {
                        String service; // 자식(NPC) 클래스의 _  단골대학생
                        int custom; // 단골대학생의 단골도

                        public Student(String name, String job, int age, int coin, int satisfaction,String service, int custom) { //단골대학생 상태
                            super(name, job, age, coin, satisfaction);
                            this.service = service; // 대학생요청 문구출력
                            this.custom = custom;   // 대학생 단골도

                        }
                        public String toString() {
                            return String.format("name:%s" + "job:%s" + "age:%s" + "satisfaction:%s",
                                                  name, this.job, this.age, satisfaction,service,custom);
                        }
                    }


                    static class Workers extends Npc {// 자식(NPC) 클래스의  _ 직장인
                        String tired; //직장인 출력문구
                        int stress; //직장인 스트레스 지수
                        public Workers(String name, String job, int age, int coin, int satisfaction, String tired, int stress) { //직장인 상태
                            super(name, job, age, coin, satisfaction);
                            this.tired= tired;
                            this.stress=stress;
                        }
                        public String toString() {
                            return String.format("name:%s" + "job:%s" + "age:%s" + "satisfaction:%s"+ "tired:%s"+"stress:%s",
                                    name, this.job, this.age, satisfaction,tired, stress);
                        }
                    }

                    static class Foreigner extends Npc { // 자식(NPC) 클래스의  _ 외국인 관광객
                       String english; //외국인 관광객 출력문구

                        public Foreigner(String name, String job, int age, int coin, int satisfaction, String english) { //외국인 관광객 상태
                            super(name, job, age, coin, satisfaction);
                            this.english = english;
                        }
                        public String toString() {
                            return String.format("name:%s" + "job:%s" + "age:%s" + "satisfaction:%s",
                                    name, this.job, this.age, satisfaction,english);
                        }
                    }


    }


