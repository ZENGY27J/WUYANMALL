package com.wuyan.mall.bean.Accept;

import java.util.ArrayList;
import java.util.List;

public class AcceptCartChecks {
    List<Integer> productIds;
    boolean isChecked;

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
