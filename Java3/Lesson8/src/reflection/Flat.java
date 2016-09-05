package reflection;

/**
 * Created by vasilenko.aleksandr on 02.09.2016.
 */
public class Flat {
    private int number;
    private int rooms;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "number=" + number +
                ", rooms=" + rooms +
                '}';
    }
}
