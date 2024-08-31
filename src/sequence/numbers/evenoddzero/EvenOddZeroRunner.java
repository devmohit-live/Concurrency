package sequence.numbers.evenoddzero;

public class EvenOddZeroRunner {
    final int start = 1;
    final int N = 5;

//    final NumberState state = new NumberState(Turn.ZERO, start);
    final NumberState state = new NumberState(Turn.ODD, start);
    public void run(){
        new Thread(new EvenPrinter(1,N,state)).start();
        new Thread(new OddPrinter(1,N,state)).start();
//        new Thread(new ZeroPrinter(1,N,state)).start();
        new Thread(new FiboPrinter(1,N,state,0,1)).start();
    }
}
