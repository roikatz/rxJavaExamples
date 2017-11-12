import rx.Observable;

import static java.lang.Thread.sleep;

public class RxBasic {
    public static void main(String[] args) throws InterruptedException {
        //Example1
        System.out.println("=======Simple==========");
            Observable<Integer> myObservable = Observable.range(1, 100) // Was not called yet
                .doOnNext(x -> System.out.print(x + "->"));


        myObservable.subscribe(
                x -> System.out.print("->" +x + " "),
                x-> System.out.print("An error occured, observable has stopped"),
                ()->System.out.print("Observable has been completed")

                ); // NON BLOCKING!!

        System.out.println("=======Simple==========");

        myObservable.subscribe(x -> System.out.print(x + " ___"));

        sleep(3000);
    }
}
