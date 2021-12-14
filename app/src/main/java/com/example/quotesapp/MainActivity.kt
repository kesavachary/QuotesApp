package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    //creating mainViewModel variable for inherit the MainViewModel properties and access it.
    lateinit var mainViewModel: MainViewModel
    //get the reference of the quoteText
    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
   //get the reference of the quoteAuthor
    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialize mainViewModel to ViewModelProvider
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        //call the current index quote using getQuote() and setQuote() is used for binding the text,author
        setQuote(mainViewModel.getQuote())

    }
    fun setQuote(quote:Quote){
        //call the quote text based on index and assign text
        quoteText.text = quote.text
        //call the quote author based on index and assign text
        quoteAuthor.text = quote.author
    }
    fun onPrevious(view: View) {
        //call the previous quote using previousQuote() and display on the UI
        setQuote(mainViewModel.previousQuote())
    }
    fun onNext(view: View) {
        //call the previous quote using nextQuote() and display on the UI
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        //For all types of sharing,Create an intent and set its action to Intent.ACTION_SEND.
        val intent = Intent(Intent.ACTION_SEND)
        //We need mentioned setType so it as text/plain
        intent.setType("text/plain")
        //Intent.EXTRA_TEXT is used to send text data that is particular quote we get from getQuote()
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        //start the activity that is operation ACTION_SEND
        startActivity(intent)
    }
}