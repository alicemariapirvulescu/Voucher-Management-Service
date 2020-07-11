package temapoo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Campaign implements Subject{
    Integer id;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int totalVouchers;
    int availableVouchers;
    CampaignStatusType StatusType;
    CampaignVoucherMap vouchersDistributed = new CampaignVoucherMap();
    ArrayList<Voucher> vouchers = new ArrayList<>();
    ArrayList<User> observers = new ArrayList<>() ;
    String strategyType;


    public Campaign(Integer id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, int budget, String strategyType, LocalDateTime current_date)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
        this.totalVouchers=budget;
        this.availableVouchers=budget;
        this.strategyType=strategyType;
        if(endDate.compareTo(current_date) < 0)
        {
            this.StatusType=CampaignStatusType.EXPIRED;
        }
        else if(startDate.compareTo(current_date) < 0)
        {
            this.StatusType=CampaignStatusType.NEW;
        }
        else
        {
            this.StatusType=CampaignStatusType.STARTED;
        }

    }

    public ArrayList<Voucher> getVouchers()
    {
        return vouchers;
    }

    public Voucher getVoucher(String code)
    {

        for(int i=0; i< vouchers.size();i++)
        {
            if( vouchers.get(i).getCode().equals(code))
                return vouchers.get(i);
        }
        return null;
    }

    public void generateVoucher (String email, String voucherType, float value)
    {
        this.availableVouchers--;
        List<User> users= observers;
        for(int i=0; i< users.size();i++)
        {
            User user= users.get(i);
            if( user.email.equals(email))
            {
                Voucher v;
                Integer id= this.totalVouchers - this.availableVouchers;
                Integer idCampaign= this.id;
                String code= id.toString();
                if (voucherType.equals("LoyalityVoucher"))
                    v=new LoyalityVoucher(id,email,value,code,idCampaign);
                else
                    v= new  GiftVoucher(id,email,value,code,idCampaign);
                if(vouchersDistributed==null)
                {
                    vouchersDistributed = new CampaignVoucherMap();
                    vouchersDistributed.addVoucher(v);
                }
                else vouchersDistributed.addVoucher(v);
                user.addVoucherToUser(v);
                vouchers.add(v);
            }
        }
    }
    public boolean redeemVoucher(String code, LocalDateTime date)
    {
       Voucher voucher= getVoucher(code);
       LocalDateTime endDate= this.endDate;
       LocalDateTime startDate=this.startDate;
       if(voucher == null) return false;
       if(endDate.compareTo(date) < 0)
            return false;
       else if(startDate.compareTo(date) < 0)
        {
            voucher.Type= VoucherStatusType.USED;
            voucher.useDate = date;
            return true;
        }
       return false;
    }

    public ArrayList<User> getObservers() {
        return observers;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void addObserver(User user)
    {
    observers.add(user);
    }

    public void removeObserver(User user)
    {
    observers.remove(user);
    }

    public void notifyAllObservers(Notification notification)
    {
     for(int i=0;i<observers.size();i++)
        {
        observers.get(i).update(notification);
        }
    }
    public String getStatus(){
        return StatusType.toString();
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return  "status " + this.StatusType;
    }
}
