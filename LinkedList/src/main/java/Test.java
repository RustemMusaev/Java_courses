import java.util.HashSet;
import java.util.Set;


public class Test {

    public Set<Integer> returnNumber(int n){
        if (n < 1) {
           throw new IllegalArgumentException();
        }
        Set<Integer> result = new HashSet<>();
        Integer next;
        while (result.size() != n) {
            next = (int) (Math.random()*Integer.MAX_VALUE);
            result.add(next);
        }
        System.out.println(result.size());
        for (Integer i : result) {
            System.out.println(i.intValue());
        }
        return result;
    }
}
