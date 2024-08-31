package sequence.numbers;

public class EvenOddRunner {
    final NumberState state = new NumberState(Turn.ODD);
    final int N = 10;
    public void run(){
        new Thread(new EvenPrinter(2,N,state)).start();
        new Thread(new OddPrinter(1,N,state)).start();
    }
}
