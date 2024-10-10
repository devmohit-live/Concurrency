package sequence.name;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AmPrinter implements Runnable{
    PrinterState state;

    @lombok.SneakyThrows
    @Override
    public void run() {
        synchronized (state) {
            while(state.getTurn()!= PrinterTurn.AM){
                state.wait();
            }
            System.out.println("Am");
            state.setTurn(PrinterTurn.MOHIT);
            state.notifyAll();
        }

    }
}
