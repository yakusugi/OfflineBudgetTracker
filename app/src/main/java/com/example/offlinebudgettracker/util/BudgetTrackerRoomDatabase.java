package com.example.offlinebudgettracker.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.offlinebudgettracker.data.BudgetTrackerDao;
import com.example.offlinebudgettracker.model.BudgetTrackerDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BudgetTrackerDto.class}, version = 1, exportSchema = false)
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
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallBack =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dataWritableExecutor.execute(() -> {
                        BudgetTrackerDao budgetTrackerDao = INSTANCE.budgetTrackerDao();
                        budgetTrackerDao.deleteAll();

                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                        BudgetTrackerDto budgetTrackerDto = new BudgetTrackerDto("2020/01/01", "DMM", "1000 points", "App", 1000);
                        budgetTrackerDao.insert(budgetTrackerDto);

                        budgetTrackerDto = new BudgetTrackerDto("2020/01/01", "DMM", "2000 points", "App", 2000);
                        budgetTrackerDao.insert(budgetTrackerDto);
                    });
                }
            };

}
