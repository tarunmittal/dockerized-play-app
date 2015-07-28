package util.encryption;

/**
 * Created with IntelliJ IDEA.
 * User: seema
 * Date: 16/10/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncryptionFactory {

    private static Encryption encryption;
    private EncryptionFactory(){

    }
    public static Encryption getInstance() {
        if(encryption == null){
            encryption = new TripleDESEncryption();
        }
        return encryption;
    }
}
