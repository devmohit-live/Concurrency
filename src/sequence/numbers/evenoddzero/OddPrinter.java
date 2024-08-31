package sequence.numbers.evenoddzero;


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
                System.out.print(state.getNumber()+" ");
                curr++;
                state.setTurn(Turn.EVEN);
                state.setNumber(state.getNumber()+1);
                state.notifyAll();
            }
        }
    }
}
