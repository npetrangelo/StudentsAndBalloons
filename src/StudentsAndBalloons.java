import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentsAndBalloons {
	private int numStudents;
	private int numFriends;
	private int numRuns;

    public static void main(String[] args) {
        int numStudents = 1000;
        int numFriends = 0;
        int numRuns = 1;

        StudentsAndBalloons sab = new StudentsAndBalloons(numStudents, numFriends, numRuns);
        System.out.println(sab.simulate());
    }

    StudentsAndBalloons(int numStudents, int numFriends, int numRuns) {
        this.numStudents = numStudents;
        this.numFriends = numFriends;
        this.numRuns = numRuns;
    }

    public int simulate() {
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

    public void advance(List<Person> people, List<Integer> balloons, int distance) {
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

    public <E> void shuffle(List<E> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            E oldI = list.set(i,list.get(j));
            list.set(j, oldI);
        }
    }
}
