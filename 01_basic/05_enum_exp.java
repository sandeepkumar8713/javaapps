enum Month {
    Jan, Feb, Mar;
}

enum Months {
    JAN(1), FEB(2), MAR(3);

    private int num;

    Months(int num){
        this.num = num;
    }

    public int getMonths(){
        return this.num;
    }

    public void setMonths(int num){
        this.num = num;
    }
}

class Year {
    public static void main(String[] args){
        System.out.println(Month.Jan);
        System.out.println(Month.Feb);
        System.out.println(Month.Mar);

        System.out.println(Months.JAN.getMonths());
        System.out.println(Months.FEB.getMonths());
        System.out.println(Months.MAR.getMonths());
    }
}

