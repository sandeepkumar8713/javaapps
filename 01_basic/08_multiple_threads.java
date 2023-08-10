class MyThread extends Thread {
    int x;

    MyThread(){
        x=5;
    }

    MyThread(int x){
        this.x = x;
    }

    public void run (){
        for (int i = 0;i<this.x;i++){
            System.out.println(getName() + "Run :" + i);
        }
    }
    
}

class MultipleThreadWithSameLogic{
    public static void main(String[] args){
        MyThread m1 = new MyThread();
        m1.start();

        MyThread m2 = new MyThread(10);
        m2.start();

        MyThread m3 = new MyThread(20);
        m3.start();

        for (int i = 0;i<20;i++){
            System.out.println("Main :" + i);
        }


    }
}

