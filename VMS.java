package temapoo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class VMS {
    static String name;
    Vector<Campaign> set_campaigns = new Vector<>();
    Vector<User> set_users = new Vector<>();

    private static VMS obj = new VMS();
    private VMS() {
        VMS.name="myVMS";
    }
    // eager instantiation - merge la threaduri
    public static VMS getInstance()
    {
        return obj;
    }

    public Vector<Campaign> getCampaigns() {
        return set_campaigns;
    }

    public Campaign getCampaign(Integer id){
    Campaign campanie=null;
    for(int i=0; i<set_campaigns.size();i++) {
        campanie = set_campaigns.get(i);
        if(campanie.id.equals(id))
            return campanie;
        }
    return null;
    }
    public boolean isAdmin (Integer id)
    {
        for(User u : set_users)
        {
            if(u.getType().equals("ADMIN") && u.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public User getUser (Integer id)
    {
        for (int i=0; i<set_users.size(); i++)
        {
            User user =set_users.get(i);
            if(user.id.equals(id))
                return user;
        }
        return null;
    }

    public User getUser( String email)
    {
        for (int i=0; i<set_users.size(); i++)
        {
            User user =set_users.get(i);
            if(user.email.equals(email))
                return user;
        }
        return null;
    }

    public void addCampaign( Campaign campaign) {
        if (set_campaigns==null) {
            this.set_campaigns = new Vector<Campaign>();
            this.set_campaigns.add(campaign);
        }
        else this.set_campaigns.add(campaign);
    }

    public boolean updateCampaign(Integer id, Campaign campaign)
    {
        for(int i=0; i<set_campaigns.size();i++) {
           Campaign oldCampaign = set_campaigns.get(i);
         //  System.out.println(oldCampaign);
            if (oldCampaign.id.equals(id) && oldCampaign.getStatus().equals("NEW"))
            {
                int vouchersD=oldCampaign.totalVouchers-oldCampaign.availableVouchers;
                oldCampaign.name = campaign.name;
                oldCampaign.description = campaign.description;
                oldCampaign.startDate = campaign.startDate;
                oldCampaign.endDate = campaign.endDate;
                oldCampaign.totalVouchers = campaign.totalVouchers;
                oldCampaign.StatusType = campaign.StatusType;
                oldCampaign.vouchersDistributed = campaign.vouchersDistributed;
                oldCampaign.observers = campaign.observers;
                oldCampaign.strategyType = campaign.strategyType;
                ArrayList<String> voucherCodes = new ArrayList<>();
                for(Voucher v : oldCampaign.getVouchers())
                {
                    voucherCodes.add(v.getCode());
                }
                Notification notification = new Notification("EDIT", LocalDateTime.now(),oldCampaign.id,voucherCodes);
                oldCampaign.notifyAllObservers(notification);
                break;
            }
            if (oldCampaign.id.equals(id) && oldCampaign.getStatus().equals("STARTED"))
            {
                int vouchersD=oldCampaign.totalVouchers-oldCampaign.availableVouchers;
                oldCampaign.totalVouchers = campaign.totalVouchers;
                oldCampaign.endDate = campaign.endDate;
                oldCampaign.availableVouchers=campaign.totalVouchers - oldCampaign.availableVouchers;
                ArrayList<String> voucherCodes = new ArrayList<>();
                for(Voucher v : oldCampaign.getVouchers())
                {
                    voucherCodes.add(v.getCode());
                }
                Notification notification = new Notification("EDIT", LocalDateTime.now(),oldCampaign.id,voucherCodes);
                oldCampaign.notifyAllObservers(notification);
                break;
            }
        }

        return true;
    }

    public void cancelCampaign(Integer id)
    {
        Campaign c=null;
        for(int i=0; i<set_campaigns.size();i++) {
             c= set_campaigns.get(i);
            if (c.id.equals(id) && (c.StatusType.equals(CampaignStatusType.NEW) || c.StatusType.equals(CampaignStatusType.STARTED)))
            {
            c.StatusType=CampaignStatusType.CANCELLED;
                ArrayList<String> voucherCodes = new ArrayList<>();
                for(Voucher v : c.getVouchers())
                {
                 voucherCodes.add(v.getCode());
                }
            Notification notification = new Notification("CANCEL", LocalDateTime.now(),c.id,voucherCodes);
                c.notifyAllObservers(notification);
            }
        }
    }
    public Vector<User> getUsers()
    {
        return this.set_users;

    }
    public void addUser(User user)
    {
        for(User u : this.set_users) {
            if(u.getId().equals(user.getId()))
            {
                return;
            }
        }
        this.set_users.add(user);
    }


}