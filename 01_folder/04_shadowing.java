class Shadowing {

    int x = 10;
    int y = 20;

    void m1(){
        System.out.println(this.x);
        System.out.println(this.y);
        int x = 50;
        System.out.println(x);
        System.out.println(y);
    }

    public static void main(String[] args){
        Shadowing shadowing = new Shadowing();
        shadowing.m1();
    }
    
}
