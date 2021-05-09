import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class vmBravoSetOfNumbers {

    public Set<Integer> getGeneratedNumbersBravo() {
        Set<Integer> GeneratedNumbers = new LinkedHashSet<>();
        Random rand = new Random();

        while(GeneratedNumbers.size() <6){
            GeneratedNumbers.add(rand.nextInt(50) +1);
        }
        return GeneratedNumbers;

    }
}
