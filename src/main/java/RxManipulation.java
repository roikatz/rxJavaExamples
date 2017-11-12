import rx.Observable;
import rx.observables.BlockingObservable;

import static java.lang.Thread.sleep;

public class RxManipulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n=======MAP==========");

        // Blocking observable, anti-pattern, just for the example.
        BlockingObservable<Integer> myObservableMap = Observable.range(1,10)
                .map(number -> number+1)
                .toBlocking();
//
        myObservableMap.subscribe(x ->  System.out.print(x + " "));

        System.out.println("\n=======DoOnNext========");
        Observable<Integer> myObservableOnNext = Observable.range(1,1000)
                .map(number -> number+1)
                .doOnNext(x->System.out.println("sending " + x)) // just for logging between steps
                .doOnCompleted(()-> System.out.println("Stream completed successfully"));

        myObservableOnNext.subscribe(x -> System.out.println("receiving: " + x + " "));

        sleep(500);

        System.out.println("\n=======DoOnNext========");
        Observable<Integer> myObservableOnNextWithFilter = Observable.range(1,10)
                .doOnNext(x->System.out.println("sending (Before Map) " + x))

                .map(number -> number+1)
                .doOnNext(x->System.out.println("sending (Before filtering) " + x))

                .filter(number-> number % 2 == 0)
                .doOnNext(x->System.out.println("sending " + x))
                .doOnCompleted(()-> System.out.println("Stream completed successfully"));

        myObservableOnNextWithFilter.subscribe(x -> System.out.println("receiving: " + x + " "));

        sleep(500);
    }
}
