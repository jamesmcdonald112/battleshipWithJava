enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);


    private int level;
    DangerLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}

class main {

    public static void main(String[] args) {
        DangerLevel high = DangerLevel.HIGH;
        System.out.println(high.getLevel());
    }
}