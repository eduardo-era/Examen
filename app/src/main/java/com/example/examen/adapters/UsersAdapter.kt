package com.example.examen.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.examen.R
import com.example.examen.pojos.UserList
import com.example.examen.utils.GeneralUtilities
import com.example.examen.views.UserDetailsActivity

class UsersAdapter(val activity: Activity, var users: ArrayList<UserList>):RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oneUser = users[position]
        if (GeneralUtilities.isNetworkAvaliable(activity)){
            holder.userName.text = String.format(oneUser.name?.title + " " + oneUser.name?.first + " " + oneUser.name?.last)
        }else{
            holder.userName.text = oneUser.fullName
        }
        holder.userMail.text = oneUser.email
        Glide.with(activity).load(oneUser.picture?.large)
            .centerCrop()
            .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageUser)

        holder.itemUser.setOnClickListener {
            showMore(oneUser)
        }
    }

    private fun showMore(oneUser: UserList){
        val i = Intent(activity, UserDetailsActivity::class.java)
        i.putExtra("mail", oneUser.email)
        i.putExtra("phone",oneUser.phone)
        i.putExtra("cel",oneUser.cell)
        i.putExtra("image", oneUser.picture?.large)

        if (GeneralUtilities.isNetworkAvaliable(activity)){
            i.putExtra("name", String.format(oneUser.name?.title + " " + oneUser.name?.first + " " + oneUser.name?.last))
            i.putExtra("lat", oneUser.location?.coordinates?.latitude)
            i.putExtra("lang", oneUser.location?.coordinates?.longitude)
            i.putExtra("city", oneUser.location?.city)
            i.putExtra("state", oneUser.location?.state)
            i.putExtra("country", oneUser.location?.country)
        }else{
            i.putExtra("name", oneUser.fullName)
            i.putExtra("lat", oneUser.latitude)
            i.putExtra("lang", oneUser.longitude)
            i.putExtra("city", oneUser.city)
            i.putExtra("state", oneUser.state)
            i.putExtra("country", oneUser.country)
        }

        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        activity.startActivity(i, options.toBundle())
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val userName: TextView = itemView.findViewById(R.id.user_name)
        var userMail: TextView = itemView.findViewById(R.id.mail_user)
        val imageUser: ImageView = itemView.findViewById(R.id.image_user_profile)
        var itemUser: RelativeLayout = itemView.findViewById(R.id.item_user)
    }
}