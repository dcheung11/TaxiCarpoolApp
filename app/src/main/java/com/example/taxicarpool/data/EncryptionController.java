package com.example.taxicarpool.data;

import android.content.Context;

import com.example.taxicarpool.join.Criteria;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;

public class EncryptionController {

    private static EncryptionController instance;
    private static AppDatabase db;
    private static UserDao dao;

    private static final String CRYPTO_ALGORITHM = "AES";
    private static final int CRYPTO_KEY_SIZE = 128;
    private SecretKey secretKey;
    private Cipher cipher;

    public EncryptionController() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CRYPTO_ALGORITHM);
        keyGenerator.init(CRYPTO_KEY_SIZE);
        secretKey = keyGenerator.generateKey();

        // Create a cipher object
        cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
    }

    public static synchronized EncryptionController getInstance(Context context) throws Exception {

        if (instance == null) {
            instance = new EncryptionController();
            db = AppDatabase.getInstance(context);
            dao = db.Dao();
            initialize();

        }
        return instance;
    }

    public static void initialize(){
        UserIdentity user1 = new UserIdentity("Justin","Dang","123@gmail.com","123456");
        UserIdentity user2 = new UserIdentity("Justin","Dang","1234@gmail.com","123456");
        dao.insertUser(user1);
        dao.insertUser(user2);
        Carpool carpool1 = new Carpool(1L,"Death","Death", 0.0F);
    }

    public Long insertUser(UserIdentity user){
        return dao.insertUser(user);
    }

    public void updateUser(UserIdentity user){
        dao.updateUser(user);
    }

    public void deleteUser(UserIdentity user){
        dao.deleteUser(user);
    }

    public void insertCarpool(Carpool carpool){
         dao.insertCarpool(carpool);
    }

    public List<Carpool> getAllCarpool(){
        return dao.getAllCarpool();
    }

    public List<UserIdentity> getAll(){
        return dao.getAll();
    }

    public UserIdentity findByName(String firstName, String lastName) throws Exception {
       return dao.findByName(firstName,lastName);
    }

    public UserIdentity findByEmail(String email) throws Exception {
        return dao.findByEmail(decrypt(encrypt(email)));
    }

    public void insertCarpoolRef(CarpoolUserCrossRef ref){
        dao.insertCarpoolUserRef(ref);
    }
    public List<Carpool> findCarpoolByTrip(String location, String destination){
        return dao.findCarpoolByTrip(location, destination);
    }



    public CarpoolWithRiders carpoolWithRiders(Long matchId){
        return dao.getCarpoolWithRiders(matchId);
    }

    public byte[] encrypt(String input) throws Exception{
        //encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the input string
        byte[] inputBytes = input.getBytes();
        byte[] encryptedBytes = cipher.doFinal(inputBytes);

        return encryptedBytes;
    }

    public String decrypt(byte[] input) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the input bytes
        byte[] decryptedBytes = cipher.doFinal(input);
        String decryptedString = new String(decryptedBytes);

        return decryptedString;
    }

}
