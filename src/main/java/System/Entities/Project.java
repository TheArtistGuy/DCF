package System.Entities;

import java.util.ArrayList;


public class Project extends DirectedGraph<LabeledNodeWithAdjacency>{

    public Project() {
        super(new ArrayList<>(), new ArrayList<>());
    }
}
