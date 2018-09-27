package data;

/**
 *
 * @author Fen
 */
public class LineItem {
    private CupCake cake;
    private int quantity;
    private String invoice_id;

    @Override
    public String toString() {
        return "LineItem{" + "cake=" + cake + ", quantity=" + quantity + '}';
    }

    public LineItem(CupCake cake, int quantity) {
        this.cake = cake;
        this.quantity = quantity;
    }

    public CupCake getCake() {
        return cake;
    }

    public void setCake(CupCake cake) {
        this.cake = cake;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }
    
    
    
}
