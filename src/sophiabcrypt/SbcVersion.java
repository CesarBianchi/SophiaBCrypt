package sophiabcrypt;

/**
 * This class store the information about version and build date of last SophiaBCrypt
 * @see SbcMainWindow
 * @author CesarBianchi
 * @since October/2018
 */
public class SbcVersion {

    private String cVersion = "1.03.1";
    private String cBuildDate = "30 Set 2018";
    
    /**
     * Get this SophiaBCrypt Version
     * @return cVersion Version of this SophiaBCrypt
     * @author CesarBianchi
     * @since October/2018
    */
    public String getVersion(){
        return cVersion;
    }

    /**
     * Get this SophiaBCrypt Build Date
     * @return cBuildDate Build Date of this SophiaBCrypt
     * @author CesarBianchi
     * @since October/2018
    */
    public String getBuildDate(){
        return cBuildDate;
    }

}
