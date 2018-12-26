package day_16;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class OpCodeParser {

    private final Map<OpCodeSample, Integer> sampleMap = new HashMap<>();
    private final Map<Integer, Set<String>> opCodeMap = new HashMap<>();

    public OpCodeParser() {
        for (int i=0; i<16; i++) {
            opCodeMap.put(i, new HashSet<>());
        }
    }

    public int partOne(final List<String> input) throws Exception {
        OpCodeSample sample = new OpCodeSample();

        for (int i = 0; i < input.size(); i++) {
            if (i % 4 == 0) {
                if (input.get(i).length() == 0) {
                    break; // We've reached the end of the samples
                }
                sample.setBefore(parseArray(input.get(i)));
            } else if (i % 4 == 1) {
                final String[] instruction = input.get(i).split(" ");
                sample.setOpCode(Integer.parseInt(instruction[0]));
                sample.setA(Integer.parseInt(instruction[1]));
                sample.setB(Integer.parseInt(instruction[2]));
                sample.setC(Integer.parseInt(instruction[3]));
            } else if (i % 4 == 2) {
                sample.setAfter(parseArray(input.get(i)));
                final int possibleOpCodes = getPossibleOpCodes(sample);
                sample = new OpCodeSample();
                sampleMap.put(sample, possibleOpCodes);
            }
        }

        final List<Integer> partOne = sampleMap.values()
                .stream()
                .filter(i -> i >= 3)
                .collect(Collectors.toList());
        return partOne.size();
    }

    public int partTwo(final List<String> input) throws Exception {
        final Map<Integer, String> opCodes = determineOpCodes();
        final int[] registers =  new int[4];

        for (int i=3130; i< input.size(); i++) {
            final String[] instruction = input.get(i).split(" ");
            final OpCode opCode = new OpCode(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3]));
            final String opCodeName = opCodes.get(Integer.parseInt(instruction[0]));
            OpCode.class.getMethod(opCodeName, int[].class).invoke(opCode, registers);
        }
        return registers[0];
    }


    private Map<Integer, String> determineOpCodes() {
        final Map<Integer, String> opCodes = new HashMap<>();
        while (opCodes.size() < 16) {
            for (final Map.Entry<Integer, Set<String>> entry : opCodeMap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    final String opCodeName = entry.getValue().iterator().next();
                    opCodes.put(entry.getKey(), opCodeName);

                }
            }

            for (Set<String> opCodeNames: opCodeMap.values()) {
                if (opCodeNames.size() > 1) {
                    opCodeNames.removeAll(opCodes.values());
                }
            }
        }
        return opCodes;
    }

    private int[] parseArray(final String s) {
        final String array = s.substring(s.indexOf('[') + 1, s.indexOf(']'));
        final String[] numbers = array.split(",");
        return Arrays.stream(numbers)
                .map(i -> i.trim())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int getPossibleOpCodes(final OpCodeSample opCodeSample)
            throws IllegalAccessException, InvocationTargetException {
        final Method[] opCodes = OpCode.class.getDeclaredMethods();
        final OpCode opCode = new OpCode(opCodeSample.getA(), opCodeSample.getB(), opCodeSample.getC());

        int possibleOpCodes = 0;
        List<String> opCodeNames = new ArrayList<>();

        for (final Method m : opCodes) {
            if (Arrays.equals(opCodeSample.getAfter(), (int[]) m.invoke(
                    opCode, Arrays.copyOf(opCodeSample.getBefore(), opCodeSample.getBefore().length)))) {
                possibleOpCodes++;
                opCodeMap.get(opCodeSample.getOpCode()).add(m.getName());
            }
        }

        return possibleOpCodes;
    }

    public static void main(String[] args) throws Exception {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/16_input.txt"), "UTF-8");
        final OpCodeParser opCodeParser = new OpCodeParser();
        System.out.println(opCodeParser.partOne(lines));
        System.out.println(opCodeParser.partTwo(lines));
    }
}
