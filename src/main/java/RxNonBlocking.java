import rx.Observable;

import java.util.concurrent.TimeUnit;

public class RxNonBlocking {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> nonBlocking = Observable.interval(1, TimeUnit.SECONDS); //Try with NANOSECONDS


        nonBlocking.subscribe(num->System.out.println("Got " + num));

        while(true){} // Comment me


    }

}
