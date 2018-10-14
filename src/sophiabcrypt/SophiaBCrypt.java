package sophiabcrypt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a Main Class of SophiaBCrypt
 * @see SbcMainWindow
 * @see SbcPswWindow
 * @see SbcPswControl
 * @see SbcPlanFile
 * @see SbcEncEngine
 * @see SbcEncryptor
 * @author CesarBianchi
 * @since October/2018
 */public class SophiaBCrypt {

    /**
     * This is a Main Method os class
     * It's exec a Prepare Classes and SbcMainClass
     * @param args the command line arguments
     * @author CesarBianchi
     * @since October/2018
    */
    public static void main(String[] args) {    
        SbcMainWindow main = new SbcMainWindow();        
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.show();
    }
}
