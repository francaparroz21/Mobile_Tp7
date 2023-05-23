package com.example.mobile_tp6.presentation.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mobile_tp6.presentation.activity.MainActivity
import com.example.mobile_tp6.databinding.FragmentDialogBinding

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private var title_dialog: String? = null
    private var description_dialog: String? = null

    companion object {
        private const val TITLE = "Title"
        private const val DESCRIPTION = "Description"

        fun newInstance(
            title: String? = null,
            description: String? = null
        ): ErrorDialogFragment {
            val fragment = ErrorDialogFragment()
            val arguments = Bundle()
            arguments.putString(TITLE, title)
            arguments.putString(DESCRIPTION, description)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDialogBinding.inflate(layoutInflater)
        showsDialog = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title_dialog = arguments?.getString(TITLE)
        description_dialog = arguments?.getString(DESCRIPTION)
        binding.buttonDialog.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}