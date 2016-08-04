package FM.Model;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 */
public class Category {
    private String name;
    private int id;

    public Category(String name) {
        this.name = name;
        this.id = -1;
    }

    public Category(String name, int id) {
        this(name);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
