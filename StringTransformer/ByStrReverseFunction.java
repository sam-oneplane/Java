public class ByStrReverseFunction implements ThreadByStringTransformer.StringFunction {
    @Override
    public String transform(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}