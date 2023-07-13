class Example_1 {
    Example_1(){
        this(10);
        System.out.println("No-arg constructor");
    }

    Example_1(int a){
        this("abc");
        System.out.println("int-arg constructor");
    }

    Example_1(String str){
        System.out.println("str-arg constructor");
    }

    public static void main(String[] args){
        Example_1 e1 = new Example_1();

        Example_1 e2 = new Example_1(10);

        Example_1 e3 = new Example_1("abc");
    }
}
