import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {

    }

    public static int simulate(int numStudents, int numFriends) {
        ArrayList<Integer> balloons = new ArrayList<>();
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            balloons.set(i,i);
            people.set(i, new Person(i));
        }

        int numAdvances = 0;
        while (people.size() > 0) {
            advance(people, balloons, numFriends);
            numAdvances++;
        }
        return numAdvances;
    }

    public static void advance(ArrayList<Person> people, ArrayList<Integer> balloons, int distance) {
        shuffle(people);
        shuffle(balloons);

        for (int i = 0; i < people.size(); i++) {
            ArrayList<Person> temp = new ArrayList<>(people);
            temp.removeIf(p -> p.number != balloons.size());
            Person match = temp.get(0);
            if (Math.abs(people.indexOf(match) - i) <= distance) {
                match.done = true;
                balloons.set(i, null);
            }
        }
        people.removeIf(p -> p.done);
        balloons.removeIf(p -> p == null);
    }

    public static void shuffle(ArrayList list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Object oldI = list.set(i,list.get(j));
            list.set(j, oldI);
        }
    }
}
