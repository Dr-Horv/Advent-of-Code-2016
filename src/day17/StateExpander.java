package day17;

import utils.Position;
import utils.graph.Node;
import utils.graph.NodeExpander;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class StateExpander implements NodeExpander<Node<State>> {

    private final String passcode;
    private final Position goal;

    public StateExpander(String passcode, Position goal) {
        this.passcode = passcode;
        this.goal = goal;
    }

    @Override
    public List<Node<State>> getNeighbouringNodes(Node<State> n) {
        State state = n.getElement();

        List<Node<State>> nodes = new ArrayList<>();

        if(state.isUp()) {
            Position newPos = state.getPosition().add(new Position(0, -1));
            addUpNeighbourIfNeeded(state, nodes, newPos, "U");
        }

        if(state.isDown()) {
            Position newPos = state.getPosition().add(new Position(0, 1));
            addUpNeighbourIfNeeded(state, nodes, newPos, "D");
        }


        if(state.isLeft()) {
            Position newPos = state.getPosition().add(new Position(-1, 0));
            addUpNeighbourIfNeeded(state, nodes, newPos, "L");
        }


        if(state.isRight()) {
            Position newPos = state.getPosition().add(new Position(1, 0));
            addUpNeighbourIfNeeded(state, nodes, newPos, "R");
        }


        return nodes;
    }

    private void addUpNeighbourIfNeeded(State state, List<Node<State>> nodes, Position newPos, String dir) {
        if(!isValidPos(newPos)){
            return;
        }
        String path = state.getPath() + dir;
        String hash = getHash(passcode + path);

        String valid = "BCDEF";
        boolean up = valid.contains(""+hash.charAt(0));
        boolean down = valid.contains(""+hash.charAt(1));
        boolean left = valid.contains(""+hash.charAt(2));
        boolean right = valid.contains(""+hash.charAt(3));

        nodes.add(new Node<>(new State(newPos, path, up, down, left, right)));

    }

    public static String getHash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            String hash = getHexHash(digest);
            //System.out.println("Hash " + data + " " + hash);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Problem");
            return null;
        }
    }


    private static String getHexHash(byte[] digest) {

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append( String.format("%02X", b) );
        }

        return sb.toString();
    }


    private boolean isValidPos(Position pos) {
        return pos.x >= 0 && pos.x < 4 && pos.y >= 0 && pos.y < 4;
    }


    @Override
    public int estimatedCost(Node<State> n) {
        State state = n.getElement();
        Position distanceToGoal = goal.subtract(state.getPosition());
        return state.getPath().length() + Math.abs(distanceToGoal.x) + Math.abs(distanceToGoal.y);
    }
}
