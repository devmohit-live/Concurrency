package sequence.name;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class HelloPrinter implements Runnable{
    PrinterState state;

    @lombok.SneakyThrows
    @Override
    public void run() {
        synchronized (state) {
            while(state.getTurn() != PrinterTurn.HELLO){
                state.wait();
            }
            System.out.println("Hello");
            state.setTurn(PrinterTurn.I);
            state.notifyAll();
        }
    }
}
