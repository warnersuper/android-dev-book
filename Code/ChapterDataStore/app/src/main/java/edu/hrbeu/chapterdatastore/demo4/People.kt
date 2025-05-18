package edu.hrbeu.chapterdatastore.demo4

data class People(
		var ID: Int = -1,
		var Name: String = "",
		var Age: Int = 0,
		var Height: Float = 0f
) {
	override fun toString(): String {
		return "ID：$ID，姓名：$Name，年龄：$Age，身高：$Height"
	}
}