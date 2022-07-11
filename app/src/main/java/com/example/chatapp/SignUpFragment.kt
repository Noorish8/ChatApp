package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mObRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_up, container, false)
        binding = FragmentSignUpBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val paaWord = binding.etPassword.text.toString()
            signUp(name,email, paaWord)
        }
    }
    private fun signUp(name:String,email:String,passWord:String){
        mAuth.createUserWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
                   val intent= Intent(requireContext(),DashBoard::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(requireActivity(), "some error occurred",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun addUserToDatabase(name:String,email:String,uid:String){
        mObRef=FirebaseDatabase.getInstance().getReference()
        mObRef.child("user").child(uid).setValue(User(name,email,uid))
    }



}