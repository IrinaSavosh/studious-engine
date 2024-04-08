public class Counter implements AutoCloseable {
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public Counter() {
        this.count = 0;//////// !!!!!!!!!!
    }

    public void add() throws IllegalStateException {
        if (count < 0) {
            throw new IllegalStateException("Работа проведена некорректно. Попробуйте снова запустить программу");
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() {
        count = 0;
    }

}
