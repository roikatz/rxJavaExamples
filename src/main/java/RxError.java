import rx.Observable;
import rx.observables.BlockingObservable;

public class RxError {
    public static void main(String[] args){

        // OnError - do something, in that example it prints the println
        System.out.println("\n=======OnError========");
        BlockingObservable<Integer> myObservableError = Observable.range(1,10)
                .map(number -> {
                        if (number == 3) throw new RuntimeException("Error");
                        else
                            return number + 1;
                })
                .filter(number-> number % 2 == 0)
                .doOnError( x-> System.out.println("[error from doOnError]"))
               // .onErrorReturn(x-> -1)
//                .onErrorResumeNext(x-> Observable.empty())
//                .onExceptionResumeNext(myObservableError)

                .toBlocking();

        myObservableError.subscribe(x -> System.out.print(x + " "),
                (error)->System.out.println("Error! " + error.getCause()));

        // on errorResumeNext - replace observable with another error observable and shut down the observable.
        System.out.println("\n=======OnErrorResumeNext========");
        BlockingObservable<Integer> myObservableErrorResume = Observable.range(1,10)
                .map(number -> {
                    if (number == 3) throw new RuntimeException();
                    return  number + 1;})
                .filter(number->  number % 2 == 0)
                .retry(2) // allow it to fail up to x times
                .onErrorResumeNext(x->Observable.error(x))
                .toBlocking();

        myObservableErrorResume.subscribe(
                x -> System.out.print(x + " "),
                x->System.out.print(x),
                ()->System.out.print("Observable completed, do something")
        );
    }

}
