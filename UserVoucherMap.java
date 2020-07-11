package temapoo;

import java.security.Key;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static javafx.scene.input.KeyCode.K;

public class UserVoucherMap extends ArrayMap<Integer, ArrayList<Voucher>>{

    boolean addVoucher(Voucher v) {
        if (super.containsKey(v.getIdCampaign()))
                super.get(v.getIdCampaign()).add(v);

        else
        {
            ArrayList<Voucher> listaNoua= new ArrayList<>();
            listaNoua.add(v);
            put(v.getIdCampaign(),listaNoua);
        }
    return true;
    }
}
