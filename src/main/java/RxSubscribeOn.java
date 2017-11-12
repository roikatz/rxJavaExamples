import rx.Observable;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class RxSubscribeOn {
    public static void main(String[] args) throws InterruptedException {

            System.out.println("\n=======SubscribeOn main========");
        BlockingObservable<Integer> stringy = Observable.just("Roi","Reactive","Extensions","Programming")
                .map(str -> str.length())
                .subscribeOn(Schedulers.io())

                .toBlocking();

        stringy.subscribe(x -> System.out.println(x +" on thread " + Thread.currentThread().getName()),
                (error)->System.out.println("Error!"));

        sleep(500);

        System.out.println("\n=======SubscribeOn other========");
        Observable<Integer> stringyOn = Observable.just("Roi","Reactive","Extensions","Programming")
                .subscribeOn(Schedulers.io())

                .map(str -> str.length());

        stringyOn.subscribe(x -> System.out.println(x +" on thread " + Thread.currentThread().getName()),
                        (error)->System.out.println("Error!"));


        sleep(500);
    }
}
