package main.PersistenceModels;

import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DebitCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<DebitCard> extent = new ArrayList<>();

    private String cardNumber;
    private LocalDate expirationDate;
    private String securityCode;

    public DebitCard(String cardNumber, LocalDate expirationDate, String securityCode) {
        ValidationUtil.notEmptyString(cardNumber, "cardNumber");
        ValidationUtil.notNull(expirationDate, "expirationDate");
        ValidationUtil.notEmptyString(securityCode, "securityCode");
        ValidationUtil.notNull(expirationDate, "securityCode");

        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;

        addToExtent(this);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        ValidationUtil.notEmptyString(cardNumber, "cardNumber");
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        ValidationUtil.notNull(expirationDate, "expirationDate");
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        ValidationUtil.notNull(securityCode, "securityCode");
        ValidationUtil.notEmptyString(securityCode, "securityCode");

        this.securityCode = securityCode;
    }

    private static void addToExtent(DebitCard dc) {
        if (dc == null) throw new IllegalArgumentException("DebitCard cannot be null");
        extent.add(dc);
    }

    public static List<DebitCard> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<DebitCard> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
