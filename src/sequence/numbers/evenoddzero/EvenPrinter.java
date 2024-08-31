package sequence.numbers.evenoddzero;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class EvenPrinter implements Runnable{
    int currFreq, maxFreq;
    NumberState state;

    @SneakyThrows
    @Override
    public void run() {
        while(currFreq <= maxFreq){
            synchronized (state) {
                while(state.getTurn() != Turn.EVEN){
                    state.wait();
                }
                System.out.println(state.getNumber());
                currFreq++;
                state.setTurn(Turn.ZERO);
                state.setNumber(state.getNumber() + 1);
                state.notifyAll();
            }
        }
    }
}
