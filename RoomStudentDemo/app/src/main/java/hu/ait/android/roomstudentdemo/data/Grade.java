package hu.ait.android.roomstudentdemo.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private int gradeId;

    @ColumnInfo(name = "studentid")
    private String studentId;

    @ColumnInfo(name = "grade")
    private String grade;

    public Grade(String studentId, String grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
