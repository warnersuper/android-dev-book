package edu.hrbeu.chapterdatastore.demo5

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

class PeopleProvider : ContentProvider() {

	companion object {
		private const val MULTIPLE_PEOPLE = 1
		private const val SINGLE_PEOPLE = 2
		private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
			addURI(PeopleContract.AUTHORITY, PeopleContract.PATH_MULTIPLE, MULTIPLE_PEOPLE)
			addURI(PeopleContract.AUTHORITY, PeopleContract.PATH_SINGLE, SINGLE_PEOPLE)
		}
	}

	// 模拟数据存储（实际开发中应使用 SQLite 数据库）
	private val peopleData = mutableListOf<ContentValues>()

	override fun onCreate(): Boolean {
		// 初始化 ContentProvider
		return true
	}

	override fun insert(uri: Uri, values: ContentValues?): Uri? {
		val match = uriMatcher.match(uri)
		if (match != MULTIPLE_PEOPLE) {
			throw IllegalArgumentException("Unknown URI: $uri")
		}
		values?.let {
			peopleData.add(it)
			val id = peopleData.size // 使用列表大小作为 ID
			return Uri.withAppendedPath(PeopleContract.CONTENT_URI, "$id")
		}

		return null
	}

	override fun query(
		uri: Uri,
		projection: Array<out String>?,
		selection: String?,
		selectionArgs: Array<out String>?,
		sortOrder: String?
	): Cursor? {
		val match = uriMatcher.match(uri)
		if (match != MULTIPLE_PEOPLE) {
			throw IllegalArgumentException("Unknown URI: $uri")
		}
		// 构建返回的 Cursor
		val cursor = MatrixCursor(arrayOf(
			PeopleContract.KEY_ID,
			PeopleContract.KEY_NAME,
			PeopleContract.KEY_AGE,
			PeopleContract.KEY_HEIGHT
		))
		for ((index, person) in peopleData.withIndex()) {
			cursor.addRow(arrayOf(
				index + 1, // ID
				person.getAsString(PeopleContract.KEY_NAME),
				person.getAsInteger(PeopleContract.KEY_AGE),
				person.getAsFloat(PeopleContract.KEY_HEIGHT)
			))
		}
		return cursor
	}

//	override fun update(
//		uri: Uri,
//		values: ContentValues?,
//		selection: String?,
//		selectionArgs: Array<out String>?
//	): Int {
//		throw UnsupportedOperationException("Update not supported")
//	}

	override fun update(
		uri: Uri,
		values: ContentValues?,
		selection: String?,
		selectionArgs: Array<out String>?
	): Int {
		val match = uriMatcher.match(uri)
		if (match != SINGLE_PEOPLE) {
			throw IllegalArgumentException("Unknown URI: $uri")
		}

		// 从 URI 中提取 ID
		val idStr = uri.lastPathSegment ?: return 0
		val id = idStr.toIntOrNull() ?: return 0

		// peopleData 索引是从 0 开始，而 ID 是从 1 开始
		val index = id - 1
		if (index < 0 || index >= peopleData.size) {
			return 0 // 找不到记录
		}

		values?.let {
			val existing = peopleData[index]
			// 更新各字段（如果存在新值就更新）
			if (it.containsKey(PeopleContract.KEY_NAME)) {
				existing.put(PeopleContract.KEY_NAME, it.getAsString(PeopleContract.KEY_NAME))
			}
			if (it.containsKey(PeopleContract.KEY_AGE)) {
				existing.put(PeopleContract.KEY_AGE, it.getAsInteger(PeopleContract.KEY_AGE))
			}
			if (it.containsKey(PeopleContract.KEY_HEIGHT)) {
				existing.put(PeopleContract.KEY_HEIGHT, it.getAsFloat(PeopleContract.KEY_HEIGHT))
			}
			return 1 // 成功更新 1 条记录
		}

		return 0 // 没有更新
	}


	override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
		val match = uriMatcher.match(uri)
		if (match != MULTIPLE_PEOPLE) {
			throw IllegalArgumentException("Unknown URI: $uri")
		}
		// 实现删除逻辑
		val count = peopleData.size // 获取当前数据量
		peopleData.clear() // 清空数据
		return count // 返回删除的记录数
	}

	override fun getType(uri: Uri): String? {
		return when {
			uri.toString().endsWith(PeopleContract.PATH_MULTIPLE) -> PeopleContract.MINE_TYPE_MULTIPLE
			uri.toString().endsWith(PeopleContract.PATH_SINGLE) -> PeopleContract.MINE_TYPE_SINGLE
			else -> throw IllegalArgumentException("Unknown URI: $uri")
		}
	}
}