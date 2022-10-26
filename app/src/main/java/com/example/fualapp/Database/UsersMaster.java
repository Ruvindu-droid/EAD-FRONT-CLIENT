/**
 ============================================================================================================================
 HEADER COMMENT BLOCK FOR THIS SPECIFIC .JAVA FILE

 App:- SMART FUEL APP
 For:- EAD Module Related Development - Sri Lankan Institute of Information Technology

 *** This Special .java file is for :-
 SQL Lite Data base Structre creation for both the users with passwords, user names and types,
 this class may use in my other pages for entering data manipulation with sql lite.

 Author(s): Ruvindu Kaushalya(Leader)

 ============================================================================================================================
 **/

package com.example.fualapp.Database;

import android.provider.BaseColumns;


public final class  UsersMaster {

    private UsersMaster() {
    }

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_TYPE = "type";
    }

} //Class Creation


