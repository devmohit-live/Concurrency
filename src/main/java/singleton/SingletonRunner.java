package singleton;

public class SingletonRunner {
    public void run(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton s = Singleton.getInstance();
             }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton s = Singleton.getInstance();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton s = Singleton.getInstance();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton s = Singleton.getInstance();
            }
        }).start();
    }
}


