package com.company.part2;

public abstract class Soldier implements Comparable<Soldier> {
    protected Integer stars;

    public Integer getStars() {
        return stars;
    }

    @Override
    public int compareTo(Soldier soldier) {
        return this.stars.compareTo(soldier.getStars());
    }
}
