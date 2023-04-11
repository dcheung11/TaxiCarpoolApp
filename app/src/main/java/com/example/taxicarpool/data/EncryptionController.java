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
        UserIdentity damien = new UserIdentity("Damien","Cheung","d@@gmail.com","123456");
        UserIdentity userForCarpool1 = new UserIdentity("guy","1","1@@gmail.com","123456");
        UserIdentity userForCarpool2 = new UserIdentity("guy","2","2@@gmail.com","123456");
        UserIdentity userForCarpool3 = new UserIdentity("guy","3","3@@gmail.com","123456");
        UserIdentity userForCarpool4 = new UserIdentity("guy","4","4@@gmail.com","123456");

        dao.insertUser(user1);
        dao.insertUser(damien);
        dao.insertUser(userForCarpool1);
        dao.insertUser(userForCarpool2);
        dao.insertUser(userForCarpool3);
        dao.insertUser(userForCarpool4);

//        Criteria van_pets = new Criteria();
//        Criteria truck_pets_gender = new Criteria();
//        van_pets.setSuv(false);
//        van_pets.setSedan(false);
//        van_pets.setTruck(false);
//        truck_pets_gender.setSuv(false);
//        truck_pets_gender.setSedan(false);
//        truck_pets_gender.setVan(false);
//        van_pets.setGender(false);
//        Carpool carpool1 = new Carpool(1L,"Information Technology Building","Death", 0.0F, van_pets);
//        Carpool carpool2 = new Carpool(2L,"Information Technology Building","Death", 0.0F, truck_pets_gender);
//        dao.insertCarpool(carpool1);
//        dao.insertCarpool(carpool2);
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
