package com.example.offlinebudgettracker.util;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.offlinebudgettracker.data.BudgetTrackerDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BudgetTrackerRoomDatabase extends RoomDatabase {

    public abstract BudgetTrackerDao budgetTrackerDao();
    public static final int NUMBER_OF_THREADS = 4;

    private static volatile BudgetTrackerRoomDatabase INSTANCE;
    public static final ExecutorService dataWritableExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BudgetTrackerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BudgetTrackerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BudgetTrackerRoomDatabase.class, "budget_tracker_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
