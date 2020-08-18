import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentsAndBalloons {
    public static void main(String[] args) {
        System.out.println(simulate(1000,0));
    }

    public static int simulate(int numStudents, int numFriends) {
        List<Integer> balloons = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            balloons.add(i);
            people.add(new Person(i));
        }

        int numAdvances = 0;
        while (people.size() > 0) {
            advance(people, balloons, numFriends);
            numAdvances++;
        }
        return numAdvances;
    }

    public static void advance(List<Person> people, List<Integer> balloons, int distance) {
        shuffle(people);
        shuffle(balloons);

        for (int i = 0; i < people.size(); i++) {
            int balloon = balloons.get(i);
            Person match = people.stream().filter(p -> p.number == balloon).findAny().get();
            if (Math.abs(people.indexOf(match) - i) <= distance) {
                match.done = true;
                balloons.set(i, null);
            }
        }
        people.removeIf(p -> p.done);
        balloons.removeIf(Objects::isNull);
    }

    public static <E> void shuffle(List<E> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            E oldI = list.set(i,list.get(j));
            list.set(j, oldI);
        }
    }
}
