package temapoo;

import java.time.LocalDateTime;

public abstract class Voucher {
    Integer id;
    String code;
    VoucherStatusType Type;
    LocalDateTime useDate;
    String email;
    Integer idCampaign;


    public Voucher(Integer id, String email, String code, Integer idCampaign)
    {
        this.id = id;
        this.email = email;
        this.Type = VoucherStatusType.UNUSED;
        this.code = code;
        this.idCampaign = idCampaign;
    }

    public abstract String toString();
    public abstract String getEmail() ;

    public abstract Integer getIdCampaign();

    public abstract VoucherStatusType getStatusType();

    public abstract void setId(Integer id);

    public abstract void setUseDate(LocalDateTime useDate);

    public abstract Integer getId();
    public abstract String getCode();
}

