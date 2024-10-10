package singleton;
public class Singleton {
    private static volatile Singleton instance = null; // in case different thread runs on different core and the, lock value is yet not propagated to the main memory from cpu memory.
    private Singleton() {}

    public static Singleton getInstance(){
       return getInstanceEarlyReturn();
    }



    public static Singleton getInstanceEarlyReturn(){
        if(instance != null){
            return instance;
        }

        synchronized (Singleton.class){
            if(instance != null){ // as sync block only allows 1 thread at a time and t1,t2 can both create a new instance one by one, so this check is needed
                instance = new Singleton();
            }
            return instance;
        }
    }


    public static Singleton getInstanceDoubleLocking(){
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                     // as sync block only allows 1 thread at a time and t1,t2 can both create a new instance one by one, so this additional check is needed

                    instance = new Singleton();
                }
                return instance;
            }
        }
        return instance;
    }


}
