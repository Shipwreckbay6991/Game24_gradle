package game24;

import java.util.Stack;

public class Combination {

    public Node[] nodes;
    public boolean valid;
    public Node solution;

    public Stack<Operator> operators;

    public String solutionExpr;

    public Combination(Node[] nodes) {
        this.nodes = nodes;
    }
}
