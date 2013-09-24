package woofer.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Locale;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Technical decisions for how the encryption scheme were chosen
 * were derived from PKCS #5 version 2.1
 * https://www.rsa.com/rsalabs/pkcs/files/h11302-wp-pkcs5v2-1-password-based-cryptography-standard.pdf
 */

@Service
public class PasswordEncryptionService {
    
    private static final Logger logger = 
            Logger.getLogger(PasswordEncryptionService.class);

    /**
     * Given the salt and the attempted clear-text password
     * verify that it matches the encrypted password
     * @param attemptedPassword
     * @param encryptedPassword
     * @param salt
     * @return true if matches, otherwise false
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException 
     */
    public boolean authenticate(
            String attemptedPassword,
            String encryptedPassword, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        return encryptedAttemptedPassword.equals(encryptedPassword);
    }

    /**
     * Generates a 40 character long hex string representing the password
     * encrypted with the salt.
     *
     * @param password the clear-text password
     * @param salt the hex string salt
     * @return the encrypted password as a hex string
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public String getEncryptedPassword(String password, String salt) {
        
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        final String ALGORITHM = "PBKDF2WithHmacSHA1";
        
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        final int KEY_LENGTH = 160;
        
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        final int ITERATIONS = 20000;

        try {
            byte[] saltBytes = Hex.decodeHex(salt.toCharArray());
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes,
                    ITERATIONS, KEY_LENGTH);
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);

            return Hex.encodeHexString(f.generateSecret(spec).getEncoded())
                    .toUpperCase(Locale.US);
        } catch (Exception e) {
            logger.fatal("Failed to acquire password", e.fillInStackTrace());
            return null;
        }
    }
    
    /**
     * Securely generates a random 64 bit salt.
     * @return 16 character hex string representing the salt.
     * @throws NoSuchAlgorithmException
     */
    public String generateSalt() {
        
        try {
            // VERY important to use SecureRandom instead of just Random
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

            // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
            byte[] salt = new byte[8];
            random.nextBytes(salt);

            return Hex.encodeHexString(salt).toUpperCase(Locale.US);
        } catch(NoSuchAlgorithmException e) {
            logger.fatal("Invalid Algorithm", e.fillInStackTrace());
            return null;
        }
    }
}
