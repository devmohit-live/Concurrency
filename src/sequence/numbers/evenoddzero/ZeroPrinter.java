package sequence.numbers.evenoddzero;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ZeroPrinter implements Runnable{
    int currFreq, maxFreq;
    NumberState state;

    @SneakyThrows
    @Override
    public void run() {
        final int ZERO = 0;
        while(currFreq <= maxFreq){
            synchronized (state) {
                while(state.getTurn() != Turn.ZERO){
                    state.wait();
                }
                System.out.println(ZERO);
                currFreq++;
                state.setTurn(Turn.ODD);
                state.notifyAll();
            }
        }
    }
}
