package sequence.numbers;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class OddPrinter implements Runnable{
    int curr, limit;
    NumberState state;

    @SneakyThrows
    @Override
    public void run() {
        while(curr<=limit){
            synchronized (state) {
                while(state.getTurn() != Turn.ODD){
                    state.wait();
                }
                System.out.println(curr);
                curr+=2;
                state.setTurn(Turn.EVEN);
                state.notifyAll();
            }
        }
    }
}
