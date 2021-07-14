package System.Entities;

import java.util.Iterator;
import java.util.List;

public class DirectedGraph <T extends LabeledNodeWithAdjacency>{

    List<T> nodes;
    List<DirectedEdge<T>> edges;

    public DirectedGraph(List<T> nodes, List<DirectedEdge<T>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Iterator<T> getNodes(){
        return nodes.iterator();
    }

    public Iterator<DirectedEdge<T>> getEdges(){
        return edges.iterator();
    }

    /**
     *
     * @param e the Edge to add
     * @return there wasn't a copy already in List and the Edge was added Sucessfully
     */
    public boolean addEdge(DirectedEdge<T> e){
        for (DirectedEdge edge : edges) {
            if (e.getTo() == edge.getTo() && e.getFrom() == edge.getFrom() ){
                return false;
            }
        }
        edges.add(e);
        if (!contains(e.getFrom().getOutgoingEdges() , e)) {
            e.getFrom().addOutgoingEdge(e);
        }
        if (!contains((e.getTo().getIncomingEdges()) ,e)) {
            e.getTo().addIncomingEdge(e);
        }
        return true;
    }

    public void addNode(T n){
        nodes.add(n);
    }


    private boolean contains(Iterator<DirectedEdge> edgeIterator, DirectedEdge<T> e) {
        for (Iterator<DirectedEdge> it = edgeIterator; it.hasNext(); ) {
            DirectedEdge edge = it.next();
            if (edge.getFrom().equals(e.getFrom()) && edge.getTo().equals(e.getTo())){
                return true;
            }
        }
    return false;
    }
}
