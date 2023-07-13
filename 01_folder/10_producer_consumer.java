class Factory {

    int items;
    boolean itemInFactory;

    synchronized void produce(int items){
        if (itemInFactory){

            try {
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        this.items = items;
        itemInFactory = true;
        System.out.println("Produced items: " + items);
        notify();
    }

    synchronized int consume(){
        if (!itemInFactory){

            try {
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        System.out.println("items Consumed: " + items);
        itemInFactory = false;
        notifyAll();
        return items;
    }
    
}

class Producer implements Runnable{
    Factory fa;

    Producer(Factory fa){
        this.fa = fa;
        new Thread(this,"Producer").start();
    }

    public void run(){
        int i = 1;
        while(i<=10){
            fa.produce(i++);
        }
    }
}

class Consumer implements Runnable{
    Factory fa;

    Consumer(Factory fa){
        this.fa = fa;
        new Thread(this,"Consumer").start();
    }

    public void run(){
        int i = 1;
        while(i<=10){
            fa.consume();
            i++;
        }
    }
}

class ITCWithWaitNotify{
    public static void main(String[] args) {
        Factory bajaj = new Factory();
        new Consumer(bajaj);
        new Producer(bajaj);
    }
}
