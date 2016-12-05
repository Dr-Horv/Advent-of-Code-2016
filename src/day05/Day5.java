package day05;

import utils.Solver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 implements Solver {
    @Override
    public String solve() {
        String input = "wtnhxymk";
        //String input = "abc";
        char[] output = new char[8];

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            int index = 0;
            while(true) {

                MessageDigest currMd = (MessageDigest) md.clone();
                currMd.update((index + "").getBytes());
                byte[] digest = currMd.digest();

                String hexhash = getHexHash(digest);

                if (hexhash.startsWith("00000")) {
                    char c = hexhash.charAt(5);
                    char value = hexhash.charAt(6);
                    int position = getPosition(c);
                    if(position >= 0 && position < 8 && output[position] == 0) {
                        output[position] = value;
                        System.out.println("Pos " + position + " " + value);
                    }

                }

                index++;

                boolean done = isDone(output);
                if(done) {
                    break;
                }
            }

            return new String(output);

        } catch (NoSuchAlgorithmException | CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getHexHash(byte[] digest) {
        return String.format("%02X", digest[0]) + String.format("%02X", digest[1]) + String.format("%02X", digest[2]) + String.format("%02X", digest[3]);
    }

    private boolean isDone(char[] output) {
        boolean done = true;
        for(char c : output) {
            if(c == 0) {
                done = false;
            }
        }
        return done;
    }

    private int getPosition(char c) {
        int position;
        try {
            position = Integer.parseInt(c+"");
        } catch (NumberFormatException e) {
            position = -1;
        }
        return position;
    }
}
