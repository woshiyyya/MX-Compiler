package Test;

class son extends test.father {
    int b;

    public void Print(){
        super.Printf();
        System.out.println("son method " + this.getClass());
    }

    son(int B){
        b = B;
    }
}

class daughter extends test.father {
    int b;

    public void Print(){
        System.out.println("daughter method " + this.getClass());
    }
}