package edu.hrbeu.chapterdemo.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.hrbeu.chapterdemo.data.net.News
import kotlin.apply
import kotlin.collections.toTypedArray
import kotlin.let

class DBAdapter(context: Context) {

	private val dbHelper = DBOpenHelper(context)
	private var db: SQLiteDatabase? = null

	fun open() {
		db = dbHelper.writableDatabase
	}

	fun close() {
		db?.close()
	}

	fun insert(dbNews: DBNews): Long {
		val values = ContentValues().apply {
			put(DBOpenHelper.KEY_TITLE, dbNews.title)
			put(DBOpenHelper.KEY_LINK, dbNews.link)
			put(DBOpenHelper.KEY_DESCRIPTION, dbNews.description)
			put(DBOpenHelper.KEY_AUTHOR, dbNews.author)
			put(DBOpenHelper.KEY_PUBDATE, dbNews.pubDate)
			put(DBOpenHelper.KEY_HASHCODE, dbNews.hashCode)
		}
		return db?.insert(DBOpenHelper.DB_TABLE, null, values) ?: -1
	}

	fun queryAllData(): Array<DBNews>? {
		val cursor = db?.query(
			DBOpenHelper.DB_TABLE,
            arrayOf(
                DBOpenHelper.KEY_ID,
                DBOpenHelper.KEY_TITLE,
                DBOpenHelper.KEY_LINK,
                DBOpenHelper.KEY_DESCRIPTION,
				DBOpenHelper.KEY_AUTHOR,
				DBOpenHelper.KEY_PUBDATE,
				DBOpenHelper.KEY_HASHCODE,
            ),
			null, null, null, null, null
		)
		return cursor?.let { convertToNews(it) }
	}

	fun deleteAllData(): Long {
		return (db?.delete(DBOpenHelper.DB_TABLE, null, null) ?: -1).toLong()
	}


	private fun convertToNews(cursor: Cursor): Array<DBNews> {
		val result = mutableListOf<DBNews>()
		while (cursor.moveToNext()) {
			result.add(
				DBNews().apply {
					id = cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_ID))
					title = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_TITLE))
					link = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_LINK))
					description = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_DESCRIPTION))
					author = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_AUTHOR))
					pubDate = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_PUBDATE))
					hashCode = cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_HASHCODE))
				}
			)
		}
		cursor.close()
		return result.toTypedArray()
	}

	fun getCount(): Int {
		val db = dbHelper.readableDatabase
		val cursor = db.rawQuery("SELECT COUNT(*) FROM ${DBOpenHelper.DB_TABLE}", null)
		var count = 0
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0)
		}
		cursor.close()
		return count
	}

	fun exists(newsHashCode: Int): Boolean {
		val db = dbHelper.readableDatabase
		val cursor = db.rawQuery("SELECT 1 FROM ${DBOpenHelper.DB_TABLE} WHERE ${DBOpenHelper.KEY_HASHCODE} = ?", arrayOf(newsHashCode.toString()))
		val exists = cursor.moveToFirst()
		cursor.close()
		return exists
	}


	fun dropTable() {
		val db = dbHelper.writableDatabase
		db.execSQL("DROP TABLE IF EXISTS ${DBOpenHelper.DB_TABLE}")
	}

	class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

		companion object {
			private const val DB_NAME = "news.db"
			const val DB_TABLE = "newsinfo"
			private const val DB_VERSION = 1
			const val KEY_ID = "_id"
			const val KEY_TITLE = "title"
			const val KEY_LINK = "link"
			const val KEY_DESCRIPTION = "description"
			const val KEY_AUTHOR = "author"
			const val KEY_PUBDATE = "pubDate"
			const val KEY_HASHCODE = "hashCode"
			private const val DB_CREATE = "CREATE TABLE $DB_TABLE (" +
					"$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"$KEY_TITLE TEXT NOT NULL, " +
					"$KEY_LINK TEXT NOT NULL, " +
					"$KEY_DESCRIPTION TEXT NOT NULL, " +
					"$KEY_AUTHOR TEXT, " +
					"$KEY_PUBDATE TEXT NOT NULL, " +
					"$KEY_HASHCODE INTEGER)"
		}


		override fun onCreate(db: SQLiteDatabase) {
			db.execSQL(DB_CREATE)
		}

		override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
			db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
			onCreate(db)
		}
	}
}