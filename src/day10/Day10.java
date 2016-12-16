package day10;

import utils.FileReader;
import utils.Solver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day10 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day10/input");
        String[] inputArr = input.toArray(String[]::new);

        Map<String, Bot> bots = createBots(inputArr);
        Map<String, Output> outputs = createOutputs(inputArr);

        System.out.println("Connecting...");
        connectBotsAndOutputs(bots, outputs, inputArr);

        System.out.println("Applying");
        applyInputs(bots, inputArr);

        Bot b = findBot(new Chip(61), new Chip(17), bots.values());

        String part1 = b != null ? b.getName() : "None";
        String part2 = "" + (outputs.get("output 0").getBin().get(0).value * outputs.get("output 1").getBin().get(0).value * outputs.get("output 2").getBin().get(0).value);

        return part1 + " " + part2;

    }

    private Bot findBot(Chip c1, Chip c2, Collection<Bot> bots) {
        for(Bot b : bots) {
            Chip b1 = b.getFirstChip();
            Chip b2 = b.getSecondChip();
            if( (b1.equals(c1) && b2.equals(c2)) || (b1.equals(c2) && b2.equals(c1)) ) {
                return b;
            }
        }
        return null;
    }

    private void applyInputs(Map<String, Bot> bots, String[] inputArr) {
        for(String line : inputArr) {
            String[] parts = line.split(" ");
            if(parts[0].equals("value")) {
                Chip c = new Chip(Integer.parseInt(parts[1]));
                String botName = parts[4] + " " + parts[5];
                bots.get(botName).receive(c);
            }
        }
    }

    private void connectBotsAndOutputs(Map<String, Bot> bots, Map<String, Output> outputs, String[] inputArr) {
        String regex = "(bot \\d+) gives (\\w+) to (\\w+ \\d+) and (\\w+) to (\\w+ \\d+)";
        Pattern pattern = Pattern.compile(regex);

        for(String line : inputArr) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String giver = matcher.group(1);

                Bot b = bots.get(giver);

                for(int i = 2; i < 5; i+=2) {
                    String type = matcher.group(i);
                    String receiver = matcher.group(i+1);
                    Receiver r = bots.get(receiver);
                    if(r == null) {
                        r = outputs.get(receiver);
                    }

                    if(type.equals("high")) {
                        b.setHighReceiver(r);
                    } else {
                        b.setLowReceiver(r);
                    }

                }
            }
        }
    }

    private Map<String, Output> createOutputs(String[] inputArr) {
        String regex = "(output \\d+)";

        Map<String, Output> outputMap = new HashMap<>();

        Pattern pattern = Pattern.compile(regex);
        for (String line : inputArr) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String group = matcher.group(i);
                    Output output = outputMap.getOrDefault(group, new Output(group));
                    outputMap.put(group, output);
                }
            }
        }
        return outputMap;
    }

    @SuppressWarnings("Duplicates")
    private Map<String, Bot> createBots(String[] inputArr) {
        String regex = "(bot \\d+)";

        Map<String, Bot> botMap = new HashMap<>();

        Pattern pattern = Pattern.compile(regex);
        for (String line : inputArr) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String group = matcher.group(i);
                    Bot b = botMap.getOrDefault(group, new Bot(group));
                    botMap.put(group, b);
                }
            }
        }
        return botMap;
    }
}
