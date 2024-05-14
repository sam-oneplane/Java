public class AdvUppercaseFunction implements AdvFixStringTransformer.StringFunction {
    @Override
    public String transform(String str) {
        return str.toUpperCase();
    }
}
