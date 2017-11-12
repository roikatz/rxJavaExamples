import rx.Observable;

import static java.lang.Thread.sleep;

// Look where the System.out.println("Subscribing..."); is executing
public class RxDefer {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> noDefer = Observable.from(SomeSlowNetworkAPI())
                .doOnNext(x -> System.out.print(" (+tx) "));// foreach element

        System.out.println("Obserable just waited for slow network (created on definition)");

        for (int i = 0; i < 10; i++) {
            System.out.print(".");
            sleep(100);
        }
        System.out.println("Subscribing...");

        noDefer.subscribe(x -> System.out.print(x + " (rx) "));


        Observable<String> defer = Observable.defer(() -> Observable.from(SomeSlowNetworkAPI())
                .doOnNext(x -> System.out.print(" (+tx) "))); // foreach element

        System.out.println("Obserable did not wait for slow network (will be created on subscription)");
        System.out.println("Subscribing...");

        for (int i = 0; i < 10; i++) {
            System.out.print(".");
            sleep(100);
        }
        defer.subscribe(x -> System.out.print(x + " (rx) "));

        sleep(500);
    }


    public static String[] SomeSlowNetworkAPI(){
        System.out.println("(inner): Starting slow method");

        try {
            sleep(500);

            for (int i=0; i<3 ; i++) {
                System.out.println("(inner): processing... ");
                sleep(500);
            }
        }
        catch (InterruptedException e)
        {/*Nothing to do here*/}

        String[] arr = {"Roi","Reactive","Extensions","Programming"};

        System.out.println("(inner): Ending slow method");

        return arr;
    }
}
