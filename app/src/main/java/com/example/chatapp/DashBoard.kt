package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.chatapp.databinding.ActivityDashBoardBinding
import com.example.chatapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashBoard : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbRef :DatabaseReference
    private lateinit var adapter: UserAdapter
    private lateinit var userList:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mDbRef=FirebaseDatabase.getInstance().getReference()

        mAuth = FirebaseAuth.getInstance()

          userList= ArrayList()
        adapter= UserAdapter(this,userList)
        binding.recyUser.adapter=adapter

//       val  userList= ArrayList<User>()
//           binding.recyUser.adapter=UserAdapter(this, userList)

        mDbRef.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //previews list clear
                userList.clear()
             for (postSnapshot in snapshot.children){
                 val currentUser =postSnapshot.getValue(User::class.java)
                 userList.add(currentUser!!)
             }
           //     adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            mAuth.signOut()
            val intent =Intent(this@DashBoard,LoginFragment::class.java)
            finish()
            startActivity(intent)

            return true
        }
        return true
    }
}