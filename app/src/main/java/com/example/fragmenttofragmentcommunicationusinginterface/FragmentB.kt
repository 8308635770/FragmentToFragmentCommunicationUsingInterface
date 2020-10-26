package com.example.fragmenttofragmentcommunicationusinginterface

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.RuntimeException

class FragmentB : Fragment() {

     var fragBlistener: FragBlistener?=null


    lateinit var button_ok: Button
    lateinit var editText: EditText

    public interface FragBlistener {
        fun onInputBSent(input: String);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundel=arguments
        val welcomeMEssage= bundel?.getString("attach message")
        Toast.makeText(context,welcomeMEssage, Toast.LENGTH_SHORT).show()

        button_ok = view.findViewById<Button>(R.id.button_ok)
        editText = view.findViewById<EditText>(R.id.edit_text)

        button_ok.setOnClickListener(View.OnClickListener {
            val input = editText.text.toString()
            fragBlistener?.onInputBSent(input)
        })
    }

    fun updateEditText(inputText: String) {
        editText.setText(inputText)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragBlistener){
            fragBlistener=context
        }else{
            throw RuntimeException(context.toString()+"fragB listener must be implemented")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragBlistener=null
    }
}