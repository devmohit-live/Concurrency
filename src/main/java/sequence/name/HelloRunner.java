package sequence.name;

public class HelloRunner {
    PrinterState state = new PrinterState(PrinterTurn.HELLO);
    public void run(){
        new Thread(new AmPrinter(state)).start();
        new Thread(new MohitPrinter(state)).start();
        new Thread(new HelloPrinter(state)).start();
        new Thread(new IPrinter(state)).start();
    }
}
