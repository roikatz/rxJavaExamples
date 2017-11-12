import rx.Observable;
import rx.schedulers.Schedulers;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

public class RxObserveOn {
    public static void main(String[] args) throws InterruptedException {
        out.println("\n=======ObserveOn========");
        Observable<Integer> myObservableOnNextObserve = Observable.range(1,10)
                .map(number -> number+1)
                .filter(number-> number % 2 == 0)
                .doOnNext(x-> out.println("sending " + x + " on thread " + Thread.currentThread().getName()))
                .observeOn(Schedulers.io()) // choose the method on which to observe on

                .subscribeOn(Schedulers.computation()) // choose the method on which to subscribe on
                .doOnCompleted(()-> out.println("Stream completed successfully"));

        myObservableOnNextObserve.subscribe(x -> out.println("receiving: " + x + " on thread " + Thread.currentThread().getName()));

        sleep(500);
    }

}
