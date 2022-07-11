package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chatapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var mAuth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater)

        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {
            (activity as MainActivity).showFragment(SignUpFragment())
        }
        binding.btnLogin.setOnClickListener {
            val email =binding.etEmail.text.toString()
            val paaWord =binding.etPassword.text.toString()

            login(email,paaWord)
        }
    }
    private fun login(email:String,passWord:String){

        mAuth.signInWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val intent =Intent(requireContext(),DashBoard::class.java)
                    startActivity(intent)
                    // Sign in success, update UI with the signed-in user's information

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(context, "user does not exist ",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }
}