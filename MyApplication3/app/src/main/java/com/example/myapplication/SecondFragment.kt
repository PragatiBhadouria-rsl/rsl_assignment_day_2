package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.SecondFragmentBinding
import com.example.myapplication.others.PaymentInformation
import com.example.myapplication.others.TransactionStatus

class SecondFragment : Fragment() {

    private var _binding : SecondFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SecondFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = SecondFragmentArgs.fromBundle(requireArguments())
        val paymentInformation = args.paymentInformation

        displayInfo(paymentInformation)
    }

    private fun displayInfo(paymentInformation: PaymentInformation){
        binding.tvUsername.text = "Name: ${paymentInformation.username}"
        binding.tvAmount.text = "Amount: ${paymentInformation.amount}"
        binding.tvTransactionType.text = "Transaction Type: ${paymentInformation.transactionType}"
        binding.tvPayDetail.text = "Payment Details: ${paymentInformation.paymentDetails}"

        val totalAmountWithTax = paymentInformation.amount * 0.08  + paymentInformation.amount// 8% tax
        binding.tvAmountWithTax.text = "Total Amount with Tax: $totalAmountWithTax"
    }

    override fun onResume() {
        super.onResume()
        binding.cancelButton.setOnClickListener{
            findNavController().popBackStack(R.id.firstFragment, false)
        }

        binding.confirmButton.setOnClickListener{
            val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                TransactionStatus.Completed)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



