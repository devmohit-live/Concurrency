package sequence.name;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MohitPrinter implements Runnable{
    PrinterState state;


    @lombok.SneakyThrows
    @Override
    public void run() {
        synchronized (state) {
            while(state.getTurn()!= PrinterTurn.MOHIT){
                state.wait();
            }
            System.out.println("Mohit");
            state.setTurn(null);
            state.notifyAll();
        }

    }
}
