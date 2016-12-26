package day20;

public class IpFilter {

    private final Long start;
    private final Long end;


    public IpFilter(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public boolean allowed(Long ip) {
        return !(ip >= start && ip <= end);
    }

    public boolean blocked(Long ip) {
        return !allowed(ip);
    }

    @Override
    public String toString() {
        return "IpFilter{" + start + " - " + end + '}';
    }

    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }
}
