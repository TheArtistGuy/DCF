package System.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LabeledNodeWithAdjacency extends LabeledNode {
     private List<DirectedEdge> incomingEdges;
     private List<DirectedEdge> outgoingEdges;

     public LabeledNodeWithAdjacency(String name) {
          super(name);
          this.outgoingEdges = new ArrayList<>();
          this.incomingEdges = new ArrayList<>();
     }

     public Iterator<DirectedEdge> getIncomingEdges(){
          return incomingEdges.iterator();
     }

     public Iterator<DirectedEdge> getOutgoingEdges(){
          return outgoingEdges.iterator();
     }

     public void addIncomingEdge(DirectedEdge e){
          incomingEdges.add(e);
     }

     public void addOutgoingEdge(DirectedEdge e){
          outgoingEdges.add(e);
     }
}
