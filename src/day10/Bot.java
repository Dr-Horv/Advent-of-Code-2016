package day10;

public class Bot implements Receiver {

    private final String name;
    private Receiver lowReceiver;
    private Receiver highReceiver;

    private Chip firstChip = null;

    private Chip secondChip = null;

    public Bot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Chip getFirstChip() {
        return firstChip;
    }

    public Chip getSecondChip() {
        return secondChip;
    }

    public Receiver getLowReceiver() {
        return lowReceiver;
    }

    public void setLowReceiver(Receiver lowReceiver) {
        //System.out.println(name + " low " + lowReceiver);
        this.lowReceiver = lowReceiver;
    }

    public Receiver getHighReceiver() {
        return highReceiver;
    }

    public void setHighReceiver(Receiver highReceiver) {
        //System.out.println(name + " high " + highReceiver);
        this.highReceiver = highReceiver;
    }

    @Override
    public void receive(Chip c) {
        if(firstChip == null) {
            firstChip = c;
        } else {
            secondChip = c;
            Chip low;
            Chip high;
            if(c.value < firstChip.value) {
                low = c;
                high = firstChip;
            } else {
                low = firstChip;
                high = c;
            }

            highReceiver.receive(high);
            lowReceiver.receive(low);
        }
    }

    @Override
    public String toString() {
        return "Bot{" +
                "name='" + name + '\'' +
                ", lowReceiver=" + lowReceiver +
                ", highReceiver=" + highReceiver +
                ", firstChip=" + firstChip +
                ", secondChip=" + secondChip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bot bot = (Bot) o;

        return name != null ? name.equals(bot.name) : bot.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
