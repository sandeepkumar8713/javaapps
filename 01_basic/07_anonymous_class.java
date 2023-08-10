interface Example_2{
    void m1();
}

class Test_2 {
    public static void main(String[] args){
        Example_2 e = new Example_2(){
            public void m1(){
                System.out.println("Anonymous overriding method m1");
                m2();
            }
            public void m2(){
                System.out.println("Anonymous own method m2");
            }
        };
        e.m1();
    }
}