package com.example.employeedirectoryapp.ui.EmpDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.employeedirectoryapp.data.entities.Emp
import com.example.employeedirectoryapp.databinding.EmpDetailFragmentBinding
import com.example.employeedirectoryapp.utils.Resource
import com.example.employeedirectoryapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmpDetailFragment : Fragment() {

    private var binding: EmpDetailFragmentBinding by autoCleared()
    private val viewModel: EmpDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmpDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.emp.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindEmp(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.empCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.empCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindEmp(emp: Emp) {
        binding.name.text = emp.name
        binding.userName.text = emp.username
        binding.email.text = emp.email
        binding.address.text = """${emp.address.street} - ${emp.address.city} - ${emp.address.zipcode}"""
        binding.phone.text = emp.phone
        binding.website.text = emp.website
        binding.company.text = """${emp.company.name} - ${emp.company.catchPhrase} - ${emp.company.bs}"""
        Glide.with(binding.root)
            .load(emp.profile_image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}