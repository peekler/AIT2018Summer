package hu.ait.android.roomstudentdemo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GradeDAO {

    @Query("SELECT * FROM grade WHERE grade=\"B\"")
    List<Grade> getBGrades();

    @Query("SELECT * FROM grade WHERE grade=:grade")
    List<Grade> getSpecificGrades(String grade);

    @Query("SELECT * FROM grade")
    List<Grade> getAllGrades();

    @Insert
    void insertGrades(Grade... grades);

    @Delete
    void deleteGrade(Grade grade);


}
