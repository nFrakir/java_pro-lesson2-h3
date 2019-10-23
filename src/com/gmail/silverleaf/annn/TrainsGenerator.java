package com.gmail.silverleaf.annn;

import java.util.*;

public class TrainsGenerator {
    static final int OUTPUT_STRING_LENGTH = 7;
    List<Train> trains = new ArrayList<>();

    public TrainsGenerator(List<Train> trains) {
        super();
        this.trains = trains;
    }

    public TrainsGenerator() {
        super();
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    @Override
    public String toString() {
        return "TrainsGenerator{" +
                "trains=" + trains +
                '}';
    }

    public void generateList(int count) {
        for (int i = 0; i < count; i+=1) {
            Train train = generateTrain();
            trains.add(train);
        }
    }

    public Train generateTrain() {
        Train train = new Train();
        Random rnd = new Random();
        train.setId(Integer.toString(rnd.nextInt(100)));
        train.setFrom(getNextRandomString(rnd));
        train.setTo(getNextRandomString(rnd));
        train.setDate(getNextRandomDate(rnd));
        train.setDeparture(getNextRandomTime(rnd));
        return train;
    }

    private static String getNextRandomString(Random rnd) {
        String strAllowedCharacters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sbRandomString = new StringBuilder(OUTPUT_STRING_LENGTH);

        for(int i = 0 ; i < OUTPUT_STRING_LENGTH; i++){
            int randomInt = rnd.nextInt(strAllowedCharacters.length());
            sbRandomString.append(strAllowedCharacters.charAt(randomInt));
        }

        return sbRandomString.toString();
    }

    private static Date getNextRandomDate(Random rnd) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.DAY_OF_MONTH, rnd.nextInt(30));
        cld.set(Calendar.MONTH, rnd.nextInt(12));

        return cld.getTime();
    }

    private static Date getNextRandomTime(Random rnd) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR, rnd.nextInt(23));
        cld.set(Calendar.MINUTE, rnd.nextInt(59));

        return cld.getTime();
    }
}
