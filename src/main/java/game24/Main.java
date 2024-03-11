package game24;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Combination> combinations = CombinationProducer.produceCombinations(1, 13);
        CombinationProducer.validate(combinations);
        List<Combination> validCombinations = CombinationProducer.filter(combinations);
    }
}