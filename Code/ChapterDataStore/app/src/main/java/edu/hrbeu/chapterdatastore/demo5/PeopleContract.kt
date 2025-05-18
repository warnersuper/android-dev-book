package edu.hrbeu.chapterdatastore.demo5

import android.net.Uri

object PeopleContract {
	// MIME 类型前缀
	const val MIME_DIR_PREFIX = "vnd.android.cursor.dir"
	const val MIME_ITEM_PREFIX = "vnd.android.cursor.item"
	const val MINE_ITEM = "vnd.hrbeu.people"

	// MIME 类型定义
	const val MINE_TYPE_SINGLE = "$MIME_ITEM_PREFIX/$MINE_ITEM"
	const val MINE_TYPE_MULTIPLE = "$MIME_DIR_PREFIX/$MINE_ITEM"

	// ContentProvider 的 Authority 和路径
	const val AUTHORITY = "edu.hrbeu.peopleprovider"
	const val PATH_SINGLE = "people/#"
	const val PATH_MULTIPLE = "people"
	const val CONTENT_URI_STRING = "content://$AUTHORITY/$PATH_MULTIPLE"
	val CONTENT_URI: Uri = Uri.parse(CONTENT_URI_STRING)

	// 数据库字段名
	const val KEY_ID = "_id"
	const val KEY_NAME = "name"
	const val KEY_AGE = "age"
	const val KEY_HEIGHT = "height"
}