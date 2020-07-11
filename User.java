package temapoo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

public class User implements Observer {
    Integer id;
    String name;
    String email;
    String password;
    EnumerationU userType;
    UserVoucherMap vouchers = new UserVoucherMap();
    Vector<Notification> notifications = new Vector<>();

    public User( Integer id, String name, String email, String password, String userType)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.userType=EnumerationU.valueOf(userType);
    }
    public Vector<Notification> getNotifications()
    {
        return this.notifications;
    }
    public String toString()
    {
        return "["+id+ ";"+name+";"+email+";"+userType+ "]";
    }

    public void addVoucherToUser( Voucher v)
    {
        this.vouchers.addVoucher(v);
    }

    public void Update(Notification n)
    {
        this.notifications.add(n);
    }
    public String getEmail() {
        return email;
    }

    public EnumerationU getUserType() {
        return userType;
    }
    public String getType(){
        return userType.toString();
    }

    public UserVoucherMap getVouchers() {
        return vouchers;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
    public void update(Notification notification)
    {
        notifications.add(notification);
    }


}
