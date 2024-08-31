package sequence.name;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IPrinter implements Runnable{
    PrinterState state;

    @lombok.SneakyThrows
    @Override
    public void run() {
        synchronized (state) {
            while(state.getTurn()!= PrinterTurn.I){
                state.wait();
            }
            System.out.println("I");
            state.setTurn(PrinterTurn.AM);
            state.notifyAll();
        }

    }
}
