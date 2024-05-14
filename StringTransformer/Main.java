import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it

        List<String> startingData = Arrays.asList("hello","there", "world");

        AdvFixStringTransformer transformer = new AdvFixStringTransformer(startingData);
        ThreadByStringTransformer transformer1 = new ThreadByStringTransformer(startingData);

        List<AdvFixStringTransformer.StringFunction> functions = new ArrayList<>();
        functions.add(new AdvUppercaseFunction());
        functions.add(new AdvReverseFunction());

        List<ThreadByStringTransformer.StringFunction> functions1 = new ArrayList<>();
        functions1.add(new ByStrUppercaseFunction());
        functions1.add(new ByStrReverseFunction());


        try {
            List<String> transformedData = transformer.transform(functions);
            System.out.println("Thread By Function");
            System.out.println(transformedData); // Output: [OLLEH, DLROW]

            System.out.println("Thread By String");
            List<String> transformedData1 = transformer1.transform(functions1);
            System.out.println(transformedData1); // Output: [OLLEH, DLROW]

        } catch (InterruptedException e) {
            /* handle catch */
        }
    }
}