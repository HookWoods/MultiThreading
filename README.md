# MultiThreading

MultiThreading is a java class to use the ExecutorService. It permits you to launch runnable async, to create repeating task or to schedule a task with a delay.

## Usage

Repeating scheduler: 

```java
MultiThreading.schedule(runnable, initialDelay, interval, TimeUnit.YOURTIMES);
```

Delayed scheduler: 

```java
MultiThreading.schedule(runnable, delay, TimeUnit.YOURTIMES);
```

Async scheduler: 

```java
MultiThreading.runAsync(runnable);
```

## Example
```java

MultiThreading.schedule(() -> {
                    System.out.println("This scheduler will start after 5 seconds");
                    System.out.println("And repeat this runnable every 10 seconds.");
}, 5, 10, TimeUnit.SECONDS);


MultiThreading.schedule(() -> {
            System.out.println("This scheduler will run after 5 seconds");
}, 5, TimeUnit.SECONDS);


// Useful to save file or to save data in MySQL or MongoDB
MultiThreading.runAsync(() -> { 
  if (configuration != null) persist.save(configuration);
});

                
```
