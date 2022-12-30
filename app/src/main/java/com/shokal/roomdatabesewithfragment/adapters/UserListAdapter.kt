package com.shokal.roomdatabesewithfragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shokal.roomdatabesewithfragment.R
import com.shokal.roomdatabesewithfragment.models.User
import com.shokal.roomdatabesewithfragment.viewModels.UserViewModel

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

        holder.deleteButton.setOnClickListener {
            viewModel.deleteUser(user)
            notifyItemRemoved(arrayList.indexOf(user))
        }

    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "Blog list is empty", Toast.LENGTH_SHORT).show()
        }
        return arrayList.size
    }
}