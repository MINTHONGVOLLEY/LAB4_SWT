
package molder;

/**
 *
 * @author DELL DN
 */
public class Order {
    private int orderId;
    private String date;
    private int customerId;
    private double totalMoney;
    private String address;
    private String phone;
    private String name;

    public Order() {
    }

    public Order(int orderId, String date, int customerId, double totalMoney, String address, String phone, String name) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.totalMoney = totalMoney;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", date=" + date + ", customerId=" + customerId + ", totalMoney=" + totalMoney + ", address=" + address + ", phone=" + phone + ", name=" + name + '}';
    }
    
}
