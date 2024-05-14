public class ByStrUppercaseFunction implements ThreadByStringTransformer.StringFunction {
    @Override
    public String transform(String str) {
        return str.toUpperCase();
    }
}