package pointage.itm.maxime.pointageapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="userPointage.db";
    public static final String TABLE_NAME = "POINT";
    public static final String COL_1 = "id";
    public static final String COL_2 = "userID";
    public static final String COL_3 = "hours";
    static int DB_version=1;

    public LocalSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFilTable = "CREATE TABLE POINT("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_2+" TEXT not null, "+COL_3+" TEXT not null);";
        db.execSQL(sqlFilTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion;i<newVersion;i++) {
            int versionToUpdate = i+1;
            if(versionToUpdate==2){
                //code pour mettre la base en version 2
            }
            else if(versionToUpdate==3){
                //code pour mettre la base en version 3
            }
            //[...]
        }
    }

    public void insertUser(User user) {
        String strSql = "insert into POINT(userID, hours) values ('"
                + user.getID() + "','"
                + user.getHeure() + "')";
        this.getWritableDatabase().execSQL(strSql);
    }


    public void deleteTable()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String strSQL = "DROP TABLE Pointage";
        database.execSQL(strSQL);
    }


    public Cursor getListContents()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM POINT",null);
    }


}
