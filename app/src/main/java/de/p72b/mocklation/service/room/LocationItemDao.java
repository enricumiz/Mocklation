package de.p72b.mocklation.service.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface LocationItemDao {
    @Query("SELECT * FROM locations")
    Maybe<List<LocationItem>> getAll();

    @Query("SELECT * FROM locations WHERE mode='FIXED'")
    Maybe<List<LocationItem>> getAllFixed();

    @Query("SELECT * FROM locations WHERE mode='ROUTE'")
    Maybe<List<LocationItem>> getAllRoute();

    @Query("SELECT * FROM locations WHERE code IN (:locationItemIds)")
    Flowable<List<LocationItem>> loadAllByIds(int[] locationItemIds);

    @Query("SELECT * FROM locations where code = :code")
    Single<LocationItem> findByCode(String code);

    @Query("SELECT * FROM locations where displayed_name = :displayedName")
    Maybe<List<LocationItem>> findByDisplayedName(String displayedName);


    @Insert
    void insertAll(LocationItem... locationItems);

    @Delete
    void delete(LocationItem locationItem);

    @Update
    void updateLocationItems(LocationItem... locationItems);
}
