package com.shokal.roomdatabesewithfragment.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shokal.roomdatabesewithfragment.R
import com.shokal.roomdatabesewithfragment.fragments.UserListFragmentDirections
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class UserLIstAdapter(
    private val context: Context,
    private val viewModel: UserViewModel,
    private val arrayList: ArrayList<User>
): RecyclerView.Adapter<UserLIstAdapter.UserViewHolder>() {

    class UserViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val name: TextView = binding.findViewById(R.id.name)
        val email: TextView = binding.findViewById(R.id.email)
        val age : TextView = binding.findViewById(R.id.age)
        val deleteButton: ImageButton = binding.findViewById(R.id.delete)
        val updateButton : ImageButton = binding.findViewById(R.id.update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = arrayList[position]
        holder.name.text = user.name
        holder.email.text = user.email
        holder.age.text = user.age.toString()
        holder.itemView.imageView1.load(user.profilePhoto)

        holder.deleteButton.setOnClickListener {
            viewModel.deleteUser(user)
            notifyItemRemoved(arrayList.indexOf(user))
        }

        if (arrayList.size <= 0){
            holder.itemView.recycler.visibility = View.INVISIBLE
            holder.itemView.empty_view.visibility = View.VISIBLE
        }

        holder.updateButton.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToUpdateFragment(user)
            Navigation.findNavController(holder.updateButton).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "Blog list is empty", Toast.LENGTH_SHORT).show()
        }
        return arrayList.size
    }
}