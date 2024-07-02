package com.example.billingproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("create table " + BillingDbSchema.BillingTable.NAME + "(" +
                BillingDbSchema.BillingTable.Cols.CLIENT_ID + ", " +
                BillingDbSchema.BillingTable.Cols.CLIENT_NAME + ", " +
                BillingDbSchema.BillingTable.Cols.PRODUCT_NAME + ", " +
                BillingDbSchema.BillingTable.Cols.PRD_PRICE + ", " +
                BillingDbSchema.BillingTable.Cols.PRD_QTY + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues (Billing billing) {
        ContentValues values = new ContentValues();

        values.put(BillingDbSchema.BillingTable.Cols.CLIENT_ID, billing.getClient_ID());
        values.put(BillingDbSchema.BillingTable.Cols.CLIENT_NAME, billing.getClient_Name());
        values.put(BillingDbSchema.BillingTable.Cols.PRODUCT_NAME, billing.getProduct_Name());
        values.put(BillingDbSchema.BillingTable.Cols.PRD_PRICE, billing.getPrd_Price());
        values.put(BillingDbSchema.BillingTable.Cols.PRD_QTY, billing.getPrd_Qty());

        return values;
    }

    public void createBilling(Billing billing) {
        //input code here
    }

    public void updateBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        String client_idString = billing.getClient_ID()+"";

        ContentValues values = getContentValues(billing);

        db.update(BillingDbSchema.BillingTable.NAME,
                values,
                BillingDbSchema.BillingTable.Cols.CLIENT_ID + "=?",
                new String[]{client_idString});
    }

    public void deleteBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        String client_idString = billing.getClient_ID()+"";

        db.delete(BillingDbSchema.BillingTable.NAME,
                BillingDbSchema.BillingTable.Cols.CLIENT_ID + "=?",
                new String[]{client_idString});
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
                    returnBilling.setClient_ID(cursorBilling.getInt(0));
                    returnBilling.setClient_Name(cursorBilling.getString(1));
                    returnBilling.setProduct_Name(cursorBilling.getString(2));
                    returnBilling.setPrd_Price(cursorBilling.getDouble(3));
                    returnBilling.setPrd_Qty(cursorBilling.getInt(4));
                } while (cursorBilling.moveToNext());
            }
        }
        else {
            returnBilling.setClient_ID(client_id);
            returnBilling.setClient_Name("Not found in database");
        }

        cursorBilling.close();

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

        return billingModalArrayList;
    }

}
