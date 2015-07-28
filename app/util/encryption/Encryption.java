package util.encryption;

/**
 * Created with IntelliJ IDEA.
 * User: seema
 * Date: 16/10/13
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Encryption {
    public String encrypt(String clearText);
    public String decrypt(String cipherText);
}
