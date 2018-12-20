package quizgm.c443.umb.edu.quizmania;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuizeHelper extends SQLiteOpenHelper {

    private Context ctx;
    private static final String Database_name = "TQuiz.db";
    private static final String Val1 = "OPTA";

    private static final String Val2 = "OPTB";

    private static final String Val3 = "OPTC";

    private static final String Val4 = "OPTD";

    private static final int DB_ver = 3;

    private static final String Question = "QUESTION";
    private static final String TABLE_NAME = "TQ";

    private static final String MID = "_UID";

    private static final String ANS = "ANSWER";
    //Creat DB for question
    private static final String Create_tbl = "CREATE TABLE " + TABLE_NAME + " ( " + MID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Question + " VARCHAR(255), " + Val1 + " VARCHAR(255), " + Val2 + " VARCHAR(255), " + Val3 + " VARCHAR(255), " + Val4 + " VARCHAR(255), " + ANS + " VARCHAR(255));";
    //Drop table query
    private static final String Delete_table = "DROP TABLE IF EXISTS " + TABLE_NAME;


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(Delete_table);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table
        sqLiteDatabase.execSQL(Create_tbl);
    }
    QuizeHelper(Context context)
    {
        super(context, Database_name, null, DB_ver);
        this.ctx = context;
    }

    public void refreshDb(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Delete_table);
        onCreate(sqLiteDatabase);
    }

    void SetQuestions() {
        ArrayList<QuizQuestion> arraylist = new ArrayList<>();

        arraylist.add(new QuizQuestion("What command would you use to keep a record of every command you type and the output of those commands ?", "tail", "Control A", "Control E", "script", "script"));

        arraylist.add(new QuizQuestion("What is the text editor used by most system administrators ?", "gedit", "nano", "vi", "emacs", "vi"));

        arraylist.add(new QuizQuestion("How do you you abort a running program ?", "Control A", "Control C", "hit q", "hit Esc", "Control C"));

        arraylist.add(new QuizQuestion("How do you get the next screen while using 'man' command ?", "hit n", "hit down arrow", "scroll using mouse", "hit the Space bar", "hit the Space bar"));

        arraylist.add(new QuizQuestion("What would you type at the command line to see what directory you are currently in ?", "cwd", "pmd", "pwd", "wmd", "pwd"));

        arraylist.add(new QuizQuestion("What would you type at the command line to see all the files in the directory named '/home/anubhaw'", "ls  /home/anubhaw", "ls -a /home/anubhaw", "ps /home/anubhaw", "ls *", "ls -a /home/anubhaw"));

        arraylist.add(new QuizQuestion("What would you type at the command line to move the file named 'memo.txt' into the directory named 'work'? ?", "mv  memo.txt  work", "mv  work  memo.txt", "cp  work  memo.txt", "cp -r work  memo.txt", "mv  memo.txt  work"));

        arraylist.add(new QuizQuestion("Which of these are NOT a whitespace character ?", "Backslash", "Space", "Tab", "Newline", "Backslash"));

        arraylist.add(new QuizQuestion("What is the name of the command you would use to change the access permissions for a file or directory ?", "xmod", "chaccess", "getaccess", "chmod", "chmod"));

        arraylist.add(new QuizQuestion("Which of these is not a access permission ?", "read", "write", "execute", "perform", "perform"));

        arraylist.add(new QuizQuestion("What is a process ?", "Shell script", "A running program", "function", "Control structure", "A running program"));

        arraylist.add(new QuizQuestion("What does a program do just before it stops running ?", "sends an exit status to the shell", "call another program", "goes to sleep", "wakes up", "sends an exit status to the shell"));

        arraylist.add(new QuizQuestion("What does an exit status 0 mean ?", "program encountered one error", "no error encountered", "number of argument was invalid", "program ended abruptly", "no error encountered"));

        arraylist.add(new QuizQuestion("What does the continue command in a loop do?", "Stop a loop", "Change value of loop variable", "Skip loop iteration", "Continue the loop iteration", "Skip loop iteration"));

        arraylist.add(new QuizQuestion("if statement ends with ?", "}(right curly brace)", "fi", "semi-colon", "else", "fi"));

        arraylist.add(new QuizQuestion("What pattern would you use in a case statement if you wanted to match 11, 12 or 13 ?", "11|12|13", "11&&12&&13", "11||12||13", "[11,12,13]", "11|12|13"));

        arraylist.add(new QuizQuestion("select statement in Unix is used for ?", "Printing a menu options", "Checking a condition", "Assigning a value", "Printing a value", "Printing a menu options"));

        arraylist.add(new QuizQuestion("How do you mark the end of a code block in a case statement ?", ";", "}", ";;", "$$", ";;"));

        arraylist.add(new QuizQuestion("What would you write at the command line to define the alias ll whose value was ls -l ?", "set ll='ls -l'", "export ll=ls -l", "alias ll=ls -l", "alias ll='ls -l'", "alias ll='ls -l'"));

        arraylist.add(new QuizQuestion("What would you type at the command line to create the global variable bar and assign it the value 'BAR' ?", "bar=BAR", "export bar=BAR", "assign bar='export BAR'", "alias bar=BAR", "export bar=BAR"));

        this.Addquest(arraylist);

    }


    private void Addquest(ArrayList<QuizQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME,null,null);
            ContentValues valz = new ContentValues();
            for (QuizQuestion question : allQuestions) {
                valz.put(Question, question.getQuestion());
                valz.put(Val2, question.getVal2());
                valz.put(Val3, question.getVal3());
                valz.put(Val4, question.getVal4());
                valz.put(Val1, question.getVal1());

                valz.put(ANS, question.getAnswer());
                db.insert(TABLE_NAME, null, valz);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<QuizQuestion> getQuestions() {

        List<QuizQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {MID, Question, Val1, Val2, Val3, Val4, ANS};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            QuizQuestion qtn = new QuizQuestion();
            qtn.setId(cursor.getInt(0));
            qtn.setQuestion(cursor.getString(1));
            qtn.setOptA(cursor.getString(2));

            qtn.setOptD(cursor.getString(5));
            qtn.setOptB(cursor.getString(3));
            qtn.setOptC(cursor.getString(4));
            qtn.setAnswer(cursor.getString(6));
            questionsList.add(qtn);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
