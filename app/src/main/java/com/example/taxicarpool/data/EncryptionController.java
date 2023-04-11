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
        UserIdentity damien = new UserIdentity("Damien","Cheung","d@gmail.com","123456");
        UserIdentity userForCarpool1 = new UserIdentity("guy","1","1@gmail.com","123456");
        UserIdentity userForCarpool2 = new UserIdentity("guy","2","2@gmail.com","123456");
        UserIdentity userForCarpool3 = new UserIdentity("guy","3","3@gmail.com","123456");
        UserIdentity userForCarpool4 = new UserIdentity("guy","4","4@gmail.com","123456");
        UserIdentity userForCarpool5 = new UserIdentity("guy","5","5@gmail.com","123456");


        Criteria van_pets = new Criteria(false,false,false,true,false,true);
        Criteria van_gender_pets = new Criteria(false,false,false,true,false,true);
        Criteria truck = new Criteria(false,false,true, false,false,false);
        Criteria truck_gender = new Criteria(false,false,true, false,true,false);
        Criteria sedan_gender_pets = new Criteria(false,true,false,false,true,true);
        Criteria suv_pets = new Criteria(true,false,false,false,false,true);


        float itb_lazeez_distance = (float) 1.2182198;
        Carpool itb_lazeez_1 = new Carpool(1L,"Information Technology Building","Lazeez Shawarma", itb_lazeez_distance, van_pets);
        Carpool itb_lazeez_2 = new Carpool(2L,"Information Technology Building","Lazeez Shawarma", itb_lazeez_distance, van_gender_pets);
        Carpool itb_lazeez_3 = new Carpool(3L,"Information Technology Building","Lazeez Shawarma", itb_lazeez_distance, truck);
        Carpool itb_lazeez_4 = new Carpool(4L,"Information Technology Building","Lazeez Shawarma", itb_lazeez_distance, truck_gender);
        Carpool itb_lazeez_5 = new Carpool(5L,"Information Technology Building","Lazeez Shawarma", itb_lazeez_distance, sedan_gender_pets);

        //Demo make this one? or something
//        Carpool itb_lazeez_6 = new Carpool(6L,"Information Technology Building","Lazeez Shawarma", 3.0F, suv_pets);

        CarpoolUserCrossRef crossRef1 = new CarpoolUserCrossRef(itb_lazeez_1, userForCarpool1);
        CarpoolUserCrossRef crossRef2 = new CarpoolUserCrossRef(itb_lazeez_2, userForCarpool2);
        CarpoolUserCrossRef crossRef3 = new CarpoolUserCrossRef(itb_lazeez_3, userForCarpool3);
        CarpoolUserCrossRef crossRef4 = new CarpoolUserCrossRef(itb_lazeez_4, userForCarpool4);
        CarpoolUserCrossRef crossRef5 = new CarpoolUserCrossRef(itb_lazeez_5, userForCarpool5);

        dao.insertUser(user1);
        dao.insertUser(damien);
        dao.insertUser(userForCarpool1);
        dao.insertUser(userForCarpool2);
        dao.insertUser(userForCarpool3);
        dao.insertUser(userForCarpool4);
        dao.insertCarpool(itb_lazeez_1);
        dao.insertCarpool(itb_lazeez_2);
        dao.insertCarpool(itb_lazeez_3);
        dao.insertCarpool(itb_lazeez_4);
        dao.insertCarpool(itb_lazeez_5);
        dao.insertCarpoolUserRef(crossRef1);
        dao.insertCarpoolUserRef(crossRef2);
        dao.insertCarpoolUserRef(crossRef3);
        dao.insertCarpoolUserRef(crossRef4);
        dao.insertCarpoolUserRef(crossRef5);

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

    public RiderWithCarpools riderWithCarpools(Long id){
        return dao.getRiderWithCarpools(id);
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
