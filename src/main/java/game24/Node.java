package game24;

public class Node {

    public Rational result;
    public Node operand1;
    public Node operand2;
    public Operator operator;

    public Node(Rational result, Node operand1, Node operand2, Operator operator) {
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public Node(Rational result) {
        this.result = result;
    }

    public Node(int result) {
        this.result = new Rational(result, 1);
    }
}
