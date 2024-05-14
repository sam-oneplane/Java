public class AdvReverseFunction implements AdvFixStringTransformer.StringFunction {
    @Override
    public String transform(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}