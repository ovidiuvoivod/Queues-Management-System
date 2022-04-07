package ro.utcluj.model;

public class Client implements Comparable<Client> {
    private Integer id;
    private Integer arrivalTime;
    private Integer serviceTime;

    public Client(Integer id, Integer arrivalTime, Integer serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public String toString() {
        return "(" + this.id + "," + this.arrivalTime + "," + this.serviceTime + ")";
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public int compareTo(Client o) {
        if (this.arrivalTime < o.arrivalTime) return -1;
        else return 1;
    }
}
