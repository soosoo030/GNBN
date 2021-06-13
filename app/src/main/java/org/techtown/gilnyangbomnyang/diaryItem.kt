package org.techtown.gilnyangbomnyang

import com.google.firebase.database.Exclude

data class diaryItem(
    var objectId: String,
    var diary_date: String,
//    var feed: Boolean,
//    var water: Boolean,
//    var rescue: Boolean,
    var diary_content: String
    ){
    @Exclude
    fun toMap() : HashMap<String,Any>{
        val result: HashMap<String, Any> = HashMap()
        result["objectID"] = objectId
        result["diary_date"] = diary_date
//        result["feed"] = feed
//        result["water"] = water
//        result["rescue"] = rescue
        result["diary_content"] = diary_content

        return result
    }
}
