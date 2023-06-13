package com.example.locationalert.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.locationalert.R

class SecondBoard: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_boarding, container, false)
    }
    override fun onStart() {
        super.onStart();
        val button = view?.findViewById<Button>(R.id.onBoard_next)

        button?.setOnClickListener {

            findNavController().navigate(R.id.action_SecondBoard)
        }

    }
}