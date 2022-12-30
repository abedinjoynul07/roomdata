package com.shokal.roomdatabesewithfragment.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shokal.roomdatabesewithfragment.R
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*

class AddUserFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        addUserButton.setOnClickListener {
            addData()
        }
    }


    private fun addData() {
        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val age = ageEditText.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(age)) {
            val user = User(null, name, email, age.toInt())
            viewModel.addUser(user)
        }

        nameEditText.text?.clear()
        emailEditText.text?.clear()
        ageEditText.text?.clear()

        findNavController().navigate(R.id.action_addUserFragment_to_userListFragment)
    }
}