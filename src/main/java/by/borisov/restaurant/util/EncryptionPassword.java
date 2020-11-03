package by.borisov.restaurant.util;

import by.borisov.restaurant.exception.CommandException;
import by.borisov.restaurant.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionPassword {
    /**
     * This method encodes password
     *
     * @param value unencrypted password
     * @return encrypted password
     * @throws ServiceException if happens NoSuchAlgorithmException
     */

    private static final String MESSAGE_DIGEST = "MD5";
    private static final int HEXADECIMAL_FORMAT = 0xff;
    private static final int HEXADECIMAL_FORMAT_ADD = 0x100;
    private static final int RADIX = 16;
    private static final int OFFSET = 1;

    public static String encrypt(String value) throws CommandException {
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance(MESSAGE_DIGEST);
            md.update(value.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & HEXADECIMAL_FORMAT)
                        + HEXADECIMAL_FORMAT_ADD, RADIX).substring(OFFSET));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException(e);
        }
        return generatedPassword;
    }
}
