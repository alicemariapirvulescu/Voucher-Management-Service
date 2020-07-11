package temapoo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class LoyalityVoucher extends Voucher{
    float discount;

    public LoyalityVoucher(Integer Id, String email, float value, String code, Integer idCampaign)
    {
        super(Id, email,code,idCampaign);
        this.discount=value;

    }

    public String toString()
    {
        String str= new String();
        str=str+";"+getStatusType()+";"+this.email+";"+discount+";"+idCampaign;
        return str;
    }

    public float getDiscount() {
        return discount;
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

    public void setUseDate(LocalDateTime useDate)
    {
        this.useDate = useDate;
    }
    public String getCode(){
        return code;
    }
    public Integer getId() {
        return id;
    }
}
