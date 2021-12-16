package tn.fsb.tp3calcul_imc_activity.model;

public class Profile {
    private int id;
    private String username;
    private int height;
    private int weight;
    private String msgBMI;

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMsgBMI() {
        return msgBMI;
    }

    public void setMsgBMI(String msgBMI) {
        this.msgBMI = msgBMI;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", msgBMI='" + msgBMI + '\'' +
                '}';
    }
}
