package game24;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

class CombinationProducerTest {

    @Test
    void produceCombinations() {
        List<Combination> combinations = CombinationProducer.produceCombinations(1,3);
        for(Combination comb: combinations){
            for(Node node: comb.nodes)
                System.out.print(node.result.getNumerator() + "/" + node.result.getDenominator() + " ");
            System.out.print("\n");
        }
    }

    @Test
    void validate() {
        List<Combination> combinations = CombinationProducer.produceCombinations(1,3);
        CombinationProducer.validate(combinations);
        for(Combination comb: combinations){
            for(Node node: comb.nodes)
                System.out.print(node.result.getNumerator() + "/" + node.result.getDenominator() + " ");
            System.out.print(comb.valid + " " + comb.solutionExpr +"\n");
        }
    }

    @Test
    void parseSolution() {
        Node node1 = new Node(3);
        Node node2 = new Node(4);
        Node node3 = new Node(2);
        Node node4 = new Node(5);

        Node mid1 = new Node(new Rational(7,1), node1, node2, Operator.ADD);
        Node mid2 = new Node(new Rational(-3,1), node3, node4, Operator.SUB);
        Node final1 = new Node(new Rational(-21,  1), mid1, mid2, Operator.MUL);
        Node final2 = new Node(new Rational(7,  -3), mid1, mid2, Operator.DIV);

        Stack<Operator> operatorStack1 = new Stack<>();
        Stack<Operator> operatorStack2 = new Stack<>();

        String expr1 = CombinationProducer.parseSolution(final1, operatorStack1);
        System.out.print(expr1);
        for(int i = 0; i < 3; i++){
            System.out.print(operatorStack1.pop()+" ");
        }
        System.out.print("\n");

        String expr2 = CombinationProducer.parseSolution(final2, operatorStack2);
        System.out.print(expr2);
        for(int i = 0; i < 3; i++){
            System.out.print(operatorStack2.pop()+" ");
        }
        System.out.print("\n");
    }

}