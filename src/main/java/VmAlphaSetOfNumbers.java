import java.util.*;

public class VmAlphaSetOfNumbers {

    public Set<Integer> getGeneratedNumbers() {
        Set<Integer> GeneratedNumbers = new LinkedHashSet<>();
        Random rand = new Random();

        while(GeneratedNumbers.size() <6){
            GeneratedNumbers.add(rand.nextInt(50) +1);
        }
        return GeneratedNumbers;

    }

}
