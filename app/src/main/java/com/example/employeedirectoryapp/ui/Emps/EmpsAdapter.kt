package com.example.employeedirectoryapp.ui.Emps

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.employeedirectoryapp.data.entities.Emp
import com.example.employeedirectoryapp.databinding.ItemEmpBinding

class EmpsAdapter(private val listener: EmpItemListener) : RecyclerView.Adapter<EmpViewHolder>() {

    interface EmpItemListener {
        fun onClickedEmp(empId: Int)
    }

    private val items = ArrayList<Emp>()

    fun setItems(items: ArrayList<Emp>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val binding: ItemEmpBinding = ItemEmpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmpViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) = holder.bind(items[position])
}

class EmpViewHolder(private val itemBinding: ItemEmpBinding, private val listener: EmpsAdapter.EmpItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var emp: Emp

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Emp) {
        this.emp = item
        itemBinding.name.text = item.name
        itemBinding.companyName.text = item.company.name
        Glide.with(itemBinding.root)
            .load(item.profile_image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedEmp(emp.id)
    }
}
