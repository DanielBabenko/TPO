package task3;

public enum Size {
    MICROSCOPIC(1),
    SMALL(2),
    NORMAL(3),
    BIG(4),
    LARGE(5);

    private final int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
