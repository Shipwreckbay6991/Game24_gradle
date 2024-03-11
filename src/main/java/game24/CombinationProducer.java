package game24;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombinationProducer {

    /**
     * produce sorted sequences as unique combinations (same numbers can occur in the same combination, order doesn't matter)
     * @param smallestNumber smallest number in combination
     * @param biggestNumber biggest number in combination
     * @return a list of all possible number combinations
     */
    public static List<Combination> produceCombinations(int smallestNumber, int biggestNumber){
        List<Combination> combinations = new ArrayList<>();
        for(int n1 = smallestNumber; n1 <= biggestNumber; n1++){
            for(int n2 = n1; n2 <= biggestNumber; n2++){
                for(int n3 = n2; n3 <= biggestNumber; n3++){
                    for(int n4 = n3; n4 <= biggestNumber; n4++){
                        Node[] nodes = new Node[]{new Node(n1), new Node(n2), new Node(n3), new Node(n4)};
                        Combination comb = new Combination(nodes);
                        combinations.add(comb);
                    }
                }
            }
        }
        return combinations;
    }

    /**
     * @param combinations all combinations
     * @return only valid combinations that has a solution to 24
     */
    public static List<Combination> filter(List<Combination> combinations){
        return combinations.stream().filter(c -> c.valid).toList();
    }


    /**
     * validate a list of combinations, update all fields except for the field nodes of each combination for further filter and save
     * @param combinations a list of combinations
     */
    public static void validate(List<Combination> combinations){
        for(Combination comb: combinations){
            List<Node[]> mergedCombinations = mergeNodes(comb.nodes, 4);
            validateHelper(comb, mergedCombinations, 3);
        }
    }

    /**
     * validate a list of combinations of given for potential of reaching 24
     * @param originalComb original combination, from which the given combinations originate, passed down for potential field updating
     * @param combinations already partially / totally merged combinations, for further computing
     * @param length length of each combination
     */
    private static void validateHelper(Combination originalComb, List<Node[]> combinations, int length){
        if(originalComb.valid)
            return;

        if(length == 1){
            for(Node[] combination: combinations){
                Node finalNode = combination[0];
                if(finalNode.result.equalsInt(24)){
                    originalComb.valid = true;
                    originalComb.solution = finalNode;
                    originalComb.operators = new Stack<>();
                    originalComb.solutionExpr = parseSolution(finalNode, originalComb.operators);
                    break; //save as few solutions as possible
                }
            }
        }

        for(Node[] combination: combinations){
            if(originalComb.valid)
                return;
            List<Node[]> mergedCombinations = mergeNodes(combination, length);
            validateHelper(originalComb, mergedCombinations, length-1);
        }
    }


    /**
     * merge each pair of numbers in a given combination in all possible way to make a new number node, which will form new combination with other unmerged nodes
     * @param currentComb current combination that we work on
     * @param length length of current combination
     * @return a list of all possible merged combinations, length of each combination equals given length - 1
     */
    private static List<Node[]> mergeNodes(Node[] currentComb, int length){
        List<Node[]> mergedCombinations = new ArrayList<>();
        for(int i = 0; i < length - 1; i++){
            for(int j = i+1; j < length; j++){
                for(int k = 0; k < 4; k++){
                    if(k != 3 || !currentComb[j].result.equalsInt(0)){ //avoid divide-by-zero-problem
                        Node[] mergeNode1 = new Node[length-1];
                        int i1 = 0;
                        for(int t = 0; t < length; t++){
                            if(t != i && t != j){
                                mergeNode1[i1] = currentComb[t];
                                i1++;
                            }
                        }
                        mergeNode1[i1] = computeResult(currentComb[i], currentComb[j],k);
                        mergedCombinations.add(mergeNode1);
                    }
                    if(k != 3 || !currentComb[i].result.equalsInt(0)){ //avoid divide-by-zero-problem
                        if(k == 1 || k == 3){ //when the order of operands matters, reverse order, perform operation again and save
                            Node[] mergeNode2 = new Node[length-1];
                            int i2 = 0;
                            for(int t = 0; t < length; t++){
                                if(t != i && t != j){
                                    mergeNode2[i2] = currentComb[t];
                                    i2++;
                                }
                            }
                            mergeNode2[i2] = computeResult(currentComb[j], currentComb[i],k);
                            mergedCombinations.add(mergeNode2);
                        }
                    }
                }
            }
        }
        return mergedCombinations;
    }

    /**
     * compute arithmetic result for nodes
     * @param operand1 operand 1
     * @param operand2 operand 2
     * @param operatorID ID of operator
     * @return a node that contains computed result, operands and operator
     */
    private static Node computeResult(Node operand1, Node operand2, int operatorID){
        return switch (operatorID) {
            case 0 -> new Node(operand1.result.add(operand2.result), operand1, operand2, Operator.ADD);
            case 1 -> new Node(operand1.result.sub(operand2.result), operand1, operand2, Operator.SUB);
            case 2 -> new Node(operand1.result.mul(operand2.result), operand1, operand2, Operator.MUL);
            case 3 -> new Node(operand1.result.div(operand2.result), operand1, operand2, Operator.DIV);
            default -> throw new RuntimeException("Not gonna happen!");
        };
    }

    /**
     * parse the computation path in a node as string
     * @param solution A node that contains computation path
     * @param operators operators from precedent nodes, corresponding to operations happened later than expression of current node
     * @return A string shows the complete computation path
     */
    public static String parseSolution(Node solution, Stack<Operator> operators){
        if(solution.operator == null)
            return String.valueOf(solution.result.toInt());
        String operator = switch (solution.operator) {
            case ADD -> "+";
            case SUB -> "-";
            case MUL -> "*";
            case DIV -> "/";
        };
        operators.push(solution.operator);
        String expr1 = parseSolution(solution.operand1, operators);
        String expr2 = parseSolution(solution.operand2, operators);
        if(!expr1.matches("-?\\d+"))
            expr1 = "("+expr1+")";
        if(!expr2.matches("-?\\d+"))
            expr2 = "("+expr2+")";
        return expr1 +" "+ operator + " " + expr2;
    }


}
