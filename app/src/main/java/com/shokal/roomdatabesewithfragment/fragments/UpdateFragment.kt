package com.shokal.roomdatabesewithfragment.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shokal.roomdatabesewithfragment.R
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*
import kotlinx.android.synthetic.main.fragment_add_user.ageEditText
import kotlinx.android.synthetic.main.fragment_add_user.emailEditText
import kotlinx.android.synthetic.main.fragment_add_user.imageEditText
import kotlinx.android.synthetic.main.fragment_add_user.nameEditText
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {
    val args: UpdateFragmentArgs by navArgs()
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText.setText(args.user.name)
        emailEditText.setText(args.user.email)
        ageEditText.setText(args.user.age.toString())
        imageEditText.setText(args.user.profilePhoto)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        updateUserButton.setOnClickListener {
            val id = args.user.id
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val age = ageEditText.text.toString()
            val image = imageEditText.text.toString()

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(image)) {
                val user = User(id, name, email, age.toInt(), image)
                viewModel.updateUser(user)

            }
            nameEditText.text?.clear()
            emailEditText.text?.clear()
            ageEditText.text?.clear()

            findNavController().navigate(R.id.action_updateFragment_to_userListFragment)
        }
    }
}