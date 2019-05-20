package util;



import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import encrypt.Encryptor;

public class ReadConfigration {

	private static final String[] serverConfig = {
	        ConstantsUtil.CONFIG_FILE_USER_PROPERTY, ConstantsUtil.CONFIG_FILE_PASSWORD_PROPERTY, ConstantsUtil.CONFIG_FILE_HOST_PROPERTY, ConstantsUtil.CONFIG_FILE_PORT_PROPERTY,
	        ConstantsUtil.CONFIG_FILE_SID_PROPERTY
	    };

	    public static String getPropertyfromfile(String Key) {

	        String propValue = null;
	        PropertiesConfiguration prop = new PropertiesConfiguration();
	        try {
	            prop.load(ConstantsUtil.CONFIG_FILE_LOCATION);
	            propValue = prop.getString(Key);
	            return propValue;
	        } catch (ConfigurationException e) {
	            MsgLog.write("Configration file Error : " + e.getMessage());
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static boolean isEncrypted() {
	        boolean encrypted = getPropertyfromfile(ConstantsUtil.CONFIG_FILE_ISENCRYPTED_PROPERTY).matches("true");
	        return encrypted;
	    }

	    public static void encryptConfigFile() {

	        try {
	            PropertiesConfiguration config = new PropertiesConfiguration(ConstantsUtil.CONFIG_FILE_LOCATION);
	            boolean isEncrypted = isEncrypted();
	            if (isEncrypted == false) {
	                for (int i = 0; i < serverConfig.length; i++) {

	                    String encryptedProperty = Encryptor.encrypt(config.getString(serverConfig[i]));
	                    //  System.out.println("Encryption done and encrypted Property is : " + encryptedProperty);
	                    MsgLog.write("Encryption done for  encrypted Property  :" + serverConfig[i]);

	                    config.setProperty(serverConfig[i], encryptedProperty);
	                    config.save();
	                }
	                // Set the boolean flag to true to indicate future encryption operation that password is already encrypted

	                config.setProperty(ConstantsUtil.CONFIG_FILE_ISENCRYPTED_PROPERTY, "true");
	                config.save();

	            } else {

	                MsgLog.write("Property is already encrypted ");
	            }
	        } catch (ConfigurationException e) {
	            MsgLog.write("Property is already encrypted " + e.getMessage());
	            System.out.println(e.getMessage());
	        }
	    }

	    public static String[] DecryptConfigFile() {
	        String[] dbConfigration = new String[5];
	        String property = null;
	        String decryptProperty = null;

	        for (int x = 0; x < serverConfig.length; x++) {
	            if (getPropertyfromfile(serverConfig[x]) != null) {

	                property = getPropertyfromfile(serverConfig[x]);
	                decryptProperty = Encryptor.decryptText(property);
	                dbConfigration[x] = decryptProperty;
	            }
	        }
	        return dbConfigration;
	    }

	    public static String[] getDBconfigration() {

	        if (isEncrypted() == false) {
	            encryptConfigFile();
	            return DecryptConfigFile();
	        }
	        if (isEncrypted() == true) {
	            return DecryptConfigFile();
	        } else {
	            // System.out.println("Property is already encrypted");
	            MsgLog.write("Property is already encrypted ");
	        }
	        return null;
	    }
	}
