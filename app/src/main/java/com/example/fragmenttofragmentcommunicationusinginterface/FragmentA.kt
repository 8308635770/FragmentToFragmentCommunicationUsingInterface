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

class FragmentA: Fragment() {

     var fragAlistener: FragAlistener?= null

    lateinit var  button_ok : Button
    lateinit var  editText: EditText

    public interface FragAlistener{
        fun onInputASent(input :String);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundel=arguments
        val welcomeMEssage= bundel?.getString("attach message")
        Toast.makeText(context,welcomeMEssage,Toast.LENGTH_SHORT).show()


         button_ok=view.findViewById<Button>(R.id.button_ok)
         editText=view.findViewById<EditText>(R.id.edit_text)

        button_ok.setOnClickListener(View.OnClickListener {
            val input = editText.text.toString()
            fragAlistener?.onInputASent(input)
        })
    }

    fun updateEditText(inputText:String){
        editText.setText(inputText)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       if (context is FragAlistener){
           fragAlistener=context
       }else{
           throw RuntimeException(context.toString()+"Must be implement FragmentA listener")
       }
    }

    override fun onDetach() {
        super.onDetach()
        fragAlistener=null
    }


}