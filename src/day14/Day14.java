package day14;

import utils.Solver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Solver {

    private HashMap<String, Boolean> fiveTimeHashes = new HashMap<>();
    private HashMap<Integer, String> hashes = new HashMap<>();


    @Override
    public String solve() {
        String salt = "zpqevtbw";
        HashMap<Integer, String> validHashes = new HashMap<>();
        int nbrValid = 64;


        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt.getBytes());

            int index = 0;

            while(validHashes.size() < nbrValid) {
                String hash = getStretchedHash(md, index);

                if(hashContainsSameCharacterXTimes(hash, 3)){
                    char same ='-';
                    for(char c : hash.toCharArray()) {
                        if(hashContainsSpecificCharacterXTimes(hash, 3, c)) {
                            same = c;
                            break;
                        }
                    }

                    if(isValidKey(same, index, md)) {
                        System.out.println("isValid " + index);
                        validHashes.put(index, hash);
                    }

                    if(validHashes.size() == nbrValid) {
                        break;
                    }
                }

                if(index % 1000 == 0) {
                    System.out.println("Index: " + index);
                }

                index++;
            }

            System.out.println("VALID " + validHashes.size());

            return "" + index;

        } catch (NoSuchAlgorithmException | CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isValidKey(char same, int index, MessageDigest md) throws CloneNotSupportedException, NoSuchAlgorithmException {
        for (int i = 1; i <= 1000; i++) {
            int testIndex = index+i;
            String testHash = getStretchedHash(md, testIndex);
            if(containSameFiveTimes(testHash, same)){
                return true;
            }
        }


        return false;
    }

    private boolean containSameFiveTimes(String testHash, char same) {
        if (!fiveTimeHashes.containsKey(testHash)) {
            boolean test = hashContainsSameCharacterXTimes(testHash, 5);
            fiveTimeHashes.put(testHash, test);
        }

        return fiveTimeHashes.get(testHash) && hashContainsSpecificCharacterXTimes(testHash, 5, same);

    }

    public static boolean hashContainsSameCharacterXTimes(String hash, int times) {
        char[] chars = hash.toCharArray();
        for (int i = 0; i < chars.length - (times-1); i++) {
            boolean test = sameTest(times, chars, i, chars[i]);
            if(test) {
                return true;
            }
        }
        return false;
    }

    private static boolean sameTest(int times, char[] chars, int i, char c) {
        boolean test = true;
        for (int t = 0; t < times; t++) {
            if(chars[i] != c || chars[i+t] != c) {
                test = false;
                break;
            }
        }
        return test;
    }

    public static boolean hashContainsSpecificCharacterXTimes(String hash, int times, char c) {
        char[] chars = hash.toCharArray();
        for (int i = 0; i < chars.length - (times-1); i++) {
            boolean test = sameTest(times, chars, i, c);
            if(test) {
                return true;
            }
        }
        return false;
    }

    private String getHash(MessageDigest md, int index) throws CloneNotSupportedException {
        if(hashes.containsKey(index)) {
            return hashes.get(index);
        }
        MessageDigest currMd = (MessageDigest) md.clone();
        currMd.update((index + "").getBytes());
        byte[] digest = currMd.digest();
        String hash = getHexHash(digest);
        hashes.put(index, hash);
        return hash;
    }

    public String getStretchedHash(MessageDigest md, int index) throws CloneNotSupportedException, NoSuchAlgorithmException {
        if(hashes.containsKey(index)) {
            return hashes.get(index);
        }
        MessageDigest currMd = (MessageDigest) md.clone();
        currMd.update((index + "").getBytes());
        byte[] digest = currMd.digest();
        String hash = getHexHash(digest).toLowerCase();

        MessageDigest newMd = MessageDigest.getInstance("MD5");
        for (int i = 0; i < 2016; i++) {
            newMd.update(hash.getBytes());
            byte[] d = newMd.digest();
            newMd.reset();
            hash = getHexHash(d).toLowerCase();
        }

        hashes.put(index, hash);
        return hash;
    }

    private String getHexHash(byte[] digest) {

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append( String.format("%02X", b) );
        }

        return sb.toString();
    }
}
