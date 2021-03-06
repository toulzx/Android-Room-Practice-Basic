package com.toulzx.roompractice;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private LiveData<List<Word>> allWordsLive;

    private WordDao wordDao;


    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }


    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }


    // interface

    public void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }
    public void updateWords(Word... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }
    public void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }
    public void deleteAllWords() {
        new DeleteAllAsyncTask(wordDao).execute();
    }




    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         *  将`插入`操作在后台运行
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         *  将`更新`操作在后台运行
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         *  将`删除`操作在后台运行
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         *  将`删除全部`操作在后台运行
         */
        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }

}
