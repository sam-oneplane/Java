import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it

        List<String> startingData = Arrays.asList("hello","there", "world");

        ThreadByStringTransformer transformer = new ThreadByStringTransformer(startingData);

        List<ThreadByStringTransformer.StringFunction> functions1 = new ArrayList<>();
        functions1.add(new ByStrUppercaseFunction());
        functions1.add(new ByStrReverseFunction());


        try {
         
            System.out.println("Thread By String");
            List<String> transformedData = transformer.transform(functions1);
            System.out.println(transformedData); // Output: [OLLEH, DLROW]

        } catch (InterruptedException e) {
            /* handle catch */
        }
    }
}
