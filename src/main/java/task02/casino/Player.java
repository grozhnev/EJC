package task02.casino;

class Player {
    private int money;
    private boolean abilityToPlay;
    private int answer;

    public Player() {
        money = 500;
    }

    public Player(int sum) {
        money = sum;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean canPlay() {
        return abilityToPlay;
    }

    public int showTheMoney() {
        return money;
    }

    public void earnTheMoney(int money) {
        this.money += money;
    }

    public void payTheMoney(int money) {
        this.money -= money;
    }

    public void isAbleToPlay() {
        if (showTheMoney() > 0) {
            switch (answer) {
                case '9':
                    abilityToPlay = true;
                    break;
                case '0':
                    abilityToPlay = false;
                    break;
            }
        } else {
            Announcement.noMoney();
            abilityToPlay = false;
        }
    }
}