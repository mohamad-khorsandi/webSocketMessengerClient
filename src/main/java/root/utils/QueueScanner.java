package root.utils;

import root.Client;

import java.io.Closeable;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class QueueScanner implements Closeable, Callable<Void> {
    public QueueScanner(InputStream inputStream) {
        receive = new Scanner(inputStream);
        Client.executor.submit(this);
    }

    private Scanner receive;
    private LinkedList<Pair<Type, CompletableFuture<String>>> receivers = new LinkedList<>();

    private LinkedList<Pair<String, CompletableFuture<String>>> waiters = new LinkedList<>();

    Object Waiter = new Object();
    public Void call() throws InterruptedException {
        while (true){
            String msg = receive.next();
            Optional<Pair<String, CompletableFuture<String>>> optPair = waiters.stream().filter((pair -> pair.key.equals(msg))).findFirst();

            if (optPair.isPresent()){
                optPair.get().val.complete(msg);
                waiters.remove(optPair.get());
                continue;
            }

            synchronized (Waiter){
                if (receivers.size() == 0)
                    Waiter.wait();
            }

            Pair<Type, CompletableFuture<String>> curReceiver = receivers.removeLast();

            if (curReceiver.key.equals(Type.NEXT_LINE))
                curReceiver.val.complete(msg + receive.nextLine());
            else
                curReceiver.val.complete(msg);
        }
    }

    public String waitForNext(String msg) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        waiters.addFirst(new Pair<>(msg, future));
        return future.get();
    }

    public String next() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        receivers.addFirst(new Pair<>(Type.NEXT, future));
        synchronized (Waiter){
            Waiter.notify();
        }
        return future.get();
    }

    public int nextInt() throws ExecutionException, InterruptedException {
        return Integer.parseInt(next());
    }

    public String nextLine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        receivers.addFirst(new Pair<>(Type.NEXT_LINE, future));
        synchronized (Waiter){
            Waiter.notify();
        }
        return future.get();
    }

    @Override
    public void close() {
        receive.close();
    }


}

enum Type {
    NEXT, NEXT_LINE;
}
