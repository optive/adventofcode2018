package day_05;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PolymerReactor {

    private LinkedList<Character> polymer = new LinkedList<>();

    public PolymerReactor(final String inputPolymer) {
        for (final char c : inputPolymer.toCharArray()) {
            polymer.add(c);
        }
    }

    public int reactPolymer() {
        return reactPolymer(polymer);
    }

    public int reactPolymer(final LinkedList<Character> polymer) {
        int i = 1;
        while (i < polymer.size()) {

            Character c1 = polymer.get(i - 1);
            Character c2 = polymer.get(i);

            if ((c1.equals(Character.toUpperCase(c2)) || c2.equals(Character.toUpperCase(c1))) &&
                    (c1.equals(Character.toLowerCase(c2)) || c2.equals(Character.toLowerCase(c1)))) {
                polymer.remove(i);
                polymer.remove(i - 1);

                if(i > 1) {
                    i--;
                }
            } else {
                i++;
            }
        }

        return polymer.size();
    }

    public int findBestReaction() {
        final Map<Character, Integer> polymerMap = new HashMap<>();
        final char[] types = ("abcdefghijklmnopqrstuvwxyz".toCharArray());
        for (final char type: types) {
            // Create a copy of the polymer and remove the type.
            final LinkedList<Character> polymerCopy = (LinkedList) polymer.clone();
            polymerCopy.removeAll(Arrays.asList(new Character(type), new Character(Character.toUpperCase(type))));

            final int reactedLength = reactPolymer(polymerCopy);
            polymerMap.put(type, reactedLength);
        }
        return Collections.min(polymerMap.values());
    }

    public String getPolymerString() {
        final StringBuilder sb = new StringBuilder();
        polymer.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        final String input = FileUtils.readFileToString(new File("src/main/resources/05_input.txt"), "UTF-8");
        final PolymerReactor polymerReactor = new PolymerReactor(input.trim());
        ;
        System.out.println(polymerReactor.reactPolymer());
        System.out.println("Part 2 length: " + polymerReactor.findBestReaction());
    }
}
