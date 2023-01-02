package com.shokal.roomdatabesewithfragment.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shokal.roomdatabesewithfragment.R
import com.shokal.roomdatabesewithfragment.adapters.UserLIstAdapter
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.list_item.*

class UserListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recycler
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initializeAdapter()

        addUserViewButton.setOnClickListener {
            findNavController().navigate(R.id.action_userListFragment_to_addUserFragment)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.deleteAllUser()
                initializeAdapter()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun initializeAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        observeData()
    }

    private fun observeData() {
        viewModel.userList.observe(viewLifecycleOwner) {
            recyclerView.adapter = UserLIstAdapter(requireContext(), viewModel,
                it as ArrayList<User>
            )
        }
    }

}