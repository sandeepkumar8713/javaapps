class MyRunnable implements Runnable {

    public void run(){
        System.out.println("run");
    }

    public static void main(String[] args){
        System.out.println("main");

        MyRunnable mr = new MyRunnable();
        Thread th = new Thread(mr);
        th.start();
    }
    
}
