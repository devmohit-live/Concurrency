package sequence.numbers.evenoddzero;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class FiboPrinter implements Runnable{
    int currFreq, maxFreq;
    NumberState state;
    int a ,b;
    @SneakyThrows
    @Override
    public void run() {
        while(currFreq <= maxFreq){
            synchronized (state) {
                while(state.getTurn() != Turn.FIBONACCI){
                    state.wait();
                }
                int c = a + b;
                a = b;
                b = c;
                System.out.print(c+" ");
                currFreq++;
                state.setTurn(Turn.ODD);
                state.notifyAll();
            }
        }
    }
}
