import java.util.*;
import java.util.concurrent.*;

public class ThreadByStringTransformer {
    final private List<String> data;

    public ThreadByStringTransformer(List<String> startingData) {
        this.data = new ArrayList<>(startingData);
    }

    public List<String> transform(List<StringFunction> functions) throws InterruptedException {
        int dataSize = data.size();
        List<Future<String>> results = new ArrayList<>();
        List<String> transformedData = new ArrayList<>();
        try(ExecutorService executor = Executors.newFixedThreadPool(dataSize)) {

            for (int i = 0; i < dataSize; i++) {
                final String str = data.get(i);
                final List<StringFunction> funcs = new ArrayList<>(functions);
                if (str != null) {
                    Callable<String> task = new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            String transformedStr = str;
                            for (StringFunction function : funcs) {
                                transformedStr = function.transform(transformedStr);
                            }
                            return transformedStr;
                        }
                    };

                    results.add(executor.submit(task));
                }
            }

            for (Future<String> result : results) {
                try {
                    transformedData.add(result.get());
                } catch (ExecutionException e) {
                    // Handle exception
                }
            }

            executor.shutdown();
        }
        return transformedData;
    }

    public static interface StringFunction {
        public String transform(String str);
    }
}
/*
In this version:

    Each string in the input data list is processed by a separate Callable task,
    The results of the transformations are collected and returned as a list of transformed strings after all tasks have completed.


solve the following problems :
    1. thread safe on each string without  CopyOnWriteArrayList<>(startingData)
    2. run better with thread pool if string array is much larger then number of function
    3. if function order is important
    4. null string will be dealt outside the thread task
*/
