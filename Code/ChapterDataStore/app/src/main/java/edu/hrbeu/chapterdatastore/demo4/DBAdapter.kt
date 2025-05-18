package edu.hrbeu.chapterdatastore.demo4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBAdapter(context: Context) {

	private val dbHelper = DBOpenHelper(context)
	private var db: SQLiteDatabase? = null

	fun open() {
		db = dbHelper.writableDatabase
	}

	fun close() {
		db?.close()
	}

	fun insert(people: People): Long {
		val values = ContentValues().apply {
			put(DBOpenHelper.KEY_NAME, people.Name)
			put(DBOpenHelper.KEY_AGE, people.Age)
			put(DBOpenHelper.KEY_HEIGHT, people.Height)
		}
		return db?.insert(DBOpenHelper.DB_TABLE, null, values) ?: -1
	}

	fun queryAllData(): Array<People>? {
		val cursor = db?.query(
			DBOpenHelper.DB_TABLE,
			arrayOf(DBOpenHelper.KEY_ID, DBOpenHelper.KEY_NAME, DBOpenHelper.KEY_AGE, DBOpenHelper.KEY_HEIGHT),
			null, null, null, null, null
		)
		return cursor?.let { convertToPeople(it) }
	}

	fun deleteAllData(): Long {
		return (db?.delete(DBOpenHelper.DB_TABLE, null, null) ?: -1).toLong()
	}

	private fun convertToPeople(cursor: Cursor): Array<People> {
		val result = mutableListOf<People>()
		while (cursor.moveToNext()) {
			result.add(
				People().apply {
					ID = cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_ID))
					Name = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_NAME))
					Age = cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_AGE))
					Height = cursor.getFloat(cursor.getColumnIndexOrThrow(DBOpenHelper.KEY_HEIGHT))
				}
			)
		}
		cursor.close()
		return result.toTypedArray()
	}

	class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

		companion object {
			private const val DB_NAME = "people.db"
			const val DB_TABLE = "peopleinfo"
			private const val DB_VERSION = 1
			const val KEY_ID = "_id"
			const val KEY_NAME = "name"
			const val KEY_AGE = "age"
			const val KEY_HEIGHT = "height"
			private const val DB_CREATE = "CREATE TABLE $DB_TABLE (" +
					"$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"$KEY_NAME TEXT NOT NULL, " +
					"$KEY_AGE INTEGER, " +
					"$KEY_HEIGHT FLOAT)"
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