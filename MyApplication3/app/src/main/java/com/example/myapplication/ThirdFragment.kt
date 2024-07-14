package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ThirdFragmentBinding
import com.example.myapplication.others.TransactionStatus

class ThirdFragment: Fragment() {
    private var _binding : ThirdFragmentBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ThirdFragmentBinding.inflate(inflater, container,false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get transaction status from arguments
        val status = arguments?.getSerializable("transactionStatus") as TransactionStatus

        // Display transaction status based on sealed class
        when (status) {
            is TransactionStatus.Completed -> binding.tvTransactionStatus.text = "Transaction: Completed"
            is TransactionStatus.Cancelled -> binding.tvTransactionStatus.text = "Transaction:  Cancelled"
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}