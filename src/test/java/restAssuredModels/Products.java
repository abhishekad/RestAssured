package restAssuredModels;

public class Products {

    private static String id;
    private static String name;
    private static String description;
    private static double price;
    private static int category_id;
    private String category_name;

    public Products(){
    }

    // For Post call
    public Products(String name, String description, double price, int category_id){
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory_id(category_id);
    }

    // for put call
    public Products(String id, String name, String description, double price, int category_id){
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory_id(category_id);
    }

    // for get call
    public Products(String id, String name, String description, double price, int category_id, String category_name){
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory_id(category_id);
        setCategory_name(category_name);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
