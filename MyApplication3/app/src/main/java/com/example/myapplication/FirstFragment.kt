package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FirstFragmentBinding
import com.example.myapplication.others.PaymentInformation
import com.example.myapplication.others.TransactionType

class FirstFragment : Fragment() {
    private var _binding: FirstFragmentBinding? = null
    private val binding get() = _binding!!
    private var selectedOption: TransactionType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FirstFragmentBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            checkValidation()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun checkValidation() {
        val userName = binding.username.text.toString().trim()
        val amount = binding.amount.text.toString().trim()
        val payInfo = binding.etPaymentInfo.text.toString().trim()

        if (userName.isEmpty() || amount.isEmpty()) {
            Toast.makeText(activity, "Username and Amount cannot be empty", Toast.LENGTH_SHORT)
                .show()
            return
        } else if (payInfo.isEmpty()) {
            Toast.makeText(activity, "Please enter Payment data", Toast.LENGTH_SHORT).show()
            return
        }
        selectedOption = when {
            binding.radiobtnCash.isChecked -> TransactionType.CASH
            binding.radiobtnCard.isChecked -> TransactionType.CARD
            binding.radiobtnNetbanking.isChecked -> TransactionType.NETBANKING
            binding.radiobtnUpi.isChecked -> TransactionType.UPI

            else -> {
                Toast.makeText(activity, "Please select Transaction Type", Toast.LENGTH_SHORT)
                    .show()
                return
            }
        }

        val paymentInformation = PaymentInformation(
            username = userName,
            amount = amount.toDouble(),
            transactionType = selectedOption!!,
            paymentDetails = payInfo
        )
        Log.d("pay", paymentInformation.toString())

        if (paymentInformation != null) {
            navigateToSecondFragment(paymentInformation)
        }
    }

    private fun navigateToSecondFragment(paymentInformation: PaymentInformation) {
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(paymentInformation)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}