package org.techtown.gilnyangbomnyang

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_c.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentC : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter:diaryItemAdapter
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_c, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById(R.id.listview)as RecyclerView

        // recyclerView.layoutManager = LinearLayoutManager(activity)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true)
        recyclerView.layoutManager = layoutManager

        adapter = diaryItemAdapter()
        recyclerView.adapter = adapter

        databaseRef = FirebaseDatabase.getInstance().reference

        addDiary.setOnClickListener{
            diary_write.visibility = VISIBLE
        }
        submitBtn.setOnClickListener{
            val date = date.text.toString()
//            val feed = feed.isChecked
//            val water = water.isChecked
//            val rescue = rescue.isChecked
            val diaryContent = diaryContent.text.toString()

            saveDiary(date, diaryContent)
            diary_write.visibility = INVISIBLE
        }
        cancelBtn.setOnClickListener {
            diary_write.visibility = INVISIBLE
        }
        databaseRef.orderByKey().limitToFirst(10).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("test","loadItem:onCancelled : ${error.toException()}")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                loadDiaryList(snapshot)
            }
        })

    }
    fun loadDiaryList(dataSnapshot: DataSnapshot){
        val collectionIterator = dataSnapshot!!.children.iterator()
        if(collectionIterator.hasNext()){
            adapter.items.clear()
            val diaries = collectionIterator.next()
            val itemsIterator = diaries.children.iterator()
            while (itemsIterator.hasNext()){
                val currentItem = itemsIterator.next()
                val map = currentItem.value as HashMap<String, Any>
                val objectId = map["objectID"].toString()
                val diary_date = map["diary_date"] as String
                val diary_content = map["diary_content"] as String

                adapter.items.add(diaryItem(objectId, diary_date, diary_content))
            }
            adapter.notifyDataSetChanged()
        }
    }
    fun saveDiary(date:String, diaryContent:String){
        val key : String? = databaseRef.child("diaries").push().getKey()
        val diary = diaryItem(key!!, date, diaryContent)
        val diaryValues : HashMap<String, Any> = diary.toMap()
        val childUpdates: MutableMap<String, Any> = HashMap()
        childUpdates["/diaries/$key"] = diaryValues
        databaseRef.updateChildren(childUpdates)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}