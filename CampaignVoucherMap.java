package temapoo;

import java.util.ArrayList;

public class CampaignVoucherMap<K,V> extends ArrayMap<String,ArrayList<Voucher>> {
    boolean addVoucher(Voucher v) {
        if (super.containsKey(v.getEmail()))
                super.get(v.getEmail()).add(v);
        else
        {
            ArrayList<Voucher> listaNoua= new ArrayList<>();
            listaNoua.add(v);
            put(v.getEmail(),listaNoua);
        }
        return true;
    }
}
