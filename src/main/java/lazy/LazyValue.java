package lazy;

public class LazyValue<T> {
    private T value;

    private boolean computed = false;

    private Compution<T> compution;

    public LazyValue(Compution<T> compution) {
        this.compution = compution;
    }

    public T getValue() {
        if (!computed) {
            computed = true;
            value = compution.compute();
            compution = null;
        }

        return value;
    }
}
