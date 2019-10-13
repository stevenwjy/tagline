package tagline.model.contact;

import tagline.model.contact.exceptions.InvalidIdException;

/**
 * Represent a Contact unique Id.
 */
public class Id {
    private static int digit = 5;

    private final int id;

    /**
     * Construct Id from integer.
     */
    Id(int id) {
        if (id >= Math.pow(10, digit)) {
            throw new InvalidIdException("Id too large");
        }
        this.id = id;
    }

    /**
     * Increase the number of digit in Id.
     */
    static void incrementDigit() {
        digit++;
    }

    static int getDigit() {
        return digit;
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public String toString() {
        String idString = ((Integer) id).toString();
        while (idString.length() < digit) {
            idString = "0" + idString;
        }
        return idString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        int other;
        if (obj instanceof Integer) {
            other = (Integer) obj;
        } else if (obj instanceof Id) {
            other = ((Id) obj).id;
        } else {
            return false;
        }

        return id == other;
    }
}
