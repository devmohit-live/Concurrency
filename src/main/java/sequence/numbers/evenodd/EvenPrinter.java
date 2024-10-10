package sequence.numbers.evenodd;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class EvenPrinter implements Runnable{
    int curr, limit;
    NumberState state;

    @SneakyThrows
    @Override
    public void run() {
        while(curr<=limit){
            synchronized (state) {
                while(state.getTurn() != Turn.EVEN){
                    state.wait();
                }
                System.out.println(curr);
                curr+=2;
                state.setTurn(Turn.ODD);
                state.notifyAll();
            }
        }
    }
}
