package System.Entities;

public class DirectedEdge <T extends LabeledNode>{
    private T from;
    private T to;

    public DirectedEdge(T from, T to){
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
