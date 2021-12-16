package tn.fsb.tp3calcul_imc_activity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tn.fsb.tp3calcul_imc_activity.model.Profile;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "profilesManager";
    private static final String TABLE_CONTACTS = "profiles";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_BMI = "BMI";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_HEIGHT + " TEXT," + KEY_WEIGHT + " TEXT,"
                + KEY_BMI + " TEXT"  + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addContact(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, profile.getUsername()); // Profile Name
        values.put(KEY_HEIGHT, profile.getHeight()); // Profile height
        values.put(KEY_WEIGHT, profile.getWeight()); // Profile weight
        values.put(KEY_BMI, profile.getMsgBMI()); // Profile bmi

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    /*public List<Profile> getAllProfiles() {
        List<Profile> profileList = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile();
                profile.setId(Integer.parseInt(cursor.getString(0)));
                profile.setUsername(cursor.getString(1));
                profile.setHeight(Integer.parseInt(cursor.getString(2)));
                profile.setWeight(Integer.parseInt(cursor.getString(3)));
                profile.setMsgBMI(cursor.getString(4));
                // Adding contact to list
                profileList.add(profile);
            } while (cursor.moveToNext());
        }

        // return profile list
        return profileList;
    }*/

}
