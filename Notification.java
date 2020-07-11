package temapoo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    NotificationType type;
    LocalDateTime date;
    Integer campaignId;
    ArrayList<String> vouchersCode = new ArrayList<>() ;

    public Notification(String type, LocalDateTime date, Integer campaignId, ArrayList<String> vouchersCode)
    {
        this.type=NotificationType.valueOf(type);
        this.date=date;
        this.campaignId=campaignId;
        this.vouchersCode=vouchersCode;
    }

    public String toString()
    {
        return type+ " "+campaignId+ " " + vouchersCode;
    }
    public void addVoucher(String voucherCode)
    {
        ArrayList<String> list= this.vouchersCode;
        list.add(voucherCode);
    }

    public NotificationType getType() {
        return type;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public ArrayList<String> getVouchersCode() {
        return vouchersCode;
    }
}
