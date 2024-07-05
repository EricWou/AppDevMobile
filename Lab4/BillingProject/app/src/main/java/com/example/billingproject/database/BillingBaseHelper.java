package com.example.billingproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.billingproject.model.Billing;

import java.util.ArrayList;

public class BillingBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "billingBase.db";

    public BillingBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //need to specify the data types in order for update and delete to work properly?
        db.execSQL("create table " + BillingDbSchema.BillingTable.NAME + " (" +
                BillingDbSchema.BillingTable.Cols.CLIENT_ID + " INTEGER PRIMARY KEY, " +
                BillingDbSchema.BillingTable.Cols.CLIENT_NAME + " TEXT, " +
                BillingDbSchema.BillingTable.Cols.PRODUCT_NAME + " TEXT, " +
                BillingDbSchema.BillingTable.Cols.PRD_PRICE + " REAL, " +
                BillingDbSchema.BillingTable.Cols.PRD_QTY + " INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues (Billing billing) {
        ContentValues values = new ContentValues();

        values.put(BillingDbSchema.BillingTable.Cols.CLIENT_ID, billing.getClient_id());
        values.put(BillingDbSchema.BillingTable.Cols.CLIENT_NAME, billing.getClient_name());
        values.put(BillingDbSchema.BillingTable.Cols.PRODUCT_NAME, billing.getProduct_name());
        values.put(BillingDbSchema.BillingTable.Cols.PRD_PRICE, billing.getPrd_price());
        values.put(BillingDbSchema.BillingTable.Cols.PRD_QTY, billing.getPrd_qty());

        return values;
    }

    public void createBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(billing);

        db.insert(BillingDbSchema.BillingTable.NAME, null, values);

        //db.close();
    }

    public void updateBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        String client_idString = String.valueOf(billing.getClient_id());

        ContentValues values = getContentValues(billing);

        Log.d("contentValues", values.toString());
        Log.d("updateBilling(2)", billing.toString());

        try {
            int rows = db.update(BillingDbSchema.BillingTable.NAME,
                    values,
                    BillingDbSchema.BillingTable.Cols.CLIENT_ID + "=?",
                    new String[]{client_idString});
            Log.d("update success", "rows: "+rows);
        } catch (SQLException e) {
            Log.e("update failed", "Error updating billing", e);
        }

        //db.close();
    }

    public void deleteBilling(int client_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String client_idString = client_id+"";

        db.delete(BillingDbSchema.BillingTable.NAME,
                BillingDbSchema.BillingTable.Cols.CLIENT_ID + "=?",
                new String[]{client_idString});

        //db.close();
    }

    public Billing searchBilling(int client_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Billing returnBilling = new Billing();

        String client_idString = client_id+"";

        Cursor cursorBilling = db.rawQuery("select * from "+BillingDbSchema.BillingTable.NAME+
                                                " where "+BillingDbSchema.BillingTable.Cols.CLIENT_ID+
                                                " =\""+client_idString+"\"", null);

        if (cursorBilling!=null) {
            if (cursorBilling.moveToFirst()) {
                do {
                    returnBilling.setClient_id(cursorBilling.getInt(0));
                    returnBilling.setClient_name(cursorBilling.getString(1));
                    returnBilling.setProduct_name(cursorBilling.getString(2));
                    returnBilling.setPrd_price(cursorBilling.getDouble(3));
                    returnBilling.setPrd_qty(cursorBilling.getInt(4));
                } while (cursorBilling.moveToNext());
            }
        }
        else {
            returnBilling.setClient_id(client_id);
            returnBilling.setClient_name("Not found in database");
        }

        cursorBilling.close();
        //db.close();

        return returnBilling;
    }

    public ArrayList<Billing> readBillings() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Billing> billingModalArrayList = new ArrayList<>();

        Cursor cursorBilling = db.rawQuery("select * from "+BillingDbSchema.BillingTable.NAME, null);

        if (cursorBilling.moveToFirst()) {
            do {
                billingModalArrayList.add(new Billing(cursorBilling.getInt(0),
                                                    cursorBilling.getString(1),
                                                    cursorBilling.getString(2),
                                                    cursorBilling.getDouble(3),
                                                    cursorBilling.getInt(4)));
            } while (cursorBilling.moveToNext());
        }

        cursorBilling.close();
        //db.close();

        return billingModalArrayList;
    }

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table "+ BillingDbSchema.BillingTable.NAME);
    }

}
