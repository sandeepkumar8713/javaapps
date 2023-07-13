class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super();
    }
    
    public InvalidAmountException(String msg) {
        super(msg);
    }
    
}

class HDFCBank {
    private double balance;
    public void deposite (double amt) throws InvalidAmountException {
        if (amt<=0)
            throw new InvalidAmountException(amt+"is invalid amt");
        balance=balance+amt;
    }
    public static void main(String[] args){
        HDFCBank acc1=new HDFCBank();

        try {
            acc1.deposite(100);
            System.out.println("Amount deposited");
        }
        catch(InvalidAmountException ae){
            System.out.println(ae.getMessage());
        }
    }
}
