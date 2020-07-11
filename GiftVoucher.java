package temapoo;

import java.time.LocalDateTime;
import java.util.Date;

public class GiftVoucher extends Voucher {
    float sum;

    public GiftVoucher(Integer Id, String email, float value, String code, Integer idCampaign)
    {
        super(Id,email,code,idCampaign);
        this.sum = value;
    }
    public String toString()
    {
        String str= new String();
        str=str+getStatusType()+";"+this.email+";"+idCampaign+";"+sum;
        return str;
    }
    public String getCode(){
        return code;
    }
    public float getSum() {
        return sum;
    }
    public String getEmail() {
        return email;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public VoucherStatusType getStatusType() {
        return Type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUseDate(LocalDateTime useDate) {
        this.useDate = useDate;
    }
    public Integer getId() {
        return id;
    }
}
