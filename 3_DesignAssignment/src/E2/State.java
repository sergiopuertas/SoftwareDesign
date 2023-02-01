package E2;
public enum State implements Comparable<State>{
    RED(2),
    ORANGE(1),
    GOOD(0);
    private final int num;
    public int getNum(){
        return num;
    }
    State(int i) {
        this.num = i;
    }
}
