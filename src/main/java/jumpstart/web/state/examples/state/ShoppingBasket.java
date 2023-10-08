package jumpstart.web.state.examples.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class ShoppingBasket {
    private int applesQuantity = 0;
    private int orangesQuantity = 0;
    private int bananasQuantity = 0;

    public int getApplesQuantity() {
        return applesQuantity;
    }

    public void setApplesQuantity(int applesQuantity) {
        this.applesQuantity = applesQuantity;
    }

    public int getOrangesQuantity() {
        return orangesQuantity;
    }

    public void setOrangesQuantity(int orangesQuantity) {
        this.orangesQuantity = orangesQuantity;
    }

    public int getBananasQuantity() {
        return bananasQuantity;
    }

    public void setBananasQuantity(int bananasQuantity) {
        this.bananasQuantity = bananasQuantity;
    }
}