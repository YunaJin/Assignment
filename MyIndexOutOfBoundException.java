public class MyIndexOutOfBoundException {
    int lowerBond;
    int upperBond;
    int index;

    public static void indexOutOfBoundException(int index, int lowerBond, int upperBond) {
        if (index<lowerBond || index >= upperBond) {
            System.out.println("\"Error Message: " + "Index: " + index + ", but Lower bound: "
                    + lowerBond+ ", Upper bound: " +upperBond + "\"" + "\n");
        }else{
            System.out.println("inside of bond");
        }

    }
    public static void main(String args[]){
    MyIndexOutOfBoundException myIndexOutOfBoundException=new MyIndexOutOfBoundException();
    myIndexOutOfBoundException.indexOutOfBoundException(10,0,9);
    }
}