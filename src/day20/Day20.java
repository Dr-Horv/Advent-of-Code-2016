package day20;

import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20 implements Solver {
    @Override
    public String solve() {

        Stream<String> input = FileReader.readFile("day20/input");
        String[] inputArr = input.toArray(String[]::new);

        List<IpFilter> rules = new ArrayList<>();
        List<IpFilter> allowed = new ArrayList<>();

        for(String s : inputArr) {
            rules.add(parseFilter(s));
        }

        rules.sort(Comparator.comparingLong(IpFilter::getStart));

        Long timestamp = System.currentTimeMillis();


        Long count = 0L;
        Long ip = 0L;
        Long firstValid = -1L;

        while(ip <= 4294967295L) {

            boolean test = true;

            for(IpFilter filter : rules) {
                if(filter.blocked(ip)) {
                    test = false;
                    ip = filter.getEnd() + 1;
                    break;
                }
            }

            if(test) {
                if(firstValid < 0){
                    firstValid = ip;
                }
                count++;
                ip++;
            }

            Long now = System.currentTimeMillis();
            if((now - timestamp) > 10_000 ) {
                timestamp = now;
                final Long currentIp = ip;
                rules = rules.stream().filter(f -> (f.getEnd() > currentIp)).collect(Collectors.toList());
                System.out.println("Update " + (((double) ip)/4294967295L) + " " + rules.size());
            }


        }


        return firstValid+" "+count;

    }

    private IpFilter parseFilter(String s) {
        String[] parts = s.split("-");
        return new IpFilter(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }
}
