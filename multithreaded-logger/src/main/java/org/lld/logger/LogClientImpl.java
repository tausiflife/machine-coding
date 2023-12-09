package org.lld.logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogClientImpl implements LogClient {
    //MIght happen that the has method puts the same process
    //into the same queue, hence those needs to be addressed
    private final ConcurrentSkipListMap<Long, List<Process>> queue;
    private final Map<String, Process> processes;
    //Futures will be updated by end method everytime a poll method is called and the
    // the process isn't completed, the it will create a future for the process
    private final BlockingQueue<CompletableFuture<String>> futures;
    private final Lock lock;
    // To make sure that the start and end of the task are executed in the same thread
    // and in sequential order
    private final ExecutorService[] taskScheduler;
    public LogClientImpl(int threads) {
        //we had to change from queue to cocurrentskiplistmap to be able to sort and have both key and value
        // along with that priority blocking needs number of threads as input
        this.queue =
                new ConcurrentSkipListMap<>();
        this.processes = new ConcurrentHashMap<>();
        this.futures = new LinkedBlockingQueue<>();
        this.lock = new ReentrantLock();
        this.taskScheduler = new ExecutorService[threads];
        for (int i=0; i<taskScheduler.length; i++) {
            taskScheduler[i] = Executors.newSingleThreadExecutor();
        }
    }

    @Override
    public void start(String processId, long timestamp) {
        taskScheduler[processId.hashCode() % taskScheduler.length].execute(() -> {
            Process process = new Process(processId, timestamp);
            this.processes.put(processId, process);
            queue.putIfAbsent(timestamp, new CopyOnWriteArrayList<>());
            queue.get(timestamp).add(process);
        });
    }

    @Override
    public void end(String processId) {
        taskScheduler[processId.hashCode() % taskScheduler.length].execute(() -> {
            final long now = System.currentTimeMillis();
            this.processes.get(processId).setEndTime(now);
            lock.lock();
            try {

                //if this end has been called for the 1st process in the queue
                //we complete it
                String result;
                if (!futures.isEmpty() && (result = pollNow()) != null) {
                    futures.take().complete(result);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
    }

    @Override
    public String poll() {
        final var result = new CompletableFuture<String>();
        lock.lock();
        try {
            try {
                String logStatement;
                if(!futures.isEmpty()) {
                    futures.offer(result);
                } else if ((logStatement = pollNow()) != null) {
                    return logStatement;
                } else {
                    futures.offer(result);
                }
            } finally {
                lock.unlock();
            }
            //wait for end to be called for the first process
            return result.get(11, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private String pollNow() {
        if(!queue.isEmpty()) {
            for (final Process earliest : queue.firstEntry().getValue()) {
                if(earliest.getEndTime() != -1) {
                    queue.firstEntry().getValue().remove(earliest);
                    if(queue.firstEntry().getValue().isEmpty())
                        queue.pollFirstEntry();
                    processes.remove(earliest.getId());
                    final var logStatement = earliest.getId() + " started at " + earliest.getStartTime() + " and ended at "
                            + earliest.getEndTime();
                    System.out.println(logStatement);
                    return logStatement;
                }
            }
        }
        return null;
    }
}
