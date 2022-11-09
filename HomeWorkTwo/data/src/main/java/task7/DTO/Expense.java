package task7.DTO;

public class Expense {
    private int num;
    private String paydate;
    private int receiver;
    private float value;

    public int getNum() {
        return num;
    }

    public String getDate() {
        return paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public float getValue() {
        return value;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

